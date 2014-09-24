


public interface IFitnessCalculation {

    public void prepareFitnessCalculation(Population population);
    
    public double recalculateFitness(double value);
}
