package facade;

import controllers.UsuarioController;
import easyaccept.EasyAccept;

public class Facade {
	private UsuarioController usuarioController = new UsuarioController();

	public static void main(String[] args) {
		args = new String[] { "facade.Facade", "acceptionTest/use_case_1.txt" };
		EasyAccept.main(args);
	}

	public void lerReceptores(String caminho) throws Exception {
		usuarioController.lerReceptores(caminho);
	}

	public String adicionaDoador(String id, String nome, String email, String celular, String classe) throws Exception {
		return usuarioController.adicionaDoador(id, nome, email, celular, classe);
	}

	public String pesquisaUsuarioPorId(String id) {
		return usuarioController.pesquisaUsuarioPorId(id);
	}

	public String pesquisaUsuarioPorNome(String nome) {
		return usuarioController.pesquisaUsuarioPorNome(nome);
	}

	public String atualizaUsuario(String id, String nome, String email, String celular) {
		return usuarioController.atualizaUsuario(id, nome, email, celular);
	}

	public void removeUsuario(String id) {
		usuarioController.removeUsuario(id);
	}

}
