import java.util.ArrayList;


public class RecombinationAverage implements IRecombination{

    @Override
    public ArrayList<Individual> crossover(Individual[] parents, int number_of_childs) {
        ArrayList<Individual> individuals = new ArrayList<Individual>();
        
        while(individuals.size() < number_of_childs){
            individuals.add(new Individual(crossOperator(parents[0].getDna(),parents[1].getDna())));
        }
        
        return individuals;
    }

    @Override
    public double[] crossOperator(double[] a, double[] b) {
        double[] child_dna = new double[10];
        for(int i=0; i<10; i++){
            child_dna[i] = a[i]+(b[i]-a[i])*player11.rnd.nextDouble();
        }
        
        return child_dna;
    }

    @Override
    public void setMutation(IMutation mutation) {
        // TODO Auto-generated method stub
        
    }

}
