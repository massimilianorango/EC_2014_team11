
public class FitnessShiftedValue implements IFitnessCalculation {

    private double minValue = 0;
    
    @Override
    public double recalculateFitness(double value) {
        if( minValue < 0){
            return value + (-1)*minValue;
        }else{
            return value;
        }
    }

    @Override
    public void prepareFitnessCalculation(Population population) {
        for(Individual individual : population.getIndividuals()){
            if(individual.getFitness() < minValue){
                minValue = individual.getFitness();
            }
        }
    }

}
