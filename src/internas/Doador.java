package internas;

public class Doador extends Usuario {
	
	public Doador(String id, String nome, String email, String celular, String classe) {
		super(id, nome, email, celular, classe);
	}
	
	@Override
	public String toString() {
		return super.toString() + "doador";
	}
}
