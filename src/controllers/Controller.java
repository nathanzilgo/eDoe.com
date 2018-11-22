package controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Scanner;

import ferramentas.Validar;
import internas.Doador;
import internas.Receptor;
import internas.Usuario;

public class Controller {
	private LinkedHashMap<String, Usuario> mapaUsuarios = new LinkedHashMap<>();

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
		// TODO Auto-generated method stub
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
			Usuario novoUsuario = new Receptor(dado[0], dado[1], dado[2], dado[3], dado[4]);
			this.mapaUsuarios.put(dado[0], novoUsuario);
		}
		sc.close();
	}

}
