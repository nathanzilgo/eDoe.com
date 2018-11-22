package controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.Scanner;

import ferramentas.Validar;
import internas.Doador;
import internas.Receptor;
import internas.Usuario;

public class Controller {
	private LinkedHashMap<String, Usuario> mapaUsuarios = new LinkedHashMap<>();

	/**
	 * Metodo responsevel por criar um novo doador e adiciona-lo no mapa de
	 * usuarios. O metodo checa se o usuário ja existe e se a classe e valida antes
	 * de cria-lo, caso nao sejam, da erro.
	 * 
	 * @param id      Key do doador no mapa de usuários.
	 * @param nome    Nome do doador.
	 * @param email   Email do doador.
	 * @param celular Celular do doador.
	 * @param classe  Classe do doador.
	 * @return Retorna o id do doador.
	 */
	public String adicionaDoador(String id, String nome, String email, String celular, String classe) {
		Validar.adicionaUsuario(id, nome, email, celular, classe);
		if (!existeusuario(id)) {
			if (existeClasse(classe)) {
				Usuario novoUsuario = new Doador(id, nome, email, celular, classe);
				this.mapaUsuarios.put(id, novoUsuario);
				return id;
			}
			throw new IllegalArgumentException("Entrada invalida: opcao de classe invalida.");
		}
		throw new IllegalArgumentException("Usuario ja existente: " + id + ".");
	}

	/**
	 * Metodo responsavel pela pesquisa de um usuario no mapa de usuarios. O id e
	 * passado como parametro pois a a Key do usuario no mapa.
	 * 
	 * @param id Id do usuario a ser pesquisado.
	 * @return retorna a representacao em String do usuario.
	 */

	public String pesquisaUsuarioPorId(String id) {
		Validar.validaId(id);
		if (existeusuario(id)) {
			return this.mapaUsuarios.get(id).toString();
		}
		throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
	}

	/**
	 * Método responsavel pela pesquisa de um ou mais usuarios pelo nome. Se o nome
	 * dos usuarios forem iguais, todos serao retornados na ordem de insercao.
	 * 
	 * @param nome Nome do usuario a ser pesquisado.
	 * @return Retorna o(s) usuario(s).
	 */

	public String pesquisaUsuarioPorNome(String nome) {
		Validar.validaNome(nome);
		String retorno = "";
		for (Usuario usuario : this.mapaUsuarios.values()) {
			if (usuario.getNome().equalsIgnoreCase(nome)) {
				retorno += usuario.toString() + " | ";
			}

		}
		if (!retorno.trim().equals("")) {
			retorno = retorno.substring(0, retorno.length() - 3);
			return retorno.trim();
		}
		throw new IllegalArgumentException("Usuario nao encontrado: " + nome + ".");
	}

	/**
	 * Metodo responsavel por atualizar os dados de um usuario. Caso algum campo
	 * seja vazio ou nulo, ele não atualiza esse campo.
	 * 
	 * @param id Key do usuario a ser atualizado.
	 * @param nome Nome a ser atualizado.
	 * @param email Email a ser atualizado.
	 * @param celular Celular a ser atualizado.
	 * @return Retorna uma representacao em String do usuario. 
	 */

	public String atualizaUsuario(String id, String nome, String email, String celular) {
		Validar.validaId(id);
		if (!mapaUsuarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
		}

		mapaUsuarios.get(id).atualizaUsuario(nome, email, celular);
		return mapaUsuarios.get(id).toString();
	}

	public void removeUsuario(String id) {
		Validar.validaId(id);

		if (!mapaUsuarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
		}

		mapaUsuarios.remove(id);
	}

	private boolean existeusuario(String id) {
		return this.mapaUsuarios.containsKey(id);
	}

	private boolean existeClasse(String classe) {
		if (classe.trim().equalsIgnoreCase("pessoa_fisica") || classe.trim().equalsIgnoreCase("igreja")
				|| classe.trim().equalsIgnoreCase("orgao_publico_municipal")
				|| classe.trim().equalsIgnoreCase("orgao_publico_estadual")
				|| classe.trim().equalsIgnoreCase("orgao_publico_federal") || classe.trim().equalsIgnoreCase("ong")
				|| classe.trim().equalsIgnoreCase("associacao") || classe.trim().equalsIgnoreCase("sociedade")) {
			return true;
		}
		return false;
	}

	public void lerReceptores(File arquivo) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileReader(arquivo));
		while (sc.hasNextLine()) {
			String[] dado = sc.nextLine().split(",");
			if (!existeusuario(dado[0])) {
				Usuario novoUsuario = new Receptor(dado[0], dado[1], dado[2], dado[3], dado[4]);
				this.mapaUsuarios.put(dado[0], novoUsuario);
			} else {
				this.mapaUsuarios.get(dado[0]).setNome(dado[1]);
				this.mapaUsuarios.get(dado[0]).setEmail(dado[2]);
				this.mapaUsuarios.get(dado[0]).setTelefone(dado[3]);
				this.mapaUsuarios.get(dado[0]).setClasse(dado[4]);

			}

		}
		sc.close();
	}

}
