import java.util.ArrayList;
import java.util.Collections;

public class Population {

	private ArrayList<Individual> individuals;
	private int generation;

	public Population() {
		individuals = new ArrayList<Individual>();
		generation = 1;
	}

	public ArrayList<Individual> getIndividuals() {
		return individuals;
	}

	public void setIndividuals(ArrayList<Individual> individuals) {
		this.individuals = individuals;
	}

	public void addIndividual(Individual individual) {
		individuals.add(individual);
	}

	public void addIndividuals(ArrayList<Individual> individuals) {
		this.individuals.addAll(individuals);
	}

	public void removeIndividual(Individual individual) {
		individuals.remove(individual);
	}

	public void removeAllIndividuals() {
		individuals.clear();
	}

	public int getGeneration() {
		return generation;
	}

	public int increaseGeneration() {
		return ++generation;
	}

	public int getPopulationSize() {
		return individuals.size();
	}

	public Individual getBestIndividual() {
		ArrayList<Individual> individuals = new ArrayList<Individual>(this.individuals);
		Collections.sort(individuals);
		if (individuals.size() > 0) {
			return individuals.get(individuals.size() - 1);
		} else {
			return null;
		}
	}

}
