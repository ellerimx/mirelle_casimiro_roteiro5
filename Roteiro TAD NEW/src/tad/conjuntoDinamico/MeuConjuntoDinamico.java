package tad.conjuntoDinamico;

import java.util.Arrays;

import tad.ElementoNaoEncontradoException;

public class MeuConjuntoDinamico implements ConjuntoDinamicoIF<Integer> {

	private Integer[] meusDados = new Integer[10];
	private int posInsercao = 0;

	public MeuConjuntoDinamico() {
		this.meusDados = new Integer[10];
		this.posInsercao = 0;
	}

	/**
	 * metodo para insercaoo de um elemento no conjunto dinamico. Se estiver cheio
	 * é chamada uma funcaoo para aumentar o tamanho do conjunto.
	 *
	 * @author Mirelle Casimiro 
	 *
	 */

	@Override
	public void inserir(Integer item) throws IllegalArgumentException{
		if(item == null) {
			throw new IllegalArgumentException("Elemento não pode ser null");
		}
		if (posInsercao == meusDados.length) {
			meusDados = aumentarArray();
		}
		meusDados[posInsercao] = item;
		posInsercao++;
	}

	/**
	 * Metodo para aumentar o tamanho do array do conjunto dinamico
	 * Copia os dados do array e cola para o novo array.
	 *
	 *
	 * @author Mirelle Casimiro
	 */

	private Integer[] aumentarArray() {
		// criar um array maior (arrayMaior)
		// Qual é a taxa de aumento desse array?
		// copiar os dados de meusDados (array cheio)
		// colar os dados para o novo array (arrayMaior)

		return Arrays.copyOf(meusDados, meusDados.length * 2);
	}

	@Override
	public Integer remover(Integer item) throws ConjuntoDinamicoVazioException, ElementoNaoEncontradoException {
		if (posInsercao == 0) {
			throw new ConjuntoDinamicoVazioException();
		}

		if (item == null) {
			throw new IllegalArgumentException("Elemento não pode ser null");
		}

		int index = -1;
		for (int i = 0; i < posInsercao; i++) {
			if (meusDados[i].equals(item)) {
				index = i;
				break;
			}
		}
		if (index == -1) {
			throw new ElementoNaoEncontradoException();
		}
		Integer removido = meusDados[index];

		for (int i = index; i < posInsercao - 1; i++) {
			meusDados[i] = meusDados[i + 1];
		}
		meusDados[posInsercao - 1] = null;
		posInsercao--;

		return removido;
	}


	@Override
	public Integer predecessor(Integer item) throws ConjuntoDinamicoVazioException {
		if (posInsercao == 0){
			throw new ConjuntoDinamicoVazioException();
		}

		int index = -1;
		for (int i = 0; i < posInsercao; i++){
			if (meusDados[i].equals(item)){
				index = i;
				break;
			}
		}

		if (index == 0){
			return null;
		}

		return meusDados[index-1];
	}

	@Override
	public Integer sucessor(Integer item) throws ConjuntoDinamicoVazioException {

		if (tamanho() == 0) {
			throw new ConjuntoDinamicoVazioException();
		}

		for (int i = 0; i < posInsercao; i++) {
			if (meusDados[i].equals(item)) {
				if (i + 1 < posInsercao) {
					return meusDados[i + 1];
				} else {
					return null;
				}
			}
		}
		return null;
	}

		@Override
	public int tamanho() {
		return posInsercao;
	}

	@Override
	public Integer buscar(Integer item) throws ElementoNaoEncontradoException {
		if (item == null) {
			throw new IllegalArgumentException("Elemento não pode ser null");
		}
		for (int i = 0; i < posInsercao; i++) {
			if (meusDados[i].equals(item)) {
				return meusDados[i];
			}
		}
		throw new ElementoNaoEncontradoException();
	}

	@Override
	public Integer minimum() throws ConjuntoDinamicoVazioException {
		if(posInsercao == 0){
			throw new ConjuntoDinamicoVazioException();
		}

		Integer min = meusDados[0];
		for(int i = 1; i < posInsercao; i++){
			if (meusDados[i] < min){
				min = meusDados[i];
			}
		}
		return min;
	}

	@Override
	public Integer maximum() throws ConjuntoDinamicoVazioException {
		if(posInsercao == 0){
			throw new ConjuntoDinamicoVazioException();
		}

		Integer max = meusDados[0];
		for(int i = 1; i < posInsercao; i++){
			if(meusDados[i] > max){
				max = meusDados[i];
			}
		}
		return max;
	}

}
