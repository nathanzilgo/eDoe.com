package internas;

import java.util.ArrayList;

public class Receptor extends Usuario {
	
	private ArrayList<Match> matches;
	
	public Receptor(String id, String nome, String email, String celular, String classe) {
		super(id, nome, email, celular, classe);
		this.matches = new ArrayList<>();
	}

	@Override
	public String toString() {
		return super.toString() + "receptor";
	}


	@Override
	public boolean existeItem(int idItem) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Incompleto
	 */
	@Override
	public void match(Item itemMatch, Item itemNec) {
		int pontos = 20;
		
		//for(int iter = 0; iter < itemMatch.listaTags().size(); iter++) {
			//if(itemMatch.listaTags())
		//}
		for(int iter = 0; iter < itemMatch.listaTags().size(); iter++) {
			if(itemMatch.listaTags().get(iter).toLowerCase().equals(itemNec.listaTags().get(iter).toLowerCase())) {
				pontos += 5;
			}
		}
		
	}
}
