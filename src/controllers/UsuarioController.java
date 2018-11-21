package controllers;

import ferramentas.Validar;

public class UsuarioController {

	public String adicionaDoador(String id, String nome, String email, String celular, String classe) throws Exception {
		Validar.adicionaDoador(id, nome, email, celular, classe);
		return null;
	}

	public String pesquisaUsuarioPorId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public String pesquisaUsuarioPorNome(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	public String atualizaUsuario(String id, String nome, String email, String celular) {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeUsuario(String id) {
		// TODO Auto-generated method stub

	}

}
