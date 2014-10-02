import java.util.ArrayList;
import java.util.Collections;


public class ParentSelectionTournament implements IParentSelection {

    private int TOURNAMENT_SIZE;
    
    public ParentSelectionTournament(){
        this(4);
    }
    
    public ParentSelectionTournament(int TOURNAMENT_SIZE){
        this.TOURNAMENT_SIZE = TOURNAMENT_SIZE;
    }
    @Override
    public Individual[] selectParents(Population population, int numberOfParents) {
        Individual[] individuals = new Individual[numberOfParents];
        for(int i = 0; i< numberOfParents; i++){
            ArrayList<Individual> tournament = new ArrayList<Individual>();
            while(tournament.size() < TOURNAMENT_SIZE){
                Individual ind = population.getIndividuals().get(player11.getRnd().nextInt(population.getIndividuals().size()));
                if(!tournament.contains(ind)){
                    tournament.add(ind);
                }
            }
            
            Collections.sort(tournament);
            individuals[i] = tournament.get(tournament.size()-1);
        }
        
        return individuals;
    }

}
