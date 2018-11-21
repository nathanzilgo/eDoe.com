package ferramentas;

public class Validar {

	private static boolean isNull(String valor) {
		return valor == null;
	}

	private static boolean isEmpty(String valor) {
		return valor.trim().equals("");
	}

	public static void adicionaUsuario(String id, String nome, String email, String celular, String classe)
			throws Exception {
		if (isNull(nome) || isEmpty(nome))
			throw new Exception("Entrada invalida: nome nao pode ser vazio ou nulo.");
		if (isNull(email) || isEmpty(email))
			throw new Exception("Entrada invalida: email nao pode ser vazio ou nulo.");
		if (isNull(celular) || isEmpty(celular))
			throw new Exception("Entrada invalida: celular nao pode ser vazio ou nulo.");
		if (isNull(classe) || isEmpty(classe))
			throw new Exception("Entrada invalida: classe nao pode ser vazia ou nula.");
		if (isNull(id) || isEmpty(id))
			throw new Exception("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
	}

	public static void validaId(String id) throws Exception {
		if (isNull(id) || isEmpty(id))
			throw new Exception("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
	}

}
