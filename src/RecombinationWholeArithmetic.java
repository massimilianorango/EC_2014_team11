import java.util.ArrayList;


public class RecombinationWholeArithmetic extends IRecombination {

    private double ALPHA;
    
    public RecombinationWholeArithmetic(){
        this(0.5);
    }
    
    public RecombinationWholeArithmetic(double ALPHA) {
       this.ALPHA = ALPHA;
    }
    
    @Override
    public ArrayList<Individual> crossover(Individual[] parents, int number_of_children) {
        ArrayList<Individual> childs = new ArrayList<Individual>();
        while(childs.size() < number_of_children){
            double[] dna_child_a = new double[10];
            for(int i = 0; i< 10; i++){
                dna_child_a[i] = parents[0].getDna()[i]*ALPHA+(1-ALPHA)*parents[1].getDna()[i];
            }
            
            double[] sigma_dna = sigmaCrossover(parents);
            Individual ind_a = new Individual(dna_child_a);
            ind_a.setSigma_mutation_step_sizes(sigma_dna);
            childs.add(ind_a);
        }
        
        
        return childs;
    }

}
