package controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import java.util.LinkedHashMap;
import java.util.Scanner;

import ferramentas.DescricaoComparator;
import ferramentas.QuantidadeComparator;
import ferramentas.Validar;
import internas.Doador;
import internas.Item;
import internas.Receptor;
import internas.Usuario;

public class Controller {

	private LinkedHashMap<String, Usuario> mapaUsuarios;
	private HashSet<String> descritores;
	private int contadorIdItem;
	private DescricaoComparator descricaoComparator;
	private QuantidadeComparator quantidadeComparator;

	public Controller() {
		this.mapaUsuarios = new LinkedHashMap<>();
		this.descritores = new HashSet<>();
		this.contadorIdItem = 0;
		this.descricaoComparator = new DescricaoComparator();
		this.quantidadeComparator = new QuantidadeComparator();
	}

	/**
	 * Metodo responsevel por criar um novo doador e adiciona-lo no mapa de
	 * usuarios. O metodo checa se o usuario ja existe e se a classe e valida antes
	 * de cria-lo, caso nao sejam, da erro.
	 * 
	 * @param id      Key do doador no mapa de usuários.
	 * @param nome    Nome do doador.
	 * @param email   Email do doador.
	 * @param celular Celular do doador.
	 * @param classe  Classe do doador.
	 * @return Retorna o id do doador.
	 */
	public String adicionaDoador(String id, String nome, String email, String celular, String classe) throws Exception {

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
	 * Metodo responsavel pela pesquisa de um ou mais usuarios pelo nome. Se o nome
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
	 * @param id      Key do usuario a ser atualizado.
	 * @param nome    Nome a ser atualizado.
	 * @param email   Email a ser atualizado.
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

	// -------------------------------------------------------------------------US2------------------------------------------------------------------------

	/**
	 * Retorna uma descricao caso ela esteja no conjunto de descritores
	 * 
	 * @param descricao
	 * @return String
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
	 * Valida se a descricao e nula ou vazia. Caso esteja correto, remove espacos
	 * desnecessarios da entrada e a adiciona no conjunto de descritores.
	 * 
	 * @param descricao
	 * @throws Exception
	 */
	public void adicionaDescritor(String descricao) throws Exception {
		Validar.validaDescritor(descricao);
		descricao = Validar.retiraEspacos(descricao);

		if (this.descritores.contains(descricao.toLowerCase())) {
			throw new IllegalArgumentException(
					"Descritor de Item ja existente: " + this.getDescritor(descricao.toLowerCase()) + ".");
		} else {
			this.descritores.add(descricao.toLowerCase());
		}
	}

	/**
	 * Adiciona um item a um usuario. retorna o id do usuario como String. Verifica
	 * se as entradas sao validas, caso contrario, excesoes serão lancadas.
	 *
	 * @param id
	 * @param descricao
	 * @param quantidade
	 * @param tags
	 * @return String
	 */
	public int adicionaItemParaDoacao(String id, String descricaoItem, int quantidade, String tags) {
		Validar.validaAdicionaItem(id, descricaoItem, quantidade, tags);

		if (!this.existeusuario(id)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
		}

		return this.mapaUsuarios.get(id).adicionaItem(incrementador(), descricaoItem, quantidade, tags);

	}

	private boolean existeItem(int idItem) {
		for (Usuario usuario : this.mapaUsuarios.values()) {
			if (usuario.existeItem(idItem)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Retorna o metodo toString() de Item.java Recebe como parametros o id do Item
	 * a ser exibido e do doador que possui o item. Verifica se as entradas sao
	 * validas, caso contrario, excecoes serao lancadas.
	 * 
	 * @param idItem
	 * @param idDoador
	 * @return String
	 */
	public String exibeItem(int idItem, String idDoador) throws Exception {
		Validar.validaExibeItem(idItem, idDoador);

		if (!this.existeusuario(idDoador)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
		}

		if (!this.mapaUsuarios.get(idDoador).existeItem(idItem)) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		}

		return this.mapaUsuarios.get(idDoador).getItem(idItem).toString();
	}

	/**
	 * Atualiza um item para doacao. Apenas as tags e quantidade podem ser
	 * modificadas. Quantidade so eh modificada se o novo valor for maior que 0 Tags
	 * so sao modificadas se a nova string nao for nula retorna o toString() do Item
	 * em questao.
	 * 
	 * @param idItem
	 * @param idDoador
	 * @param quantidade
	 * @param tags
	 * @return String
	 * @throws Exception
	 */
	public String atualizaItemParaDoacao(int idItem, String idDoador, int quantidade, String tags) throws Exception {
		Validar.validaItem(idItem, idDoador);

		if (!this.existeusuario(idDoador))
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
		if (!this.mapaUsuarios.get(idDoador).existeItem(idItem))
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");

		if (quantidade != 0)
			this.mapaUsuarios.get(idDoador).getItem(idItem).setQuantidade(quantidade);
		if (tags != null)
			this.mapaUsuarios.get(idDoador).getItem(idItem).setTags(tags);

		return this.mapaUsuarios.get(idDoador).getItem(idItem).toString();
	}

	/**
	 * Remove um item para doacao de um Usuario Doador, caso o item e o usuario
	 * existam e as entradas sejam validas. O descritor do item permanece no
	 * conjunto de descritores.
	 *
	 * @param idItem
	 * @param idDoador
	 * @throws Exception
	 */
	public void removeItemParaDoacao(int idItem, String idDoador) throws Exception {
		Validar.validaItem(idItem, idDoador);

		if (!this.existeusuario(idDoador))
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
		if (this.mapaUsuarios.get(idDoador).getItens().isEmpty())
			throw new IllegalArgumentException("O Usuario nao possui itens cadastrados.");
		if (!this.mapaUsuarios.get(idDoador).existeItem(idItem))
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");

		this.mapaUsuarios.get(idDoador).getItens().remove(idItem);
	}

	private int incrementador() {
		this.contadorIdItem++;
		return this.contadorIdItem;
	}

	public int adicionaItemNecessario(String idReceptor, String descricaoItem, int quantidade, String tags) {
		Validar.validaAdicionaItem(idReceptor, descricaoItem, quantidade, tags);

		if (!this.existeusuario(idReceptor)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idReceptor + ".");
		}

		return this.mapaUsuarios.get(idReceptor).adicionaItem(incrementador(), descricaoItem, quantidade, tags);

	}

	public String listaItensNecessarios() {
		ArrayList<String> itens = new ArrayList<String>();

		for (Usuario usuario : mapaUsuarios.values()) {
			if (usuario instanceof Receptor) {
				for (Item item : usuario.retornaItensUsuario()) {
					itens.add(item.toString() + ", Receptor: " + usuario.getNome() + "/" + usuario.retornaId());
				}
			}
		}

		Collections.sort(itens);

		String retorno = "";

		for (int i = 0; i < itens.size() - 1; i++) {

			retorno += itens.get(i) + " | ";
		}

		retorno += itens.get(itens.size() - 1);

		return retorno;
	}

	public String atualizaItemNecessario(String idReceptor, int idItem, int novaQuantidade, String novasTags) {

		Validar.validaId(idReceptor);

		if (!mapaUsuarios.containsKey(idReceptor)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idReceptor + ".");
		}
		return mapaUsuarios.get(idReceptor).atualizaItem(idItem, novaQuantidade, novasTags);
	}

	public void removeItemNecessario(String idReceptor, int idItem) {

		Validar.validaId(idReceptor);

		if (!mapaUsuarios.containsKey(idReceptor)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idReceptor + ".");
		}

		mapaUsuarios.get(idReceptor).removeItemNecessario(idItem);

	}

