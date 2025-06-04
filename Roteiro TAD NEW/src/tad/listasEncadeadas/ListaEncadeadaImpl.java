package tad.listasEncadeadas;

import tad.ElementoNaoEncontradoException;

/**
 * Implementação de uma lista encadeada simples com nós sentinela (cabeça e cauda).
 * Cada nó contém apenas uma referência para o próximo elemento.
 *
 * @param <T> Tipo dos elementos armazenados na lista, deve implementar Comparable
 */
public class ListaEncadeadaImpl<T extends Comparable<T>> implements ListaEncadeadaIF<T> {

	// Nó sentinela que representa o início da lista
	public NodoListaEncadeada<T> cabeca = null;
	// Nó sentinela que representa o fim da lista
	NodoListaEncadeada<T> cauda = null;

	// Contador para armazenar o tamanho atual da lista
	private int tamanho = 0;

	/**
	 * Construtor que inicializa a lista vazia com nós sentinela.
	 * Configura a cabeça para apontar para a cauda.
	 */
	public ListaEncadeadaImpl() {
		cabeca = new NodoListaEncadeada<T>();
		cauda = new NodoListaEncadeada<T>();
		cabeca.setProximo(cauda);
	}

	/**
	 * Verifica se a lista está vazia.
	 * @return true se a lista estiver vazia, false caso contrário
	 */
	@Override
	public boolean isEmpty() {
		return tamanho == 0;
	}

	/**
	 * Retorna o número de elementos na lista.
	 * @return Quantidade de elementos na lista
	 */
	@Override
	public int size() {
		return tamanho;
	}

	/**
	 * Busca um elemento na lista.
	 * @param chave Elemento a ser buscado
	 * @return Nó contendo o elemento ou null se não encontrado
	 */
	@Override
	public NodoListaEncadeada<T> search(T chave) {
		if(isEmpty()){
			return null;
		}
		NodoListaEncadeada<T> atual = cabeca.getProximo();

		while(atual != null && atual != cauda){
			if(chave == null) {
				if(atual.getChave() == null){
					return atual;
				}
			} else {
				if(atual.getChave() != null && atual.getChave().compareTo(chave) == 0){
					return atual;
				}
			}
			atual = atual.getProximo();
		}
		return null;
	}

	/**
	 * Insere um novo elemento no final da lista.
	 * @param chave Elemento a ser inserido
	 */
	@Override
	public void insert(T chave) {
		NodoListaEncadeada<T> novo = new NodoListaEncadeada<>(chave);
		NodoListaEncadeada<T> atual = cabeca;

		while (atual.getProximo() != cauda) {
			atual = atual.getProximo();
		}

		novo.setProximo(cauda);
		atual.setProximo(novo);
		tamanho++;
	}

	/**
	 * Remove um elemento específico da lista.
	 * @param chave Elemento a ser removido
	 * @return Nó removido
	 * @throws ListaVaziaException Se a lista estiver vazia
	 * @throws ElementoNaoEncontradoException Se o elemento não for encontrado
	 */
	@Override
	public NodoListaEncadeada<T> remove(T chave) throws ListaVaziaException, ElementoNaoEncontradoException {
		if (isEmpty()) {
			throw new ListaVaziaException();
		}

		NodoListaEncadeada<T> anterior = cabeca;
		NodoListaEncadeada<T> atual = cabeca.getProximo();

		while (!atual.equals(cauda) && atual.getChave().compareTo(chave) != 0) {
			anterior = atual;
			atual = atual.getProximo();
		}

		if (atual.equals(cauda)) {
			throw new ElementoNaoEncontradoException();
		}

		anterior.setProximo(atual.getProximo());
		atual.setProximo(null);
		tamanho--;
		return atual;
	}

	/**
	 * Converte a lista em um array do tipo especificado.
	 * @param clazz Classe do tipo T para criação do array
	 * @return Array contendo todos os elementos da lista
	 */
	@Override
	public T[] toArray(Class<T> clazz) {
		int tamanho = size();

		@SuppressWarnings("unchecked")
		T[] array = (T[]) java.lang.reflect.Array.newInstance(clazz, tamanho);

		NodoListaEncadeada<T> atual = cabeca.getProximo();
		int i = 0;
		while (atual != cauda && i < tamanho) {
			array[i++] = atual.getChave();
			atual = atual.getProximo();
		}
		return array;
	}

