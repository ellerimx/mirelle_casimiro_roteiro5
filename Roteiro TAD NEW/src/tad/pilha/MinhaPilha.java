package tad.pilha;

public class MinhaPilha implements PilhaIF<Integer> {
	
	private int tamanho = 10;
	private Integer[] meusDados = null;
	private int indiceTopo = -1;

	public MinhaPilha(int tamanho) {
		this.tamanho = tamanho;
		this.meusDados = new Integer[tamanho];
	}
	
	public MinhaPilha() {
		this.meusDados = new Integer[tamanho];
	}

	@Override
	public void empilhar(Integer item) throws PilhaCheiaException {
		if (indiceTopo == meusDados.length - 1) {
			throw new PilhaCheiaException();
		} else {
			meusDados[++indiceTopo] = item;
		}
	}

	@Override
	public Integer desempilhar() throws PilhaVaziaException {
		if (!isEmpty()) {
			return meusDados[indiceTopo--];
		} else {
			throw new PilhaVaziaException();
		}
	}

	@Override
	public Integer topo() {
		if (!isEmpty()) {
			return meusDados[indiceTopo];
		} else {
			return null;
		}
	}

	@Override
	public PilhaIF<Integer> multitop(int k) throws PilhaVaziaException {
		if (k <= 0) {
			throw new IllegalArgumentException("O valor de K deve ser positivo");
		}

		if (isEmpty()) {
			throw new PilhaVaziaException();
		}

		int limite = Math.min(k, indiceTopo + 1);
		MinhaPilha novaPilha = new MinhaPilha(limite);

		for (int i = indiceTopo; i > indiceTopo - limite; i--) {
			try {
				novaPilha.empilhar(meusDados[i]);
			} catch (PilhaCheiaException e) {
				e.printStackTrace();
			}
		}
		return novaPilha;
	}

	@Override
	public boolean isEmpty() {

		return indiceTopo == -1;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		MinhaPilha outra = (MinhaPilha) obj;

		if (this.indiceTopo != outra.indiceTopo) {
			return false;
		}

		for (int i = 0; i <= indiceTopo; i++) {
			if (!this.meusDados[i].equals(outra.meusDados[i])) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int capacidade() {
		return meusDados.length;
	}

	@Override
	public int tamanho() {
		return indiceTopo + 1;
	}


}
