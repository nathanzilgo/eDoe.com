package facade;

import java.io.File;

import controllers.Controller;
import easyaccept.EasyAccept;

public class Facade {
	private Controller controller = new Controller();

	public static void main(String[] args) {
		args = new String[] { "facade.Facade", "acceptionTest/use_case_1.txt", "acceptionTest/use_case_2.txt","acceptionTest/use_case_3"
				+ ".txt", "acceptionTest/use_case_4.txt" };
		EasyAccept.main(args);
	}

	public void lerReceptores(File caminho) throws Exception {
		controller.lerReceptores(caminho);
	}

	public String adicionaDoador(String id, String nome, String email, String celular, String classe) throws Exception {
		return controller.adicionaDoador(id, nome, email, celular, classe);
	}

	public String pesquisaUsuarioPorId(String id) throws Exception {
		return controller.pesquisaUsuarioPorId(id);
	}

	public String pesquisaUsuarioPorNome(String nome) throws Exception {
		return controller.pesquisaUsuarioPorNome(nome);
	}

	public String atualizaUsuario(String id, String nome, String email, String celular) throws Exception {
		return controller.atualizaUsuario(id, nome, email, celular);
	}

	public void removeUsuario(String id) {
		controller.removeUsuario(id);
	}
	
	public void adicionaDescritor(String descricao) throws Exception{
		this.controller.adicionaDescritor(descricao);
	}
	/**
	 * Retorna o ID do item cadastrado.
	 * 
	 * @param idDoador
	 * @param descricao
	 * @param quantidade
	 * @param tags
	 * @return Integer
	 * @throws Exception
	 */
	public int adicionaItemParaDoacao(String idDoador, String descricao, int quantidade, String tags) throws Exception{
		return this.controller.adicionaItemParaDoacao(idDoador, descricao, quantidade, tags);
	}
	
	/**
	 * Exibe um item cadastrado em um doador caso ele e o doador existam
	 * e as entradas sejam validas.
	 * 
	 * @param idItem
	 * @param idDoador
	 * @return String
	 */
	public String exibeItem(int idItem, String idDoador) throws Exception{
		return this.controller.exibeItem(idItem, idDoador);
	}
	
	/**
	 * Modifica as tags e quantidade de um item especifico de um doador especifico no sistema, caso ambos existam
	 * e as entradas sejam validas.
	 * 
	 * @param idItem
	 * @param idDoador
	 * @param quantidade
	 * @param tags
	 * @throws Exception
	 */
	public String atualizaItemParaDoacao(int idItem, String idDoador, int quantidade, String tags) throws Exception{
		return this.controller.atualizaItemParaDoacao(idItem, idDoador, quantidade, tags);
	}
	/**
	 * Remove um item cadastrado em um usuario doador caso ambos existam.
	 * nao remove o descritor do item
	 * 
	 * @param idItem
	 * @param idDoador
	 * @throws Exception
	 */
	public void removeItemParaDoacao(int idItem, String idDoador) throws Exception{
		this.controller.removeItemParaDoacao(idItem, idDoador);
	}
	
	public int adicionaItemNecessario(String idReceptor, String descricaoItem, int quantidade, String tags) {
		return controller.adicionaItemNecessario(idReceptor, descricaoItem, quantidade, tags);

	}
	
	public String listaItensNecessarios() { 
		return controller.listaItensNecessarios();
	}
	
	public String atualizaItemNecessario(String idReceptor, int idItem, int novaQuantidade, String novasTags) {
		return controller.atualizaItemNecessario(idReceptor, idItem, novaQuantidade, novasTags);
	}
	
	public void removeItemNecessario(String idReceptor, int idItem) {
		controller.removeItemNecessario(idReceptor, idItem);
	}
	public String pesquisaItemParaDoacaoPorDescricao(String entrada) {
		return controller.pesquisaItemParaDoacaoPorDescricao(entrada);
	}
	public String listaDescritorDeItensParaDoacao() {
		return controller.litaDescritores();
	}
	public String listaItensParaDoacao() {
		return controller.listaItensDoacao();
	}
}
