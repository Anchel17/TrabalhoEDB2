public class No {
	public int valor;
	public No noEsquerdo;
	public No noDireito;
	public int altura = 0;
	public static No raiz = null;
	public static int contador = 0;
	public static int quantNos = 0;

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
			quantNos++;
			return true;
		}

		No atual = raiz;
		while (true) {
			if (no.valor == atual.valor) {
				System.out.println(no.valor + " já está na árvore, não pode ser inserido");
				return false;
			} else if (no.valor < atual.valor) {
				if (atual.noEsquerdo == null) {
					atual.noEsquerdo = no;
					quantNos++;
					System.out.println(no.valor + " adicionado");
					return true;
				} else {
					atual = atual.noEsquerdo;
				}
			} else {
				if (atual.noDireito == null) {
					atual.noDireito = no;
					quantNos++;
					System.out.println(no.valor + " adicionado");
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
					// SE O NÓ A SER DELETADO TEM SUBARVORE APENAS À ESQUERDA
					if (atual.noEsquerdo != null && atual.noDireito == null) {
						if (atual.valor < pai.valor) {
							pai.noEsquerdo = atual.noEsquerdo;
						} else {
							pai.noDireito = atual.noEsquerdo;
						}
						atual = null;
						return true;
					}
					// SE O NÓ A SER DELETADO TEM SUBÁRVORE APENAS À DIREITA
					else if (atual.noDireito != null && atual.noEsquerdo == null) {
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
							if (maiorEsquerdo.noEsquerdo != null) {
								paiMaiorEsquerdo.noDireito = maiorEsquerdo.noEsquerdo;
							} else {
								if (paiMaiorEsquerdo == atual) {
									paiMaiorEsquerdo.noEsquerdo = null;
								} else {
									paiMaiorEsquerdo.noDireito = null;
								}
							}
							return true;
						}
						// Menor à direita está mais próximo do valor do nó removido
						else {
							atual.valor = menorDireito.valor;
							if (menorDireito.noDireito != null) {
								paiMenorDireito.noEsquerdo = menorDireito.noDireito;
							} else {
								if (paiMenorDireito == atual) {
									paiMenorDireito.noDireito = null;
								} else {
									paiMenorDireito.noEsquerdo = null;
								}
							}

							if (pai == null) {
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
		int altEsq = 0;
		int altDir = 0;

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

	public static boolean eCompleta(No raiz) {
		if (raiz == null) {
			return true;
		}

		if ((Math.pow(2, raiz.altura - 1) <= quantNos) && quantNos <= (Math.pow(2, raiz.altura) - 1)) {
			return true;
		}
		return false;
	}

	/**
	 * Verifica se a árvore é cheia ou não.
	 * Para ser cheia, todo nó precisa ter 0 ou 2 filhos.
	 * 
	 * @param raiz Nó raiz da árvore ou de seus subárvores pelas chamadas recursivas
	 * @return Cheia -> True; Não cheia -> False
	 */
	public static boolean eCheia(No raiz) {
		if (raiz == null)
			return true;

		// Verifica se o nó tem 0 filhos.
		if (raiz.noEsquerdo == null && raiz.noDireito == null)
			return true;

		// Verifica se existem 2 filhos em cada nó, se existirem é feito a chamada da
		// função em cada um deles.
		if ((raiz.noEsquerdo != null) && (raiz.noDireito != null))
			return eCheia(raiz.noEsquerdo) && eCheia(raiz.noDireito);

		// Se nenhum nó (não folha) não tiver 0/2 filhos.
		return false;
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
	 */
	public static void posicao(No raiz, int elemento) {
		if (raiz == null) {
			return;
		}

		posicao(raiz.noEsquerdo, elemento);
		contador++;

		if (raiz.valor == elemento) {
			System.out.println("Posição ocupada: " + contador);
		}

		posicao(raiz.noDireito, elemento);

	}

	@Override
	public String toString() {
		return "" + valor;
	}
}
