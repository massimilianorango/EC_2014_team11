package test;

import org.vu.contest.ContestEvaluation;

public abstract class TuningEvaluation implements ContestEvaluation {
    // Evaluations used so far
    protected int evaluations_;
    // Best fitness so far
    protected double best_;

    public void resetEvaluations(){
        best_=Integer.MIN_VALUE;
        evaluations_ = 0;
    }
}
