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
	public int adicionaItem(Item item) {
		return 0;
	}

	@Override
	public boolean existeItem(int idItem) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean existeItem(Item item) {
		// TODO Auto-generated method stub
		return false;
	}

}
