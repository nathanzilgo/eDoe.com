package ferramentas;

import java.util.Comparator;

import internas.Match;

public class MatchComparator implements Comparator<Match>{

	public int compare(Match o1, Match o2) {
		if(o1.getPontos() == o2.getPontos()) {
			return o1.getItemId() - o2.getItemId();
		}
		return o1.getPontos() - o2.getPontos();
	}
	
}
