package bd;

import controllers.Controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Classe que representa um banco de dados.
 * 
 * Essa classe usa o padrão Singleton, pois não há necessidade de ser
 * instanciada mais de uma vez.
 * 
 * @author Lucio Nathan
 *
 */

public class BancoDeDados {

	private static BancoDeDados instance = null;
	private static final String path = "arquivos_sistema";
	private static final String file = "arquivo.dat";
	private Controller controller;

	private ObjectOutputStream controllerOutput;
	private ObjectInputStream controllerInput;

	/**
	 * Construtor.
	 */
	private BancoDeDados() {
	}

	/**
	 * Retorna uma instancia de BancoDedados.
	 * 
	 * @return retorna uma instancia.
	 */
	public static BancoDeDados getInstance() {

		if (instance == null) {
			instance = new BancoDeDados();
		}
		return instance;

	}

	/**
	 * Inicia o sistema e lê o arquivo de persistência.
	 * 
	 * @throws IOException
	 */
	public void iniciaSistema() throws IOException {
		try {
			this.controllerInput = new ObjectInputStream(new FileInputStream(path + "/" + file));
			try {
				controller = (Controller) controllerInput.readObject();
			} catch (Exception e) {
				controller = new Controller();
			}

		} catch (FileNotFoundException e) {
			controller = new Controller();

			File f = new File(path + "/" + file);

			f.getParentFile().mkdirs();
			try {
				f.createNewFile();
			} catch (IOException ee) {
				System.err.println("IOException: " + e.getMessage());
			}
		} catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());
		} finally {
			this.controllerInput.close();
		}

	}

	/**
	 * Salva o estado atual do sistema em arquivo.dat
	 * 
	 * @throws IOException
	 */
	public void finalizaSistema() throws IOException {

		try {
			this.controllerOutput = new ObjectOutputStream(new FileOutputStream(path + "/" + file));

			controllerOutput.writeObject(controller);
			controllerOutput.close();

		} catch (FileNotFoundException e) {
			File f = new File(path + "/" + file);
			f.getParentFile().mkdirs();
			try {
				f.createNewFile();

			} catch (IOException ee) {
				System.err.println("IOException: " + e.getMessage());
			}

		} catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());
		} finally {
			this.controllerOutput.close();
		}

	}

	/**
	 * Retorna o objeto Controller encontrado no hug.dat
	 * 
	 * @return
	 */
	public Controller getController() {
		return this.controller;
	}
}