	public String pesquisaItemParaDoacaoPorDescricao(String entrada) {
		Validar.validaPesquisa(entrada);
		ArrayList<Item> itensPesquisados = new ArrayList<>();
		for (Usuario usuario : mapaUsuarios.values()) {
			if (usuario instanceof Doador) {
				for (Item item : usuario.pesquisaDescricao(entrada)) {
					itensPesquisados.add(item);
				}
			}
		}

		Collections.sort(itensPesquisados, descricaoComparator);
		return strItensPesquisados(itensPesquisados);
	}

	private String strItensPesquisados(ArrayList<Item> entrada) {
		String saida = "";
		for (int i = 0; i < entrada.size() - 1; i++) {
			saida += entrada.get(i).toString() + " | ";
		}
		saida += entrada.get(entrada.size() - 1).toString();
		return saida;
	}

	/**
	 * Lista os itens organizados pelos descritores
	 * 
	 * @return
	 */

	public String litaDescritores() {
		ArrayList<Item> exibeDescritores = new ArrayList<>();
		for (Usuario usuario : mapaUsuarios.values()) {
			if (usuario instanceof Doador) {
				for (Item item : usuario.getItens().values()) {
					exibeDescritores.add(item);
				}
			}
		}
		Collections.sort(exibeDescritores, descricaoComparator);
		return strExibeDescritores(exibeDescritores);
	}

	private String strExibeDescritores(ArrayList<Item> entrada) {
		String saida = "";
		for (int i = 0; i < entrada.size() - 1; i++) {
			saida += entrada.get(i).getQuantidade() + " - " + entrada.get(i).getDescricao() + " | ";
		}
		saida += entrada.get(entrada.size() - 1).getQuantidade() + " - "
				+ entrada.get(entrada.size() - 1).getDescricao();
		return saida;
	}

	public String listaItensDoacao() {
		ArrayList<Item> itensDoacao = new ArrayList<>();
		for (Usuario usuario : mapaUsuarios.values()) {
			if (usuario instanceof Doador) {
				for (Item item : usuario.getItens().values()) {
					itensDoacao.add(item);
				}
			}
		}
		Collections.sort(itensDoacao, quantidadeComparator);
		return strItensDoacao(itensDoacao);
	}

	private String strItensDoacao(ArrayList<Item> entrada) {
		String saida = "";
		for (Item item : entrada) {
			saida += item.toString();
		}
		return saida;

	}
}
