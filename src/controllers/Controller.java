package controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
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

	public void lerReceptores(String caminho) throws Exception {
		Scanner scanner = new Scanner(new File(caminho));
		scanner.nextLine();
		while (scanner.hasNextLine()) {
			String[] fileUsuario = scanner.nextLine().split(",");
			System.out.println(Arrays.toString(fileUsuario));
			Validar.adicionaUsuario(fileUsuario[0], fileUsuario[1], fileUsuario[2], fileUsuario[3], fileUsuario[4]);
			if (!existeusuario(fileUsuario[0])) {
				if (existeClasse(fileUsuario[4])) {
					Usuario novoUsuario = new Receptor(fileUsuario[0], fileUsuario[1], fileUsuario[2], fileUsuario[3],
							fileUsuario[4]);
					this.mapaUsuarios.put(fileUsuario[0], novoUsuario);
				} else {
					throw new Exception("Entrada invalida: opcao de classe invalida.");
				}
			} else {
				throw new Exception("Usuario ja existente: " + fileUsuario[0] + ".");
			}
		}
		scanner.close();
	}

}
