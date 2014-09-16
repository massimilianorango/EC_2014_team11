import java.util.Random;


public class PrototypeMutation implements IMutation {

    @Override
    public Individual mutate(double[] dna) {
        dna[3] = dna[3]+player11.rnd.nextDouble();
        
        // TODO Auto-generated method stub
        return player11.createIndividual(dna);
    }

}
