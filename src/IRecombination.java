import java.util.ArrayList;


public interface IRecombination {

    public ArrayList<Individual> crossover(Individual[] parents, int number_of_childs);
    
    public double[] crossOperator(double[] a, double[] b);
}
