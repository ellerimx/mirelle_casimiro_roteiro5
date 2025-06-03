package tad.conjuntoDinamico;

import tad.ElementoNaoEncontradoException;

public class MeuConjuntoDinamico implements ConjuntoDinamicoIF<Integer> {

	private int tamanho = 10;
	private Integer[] meusDados = new Integer [tamanho];
	private int posInsercao = 0;

	/**
	 * metodo para insercaoo de um elemento no conjunto dinamico. Se estiver cheio
	 * é chamada uma funcaoo para aumentar o tamanho do conjunto.
	 *
	 * @author Mirelle Casimiro 
	 *
	 */

	@Override
	public void inserir(Integer item) {
		if (item == null) {
			throw new IllegalArgumentException("o elemento nao pode ser null");
		}

		if (posInsercao == meusDados.length) {
			meusDados = aumentarArray();
		}

		meusDados[posInsercao] = item;
		//posInsercao++;
	}

	/**
	 * Metodo para aumentar o tamanho do array do conjunto dinamico
	 * Copia os dados do array e cola para o novo array.
	 *
	 * @return
	 * @author Mirelle Casimiro
	 */
	private Integer[] aumentarArray() {
		// criar um array maior (arrayMaior)
		// Qual é a taxa de aumento desse array?
		// copiar os dados de meusDados (array cheio)
		// colar os dados para o novo array (arrayMaior)

		int novoTamanhoArray = meusDados.length * 2;
		Integer[] arrayMaior = new Integer[novoTamanhoArray];
		System.arraycopy(meusDados, 0, arrayMaior, 0, meusDados.length);

		return arrayMaior;
	}

	@Override
	public Integer remover(Integer item) throws ConjuntoDinamicoVazioException, ElementoNaoEncontradoException {
		if (item == null) {
			throw new IllegalArgumentException("o elemento nao pode ser null");
		}

		if (posInsercao == 0) {
			throw new ConjuntoDinamicoVazioException();
		}

		for (int i = 0; i < posInsercao; i++) {
			if (meusDados[i].equals(item)) {
				Integer numeroRemovido = meusDados[i];
				for (int j = i; j < posInsercao - 1; j++) {
					meusDados[j] = meusDados[j + 1];
				}
				meusDados[--posInsercao] = null;
				return numeroRemovido;
			}
		}
		throw new ElementoNaoEncontradoException();
	}


	@Override
	public Integer predecessor(Integer item) throws ConjuntoDinamicoVazioException {
		if(posInsercao == 0){
			throw new ConjuntoDinamicoVazioException();
		}
		boolean elementoEncontrado = false;
		Integer indicePredecessor = 0;

		for(int i = 0; i < posInsercao; i++){
			if(meusDados[i].equals(item)){
				elementoEncontrado = true;
				indicePredecessor = i - 1;
			}
		}

		if(!elementoEncontrado){
			return null;
		}

		if(indicePredecessor  < 0){
			return null;
		}

		return this.meusDados[indicePredecessor];
	}

	@Override
	public Integer sucessor(Integer item) throws ConjuntoDinamicoVazioException {

		if(posInsercao == 0) {
			throw new ConjuntoDinamicoVazioException();
		}
		boolean elementoEncontrado = false;
		Integer indiceSucessor = 0;

		for(int i = 0; i < posInsercao; i++){
			if(meusDados[i].equals(item)){
				elementoEncontrado = true;
				indiceSucessor = i + 1;
			}
		}

		if(!elementoEncontrado){
			return null;
		}

		if(indiceSucessor == posInsercao){
			return null;
		}
		return this.meusDados[indiceSucessor];
	}

	@Override
	public int tamanho() {
		return posInsercao;
	}

	@Override
	public Integer buscar(Integer item) throws ElementoNaoEncontradoException {
		if(item == null){
			throw new IllegalArgumentException("Elemento não pode ser null");
		}

		for(int i = 0; i < posInsercao; i++){
			if(meusDados[i].equals(item)){
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

		Integer menorNum = meusDados[0];
		for(int i = 0 ; i < posInsercao; i++){
			if(meusDados[i] < menorNum){
				menorNum = meusDados[i];
			}
		}
		return menorNum;
	}

	@Override
	public Integer maximum() throws ConjuntoDinamicoVazioException {
		if(posInsercao == 0){
			throw new ConjuntoDinamicoVazioException();
		}

		Integer maiorNum = meusDados[0];

		for(int i = 0; i < posInsercao ; i++){
			if(meusDados[i] > maiorNum){
				maiorNum = meusDados[i];
			}
		}
		return maiorNum;
	}

}
