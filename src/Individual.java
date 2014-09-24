import java.util.Arrays;

public class Individual implements Comparable<Individual>{

    private double[] dna = null;
    private double[] sigma_mutation_step_sizes = null;
    private double[] alpha_correlation_parameters = null;
    private Double fitness = null;
    private int generation = 0;
    
    public Individual(double[] dna) {
        super();
        this.dna = dna;
        sigma_mutation_step_sizes = new double[dna.length];
        alpha_correlation_parameters = new double[(dna.length*(dna.length-1))/2];
    }
    
    @Override
    public String toString() {
        return "Individual [dna=" + Arrays.toString(dna) + ", fitness=" + fitness + "]";
    }
    public double[] getDna() {
        return dna;
    }
    public void setDna(double[] dna) {
        this.dna = dna;
    }
    public Double getFitness() {
        return fitness;
    }
    public void setFitness(Double fitness) {
        this.fitness = fitness;
    }
    
    public int getGeneration() {
        return generation;
    }
    public void setGeneration(int generation) {
        this.generation = generation;
    }
    
    public double[] getSigma_mutation_step_sizes() {
        return sigma_mutation_step_sizes;
    }
    
    public void setSigma_mutation_step_sizes(double[] sigma_mutation_step_sizes) {
        this.sigma_mutation_step_sizes = sigma_mutation_step_sizes;
    }
    
    public double[] getAlpha_correlation_parameters() {
        return alpha_correlation_parameters;
    }
    public void setAlpha_correlation_parameters(double[] alpha_correlation_parameters) {
        this.alpha_correlation_parameters = alpha_correlation_parameters;
    }
    
    @Override
    public int compareTo(Individual o) {
        if( o instanceof Individual ){
            Individual comp = (Individual) o;
            return this.getFitness().compareTo(comp.fitness);
        }
        return 0;
    }
    
}