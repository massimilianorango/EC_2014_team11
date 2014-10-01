import java.util.Arrays;
import java.util.Date;

import org.vu.contest.ContestEvaluation;

import test.AckleyEvaluation;
import test.FletcherEvaluation;
import test.LangermanEvaluation;
import test.SphereEvaluation;
import test.TuningEvaluation;


public class ParameterTuning {
    
    public static double[] best_results = null;

    public static double runTuningEvaluation(ContestEvaluation localContestEvaluation, AbstractEA algorithm, int seed){
        algorithm.setEvaluation(localContestEvaluation, Integer.parseInt(localContestEvaluation.getProperties().getProperty("Evaluations")));
        
        player11 player = new player11();
        player.setSeed(seed);
        player.setTuningAlgorithm(algorithm);

        Date localDate = new Date();
        long l3 = localDate.getTime();
        
        player.run();
       
        localDate = new Date();
        long l4 = localDate.getTime() - l3;

        System.out.println("Result: "+localContestEvaluation.getFinalResult());
        System.out.println("Runtime: " + l4 + "ms");
        
        return localContestEvaluation.getFinalResult();
    }
    
    public static void main(String[] args) {
        int NUMBER_OF_EVALS = 10000;
        int NUMBER_OF_RUNS = 10;

        /*double[][][] best_results = new double[4][][];
        
        best_results[0] = tuneESParameters(new SphereEvaluation(NUMBER_OF_EVALS), NUMBER_OF_RUNS);
        best_results[1] = tuneESParameters(new AckleyEvaluation(NUMBER_OF_EVALS), NUMBER_OF_RUNS);      
        best_results[2] = tuneESParameters(new LangermanEvaluation(NUMBER_OF_EVALS), NUMBER_OF_RUNS);      
        best_results[3] = tuneESParameters(new FletcherEvaluation(NUMBER_OF_EVALS), NUMBER_OF_RUNS);
        
        System.out.println("Sphere: ");
        printResults(best_results[0]);
        System.out.println("Ackley: ");
        printResults(best_results[1]);
        System.out.println("Langerman: ");
        printResults(best_results[2]);
        System.out.println("Fletcher: ");
        printResults(best_results[3]);*/
        
        double average_result = runEvaluation(new AckleyEvaluation(NUMBER_OF_EVALS), new AlgorithmES(7,22,1,0.000001), NUMBER_OF_RUNS);
        System.out.println(average_result);
    }
    
    public static void printResults(double[][] best_resluts_eval){
        System.out.println("MU&LAMBDA: "+Arrays.toString(best_resluts_eval[0]));
        System.out.println("ALPHA: "+Arrays.toString(best_resluts_eval[1]));
        System.out.println("EPSILON: "+Arrays.toString(best_resluts_eval[2]));
    }
    
    public static double[][] tuneESParameters(TuningEvaluation eval, int NUMBER_OF_RUNS){
        resetBestResults();
        
        for(int MU = 2; MU<10; MU++){
            for(int LAMBDA = 10; LAMBDA<50; LAMBDA+=2){
                AbstractEA algo = new AlgorithmES(MU, LAMBDA);
                double average_result = runEvaluation(eval, algo, NUMBER_OF_RUNS);
                saveResult(average_result,MU,LAMBDA);
            }
        }
        
        double[] best_result_mu_lambda = resetBestResults();
        
        for(double ALPHA = 0.1; ALPHA<2; ALPHA+=0.1){
            AbstractEA algo = new AlgorithmES((int)best_result_mu_lambda[1],(int)best_result_mu_lambda[2],ALPHA);
            double average_result = runEvaluation(eval, algo, NUMBER_OF_RUNS);
            saveResult(average_result,ALPHA);
        }
        double[] best_result_alpha = resetBestResults();
        
        for(double EPSILON = 0.000001; EPSILON<=0.1; EPSILON*=10){
            AbstractEA algo = new AlgorithmES((int)best_result_mu_lambda[1],(int)best_result_mu_lambda[2],best_result_alpha[1],EPSILON);
            double average_result = runEvaluation(eval, algo, NUMBER_OF_RUNS);
            saveResult(average_result,EPSILON);
        }
        double[] best_result_epsilon = resetBestResults();

        
        return new double[][]{best_result_mu_lambda,best_result_alpha,best_result_epsilon};
    }
    
    public static double runEvaluation(TuningEvaluation eval, AbstractEA algo, int NUMBER_OF_RUNS){
        double result_sum = 0.0;
        for(int i = 1; i<=NUMBER_OF_RUNS; i++){
            result_sum += runTuningEvaluation(eval,algo,i);
            algo.resetEvals();
            eval.resetEvaluations();
        }
        
        return result_sum/NUMBER_OF_RUNS;
    }
    
    public static void saveResult(double average_result, double value){
        if( best_results[0]  < average_result ){
            best_results[0] = average_result;
            best_results[1] = value;
        }
    }
    
    public static void saveResult(double average_result, double value, double value2){
        if( best_results[0]  < average_result ){
            best_results[0] = average_result;
            best_results[1] = value;
            best_results[2] = value2;
        }
    }
    
    public static double[] resetBestResults(){
        double[] old_best_results = best_results;
        best_results = new double[3];
        best_results[0] = Integer.MIN_VALUE;
        
        return old_best_results;
    }
    
    /*   public static void testSimple(){
        double[][] overall_results = new double[4][2];
        for( int i = 0; i<4; i++){
            overall_results[i][0] = Integer.MIN_VALUE;
        }
        
        //check different parameter configurations for Population size (form 5 to 50)
        for(int i = 0; i<1; i++){
            double[] results = evaluate_all();
            for(int j = 0; j< 4; j++){
                if(overall_results[j][0] < results[j] ){
                    overall_results[j][0] = results[j];
                    overall_results[j][1] = i;
                }
            }
        }
        
        for(int i = 0; i< 4; i++){
            System.out.println(Arrays.toString(overall_results[i]));
        }
    }*/
    

}
