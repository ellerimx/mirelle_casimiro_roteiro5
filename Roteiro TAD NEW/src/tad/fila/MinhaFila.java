package tad.fila;

/**
 * Fila deve ser implementada com array fixo e estratégia circular
 * de gerenciamento de apontadores de cauda e cabeça.
 * @author fabioleite
 *
 */
public class MinhaFila implements FilaIF<Integer> {
	
	private int tamanho = 10;

	private int cauda = 1;
	private int cabeca = 0;
	
	private Integer[] meusDados = null;

	public MinhaFila(int tamanhoInicial) {
		tamanho = tamanhoInicial;
		meusDados = new Integer[tamanho];
	}

	public MinhaFila() {
		meusDados = new Integer[tamanho];
	}

	@Override
	public void enfileirar(Integer item) throws FilaCheiaException {
		if (isFull()){
			throw new FilaCheiaException();
		}

		meusDados[cauda] = item;
		cauda = (cauda + 1) % tamanho;
	}

	@Override
	public Integer desenfileirar() throws FilaVaziaException {

		if(isEmpty()){
			throw new FilaVaziaException();
		}

		Integer item = meusDados[cauda];
		meusDados[cabeca] = null;
		cabeca = (cabeca + 1) % tamanho;
		return item;
	}

	@Override
	public Integer verificarCauda() {
		if (isEmpty()) {
			return null;
		}

		int posicao = (cauda - 1 + tamanho) % tamanho;
		return meusDados[posicao];
	}

	@Override
	public Integer verificarCabeca() {

		if (isEmpty()) {
			return null;
		}
		return meusDados[cabeca];
	}

	@Override
	public boolean isEmpty() {
		return cabeca == cauda && meusDados[cabeca] == null;
	}

	@Override
	public boolean isFull() {
		return cabeca == cauda && meusDados[cabeca] != null;
	}

	@Override
	public int capacidade() {
		return tamanho;
	}

	@Override
	public int tamanho() {
		int count = 0;
		for (Integer item : meusDados) {
			if (item != null) {
				count++;
			}
		}
		return count;
	}


}
