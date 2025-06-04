package tad.listasEncadeadas;

import tad.ElementoNaoEncontradoException;

import java.lang.reflect.Array;

public class ListaDuplamenteEncadeadaImpl<T extends Comparable<T>> implements ListaDuplamenteEncadeadaIF<T> {

	private NodoListaDuplamenteEncadeada<T> cabeca = null;
	private NodoListaDuplamenteEncadeada<T> cauda = null;

	public ListaDuplamenteEncadeadaImpl() {
		cabeca = new NodoListaDuplamenteEncadeada<>();
		cauda = new NodoListaDuplamenteEncadeada<>();
		cabeca.setProximo(cauda);
		cabeca.setAnterior(null);

		cauda.setAnterior(cabeca);
		cauda.setProximo(null);
	}

	@Override
	public boolean isEmpty() {
		return cabeca.getProximo().equals(cauda);
	}

	@Override
	public int size() {
		int count = 0;
		NodoListaDuplamenteEncadeada<T> atual = cabeca.getProximo();
		while (! atual.equals(cauda)) {
			count++;
			atual = atual.getProximo();
		}
		return count;
	}

	@Override
	public NodoListaDuplamenteEncadeada<T> search(T chave) {
		NodoListaDuplamenteEncadeada<T> atual = cabeca.getProximo();
		while (!atual.equals(cauda)) {
			if (atual.getChave().equals(chave)) {
				return atual;
			}
			atual = atual.getProximo();
		}
		return null;
	}

	@Override
	public void insert(T chave) {

		NodoListaDuplamenteEncadeada<T> novo = new NodoListaDuplamenteEncadeada<>(chave);

		novo.setAnterior(cauda.getAnterior());
		novo.setProximo(cauda);
		cauda.getAnterior().setProximo(novo);
		cauda.setAnterior(novo);
	}

	@Override
	public NodoListaDuplamenteEncadeada<T> remove(T chave) throws ElementoNaoEncontradoException, ListaVaziaException {
		if (isEmpty()) {
			throw new ListaVaziaException();
		}

		NodoListaDuplamenteEncadeada<T> atual = cabeca.getProximo();

		while (!atual.equals(cauda) && !atual.getChave().equals(chave)) {
			atual = atual.getProximo();
		}

		if (atual.equals(cauda)) {
			throw new ElementoNaoEncontradoException();
		}

		NodoListaDuplamenteEncadeada<T> anterior = atual.getAnterior();
		NodoListaDuplamenteEncadeada<T> proximo = atual.getProximo();

		anterior.setProximo(proximo);
		proximo.setAnterior(anterior);
		atual.setAnterior(null);
		atual.setProximo(null);

		return atual;
	}

	@Override
	public String imprimeEmOrdem() {
		StringBuilder sb = new StringBuilder();
		NodoListaDuplamenteEncadeada<T> atual = cabeca.getProximo();
		while (!atual.equals(cauda)) {
			sb.append(atual.getChave());
			if (!atual.getProximo().equals(cauda)) {
				sb.append(", ");
			}
			atual = atual.getProximo();
		}
		return sb.toString();
	}

	@Override
	public String imprimeInverso() {
		StringBuilder sb = new StringBuilder();
		NodoListaDuplamenteEncadeada<T> atual = cauda.getAnterior();

		while (atual != null && !atual.isNull() && atual != cabeca) {
			sb.append(atual.getChave());
			atual = atual.getAnterior();
			if (atual != cabeca && atual != null && !atual.isNull()) {
				sb.append(", ");
			}
		}
		return sb.toString();
	}

	@Override
	public NodoListaDuplamenteEncadeada<T> sucessor(T chave) {
		NodoListaDuplamenteEncadeada<T> nodo = search(chave);
		if (nodo == null) {
			return null;
		}
		NodoListaDuplamenteEncadeada<T> suc = (NodoListaDuplamenteEncadeada<T>) nodo.getProximo();

		if (suc.equals(cauda)) {
			return null;
		}
		return suc;
	}

	@Override
	public NodoListaDuplamenteEncadeada<T> predecessor(T chave) {
		NodoListaDuplamenteEncadeada<T> nodo = search(chave);
		if (nodo == null) {
			return null;
		}
		NodoListaDuplamenteEncadeada<T> pred = (NodoListaDuplamenteEncadeada<T>) nodo.getAnterior();
		if (pred.equals(cabeca)) {
			return null;
		}
		return pred;
	}

	@Override
	public T[] toArray(Class<T> clazz) {
		if (isEmpty()) {
			@SuppressWarnings("unchecked")
			T[] vazio = (T[]) Array.newInstance(clazz, 0);
			return vazio;
		}

		int size = size();
		@SuppressWarnings("unchecked")
		T[] array = (T[]) Array.newInstance(clazz, size);

		NodoListaDuplamenteEncadeada<T> atual = cabeca.getProximo();
		int i = 0;
		while (!atual.equals(cauda)) {
			array[i++] = atual.getChave();
			atual = atual.getProximo();
		}

		return array;
	}

	@Override
	public void inserePrimeiro(T elemento) {
		NodoListaDuplamenteEncadeada<T> novo = new NodoListaDuplamenteEncadeada<>();
		novo.setChave(elemento);
		novo.setProximo(cabeca.getProximo());
		novo.setAnterior(cabeca);

		cabeca.getProximo().setAnterior(novo);
		cabeca.setProximo(novo);
	}

	@Override
	public NodoListaDuplamenteEncadeada<T> removeUltimo() throws ListaVaziaException {
		if (isEmpty()) {
			throw new ListaVaziaException();
		}

		NodoListaDuplamenteEncadeada<T> ultimo = (NodoListaDuplamenteEncadeada<T>) cauda.getAnterior();
		NodoListaDuplamenteEncadeada<T> penultimo = (NodoListaDuplamenteEncadeada<T>) ultimo.getAnterior();

		penultimo.setProximo(cauda);
		cauda.setAnterior(penultimo);

		ultimo.setProximo(null);
		ultimo.setAnterior(null);

		return ultimo;
	}

	@Override
	public NodoListaDuplamenteEncadeada<T> removePrimeiro() throws ListaVaziaException {
		if (isEmpty()) {
			throw new ListaVaziaException();
		}
		NodoListaDuplamenteEncadeada<T> primeiro = cabeca.getProximo();
		NodoListaDuplamenteEncadeada<T> segundo = primeiro.getProximo();

		cabeca.setProximo(segundo);
		segundo.setAnterior(cabeca);

		primeiro.setProximo(null);
		primeiro.setAnterior(null);

		return primeiro;
	}

	@Override
	public void insert(T chave, int index) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException("indice fora do esperado");
		}

		NodoListaDuplamenteEncadeada<T> novo = new NodoListaDuplamenteEncadeada<>(chave);

		NodoListaDuplamenteEncadeada<T> atual = cabeca;
		for (int i = 0; i < index; i++) {
			atual = (NodoListaDuplamenteEncadeada<T>) atual.getProximo();
		}

		NodoListaDuplamenteEncadeada<T> proximo = (NodoListaDuplamenteEncadeada<T>) atual.getProximo();

		novo.setProximo(proximo);
		novo.setAnterior(atual);

		atual.setProximo(novo);
		proximo.setAnterior(novo);
	}

	public NodoListaEncadeada<T> getCauda(){
		return this.cauda;
	}

	public NodoListaDuplamenteEncadeada<T> getCabeca() {
		return cabeca;
	}
}
