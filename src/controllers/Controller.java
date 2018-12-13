package controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import ferramentas.DescricaoComparator;
import ferramentas.QuantidadeComparator;
import ferramentas.Validar;
import internas.Doacao;
import internas.Item;
import internas.Usuario;

public class Controller implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Usuario> usuarios;
	private Map<String, Integer> descritores;
	private int contadorIdItem;
	private DescricaoComparator descricaoComparator;
	private QuantidadeComparator quantidadeComparator;
	private ArrayList<Doacao> doacao;

	public Controller() {
		this.usuarios = new LinkedHashMap<>();
		this.descritores = new LinkedHashMap<>();
		this.contadorIdItem = 0;
		this.descricaoComparator = new DescricaoComparator();
		this.quantidadeComparator = new QuantidadeComparator();
		this.doacao = new ArrayList<>();
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
				Usuario novoUsuario = new Usuario(id, nome, email, celular, classe, false);
				this.usuarios.put(id, novoUsuario);
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
			return this.usuarios.get(id).toString();
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
		for (Usuario usuario : this.usuarios.values()) {
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
		if (!usuarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
		}

		usuarios.get(id).atualizaUsuario(nome, email, celular);
		return usuarios.get(id).toString();
	}

	/**
	 * Metodo responsavel por remover um usuario do sistema. Caso o id seja vazio ou
	 * nulo sera lançada uma excecao.
	 * 
	 * @param id Key do usuario a ser removido.
	 */
	public void removeUsuario(String id) {
		Validar.validaId(id);

		if (!usuarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
		}

		usuarios.remove(id);
	}

	private boolean existeusuario(String id) {
		return this.usuarios.containsKey(id);
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

	/**
	 * Metodo responsavel por ler arquivo csv que contem informacoes de receptores e
	 * cadastra-los no sistema ou atualizar suas informacoes caso algum ja esteja
	 * cadastrado.
	 * 
	 * @param arquivo arquivo csv com informacoes de receptores.
	 * @throws FileNotFoundException
	 */
	public void lerReceptores(File arquivo) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileReader(arquivo));
		while (sc.hasNextLine()) {
			String[] dado = sc.nextLine().split(",");
			if (!existeusuario(dado[0])) {
				Usuario novoUsuario = new Usuario(dado[0], dado[1], dado[2], dado[3], dado[4], true);
				this.usuarios.put(dado[0], novoUsuario);
			} else {
				this.usuarios.get(dado[0]).setNome(dado[1]);
				this.usuarios.get(dado[0]).setEmail(dado[2]);
				this.usuarios.get(dado[0]).setTelefone(dado[3]);
				this.usuarios.get(dado[0]).setClasse(dado[4]);

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
		for (String str : this.descritores.keySet()) {
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

		if (this.descritores.containsKey(descricao.toLowerCase())) {
			throw new IllegalArgumentException(
					"Descritor de Item ja existente: " + this.getDescritor(descricao.toLowerCase()) + ".");
		} else {
			this.descritores.put(descricao.toLowerCase(), 0);
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

		this.descritores.put(descricaoItem, quantidade);
		return this.usuarios.get(id).adicionaItem(incrementador(), descricaoItem, quantidade, tags);
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

		if (!this.usuarios.get(idDoador).existeItem(idItem)) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		}

		return this.usuarios.get(idDoador).getItem(idItem).toString();
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

		if (!this.existeusuario(idDoador)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
		}
		if (!this.usuarios.get(idDoador).existeItem(idItem)) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		}
		if (quantidade != 0) {
			this.usuarios.get(idDoador).getItem(idItem).setQuantidade(quantidade);
			this.descritores.put(getDescricaoPorId(idItem, idDoador), quantidade);
		}
		if (tags != null) {
			this.usuarios.get(idDoador).getItem(idItem).setTags(tags);
		}
		return this.usuarios.get(idDoador).getItem(idItem).toString();
	}

	/**
	 * pega a descricao de um item a partir do id do Doador e o id do Item.
	 * 
	 * @param idItem
	 * @param idDoador
	 * @return String descricao do item.
	 */
	private String getDescricaoPorId(int idItem, String idDoador) {
		return usuarios.get(idDoador).getItem(idItem).getDescricao();
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

		if (!this.existeusuario(idDoador)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
		}
		if (this.usuarios.get(idDoador).getItens().isEmpty()) {
			throw new IllegalArgumentException("O Usuario nao possui itens cadastrados.");
		}
		if (!this.usuarios.get(idDoador).existeItem(idItem)) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		}
		this.descritores.replace(getDescricaoPorId(idItem, idDoador), 0);
		this.usuarios.get(idDoador).getItens().remove(idItem);
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

		return this.usuarios.get(idReceptor).adicionaItem(incrementador(), descricaoItem, quantidade, tags);

	}

	/**
	 * Metodo responsavel por listar todos os itens necessarios e retornar uma
	 * representacao ordenada em String destes. O metodo cria um ArrayList de itens
	 * temporario e itera sobre os receptores do mapa de usuarios, depois pega seus
	 * itens e coloca as suas representacoes em String no ArrayList. Apos isso, o
	 * metodo ordena todas as representacoes dos itens de todos os receptores e
	 * retorna um String dessas representacoes.
	 * 
	 * @return retorna uma representacao em String de todos os itens necessarios.
	 */

	public String listaItensNecessarios() {
		ArrayList<String> itens = new ArrayList<String>();

		for (Usuario usuario : usuarios.values()) {
			if (usuario.getIsReceptor() == true) {
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

	/**
	 * Metodo responsavel por atualizar um item necessario. O metodo pega o usuario
	 * que contem o item pelo parametro idReceptor e o item a ser mudado pelo
	 * parametro idItem. Apos isso, a nova quantidade sera dada pelo parametro
	 * novaQuantidade e as novas tags pelo parametros novasTags.
	 * 
	 * @param idReceptor     id do usuario que contem o item.
	 * @param idItem         id do item a ser atualizado.
	 * @param novaQuantidade quantidade nova a ser colocada.
	 * @param novasTags      tags novas a serem colocadas.
	 * @return retorna uma representacao em String do item ja atualziado.
	 */

	public String atualizaItemNecessario(String idReceptor, int idItem, int novaQuantidade, String novasTags) {

		Validar.validaId(idReceptor);

		if (!usuarios.containsKey(idReceptor)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idReceptor + ".");
		}
		return usuarios.get(idReceptor).atualizaItem(idItem, novaQuantidade, novasTags);
	}

	/**
	 * Metodo responsavel por remover um item necessario. O metodo pega o id do
	 * receptor a ter um item removido pelo parametro idReceptor e o id do item a
	 * ser removido pelo parametro idItem.
	 * 
	 * @param idReceptor id do receptor a ser acessado.
	 * @param idItem     id do item a ser removido.
	 */

	public void removeItemNecessario(String idReceptor, int idItem) {

		Validar.validaId(idReceptor);

		if (!usuarios.containsKey(idReceptor)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idReceptor + ".");
		}

		usuarios.get(idReceptor).removeItemNecessario(idItem);

	}

	/**
	 * US-3 Pesquisa a partir de uma String de entrada na descricao dos itens e
	 * retorna todos os intens que possuem a string pesquisada na descricao
	 * ignorando letras maiusculas e minusculas.
	 * 
	 * @param descricao: String a ser pesqusada.
	 * @return String: Itens que possuem a String pesquisada na descricao
	 */

	public String pesquisaItemParaDoacaoPorDescricao(String descricao) {
		Validar.validaPesquisa(descricao);
		ArrayList<Item> itensPesquisados = new ArrayList<>();
		for (Usuario usuario : usuarios.values()) {
			if (usuario.getIsReceptor() == false) {
				for (Item item : usuario.pesquisaDescricao(descricao)) {
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
	 * US-3 Lista todos os descritores cadastrados no sistema organizados em ordem
	 * alfabetica e suas respectivas quantidades.
	 * 
	 * @return String
	 */

	public String listaDescritores() {
		ArrayList<String> exibeDescritores = new ArrayList<>();
		for (String descritor : descritores.keySet()) {
			exibeDescritores.add(descritor);
		}
		return strDescritoresOrganizados(exibeDescritores);
	}

	private String strDescritoresOrganizados(ArrayList<String> exibeDescritores) {
		String saida = "";
		Collections.sort(exibeDescritores, String.CASE_INSENSITIVE_ORDER);
		for (int i = 0; i < exibeDescritores.size() - 1; i++) {
			saida += descritores.get(exibeDescritores.get(i)) + " - " + exibeDescritores.get(i) + " | ";
		}
		saida += descritores.get(exibeDescritores.get(exibeDescritores.size() - 1)) + " - "
				+ exibeDescritores.get(exibeDescritores.size() - 1);
		return saida;
	}

	/**
	 * US-3 lista com todos os toString dos itens cadastrados no sistema
	 * primeiramente organizados pela quantidade em ordem decrescente e depois em
	 * ordem alfabetica.
	 * 
	 * @return String
	 */
	public String listaItensDoacao() {
		ArrayList<Item> itensDoacao = new ArrayList<>();
		for (Usuario usuario : usuarios.values()) {
			if (usuario.getIsReceptor() == false) {
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
		for (int i = 0; i < entrada.size() - 1; i++) {
			saida += entrada.get(i).toString() + ", doador: " + entrada.get(i).getUsuario().getNome() + "/"
					+ entrada.get(i).getUsuario().getId() + " | ";

		}
		saida += entrada.get(entrada.size() - 1).toString() + ", doador: "
				+ entrada.get(entrada.size() - 1).getUsuario().getNome() + "/"
				+ entrada.get(entrada.size() - 1).getUsuario().getId();
		return saida;

	}

	/**
	 * US 5 - Chama a funcao dentro do receptor informado e tenta encontrar
	 * possiveis matches para o item desejado. Caso nao existam, uma String vazia eh
	 * retornada Cria um arraylist com todos os itens que possuem o descritor
	 * procurado. Percorre essa lista fazendo a operacao Match() de Usuario para
	 * cada item da lista.
	 * 
	 * @param docReceptor
	 * @param idItemNec
	 * @return toString() de todos os Matches
	 */

	public String receptorMatch(String docReceptor, int idItemNec) {
		Validar.validaReceptor(docReceptor);
		Validar.validaItem(idItemNec);
		if (this.usuarios.get(docReceptor) == null)
			throw new IllegalArgumentException("Usuario nao encontrado: " + docReceptor + ".");
		if (this.getItem(idItemNec) == null)
			throw new IllegalArgumentException("Item nao encontrado: " + idItemNec + ".");
		if (!this.usuarios.get(docReceptor).getIsReceptor()) {
			throw new IllegalArgumentException("O Usuario deve ser um receptor: " + docReceptor + ".");
		}

		Item itemNec = this.getItem(idItemNec);

		HashSet<Item> possiveisMatches = this.pesquisaItensPorDescricao(itemNec.getDescricao().toLowerCase());

		for (Item iter : possiveisMatches) {
			this.usuarios.get(docReceptor).match(iter, itemNec);
		}

		return this.usuarios.get(docReceptor).getMatches();
	}

	/**
	 * Pesquisa um item dentre todos os usuarios, retorna null caso nao o encontre
	 * 
	 * @param idItem id do item a ser procurado.
	 * @return Item retorna o objeto do Item.
	 */
	public Item getItem(int idItem) {
		for (Usuario us : this.usuarios.values()) {
			if (us.getItens().containsKey(idItem)) {
				return us.getItem(idItem);
			}
		}
		return null;
	}

	/**
	 * US 5 - Retorna uma lista com todos os itens que possuem a descricao
	 * fornecida. Semelhante a 'String pesquisaItemParaDoacaoPorDescricao(String
	 * descricao)' porem, nesta, uma lista eh retornada no lugar de uma
	 * representacao textual.
	 * 
	 * @param descricao
	 * @return List
	 */
	public HashSet<Item> pesquisaItensPorDescricao(String descricao) {
		Validar.validaPesquisa(descricao);

		HashSet<Item> itensPesquisados = new HashSet<>();

		for (Usuario usuario : usuarios.values()) {
			if (usuario.getIsReceptor() == false) {
				for (Item item : usuario.getItens().values()) {
					if (item.getDescricao().equalsIgnoreCase(descricao)) {
						itensPesquisados.add(item);
					}
				}
			}
		}
		return itensPesquisados;
	}

	/**
	 * O metodo realizaDoacao(), tem como objetivo receber o id de um item
	 * necessario, o id de um item a ser doado, alem da data da doacao, e verificar
	 * se as informacoes são validas, caso sejam invalidas o sistema lanca uma
	 * excecao, caso contrario e retornado o toString dessa doacao.
	 * 
	 * @param idItemNec   id do item que o receptor necessita.
	 * @param idItemDoado id do item que o doador possui.
	 * @param data        data da doacao.
	 * @return representacao textual da doacao
	 * @throws Exception
	 */
	public String realizaDoacao(int idItemNec, int idItemDoado, String data) throws Exception {
		Validar.validaRealizaDoacao(idItemNec, idItemDoado, data);
		if (getItem(idItemNec) != null) {
			if (getItem(idItemDoado) != null) {
				if (getItem(idItemNec).getDescricao().trim()
						.equalsIgnoreCase(this.getItem(idItemDoado).getDescricao().trim())) {
					this.doacao.add(new Doacao(data, getItem(idItemDoado).getUsuario(), getItem(idItemDoado),
							getItem(idItemNec).getUsuario(), checaQtdItemDoacao(idItemNec, idItemDoado)));
					return getDoacao(idItemDoado).toString();
				}
				throw new IllegalArgumentException("Os itens nao tem descricoes iguais.");

			}
			throw new IllegalArgumentException("Item nao encontrado: " + idItemDoado + ".");
		}
		throw new IllegalArgumentException("Item nao encontrado: " + idItemNec + ".");
	}

	private int checaQtdItemDoacao(int idItemNec, int idItemDoado) throws Exception {
		int qtdNec = getItem(idItemNec).getQuantidade();
		int qtdDoado = getItem(idItemDoado).getQuantidade();
		int retorno = qtdNec;

		if (qtdNec > qtdDoado) {
			retorno = qtdDoado;
			getItem(idItemNec).setQuantidade(qtdNec - qtdDoado);
			removeItemParaDoacao(idItemDoado, getItem(idItemDoado).getUsuario().getId());
		} else if (qtdNec == qtdDoado) {
			removeItemNecessario(getItem(idItemNec).getUsuario().getId(), idItemNec);
			removeItemParaDoacao(idItemDoado, getItem(idItemDoado).getUsuario().getId());
		} else {
			getItem(idItemDoado).setQuantidade(qtdDoado - qtdNec);
			removeItemNecessario(getItem(idItemNec).getUsuario().getId(), idItemNec);
		}

		return retorno;
	}

	private Doacao getDoacao(int idItemDoado) {
		for (Doacao retorna : doacao) {
			if (retorna.getId() == idItemDoado) {
				return retorna;
			}
		}
		return null;
	}

	/**
	 * O metodo listaDoacoes(), tem como objetivo retornar a representacao textual
	 * de todas as doacoes cadastradas no sistema ordenadas por data, caso a data
	 * das doacoes sejam iguais e ordenada por ordem alfabetica do descritor do item
	 * doado.
	 * 
	 * @return representacao textual de todas as doacoes de forma ordenada.
	 */
	public String listaDoacoes() {
		String listar = "";
		Collections.sort(this.doacao);
		for (Doacao retorno : doacao) {
			listar += retorno.toString() + " | ";
		}
		listar = listar.substring(0, listar.length() - 3);
		return listar.trim();
	}
}
