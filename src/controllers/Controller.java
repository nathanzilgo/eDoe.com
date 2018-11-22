package controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Scanner;

import ferramentas.Validar;
import internas.Doador;
import internas.Receptor;
import internas.Usuario;

public class Controller {

	private LinkedHashMap<String, Usuario> mapaUsuarios;
	private HashSet<String> descritores;

	public Controller() {
		this.mapaUsuarios = new LinkedHashMap<>();
		this.descritores = new HashSet<>();
	}

	public String adicionaDoador(String id, String nome, String email, String celular, String classe) throws Exception {
		Validar.adicionaUsuario(id, nome, email, celular, classe);
		if (!existeusuario(id)) {
			if (existeClasse(classe)) {
				Usuario novoUsuario = new Doador(id, nome, email, celular, classe);
				this.mapaUsuarios.put(id, novoUsuario);
				return id;
			}
			throw new Exception("Entrada invalida: opcao de classe invalida.");
		}
		throw new Exception("Usuario ja existente: " + id + ".");
	}

	public String pesquisaUsuarioPorId(String id) throws Exception {
		Validar.validaId(id);
		if (existeusuario(id)) {
			return this.mapaUsuarios.get(id).toString();
		}
		throw new Exception("Usuario nao encontrado: " + id + ".");
	}

	public String pesquisaUsuarioPorNome(String nome) throws Exception {
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
		throw new Exception("Usuario nao encontrado: " + nome + ".");
	}

	public String atualizaUsuario(String id, String nome, String email, String celular) {
		return null;
	}

	public void removeUsuario(String id) {
		// TODO Auto-generated method stub

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
				this.mapaUsuarios.get(dado[0]).setTelefone(dado[4]);
				this.mapaUsuarios.get(dado[0]).setClasse(dado[2]);

			}

		}
		sc.close();
	}

	// -------------------------------------------------------------------------US
	// 2------------------------------------------------------------------------

	/**
	 * Retorna uma descrição caso ela esteja no conjunto.
	 * 
	 * @param descricao
	 * @return
	 */
	public String getDescritor(String descricao) {
		for (String str : this.descritores) {
			if (str.equals(descricao.toLowerCase())) {
				return str;
			}
		}
		return null;
	}

	/**
	 * Valida se a descrição é nula ou vazia. Caso esteja correto, remove espaços
	 * desnecessários da entrada e a adiciona no conjunto de descritores.
	 * 
	 * @param descricao
	 * @throws Exception
	 */
	public void adicionaDescritor(String descricao) throws Exception {
		Validar.validaDescritor(descricao);
		Validar.retiraEspacos(descricao);

		if (this.descritores.contains(descricao.toLowerCase())) {
			throw new IllegalArgumentException(
					"Descritor de Item ja existente: " + this.getDescritor(descricao.toLowerCase()));
		} else {
			this.descritores.add(descricao.toLowerCase());
		}
	}

	/**
	 * Adiciona um item a um usuario.
	 * retorna o id do usuario.
	 * @param id
	 * @param descricao
	 * @param quantidade
	 * @param tags
	 * @return String
	 */
	public String adicionaItemParaDoacao(String id, String descricao, int quantidade, String tags) throws Exception{
		Validar.validaAdicionaItem(id, descricao, quantidade, tags);
		if(!this.existeusuario(id)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
		}
		this.mapaUsuarios.get(id).adicionaItem();
		return id;
	}

}
