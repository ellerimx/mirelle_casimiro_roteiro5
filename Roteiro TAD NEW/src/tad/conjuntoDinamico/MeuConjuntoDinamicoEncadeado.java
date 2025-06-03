package tad.conjuntoDinamico;

import tad.ElementoNaoEncontradoException;

public class MeuConjuntoDinamicoEncadeado implements ConjuntoDinamicoIF<Integer> {

	// implementacao com lista simples
	private class Node {
		Integer valor;
		Node prox; // aponta p o proximo

		Node(Integer valor) {
			this.valor = valor;
			this.prox = null;
		}
	}

	private Node cabeca;
	private int tamanho;

	public MeuConjuntoDinamicoEncadeado() {
		cabeca = null;
		tamanho = 0;
	}


	@Override
	public void inserir(Integer item) {
		// TODO Auto-generated method stub

		Node novo = new Node(item);
		novo.prox = cabeca;
		cabeca = novo;
		tamanho++;
	}

	@Override
	public Integer remover(Integer item) throws ElementoNaoEncontradoException {
		// TODO Auto-generated method stub

		if (cabeca == null) {
			throw new RuntimeException("conjunto dinamico vazio");
		}

		Node atual = cabeca, anterior = null;

		while (atual != null) {
			if (atual.valor.equals(item)) {
				if (anterior == null) {
					cabeca = atual.prox;
				} else {
					anterior.prox = atual.prox;
				}

				tamanho--;
				return atual.valor;
			}
			anterior = atual;
			atual = atual.prox;
		}
		throw new ElementoNaoEncontradoException();
	}

	@Override
	public Integer predecessor(Integer item) throws ElementoNaoEncontradoException {
		// TODO Auto-generated method stub

		if (cabeca == null) {
			throw new RuntimeException("conjunto dinamico vazio");
		}

		Node atual = cabeca, anterior = null;

		while (atual != null) {
			if (atual.valor.equals(item)) {
				return anterior == null ? null : anterior.valor;
			}

			anterior = atual;
			atual = atual.prox;
		}

		throw new ElementoNaoEncontradoException();
	}

	@Override
	public Integer sucessor(Integer item) throws ElementoNaoEncontradoException {
		// TODO Auto-generated method stub

		if (cabeca == null) {
			throw new RuntimeException("conjunto dinamico vazio");
		}
		Node atual = cabeca;

		while (atual != null) {
			if (atual.valor.equals(item)) {
				return atual.prox == null ? null : atual.prox.valor;
			}
			atual = atual.prox;
		}
		throw new ElementoNaoEncontradoException();
	}

	@Override
	public int tamanho() {
		// TODO Auto-generated method stub

		return tamanho;
	}

	@Override
	public Integer buscar(Integer item) {
		// TODO Auto-generated method stub
		Node atual = cabeca;
		while (atual != null) {
			if (atual.valor.equals(item)) {
				return atual.valor;
			}
			atual = atual.prox;
		}
		return null;
	}

	@Override
	public Integer minimum() {
		// TODO Auto-generated method stub

		if (cabeca == null) {
			throw new RuntimeException("conjunto dinamico vazio");
		}
		Node atual = cabeca;
		int menorNum = atual.valor;
		while (atual != null) {
			if (atual.valor < menorNum) {
				menorNum = atual.valor;
			}
			atual = atual.prox;
		}
		return menorNum;
	}

	@Override
	public Integer maximum() {
		// TODO Auto-generated method stub

		if (cabeca == null) {
			throw new RuntimeException("conjunto dinamico vazio");
		}
		Node atual = cabeca;
		int maiorNum = atual.valor;

		while (atual != null) {
			if (atual.valor > maiorNum) {
				maiorNum = atual.valor;
			}
			atual = atual.prox;
		}
		return maiorNum;
	}

}
