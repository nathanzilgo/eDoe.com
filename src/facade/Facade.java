package facade;

import java.io.File;

import controllers.Controller;
import easyaccept.EasyAccept;

public class Facade {
	private Controller controller = new Controller();

	public static void main(String[] args) {
		args = new String[] { "facade.Facade", "acceptionTest/use_case_1.txt", "acceptionTest/use_case_2.txt",
				"acceptionTest/use_case_3" + ".txt", "acceptionTest/use_case_4.txt", "acceptionTest/use_case_5.txt",
				"acceptionTest/use_case_6.txt" };
		EasyAccept.main(args);
	}

	public void lerReceptores(File caminho) throws Exception {
		this.controller.lerReceptores(caminho);
	}

	public String adicionaDoador(String id, String nome, String email, String celular, String classe) throws Exception {
		return this.controller.adicionaDoador(id, nome, email, celular, classe);
	}

	public String pesquisaUsuarioPorId(String id) throws Exception {
		return this.controller.pesquisaUsuarioPorId(id);
	}

	public String pesquisaUsuarioPorNome(String nome) throws Exception {
		return this.controller.pesquisaUsuarioPorNome(nome);
	}

	public String atualizaUsuario(String id, String nome, String email, String celular) throws Exception {
		return this.controller.atualizaUsuario(id, nome, email, celular);
	}

	public void removeUsuario(String id) {
		this.controller.removeUsuario(id);
	}

	public void adicionaDescritor(String descricao) throws Exception {
		this.controller.adicionaDescritor(descricao);
	}

	public int adicionaItemParaDoacao(String idDoador, String descricao, int quantidade, String tags) throws Exception {
		return this.controller.adicionaItemParaDoacao(idDoador, descricao, quantidade, tags);
	}

	public String exibeItem(int idItem, String idDoador) throws Exception {
		return this.controller.exibeItem(idItem, idDoador);
	}

	public String atualizaItemParaDoacao(int idItem, String idDoador, int quantidade, String tags) throws Exception {
		return this.controller.atualizaItemParaDoacao(idItem, idDoador, quantidade, tags);
	}

	public void removeItemParaDoacao(int idItem, String idDoador) throws Exception {
		this.controller.removeItemParaDoacao(idItem, idDoador);
	}

	public int adicionaItemNecessario(String idReceptor, String descricaoItem, int quantidade, String tags) {
		return this.controller.adicionaItemNecessario(idReceptor, descricaoItem, quantidade, tags);

	}

	public String listaItensNecessarios() {
		return this.controller.listaItensNecessarios();
	}

	public String atualizaItemNecessario(String idReceptor, int idItem, int novaQuantidade, String novasTags) {
		return this.controller.atualizaItemNecessario(idReceptor, idItem, novaQuantidade, novasTags);
	}

	public void removeItemNecessario(String idReceptor, int idItem) {
		this.controller.removeItemNecessario(idReceptor, idItem);
	}

	public String pesquisaItemParaDoacaoPorDescricao(String entrada) {
		return this.controller.pesquisaItemParaDoacaoPorDescricao(entrada);
	}

	public String listaDescritorDeItensParaDoacao() {
		return this.controller.listaDescritores();
	}

	public String listaItensParaDoacao() {
		return this.controller.listaItensDoacao();
	}

	// **********************************************US -
	// 5**********************************************************

	public String match(String docReceptor, int idItemNec) {
		return this.controller.receptorMatch(docReceptor, idItemNec);
	}

	/**
	 * Us 6
	 * 
	 * @throws Exception **************************
	 */

	public String realizaDoacao(int idItemNec, int idItemDoado, String data) throws Exception {
		return this.controller.realizaDoacao(idItemNec, idItemDoado, data);
	}

	public String listaDoacoes() {
		return this.controller.listaDoacoes();
	}
}
