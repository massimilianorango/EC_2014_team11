


public interface IParentSelection {

    public Individual[] selectParents(Population population);
    
    /**
     * e.g. to initialize fitness-based scales
     * 
     * @param population
     */
    public void prepareSelection(Population population);
    
    public void setFitnessCalculation(IFitnessCalculation fitness);
}
