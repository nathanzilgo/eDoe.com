package ferramentas;

import java.io.Serializable;

public class Validar implements Serializable {

	private static final long serialVersionUID = 1L;

	private static boolean isNull(String valor) {
		return valor == null;
	}

	private static boolean isNull(Integer valor) {
		return valor == null;
	}

	private static boolean isEmpty(String valor) {
		return valor.trim().equals("");
	}

	private static boolean isEmpty(Integer valor) {
		return Integer.toString(valor).trim().equals("");
	}

	public static void adicionaUsuario(String id, String nome, String email, String celular, String classe) {

		validaNome(nome);
		validaId(id);
		if (isNull(email) || isEmpty(email))
			throw new IllegalArgumentException("Entrada invalida: email nao pode ser vazio ou nulo.");
		if (isNull(celular) || isEmpty(celular))
			throw new IllegalArgumentException("Entrada invalida: celular nao pode ser vazio ou nulo.");
		if (isNull(classe) || isEmpty(classe))
			throw new IllegalArgumentException("Entrada invalida: classe nao pode ser vazia ou nula.");
	}

	public static void validaId(String id) {
		if (isNull(id) || isEmpty(id))
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
	}

	public static void validaNome(String nome) {
		if (isNull(nome) || isEmpty(nome))
			throw new IllegalArgumentException("Entrada invalida: nome nao pode ser vazio ou nulo.");
	}

	public static void validaReceptor(String documento) {
		validaId(documento);
	}

	public static Boolean checaArgumento(String campo) {
		if (!(isNull(campo) || isEmpty(campo))) {
			return true;
		}
		return false;
	}

	public static void validaDescritor(String descritor) {
		if (isNull(descritor) || isEmpty(descritor)) {
			throw new IllegalArgumentException("Entrada invalida: descricao nao pode ser vazia ou nula.");
		}
	}

	/**
	 * Valida entradas para adicao de item.
	 * 
	 * @param id
	 * @param descricao
	 * @param quantidade
	 * @param tags
	 * @throws Exception
	 */
	public static void validaAdicionaItem(String id, String descricao, int quantidade, String tags) {
		validaId(id);
		validaDescritor(descricao);
		if (quantidade <= 0) {
			throw new IllegalArgumentException("Entrada invalida: quantidade deve ser maior que zero.");
		}
	}

	/**
	 * Retira espacos inuteis da entrada.
	 * 
	 * @param entrada
	 */
	public static String retiraEspacos(String entrada) {
		return entrada.trim();
	}

	public static void validaExibeItem(int idItem, String idDoador) {
		validaId(idDoador);
		if (isNull(idItem) || isEmpty(idItem))
			throw new IllegalArgumentException();

	}

	public static void validaItem(int idItem, String idUsuario) {
		validaItem(idItem);
		validaId(idUsuario);
	}

	public static void validaItem(int id) {
		if (id < 0) throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
	}

	public static void validaPesquisa(String entrada) {
		if (isNull(entrada) || isEmpty(entrada)) {
			throw new IllegalArgumentException("Entrada invalida: texto da pesquisa nao pode ser vazio ou nulo.");
		}
	}

	public static void validaRealizaDoacao(int idItemNec, int idItemDoado, String data) {
		validaItem(idItemNec);
		validaItem(idItemDoado);
		validaData(data);
	}

	public static void validaData(String data) {
		if (isNull(data) || isEmpty(data))
			throw new IllegalArgumentException("Entrada invalida: data nao pode ser vazia ou nula.");
	}
}
