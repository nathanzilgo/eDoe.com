package ferramentas;

public class Validar {

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

	public static void adicionaUsuario(String id, String nome, String email, String celular, String classe)
			throws Exception {

		if (isNull(nome) || isEmpty(nome))
			throw new IllegalArgumentException("Entrada invalida: nome nao pode ser vazio ou nulo.");
		if (isNull(email) || isEmpty(email))
			throw new IllegalArgumentException("Entrada invalida: email nao pode ser vazio ou nulo.");
		if (isNull(celular) || isEmpty(celular))
			throw new IllegalArgumentException("Entrada invalida: celular nao pode ser vazio ou nulo.");
		if (isNull(classe) || isEmpty(classe))
			throw new IllegalArgumentException("Entrada invalida: classe nao pode ser vazia ou nula.");
		if (isNull(id) || isEmpty(id))
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
	}

	public static void validaId(String id) {
		if (isNull(id) || isEmpty(id))
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
	}

	public static void validaNome(String nome) {
		if (isNull(nome) || isEmpty(nome))
			throw new IllegalArgumentException("Entrada invalida: nome nao pode ser vazio ou nulo.");
	}

	public static Boolean checaArgumento(String campo) {
		if (!(isNull(campo) || isEmpty(campo))) {
			return true;
		}
		return false;
	}

	public static void validaDescritor(String descritor) throws Exception {
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
	public static void validaAdicionaItem(String id, String descricao, int quantidade, String tags) throws Exception {
		if (isNull(id) || isEmpty(id)) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		validaDescritor(descricao);
		if (quantidade <= 0) {
			throw new IllegalArgumentException("Entrada invalida: quantidade deve ser maior que zero.");
		}
	}

	/**
	 * Retira espaacos inuteis da entrada.
	 * 
	 * @param entrada
	 */
	public static String retiraEspacos(String entrada) {
		return entrada.trim();
	}

	public static void validaExibeItem(int idItem, String idDoador) throws Exception {
		if (isNull(idDoador) || isEmpty(idDoador))
			throw new IllegalArgumentException();
		if (isNull(idItem) || isEmpty(idItem))
			throw new IllegalArgumentException();

	}

	public static void validaItem(int idItem, String idUsuario) throws Exception {
		if (idItem < 0)
			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
		if (isNull(idUsuario) || isEmpty(idUsuario))
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");

	}

}
