package tad.listasEncadeadas;

import tad.fila.FilaCheiaException;
import tad.fila.FilaIF;
import tad.fila.FilaVaziaException;

public class FilaListaEncadeada implements FilaIF<NodoListaEncadeada<Integer>> {

	private ListaEncadeadaImpl<Integer> fila;

	public FilaListaEncadeada() {
		this.fila = new ListaEncadeadaImpl<>();
	}

	@Override
	public void enfileirar(NodoListaEncadeada<Integer> item) throws FilaCheiaException {
		if (isFull()) {
			throw new FilaCheiaException();
		}
		fila.insert(item.getChave());
	}

	@Override
	public NodoListaEncadeada<Integer> desenfileirar() throws FilaVaziaException {
		if (fila.isEmpty()) {
			throw new FilaVaziaException();
		}

		NodoListaEncadeada<Integer> anterior = fila.getCabeca();
		NodoListaEncadeada<Integer> primeiro = anterior.getProximo();

		anterior.setProximo(primeiro.getProximo());
		return primeiro;
	}

	@Override
	public NodoListaEncadeada<Integer> verificarCauda() throws FilaVaziaException {
		if (isEmpty()) {
			throw new FilaVaziaException();
		}

		NodoListaEncadeada<Integer> atual = fila.getCabeca().getProximo();
		if (atual.isNull()) {
			return null;
		}
		while (!atual.getProximo().isNull()) {
			atual = atual.getProximo();
		}
		return atual;
	}

	@Override
	public NodoListaEncadeada<Integer> verificarCabeca() {
		NodoListaEncadeada<Integer> primeiroElemento = fila.getCabeca().getProximo();
		return primeiroElemento.isNull() ? null : primeiroElemento;
	}

	@Override
	public boolean isEmpty() {
		return fila.isEmpty();
	}

	@Override
	public boolean isFull() {
		return false;
	}

	@Override
	public int capacidade() {
		return Integer.MAX_VALUE;
	}

	@Override
	public int tamanho() {
		int contador = 0;
		NodoListaEncadeada<Integer> atual = fila.getCabeca().getProximo();

		while (!atual.isNull()) {
			contador++;
			atual = atual.getProximo();
		}

		return contador;
	}
}
