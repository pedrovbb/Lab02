package beans;
import demaisClasses.GerenciadorTextos;
import estrategias.*;
import excecoes.InvalidTextException;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

@ManagedBean
@SessionScoped
public class TextoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Map<String, Estrategia> estrategias;
	private String newTexto, textoSelecionado, textoRecombinado, estrategiaSelecionada;
	private GerenciadorTextos gerenciador;
	private Estrategia estrategiaUsada;
	private boolean botaoHabilitado; 

	/**
	 * Construtor
	 */
	public TextoBean() {
		setNewTexto(new String());
		setTextoSelecionado(new String());
		setTextoRecombinado(new String());
		setEstrategiaSelecionada(new String());
		gerenciador = new GerenciadorTextos();
		estrategias = new HashMap<String, Estrategia>();
		botaoHabilitado = false;
		reiniciaEstrategias();
	}

	/**
	 * Salva um novo texto
	 */
	public void salvar() {
		salvar(null);
	}
	
	public void salvar(String texto) {
		if (texto == null || texto.trim().isEmpty()) {
			// não salva espaços em branco
			if (!getNewTexto().trim().isEmpty()) {
				gerenciador.salvar(getNewTexto());
			}
		} else {
			//texto vindo da recombinação
			gerenciador.salvar(texto);
		}
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Novo texto salvo", "Quantidade de textos salvos: " + gerenciador.getNumeroTextos()));
		this.reiniciar();
	}

	/**
	 * Reinicia as variáveis
	 * 
	 * @return index.xhtml
	 */
	public String reiniciar() {
		setNewTexto(new String());
		setTextoSelecionado(new String());
		setTextoRecombinado(new String());
		setBotaoHabilitado(false);
		reiniciaEstrategias();
		return "index";
	}

	private void reiniciaEstrategias(){
		if (!estrategias.isEmpty())
			estrategias.clear();
		estrategias.put("INVERSA", new EstrategiaInversa());
		estrategias.put("COM_REPETICAO", new EstrategiaAleatoriaComRepeticao());
		estrategias.put("SEM_REPETICAO", new EstrategiaAleatoriaSemRepeticao());
		estrategiaUsada = null;
		estrategiaSelecionada = null;
	}
	
	/**
	 * Retorna uma lista de Strings com os textos cadastrados
	 * 
	 * @return List<String>
	 */
	public List<String> getTextos() {
		return gerenciador.getTextos();
	}

	/**
	 * Retornas as 12 primeiras palavras do texto
	 * 
	 * @param texto
	 * @return String
	 */
	public String get12Palavras(String texto) {
		try {
			return gerenciador.get12Palavras(texto);
		} catch (InvalidTextException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO pensar!
		return null;
	}

	/**
	 * @return String
	 */
	public String getNewTexto() {
		return newTexto;
	}

	/**
	 * @param String
	 */
	public void setNewTexto(String value) {
		this.newTexto = value;
	}

	public void onRowSelect(SelectEvent event) {
		// TODO
		System.out.println("onRowSelect");
		FacesMessage msg = new FacesMessage("Texto Selecionado: ",
				(String) (event.getObject()));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/**
	 * @return String
	 */
	public String getTextoSelecionado() {
		return textoSelecionado;
	}

	/**
	 * @param String textoSelecionado
	 */
	public void setTextoSelecionado(String textoSelecionado) {
		this.textoSelecionado = textoSelecionado;
	}

	/**
	 * @return String
	 */
	public String getTextoRecombinado() {
		return textoRecombinado;
	}

	/**
	 * @param textoRecombinado
	 */
	public void setTextoRecombinado(String textoRecombinado) {
		this.textoRecombinado = textoRecombinado;
	}

	/**
	 * Define o texto selecionado para ser recombinado
	 * 
	 * @return recombinar.xhtml
	 */
	public String marcaRecombinar() {
		if (!textoSelecionado.trim().isEmpty()) {
			this.textoRecombinado = this.textoSelecionado;
			return "recombinar";
		}
		return null;
	}

	/**
	 * Recombina o texto de acordo com a estratégia definia pelo usuário
	 */
	public void recombinarEstrategia() {
		if (estrategiaUsada == null) {
			if (estrategiaSelecionada != null && !estrategiaSelecionada.trim().isEmpty()) {
				estrategiaUsada = estrategias.get(estrategiaSelecionada);
				configuraEstrategia(textoRecombinado);
			}
		} else {
			String novaLinha = estrategiaUsada.getLinhaRecombinada();
			if (novaLinha == null){
				addMensagem("Não há mais linhas disponíveis!");
				setBotaoHabilitado(true);}
			else
				textoRecombinado += novaLinha;
		}
		
//		if (estrategiaUsada != null) {
//			// setEstrategiaSelecionada(estrategiaSelecionada);
//			// TODO não sei pq tinha essa condição aqui
//			// if
//			// (textoSelecionado.trim().equalsIgnoreCase(textoRecombinado.trim()))
//			// {
//			configuraEstrategia(textoRecombinado);
//			// }
//			String novaLinha = estrategiaUsada.getLinhaRecombinada();
//			if (novaLinha == null)
//				addMensagem("Não há mais linhas disponíveis!");
//			else
//				textoRecombinado += novaLinha;
//		}else{
//			System.out.println(estrategiaSelecionada);
//			addMensagem("Escolha uma estratégia para ser usada");
//		}
	}

	private void addMensagem(String mensagemTitulo) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(mensagemTitulo));
	}

	/**
	 * Apenas informa o texto a ser recombinado para a classe
	 * 
	 * @param textoRecombinado
	 */
	private void configuraEstrategia(String textoRecombinado) {
		estrategiaUsada.setTexto(textoRecombinado);
	}

	
	public void setEstrategiaSelecionada(String newEstrategia) {
		if (newEstrategia != null && !newEstrategia.trim().isEmpty())
			estrategiaSelecionada = newEstrategia;
	}
	
	public String getEstrategiaSelecionada() {
		return estrategiaSelecionada;
	}

	
	public boolean isBotaoHabilitado() {
		return botaoHabilitado;
	}

	public void setBotaoHabilitado(boolean valor) {
		this.botaoHabilitado = valor;
	}

}
