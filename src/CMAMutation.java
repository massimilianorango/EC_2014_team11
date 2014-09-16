import java.util.Random;

/**
 * 
 * Implementation of Covariance Matrix Adaption Evolution Strategy
 * 
 * @author tdoehmen
 *
 */
public class CMAMutation implements IMutation {
    
    public static double SIGMA_LOWER_BOUNDARY = 0.0;

    private Individual individual = null;

    @Override
    /**
     * implement the CMA Algorithm
     */
    public Individual mutate(double[] dna) {
        this.individual = individual;
        // TODO Auto-generated method stub
        //see ch04.pdf, p. 25
        return player11.createIndividual(dna);
    }
    
    public double calculate_c_covarianceValue(int i_index, int j_index){
        //see ch04.pdf, p. 24
        if( i_index == j_index ){
            return individual.getSigma_mutation_step_sizes()[i_index];
        }else if( isCorrelated(i_index,j_index) ){
            return 0.5*(Math.pow(individual.getSigma_mutation_step_sizes()[i_index], 2)
                    -Math.pow(individual.getSigma_mutation_step_sizes()[j_index], 2))
                    *Math.tan(2*individual.getAlpha_correlation_parameters()[calculate_k_alphaIndex(i_index, j_index)]);
        }
        
        //return 0 if i!=j and if i and j are not correlated
        return 0;
    }
    
    public int calculate_k_alphaIndex(int i_index, int j_index){
        //TODO check if this is correct
        return (i_index*(j_index-1))/2;
    }
    
    private boolean isCorrelated(int i_index, int j_index){
        //TODO: choose correlation
        return false;
    }

    public double calculateSigmaMutationStepSize(double previous_sigma_mutation_step_size){
        //see ch04.pdf, p.22
        return previous_sigma_mutation_step_size*Math.exp(calculateTauApostropheOverallLearningRate()*player11.rnd.nextGaussian()+
                calculateTauCoordinateWiseLearningRate()*player11.rnd.nextGaussian());
    }

    public double calculateTauApostropheOverallLearningRate(){
        return 1/(Math.sqrt(individual.getGeneration()));
    }
    
    public double calculateTauCoordinateWiseLearningRate(){
        return 1/(Math.sqrt(2*Math.sqrt(individual.getGeneration())));
    }
    
    
}
