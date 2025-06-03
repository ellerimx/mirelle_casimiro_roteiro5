package tad.fila;

public class MinhaFilaEncadeada implements FilaIF<Integer> {

	//private ListaEncadeadaIF filaEncadeada = null;

	private class Node {
		Integer valor;
		Node prox;

		Node(Integer valor) {
			this.valor = valor;
			this.prox = null;
		}
	}

	private Node cabeca = null;
	private Node cauda = null;
	private int tamanho = 0;

	@Override
	public void enfileirar(Integer item) throws FilaCheiaException {
		// TODO Auto-generated method stub

		Node novo = new Node(item);
		if(isEmpty()){
			cabeca = novo;
			cauda = novo;
		}
		else {
			//cabeca.prox = novo;
			cauda.prox = novo;
			cauda = novo;
		}
		tamanho++;
	}

	@Override
	public Integer desenfileirar() throws FilaVaziaException {
		// TODO Auto-generated method stub

		if(isEmpty()){
			throw new FilaVaziaException();
		}

		Integer valor = cabeca.valor;
		cabeca = cabeca.prox;

		if(cabeca == null){
			cauda = null;
		}
		tamanho--;
		return valor;
	}

	@Override
	public Integer verificarCauda() {
		// TODO Auto-generated method stub

		if(cauda != null){
			return cauda.valor;
		}

		return null;
	}

	@Override
	public Integer verificarCabeca() {
		// TODO Auto-generated method stub

		if(cabeca!=null){
			return cabeca.valor;
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return tamanho == 0;
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int capacidade() {
		return Integer.MAX_VALUE;
	}

	@Override
	public int tamanho() {
		return tamanho;
	}

}
