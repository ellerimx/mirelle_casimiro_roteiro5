package tad.conjuntoDinamico;

import tad.ElementoNaoEncontradoException;

class Node{
	Integer valor;
	Node prox; // aponta p o proximo

	Node(Integer valor) {
		this.valor = valor;
		this.prox = null;
	}

}
public class MeuConjuntoDinamicoEncadeado implements ConjuntoDinamicoIF<Integer> {

	private Node cabeca;
	private int tamanho;

	public MeuConjuntoDinamicoEncadeado() {
		this.cabeca = null;
		this.tamanho = 0;
	}


	@Override
	public void inserir(Integer item) {
		Node novo = new Node(item);
		novo.prox = cabeca;
		cabeca = novo;
		tamanho++;
	}

	@Override
	public Integer remover(Integer item) throws ElementoNaoEncontradoException {
		Node atual = cabeca, anterior = null;

		while (atual != null) {
			if (atual.valor.equals(item)) {
				if (anterior == null) {
					cabeca = atual.prox;
				} else {
					anterior.prox = atual.prox;
				}
				tamanho--;
				return item;
			}
			anterior = atual;
			atual = atual.prox;
		}

		throw new ElementoNaoEncontradoException();
	}

	@Override
	public Integer predecessor(Integer item) throws ElementoNaoEncontradoException {

		Node atual = cabeca, anterior = null;
		while (atual != null) {
			if (atual.valor.equals(item)) {
				return (anterior != null) ? anterior.valor : null;
			}
			anterior = atual;
			atual = atual.prox;
		}

		throw new ElementoNaoEncontradoException();
	}

	@Override
	public Integer sucessor(Integer item) throws ElementoNaoEncontradoException {
		Node atual = cabeca;
		while (atual != null && atual.prox != null) {
			if (atual.valor.equals(item)) {
				return atual.prox.valor;
			}
			atual = atual.prox;
		}
		if (atual != null && atual.valor.equals(item)) return null;
		throw new ElementoNaoEncontradoException();
	}

	@Override
	public int tamanho() {
		return tamanho;
	}

	@Override
	public Integer buscar(Integer item) throws ElementoNaoEncontradoException {
		Node atual = cabeca;
		while (atual != null) {
			if (atual.valor.equals(item)) {
				return item;
			}
			atual = atual.prox;
		}
		throw new ElementoNaoEncontradoException();
	}

	@Override
	public Integer minimum() {

		if (cabeca == null) {
			throw new RuntimeException("conjunto dinamico vazio");
		}

		Integer min = cabeca.valor;
		Node atual = cabeca.prox;
		while (atual != null) {
			if (atual.valor < min) {
				min = atual.valor;
			}
			atual = atual.prox;
		}
		return min;
	}

	@Override
	public Integer maximum() {
		// TODO Auto-generated method stub

		if (cabeca == null) {
			throw new RuntimeException("conjunto dinamico vazio");
		}
		Integer max = cabeca.valor;
		Node atual = cabeca.prox;
		while (atual != null) {
			if (atual.valor > max) {
				max = atual.valor;
			}
			atual = atual.prox;
		}
		return max;
	}

}
