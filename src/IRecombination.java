import java.util.ArrayList;


public interface IRecombination {

    public ArrayList<double[]> crossover(Individual[] parents, int number_of_childs);
}
