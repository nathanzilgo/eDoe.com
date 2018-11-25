package internas;

<<<<<<< HEAD
import ferramentas.Validar;

public class Usuario {
=======
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Usuario {
	
>>>>>>> us2_nathan
	private String id;
	private String nome;
	private String email;
	private String celular;
	private String classe;
<<<<<<< HEAD

	public Usuario(String id, String nome, String email, String celular, String classe) {
=======
	/**
	 * Estrutura que armazena os itens dos usuários doadores
	 */
	protected Map<Integer, Item> itens;
	
	public Usuario(String id, String nome, String email, String telefone, String classe) {
>>>>>>> us2_nathan
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.celular = celular;
		this.classe = classe;
		
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
		this.celular = telefone;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String getNome() {
		return this.nome;
	}
	
	public Map<Integer, Item> getItens() {
		return itens;
	}

	public void setItens(Map<Integer, Item> itens) {
		this.itens = itens;
	}
	public Item getItem(int id) {
		return this.getItens().get(id);
	}

<<<<<<< HEAD
	public void atualizaUsuario(String nome, String email, String celular) {
		if (Validar.checaArgumento(nome)) {
			this.nome = nome;
		} if (Validar.checaArgumento(email)) {
			this.email = email;
		} if (Validar.checaArgumento(celular)) {
			this.celular = celular;
		}

	}
	
	@Override
	public String toString() {
		return this.nome + "/" + getId() + ", " + this.email + ", " + this.celular + ", status: ";
	}
=======
	/**
	 * Implementada em Doador.java
	 * @param item
	 * @return Integer
	 */
	public abstract int adicionaItem(Item item);
	
	public abstract boolean existeItem(int idItem);
>>>>>>> us2_nathan
	
}


