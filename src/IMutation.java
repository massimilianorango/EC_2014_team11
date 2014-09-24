


public interface IMutation {

    /**
     * mutate current individual
     * 
     * @return
     */
    public void mutate(Individual individual);
    
    public void crossoverMutationValues(Individual crossedIndividual, Individual parentA, Individual parentB);
    
    public void intializeMutationParameters(Individual individual);
    
    public void setRecombination(IRecombination recombination);
}
