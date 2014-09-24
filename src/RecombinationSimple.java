
import java.util.ArrayList;


public class RecombinationSimple implements IRecombination {

    @Override
    public ArrayList<Individual> crossover(Individual[] parents, int number_of_childs) {
        ArrayList<Individual> individuals = new ArrayList<Individual>();
        for(int i = 0; i<number_of_childs; i++){
            individuals.add(new Individual(crossOperator(parents[0].getDna(),parents[1].getDna())));
        }
        
        return individuals;
    }

    @Override
    public double[] crossOperator(double[] a, double[] b) {
        double[] dna = new double[10];
        for(int j = 0; j<10; j++){
            if( player11.rnd.nextInt(2) == 0 ){
                dna[j] = a[j];
            }else{
                dna[j] = b[j];
            }
        }
        return dna;
    }

    @Override
    public void setMutation(IMutation mutation) {
        // TODO Auto-generated method stub
        
    }

}
