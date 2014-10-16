import java.util.ArrayList;

public class SelectionTournamentIsland implements ISelection {

	private final int K; // tournament size
	private final int MU; // number of individuals to choose (how many tournaments)

	public SelectionTournamentIsland(int tournamentSize, int numIndividualToSelect) {
		this.K = tournamentSize;
		this.MU = numIndividualToSelect;
	}

	// TODO: Use this version for function 1 (and 2?)
	@Override
	public ArrayList<Individual> select(ArrayList<Individual> from) {
		ArrayList<Individual> selected = new ArrayList<Individual>();
		for (int j = 0; j < MU; j++) {
			int[] ar = player11.shuffleArray(from.size());
			// select the best of the first K
			int bestIndex = ar[0];
			for (int i = 1; i < K; i++) {
				if (from.get(ar[i]).getFitness() > from.get(bestIndex).getFitness()) {
					bestIndex = ar[i];
				}
			}
			selected.add(from.get(bestIndex));
			from.remove(bestIndex); //TODO: remove
		}
		return selected;
	}

}
