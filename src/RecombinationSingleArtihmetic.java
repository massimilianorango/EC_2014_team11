import java.util.ArrayList;


public class RecombinationSingleArtihmetic implements IRecombination{
    
    public static double ALLELS = 1;
    public static double ALPHA = 0.5;

    @Override
    public ArrayList<Individual> crossover(Individual[] parents, int number_of_childs) {
        ArrayList<Individual> individuals = new ArrayList<Individual>();
        
        while(individuals.size() < number_of_childs){
            individuals.add(new Individual(crossOperator(parents[0].getDna(),parents[1].getDna())));
            individuals.add(new Individual(crossOperator(parents[1].getDna(),parents[0].getDna())));
        }
        
        return individuals;
    }

    public double[] crossOperator(double[] a, double[] b) {
        double[] child_dna = new double[10];
        ArrayList<Integer> chosen = new ArrayList<Integer>();
        while(chosen.size()<ALLELS && chosen.size() < 10){
            int rand = 0;
            do{
                rand = player11.getRnd().nextInt(10);
            }while(chosen.contains(rand));
            chosen.add(rand);
        }
        
        for(int i=0; i<10; i++){
            child_dna[i] = a[i];
            if( chosen.contains(i) ){
               child_dna[i] = (ALPHA*a[i])+((1-ALPHA)*+b[i]);
            }
        }
        
        return child_dna;
    }


}
