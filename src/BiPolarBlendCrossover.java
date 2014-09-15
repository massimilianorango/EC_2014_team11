import java.util.ArrayList;
import java.util.Random;

import org.vu.contest.ContestEvaluation;


public class BiPolarBlendCrossover implements IRecombination {

    public static double APLPHA_CROSS_RATE = 0.5;
    
    private Random rnd;
    private player11 contestReference;
    
    public BiPolarBlendCrossover(Random rnd, player11 contestReference){
        this.rnd = rnd;
        this.contestReference = contestReference;
    }
    
    @Override
    public ArrayList<double[]> crossover(Individual[] parent, int number_of_childs) {
        if(parent.length != 2){
            throw new RuntimeException("BiPolarBlendCrossover expected 2 input parents but received: "+parent.length);
        }

        Individual parentA = parent[0];
        Individual parentB = parent[1];
        //see slides ch04.pdf, p.31 & http://books.google.nl/books?id=QcWdO7koNUQC&lpg=PA36&ots=D-iG4JtxI5&dq=blend%20crossover%20evolutionary%20computing&hl=de&pg=PA36#v=onepage&q&f=false
        ArrayList<double[]> child_dnas = new ArrayList<double[]>();
        
        if(number_of_childs % 2 != 0){
            throw new RuntimeException("Number of childs has to be even. But was: "+number_of_childs);
        }
        
        for( int i = 0; i<number_of_childs; i=i+2){
            child_dnas.add(blendCross(parentA,parentB));
            child_dnas.add(blendCross(parentB,parentA));
        }
        
        return child_dnas;
    }

    private double[] blendCross(Individual parentOne, Individual parentTwo){
        double[] child_dna = new double[10];
        for(int j = 0; j<10; j++){
            double di_delta = parentOne.getDna()[j]-parentTwo.getDna()[j];
            if(di_delta > 0){
                child_dna[j] = parentOne.getDna()[j]+(rnd.nextDouble()*(2.0*di_delta)-(di_delta));
            }else{
                child_dna[j] = parentTwo.getDna()[j];
            }
        }
        
        //create individual
        return child_dna;
        
    }
}
