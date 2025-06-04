package tad.fila;

import tad.listasEncadeadas.NodoListaEncadeada;

public class MinhaFilaEncadeada implements FilaIF<Integer> {

	//private ListaEncadeadaIF filaEncadeada = null;

	private NodoListaEncadeada<Integer> cabeca;
	private NodoListaEncadeada<Integer> cauda;

	public MinhaFilaEncadeada(){
		cabeca = null;
		cauda = null;
	}

	@Override
	public void enfileirar(Integer item) throws FilaCheiaException {
		NodoListaEncadeada<Integer> novoNodo = new NodoListaEncadeada<>(item);

		if (isEmpty()) {
			cabeca = novoNodo;
			cauda = novoNodo;
		} else {
			cauda.setProximo(novoNodo);
			cauda = novoNodo;
		}
	}

	@Override
	public Integer desenfileirar() throws FilaVaziaException {
		if (isEmpty()) {
			throw new FilaVaziaException();
		}

		Integer valor = cabeca.getChave();
		cabeca = cabeca.getProximo();

		if (cabeca == null) {
			cauda = null;
		}

		return valor;
	}

	@Override
	public Integer verificarCauda() {
		if(cauda != null){
			return cauda.getChave();
		} else{
			return null;
		}
	}

	@Override
	public Integer verificarCabeca() {
		if (cabeca != null) {
			return cabeca.getChave();
		} else {
			return null;
		}
	}

	@Override
	public boolean isEmpty() {
		return cabeca == null;
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
		NodoListaEncadeada<Integer> atual = cabeca;
		while (atual != null) {
			contador++;
			atual = atual.getProximo();
		}
		return contador;
	}

}
