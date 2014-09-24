


public class FitnessNonZeroLogarithmic implements IFitnessCalculation{

    @Override
    public double recalculateFitness(double value){
        double fitness = 0.0;
        if( value < 0 ){
            fitness = 1/(-1*value);
        }
        else{
            fitness = value + 1;
        }
        return fitness;
    }

    @Override
    public void prepareFitnessCalculation(Population population) {
        // TODO Auto-generated method stub
    }

}
