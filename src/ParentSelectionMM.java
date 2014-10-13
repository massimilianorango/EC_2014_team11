import java.util.ArrayList;


public class ParentSelectionMM implements IParentSelection {
	
//	private final static int NEIGHBOURHOOD_SIZE = 6;

	@Override
	public Individual[] selectParents(Population population, int numberOfParents) {
		return fitnessProportionateSelection(population.getIndividuals(), numberOfParents);
	}

	private Individual[] fitnessProportionateSelection(ArrayList<Individual> currentPopulation, int numberOfParents) {
		Individual[] parents = new Individual[numberOfParents];
		double minFitness = currentPopulation.get(0).getFitness();
		for(int i = 1; i < currentPopulation.size(); i++) {
			double currentFitness = currentPopulation.get(i).getFitness(); 
			if(currentFitness < minFitness) {
				minFitness = currentFitness;
			}
		}
		
		//minFitness is to normalize if there is at least a negative fitness value
		if(minFitness >= 0) { 
			minFitness = 0; 
		}
		
		double fitnessSum = 0;
		for(int i = 0; i < currentPopulation.size(); i++) {
			fitnessSum += currentPopulation.get(i).getFitness() + (-1 * minFitness);
		}
		
		double[] a = new double[currentPopulation.size()];
		double sum_prob = 0;
		for(int i = 0; i < a.length; i++) {
			sum_prob += (currentPopulation.get(i).getFitness() + (-1 * minFitness)) / fitnessSum;
			a[i] = sum_prob;
		}
		
		Individual prev = null;
		for(int current = 0; current < numberOfParents; ) {
			double r = player11.getRnd().nextDouble();
			int i = 0;
			while(i < a.length && a[i] < r) {
				i++;
			}
			Individual chosenInd = currentPopulation.get(i); 
			if(chosenInd != prev) {
				prev = chosenInd; 
				parents[current] = chosenInd;
				current++;
			}
		} 
		
		return parents;
	}
	
	
//	@Override
//	public Individual[] selectParents(Population population, int numberOfParents) {
//		ArrayList<Individual> currentPopulation = population.getIndividuals();
//		Individual[] parents = new Individual[numberOfParents];
//		parents[0] = currentPopulation.get(player11.getRnd().nextInt(population.getPopulationSize()));
//		ArrayList<Individual> neighbours = neighbourhood(parents[0], currentPopulation);
//		parents[1] = rouletteWheel(neighbours);
//		return parents;
//	}
//
//	private Individual rouletteWheel(ArrayList<Individual> neighbours) {
//		double minFitness = neighbours.get(0).getFitness();
//		for(int i = 1; i < neighbours.size(); i++) {
//			double currentFitness = neighbours.get(i).getFitness(); 
//			if(currentFitness < minFitness) {
//				minFitness = currentFitness;
//			}
//		}
//		
//		//minFitness is to normalize if there is at least a negative fitness value
//		if(minFitness >= 0) { 
//			minFitness = 0; 
//		}
//		
//		double fitnessSum = 0;
//		for(int i = 0; i < neighbours.size(); i++) {
//			fitnessSum += neighbours.get(i).getFitness() + (-1 * minFitness);
//		}
//		
//		double[] a = new double[neighbours.size()];
//		double sum_prob = 0;
//		for(int i = 0; i < a.length; i++) {
//			sum_prob += (neighbours.get(i).getFitness() + (-1 * minFitness)) / fitnessSum;
//			a[i] = sum_prob;
//		}
//		
//		double r = player11.getRnd().nextDouble();
//		int i = 0;
//		while(i < a.length && a[i] < r) {
//			i++;
//		}
//		
//		return neighbours.get(i);
//	}
//
//	private ArrayList<Individual> neighbourhood(Individual individual, ArrayList<Individual> population) {
//		ArrayList<Individual> neighbours = new ArrayList<Individual>();
//		
//		//calculate all distances from individual
//		double[] distanceVector = new double[population.size()];
//		for(int i = 0; i < population.size(); i++) {
//			distanceVector[i] = euclidianDistance(individual.getDna(), population.get(i).getDna());
//		}
//		
//		//get neighbourhoodSize closest neighbours
//		for(int i = 0; i < NEIGHBOURHOOD_SIZE; i++) {
//			double min = Double.MAX_VALUE;
//			int indexMin = -1;
//			for(int j = 0; j < distanceVector.length; j++) {
//				if(distanceVector[j] != 0 && distanceVector[j] < min) {
//					min = distanceVector[j];
//					indexMin = j;
//				}
//			}
//			neighbours.add(population.get(indexMin));
//			distanceVector[indexMin] = Double.MAX_VALUE; //selection with replacement
//		}
//		
//		return neighbours;
//	}
//
//	private double euclidianDistance(double[] x_a, double[] x_b) {
//		double sum = 0.0;
//		for(int i = 0; i < x_a.length; i++) {
//			sum += Math.pow(x_a[i] - x_b[i], 2);
//		}
//		return Math.sqrt(sum);
//	}

}
