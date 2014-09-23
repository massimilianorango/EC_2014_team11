public class UncorrelatedMutation implements IMutation {

    private static double TAU_1 = 1 / Math.sqrt(2*10);
    private static double TAU = 1 / Math.sqrt(2 * Math.sqrt(10));
    private static double EPSILON = 0.000001; // Which value? 
    private IRecombination recombination;

    
    public void intializeMutationParameters(Individual individual){
        
        double[] sigmas = new double[10];
        for(int i = 0; i < 10; i++) {
            sigmas[i] = 0.8; //Here? Which value?
        }
        
        individual.setSigma_mutation_step_sizes(sigmas);
    }
    
    @Override
    public void mutate(Individual individual) {
        
        double[] dna = individual.getDna();
        double[] sigmas = individual.getSigma_mutation_step_sizes(); //Where to initialize? Which values?
        
        //System.out.println("Before mutation: ");
        //System.out.println(individual.toString());

        for(int i = 0; i < dna.length; i++) {
            double gaussian_1 = player11.rnd.nextGaussian();
            
            sigmas[i] = sigmas[i] * Math.exp(TAU_1 * player11.rnd.nextGaussian() + TAU * gaussian_1);
            //Set sigma[i] if too small
            if(sigmas[i] < EPSILON) {
                sigmas[i] = EPSILON;
            }
            
            dna[i] = dna[i] + sigmas[i] * gaussian_1;
            //Curtailing values to [-5, 5[ if out of interval
            if(dna[i] >= 5.0) {
                dna[i] = 4.999999999;
            } else if(dna[i] < -5.0) {
                dna[i] = -5.0;
            }
        }
        
        individual.setDna(dna);
        individual.setSigma_mutation_step_sizes(sigmas);
        
        //System.out.println("After mutation: ");
        //System.out.println(individual.toString());
        //System.out.println("_________________________________________________");
    }
    
    @Override
    public void crossoverMutationValues(Individual crossedIndividual, Individual parentA,
            Individual parentB) {
        
        double[] child_sigma = new double[10];
        for(int j = 0; j<10; j++){
            double di_delta = Math.abs(parentA.getSigma_mutation_step_sizes()[j]-parentB.getSigma_mutation_step_sizes()[j]);
            child_sigma[j] = parentA.getSigma_mutation_step_sizes()[j]+(player11.rnd.nextDouble()*(2.0*di_delta)-(di_delta));
        }
        
        crossedIndividual.setSigma_mutation_step_sizes(child_sigma);
    }

    @Override
    public void setRecombination(IRecombination recombination) {
        this.recombination = recombination;        
    }



}


