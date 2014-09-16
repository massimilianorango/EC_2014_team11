import java.util.ArrayList;


public class Population {

    private ArrayList<Individual> individuals = null;
    private int generation;
    
    public Population(){
        individuals = new ArrayList<Individual>();
        generation = 1;
    }
    
    public ArrayList<Individual> getIndividuals(){
        return individuals;
    }
    
    public void setIndividuals(ArrayList<Individual> individuals){
        this.individuals = individuals;
    }
    
    public void addIndividual(Individual individual){
        individuals.add(individual);
    }
    
    public void addIndividuals(ArrayList<Individual> individuals){
        individuals.addAll(individuals);
    }
    
    public void removeIndividual(Individual individual){
        individuals.remove(individual);
    }
    
    public int getGeneration(){
        return generation;
    }
    
    public int increaseGeneration(){
        return ++generation;
    }
    
}
