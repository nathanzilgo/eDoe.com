package facade;

import controllers.UsuarioController;
import easyaccept.EasyAccept;

public class Facade {
	private UsuarioController usuarioController = new UsuarioController();

	public static void main(String[] args) {
		args = new String[] { "facade.Facade", "acceptionTest/use_case_1.txt" };
		EasyAccept.main(args);
	}

	public int adicionaDoador(int id, String nome, String email, String celular, String classe) {
		return usuarioController.adicionaDoador(id, nome, email, celular, classe);
	}

	public String pesquisaUsuarioPorId(int id) {
		return usuarioController.pesquisaUsuarioPorId(id);
	}

	public String pesquisaUsuarioPorNome(String nome) {
		return usuarioController.pesquisaUsuarioPorNome(nome);
	}

	public String atualizaUsuario(int id, String nome, String email, String celular) {
		return usuarioController.atualizaUsuario(id, nome, email, celular);
	}

	public void removeUsuario(int id) {
		usuarioController.removeUsuario(id);
	}

}
