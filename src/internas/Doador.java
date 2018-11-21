package internas;

public class Doador implements Usuario {
	private String id;
	private String nome;
	private String email;
	private String celular;
	private String classe;

	public Doador(String id, String nome, String email, String celular, String classe) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.celular = celular;
		this.classe = classe;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

}
