package facade;

import controllers.Controller;
import easyaccept.EasyAccept;

public class Facade {
	private Controller controller = new Controller();

	public static void main(String[] args) {
		args = new String[] { "facade.Facade", "acceptionTest/use_case_1.txt" };
		EasyAccept.main(args);
	}

	public void lerReceptores(String caminho) throws Exception {
		controller.lerReceptores(caminho);
	}

	public String adicionaDoador(String id, String nome, String email, String celular, String classe) throws Exception {
		return controller.adicionaDoador(id, nome, email, celular, classe);
	}

	public String pesquisaUsuarioPorId(String id) {
		return controller.pesquisaUsuarioPorId(id);
	}

	public String pesquisaUsuarioPorNome(String nome) {
		return controller.pesquisaUsuarioPorNome(nome);
	}

	public String atualizaUsuario(String id, String nome, String email, String celular) {
		return controller.atualizaUsuario(id, nome, email, celular);
	}

	public void removeUsuario(String id) {
		controller.removeUsuario(id);
	}

}
