package tad.pilha;

import tad.listasEncadeadas.ListaEncadeadaIF;
import tad.listasEncadeadas.NodoListaEncadeada;

public class MinhaPilhaEncadeada implements PilhaIF<Integer> {
	
	//private final ListaEncadeadaIF<Integer> listaEncadeada = new ListaEncadeadaImpl<>();

	private NodoListaEncadeada<Integer> cabeca;

	public MinhaPilhaEncadeada(){
		cabeca = null;
	}

	@Override
	public void empilhar(Integer item) throws PilhaCheiaException {
		NodoListaEncadeada<Integer> novoNo = new NodoListaEncadeada<>(item);
		novoNo.setProximo(cabeca);
		cabeca = novoNo;
	}

	@Override
	public Integer desempilhar() throws PilhaVaziaException {
		if (isEmpty()) {
			throw new PilhaVaziaException();
		}
		Integer topo = cabeca.getChave();
		cabeca = cabeca.getProximo();
		return topo;
	}

	@Override
	public Integer topo() {
		if (isEmpty()) {
			return null;
		}
		return cabeca.getChave();
	}

	@Override
	public PilhaIF<Integer> multitop(int k) throws PilhaCheiaException {
		MinhaPilhaEncadeada pilhaAux = new MinhaPilhaEncadeada();
		NodoListaEncadeada<Integer> atual = cabeca;
		int count = 0;
		while (atual != null && count < k) {
			try {
				pilhaAux.empilhar(atual.getChave());
			} catch (PilhaCheiaException e) {
				break;
			}
			atual = atual.getProximo();
			count++;
		}
		return pilhaAux;
	}

	@Override
	public boolean isEmpty() {
		return cabeca == null;
	}

	@Override
	public int capacidade() {
		return Integer.MAX_VALUE;
	}

	@Override
	public int tamanho() {
		int count = 0;
		NodoListaEncadeada<Integer> atual = cabeca;
		while (atual != null) {
			count++;
			atual = atual.getProximo();
		}
		return count;
		}

}