	/**
	 * Retorna uma string com os elementos em ordem.
	 * @return String formatada com elementos separados por vírgula
	 */
	@Override
	public String imprimeEmOrdem() {
		if(isEmpty()){
			return "";
		}

		StringBuilder sb = new StringBuilder();
		NodoListaEncadeada<T> atual = cabeca.getProximo();

		while(atual != null && atual != cauda){
			sb.append(atual.getChave());
			if(atual.getProximo() != null && atual.getProximo() != cauda){
				sb.append(", ");
			}
			atual = atual.getProximo();
		}

		return sb.toString();
	}

	/**
	 * Retorna uma string com os elementos em ordem inversa (usando recursão).
	 * @return String formatada com elementos separados por vírgula (ordem inversa)
	 */
	@Override
	public String imprimeInverso() {
		if(isEmpty()) {
			return "";
		}

		return imprimeInversoRec(cabeca.getProximo()).toString();
	}

	/**
	 * Método auxiliar recursivo para imprimir em ordem inversa.
	 * @param nodo Nó atual sendo processado
	 * @return StringBuilder contendo os elementos em ordem inversa
	 */
	private StringBuilder imprimeInversoRec(NodoListaEncadeada<T> nodo) {
		if(nodo == null || nodo == cauda) {
			return new StringBuilder();
		}
		StringBuilder sb = imprimeInversoRec(nodo.getProximo());
		if(sb.length() > 0) {
			sb.append(", ");
		}
		sb.append(nodo.getChave());
		return sb;
	}

	/**
	 * Retorna o sucessor de um elemento na lista.
	 * @param chave Elemento para encontrar o sucessor
	 * @return Nó sucessor
	 * @throws ElementoNaoEncontradoException Se o elemento não for encontrado
	 */
	@Override
	public NodoListaEncadeada<T> sucessor(T chave) throws ElementoNaoEncontradoException {
		NodoListaEncadeada<T> nodo = search(chave);
		if (nodo == null) {
			throw new ElementoNaoEncontradoException("Elemento " + chave + " não encontrado na lista.");
		}
		return nodo.getProximo();
	}

	/**
	 * Retorna o predecessor de um elemento na lista.
	 * @param chave Elemento para encontrar o predecessor
	 * @return Nó predecessor
	 * @throws ElementoNaoEncontradoException Se o elemento não for encontrado ou for o primeiro
	 */
	@Override
	public NodoListaEncadeada<T> predecessor(T chave) throws ElementoNaoEncontradoException {
		if (isEmpty()) {
			throw new ElementoNaoEncontradoException();
		}

		NodoListaEncadeada<T> anterior = cabeca;
		NodoListaEncadeada<T> atual = cabeca.getProximo();

		while (atual != null && atual != cauda) {
			if (atual.getChave().compareTo(chave) == 0) {
				if (anterior.equals(cabeca)) {
					return null;
				} else {
					return anterior;
				}
			}
			anterior = atual;
			atual = atual.getProximo();
		}
		throw new ElementoNaoEncontradoException();
	}

	/**
	 * Insere um elemento em uma posição específica na lista.
	 * @param chave Elemento a ser inserido
	 * @param index Posição de inserção (0-based)
	 * @throws IndexOutOfBoundsException Se o índice for inválido
	 */
	@Override
	public void insert(T chave, int index) {
		if(index < 0 || index > tamanho) {
			throw new IndexOutOfBoundsException("Índice fora do intervalo");
		}

		NodoListaEncadeada<T> novoNo = new NodoListaEncadeada<>(chave);
		NodoListaEncadeada<T> atual = cabeca;
		for(int i = 0; i < index; i++) {
			atual = atual.getProximo();
		}

		novoNo.setProximo(atual.getProximo());
		atual.setProximo(novoNo);
		tamanho++;
	}

	/**
	 * Retorna o nó sentinela cauda (para fins de teste).
	 * @return Nó cauda
	 */
	public NodoListaEncadeada<T> getCauda() {
		return this.cauda;
	}

	public NodoListaEncadeada<T> getCabeca() {
		return this.cabeca;
	}
}