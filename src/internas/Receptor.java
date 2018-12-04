package internas;

public class Receptor extends Usuario {

	public Receptor(String id, String nome, String email, String celular, String classe) {
		super(id, nome, email, celular, classe);
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
	
	public void match(int idItem) {
		
	}
}
