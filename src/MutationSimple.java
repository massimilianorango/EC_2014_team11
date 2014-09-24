
public class MutationSimple implements IMutation {

    public static double MUTATION_PROBABILITY = 0.7;
    public static double MUTATION_RATE = 0.2;

    @Override
    public void mutate(Individual individual) {
        for(int j = 0; j<10; j++){
            //apply mutation to gene
            if( player11.rnd.nextDouble() < MUTATION_PROBABILITY ){
                //apply random mutation, the mutation rate defines how much the gene is allowed to change
                individual.getDna()[j] = individual.getDna()[j] + (player11.rnd.nextDouble()*2.0-1.0)*(MUTATION_RATE*individual.getDna()[j]);
                if( individual.getDna()[j] < -5 ){
                    individual.getDna()[j] = -5;
                }
                if( individual.getDna()[j] > 5){
                    individual.getDna()[j] = 5;
                }
            }
        }
    }

    @Override
    public void crossoverMutationValues(Individual crossedIndividual, Individual parentA, Individual parentB) {
        // TODO Auto-generated method stub

    }

    @Override
    public void intializeMutationParameters(Individual individual) {
        //nothing to do
    }

    @Override
    public void setRecombination(IRecombination recombination) {
        // TODO Auto-generated method stub

    }

}
