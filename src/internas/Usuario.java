package internas;

public class Usuario {
	
	private String id;
	private String nome;
	private String email;
	private String telefone;
	private String classe;
	
	public Usuario(String id, String nome, String email, String telefone, String classe) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.classe = classe;
	}

	@Override
	public String toString() {
		return this.nome + "/" + getId() + ", " + this.email + ", " + this.telefone + ", status: ";
	}

	private String getId() {
		StringBuilder stringBuilder = new StringBuilder(this.id);
		if (this.id.length() == 11) {
			stringBuilder.insert(this.id.length() - 8, '.');
			stringBuilder.insert(this.id.length() - 4, '.');
			stringBuilder.insert(this.id.length(), '-');
			return stringBuilder.toString();
		}
		stringBuilder.insert(this.id.length() - 12, '.');
		stringBuilder.insert(this.id.length() - 8, '.');
		stringBuilder.insert(this.id.length() - 4, '/');
		stringBuilder.insert(this.id.length() + 1, '-');
		return stringBuilder.toString();
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String getNome() {
		return this.nome;
	}
	public void adicionaItem() {
		
	}

}
