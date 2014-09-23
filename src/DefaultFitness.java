
public class DefaultFitness implements IFitness{

    @Override
    public double calculateFitness(double value){
        double fitness = 0.0;
        if( value < 0 ){
            fitness = 1/(-1*value);
        }
        else{
            fitness = value + 1;
        }
        return fitness;
    }

}
