import java.util.ArrayList;

public class RecombinationBlendCrossover extends IRecombination {

	public static double APLPHA_CROSS_RATE = 0.5;

	@Override
	public ArrayList<Individual> crossover(Individual[] parent, int number_of_childs) {
		if (parent.length != 2) {
			throw new RuntimeException("BiPolarBlendCrossover expected 2 input parents but received: " + parent.length);
		}

		Individual parentA = parent[0];
		Individual parentB = parent[1];
		// see slides ch04.pdf, p.31 &
		// http://books.google.nl/books?id=QcWdO7koNUQC&lpg=PA36&ots=D-iG4JtxI5&dq=blend%20crossover%20evolutionary%20computing&hl=de&pg=PA36#v=onepage&q&f=false
		ArrayList<Individual> child_dnas = new ArrayList<Individual>();

		if (number_of_childs % 2 != 0) {
			throw new RuntimeException("Number of childs has to be even. But was: " + number_of_childs);
		}

		for (int i = 0; i < number_of_childs; i = i + 2) {
			child_dnas.add(blendCross(parentA, parentB));
			child_dnas.add(blendCross(parentB, parentA));
		}

		return child_dnas;
	}

	private Individual blendCross(Individual parentOne, Individual parentTwo) {
		Individual child = new Individual(crossOperator(parentOne.getDna(), parentTwo.getDna()));
		child.setSigma_mutation_step_sizes(sigmaCrossover(parentOne, parentTwo));
		return child;
	}

	private double[] sigmaCrossover(Individual parentOne, Individual parentTwo) {
		double[] child_sigma = new double[player11.F_DIMENSION];
		for (int i = 0; i < child_sigma.length; i++) {
			child_sigma[i] = (parentOne.getSigma_mutation_step_sizes()[i] + parentTwo.getSigma_mutation_step_sizes()[i]) / 2;
		}
		return child_sigma;
	}

	public double[] crossOperator(double[] a, double[] b) {
		double[] child_dna = new double[10];
		for (int j = 0; j < 10; j++) {
			double di_delta = Math.abs(a[j] - b[j]);
			child_dna[j] = a[j] + (player11.getRnd().nextDouble() * (2.0 * di_delta) - (di_delta));
			if (child_dna[j] > 5) {
				child_dna[j] = 5;
			} else if (child_dna[j] < -5.0) {
				child_dna[j] = -5;
			}
		}

		return child_dna;
	}
}
