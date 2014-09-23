package test;

import java.util.Date;

import org.junit.Test;
import org.vu.contest.ContestEvaluation;
import org.vu.contest.ContestSubmission;

public class TestAlgorithm {    
    @Test
    public void runEvaluation(){
        ContestEvaluation localContestEvaluation = new SphereEvaluation();
        
        ContestSubmission localContestSubmission = null;
        try {
            localContestSubmission = (ContestSubmission) Class.forName("player11").newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
        localContestSubmission.setSeed(12);
        localContestSubmission.setEvaluation(new SphereEvaluation());

        Date localDate = new Date();
        long l3 = localDate.getTime();
        
        localContestSubmission.run();
       
        localDate = new Date();
        long l4 = localDate.getTime() - l3;

        System.out.println("Score: " + Double.toString(localContestEvaluation.getFinalResult()));
        System.out.println("Runtime: " + l4 + "ms");
     }
}
