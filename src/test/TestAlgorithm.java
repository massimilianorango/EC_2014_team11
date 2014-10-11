package test;

import java.util.Date;

import org.junit.Test;
import org.vu.contest.ContestEvaluation;
import org.vu.contest.ContestSubmission;

public class TestAlgorithm {
	
	public final static long SEED = 2;
	
	@Test
	public void runEvaluation() {
//		ContestEvaluation localContestEvaluation = new SphereEvaluation();
//		ContestEvaluation localContestEvaluation = new AckleyEvaluation();
//		ContestEvaluation localContestEvaluation = new RastriginEvaluation();
		ContestEvaluation localContestEvaluation = new SchwefelEvaluation();
//		ContestEvaluation localContestEvaluation = new DeJong2Evaluation();
//		ContestEvaluation localContestEvaluation = new SumDifferentPowerFunctionsEvaluation();

		ContestSubmission localContestSubmission = null;
		try {
			localContestSubmission = (ContestSubmission) Class.forName("player11").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		localContestSubmission.setSeed(SEED);
		localContestSubmission.setEvaluation(localContestEvaluation);

		Date localDate = new Date();
		long l3 = localDate.getTime();

		localContestSubmission.run();

		localDate = new Date();
		long l4 = localDate.getTime() - l3;

		System.out.println("Score: " + Double.toString(localContestEvaluation.getFinalResult()));
		System.out.println("Runtime: " + l4 + "ms");
	}
}
