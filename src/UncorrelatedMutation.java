import java.util.Random;


public class UncorrelatedMutation implements IMutation {

    private Individual individual;

    @Override
    public Individual mutate(double[] dna) {
        this.individual = individual;
        // TODO Auto-generated method stub
        return player11.createIndividual(dna);
    }

}
