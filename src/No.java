public class No {
	public int valor;
	public No noEsquerdo;
	public No noDireito;
	public int altura = 0;
	public static No raiz = null;
	public static int contador = 0;

	No(int valor) {
		this.valor = valor;
		this.noEsquerdo = null;
		this.noDireito = null;
	}

	public static No busca(int chave) {
		if (raiz == null) {
			return null;
		}

		No atual = raiz;

		while (true) {
			System.out.println(atual);
			if (chave == atual.valor) {
				return atual;
			} else if (chave < atual.valor) {
				if (atual.noEsquerdo != null) {
					atual = atual.noEsquerdo;
				} else {
					if (atual.noDireito == null) {
						return null;
					} else if (chave < atual.noDireito.valor) {
						return null;
					}
				}
			} else {
				if (atual.noDireito != null) {
					atual = atual.noDireito;
				} else {
					if (atual.noEsquerdo == null) {
						return null;
					} else if (chave > atual.noEsquerdo.valor) {
						return null;
					}
				}
			}
		}
	}

	public static boolean inserir(No no) {
		if (raiz == null) {
			raiz = no;
			return true;
		}

		No atual = raiz;
		while (true) {
			if (no.valor == atual.valor) {
				return false;
			} else if (no.valor < atual.valor) {
				if (atual.noEsquerdo == null) {
					atual.noEsquerdo = no;
					return true;
				} else {
					atual = atual.noEsquerdo;
				}
			} else {
				if (atual.noDireito == null) {
					atual.noDireito = no;
					return true;
				} else {
					atual = atual.noDireito;
				}
			}
		}
	}

	public static boolean remover(int chave) {
		No atual = raiz;
		No pai = null;

		while (true) {
			if (raiz == null) {
				return false;
			}

			if (chave == atual.valor) {
				// remoção de um nó folha
				if (atual.noEsquerdo == null && atual.noDireito == null) {
					if (atual.valor < pai.valor) {
						pai.noEsquerdo = null;
					} else if (atual.valor > pai.valor) {
						pai.noDireito = null;
					}
					atual = null;
					return true;
				} else {
					// SE O NÓ A SER DELETADO TEM SUBARVORE À ESQUERDA OU À DIREITA
					if (atual.noEsquerdo != null && atual.noDireito == null) {
						pai.noEsquerdo = atual.noEsquerdo;
						atual = null;
						return true;
					} else if (atual.noDireito != null && atual.noEsquerdo == null) {
						if (atual.valor < pai.valor) {
							pai.noEsquerdo = atual.noDireito;
						} else {
							pai.noDireito = atual.noDireito;
						}
						atual = null;
						return true;
					}
					// SE O NÓ A SER REMOVIDO POSSUI DUAS SUBÁRVORES
					else if (atual.noEsquerdo != null && atual.noDireito != null) {
						// ESSE CARAI TEM QUE SER REFEITO
						No paiMaiorEsquerdo = atual;
						No maiorEsquerdo = atual.noEsquerdo;

						No paiMenorDireito = atual;
						No menorDireito = atual.noDireito;

						while (menorDireito.noEsquerdo != null) {
							paiMenorDireito = menorDireito;
							menorDireito = menorDireito.noEsquerdo;
						}

						while (maiorEsquerdo.noDireito != null) {
							paiMaiorEsquerdo = maiorEsquerdo;
							maiorEsquerdo = maiorEsquerdo.noDireito;
						}

						// Maior a esquerda está mais próximo do valor do nó removido
						if ((atual.valor - maiorEsquerdo.valor) < (menorDireito.valor - atual.valor)) {
							atual.valor = maiorEsquerdo.valor;
							if (maiorEsquerdo.noEsquerdo != null){
								paiMaiorEsquerdo.noDireito = maiorEsquerdo.noEsquerdo;
							} else {
								paiMaiorEsquerdo.noDireito = null;
							}
							return true;
						}
						// Menor à direita está mais próximo do valor do nó removido
						else {
							atual.valor = menorDireito.valor;
							if(menorDireito.noDireito != null) {
								paiMenorDireito.noEsquerdo = menorDireito.noDireito;
							}else{
								paiMenorDireito.noEsquerdo = null;
							}
							
							if(pai == null){
								raiz = atual;
							}
							return true;
						}
					}
				}
			} else if (chave < atual.valor) {
				if (atual.noEsquerdo != null) {
					pai = atual;
					atual = atual.noEsquerdo;
				}
			} else {
				if (atual.noDireito != null) {
					pai = atual;
					atual = atual.noDireito;
				}
			}
		}

	}

	public static No getRaiz() {
		return raiz;
	}

	/**
	 * Calcula a altura de todos os nós a partir do nó inserido no parâmetro.
	 * 
	 * @param raiz A partir desse nó será feito todos os cálculos de altura nos nós
	 *             abaixo.
	 * @return Altura do nó inserido no parâmetro
	 */
	public static int calcAltura(No raiz) {
		if (raiz == null) {
			return -1;
		}
		int altEsq = -1;
		int altDir = -1;

		if (raiz.noEsquerdo != null) {
			altEsq = calcAltura(raiz.noEsquerdo);
		}

		if (raiz.noDireito != null) {
			altDir = calcAltura(raiz.noDireito);
		}

		if (altDir > altEsq) {
			raiz.altura = altDir + 1;
			return altDir + 1;
		} else {
			raiz.altura = altEsq + 1;
			return altEsq + 1;
		}
	}

	public static void enesimoElemento(No raiz, int enesimo) {
		if (raiz == null)
			return;

		if (contador <= enesimo) {
			enesimoElemento(raiz.noEsquerdo, enesimo);

			contador++;

			if (contador == enesimo) {
				System.out.printf("%d ", raiz.valor);
			}

			enesimoElemento(raiz.noDireito, enesimo);
		}
	}
	
	/*
	 * Tem um bug por causa do contador ser estático 
	 * */
	public static void posicao(No raiz, int elemento){
		if(raiz == null){
			return;
		}
				
		posicao(raiz.noEsquerdo, elemento);
		contador++;
		
		if(raiz.valor == elemento){
			System.out.println("Posição ocupada: " + contador);
		}
	
		posicao(raiz.noDireito,  elemento);
	
	}

	@Override
	public String toString() {
		return "" + valor;
	}
}
