public class Arvore {

	public static No raiz = null;
	public static int quantNos = 0;

	public static No busca(int chave) {
		if (raiz == null) {
			return null;
		}

		No atual = raiz;

		while (true) {
			if (chave == atual.valor) {
				System.out.println("Chave encontrada");
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
			System.out.println(no.valor + " adicionado");
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
					quantNos--;
					return true;
				} else {
					// SE O NÓ A SER DELETADO TEM SUBNo APENAS À ESQUERDA
					if (atual.noEsquerdo != null && atual.noDireito == null) {
						if (atual.valor < pai.valor) {
							pai.noEsquerdo = atual.noEsquerdo;
						} else {
							pai.noDireito = atual.noEsquerdo;
						}
						atual = null;
						quantNos--;
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
						quantNos--;
						return true;
					}
					// SE O NÓ A SER REMOVIDO POSSUI DUAS SUBÁRVORES
					else if (atual.noEsquerdo != null && atual.noDireito != null) {
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
							quantNos--;
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
							quantNos--;
							return true;
						}
					}
				}
			} else if (chave < atual.valor) {
				if (atual.noEsquerdo != null) {
					pai = atual;
					atual = atual.noEsquerdo;
				} else {
					return false;
				}
			} else {
				if (atual.noDireito != null) {
					pai = atual;
					atual = atual.noDireito;
				} else {
					return false;
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

		calcAltura(raiz);

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

	public static int qtdNos(No raiz) {
		if (raiz == null) {
			return 0;
		} else {
			return qtdNos(raiz.noEsquerdo) + qtdNos(raiz.noDireito) + 1;
		}
	}

	public static int enesimoElemento(int enesimo) {
		if (qtdNos(raiz) == 0 || enesimo < 1) {
			System.out.println("Árvore vazia ou digite um n > 0");
			return -1;
		}

		if (qtdNos(raiz) < enesimo) {
			System.out.println("O 'n' excede o número de nós da árvore");
			return -1;
		}

		No atual = raiz;

		while (true) {
			int qtdNosEsquerda = qtdNos(atual.noEsquerdo);
			if (qtdNosEsquerda + 1 == enesimo) {
				return atual.valor;
			} else if (qtdNosEsquerda + 1 > enesimo) {
				atual = atual.noEsquerdo;
			} else if (qtdNosEsquerda + 1 < enesimo) {
				atual = atual.noDireito;
				// tira a qtd de nós á esquerda dos já percorridos
				enesimo -= qtdNosEsquerda + 1;
			}
		}
	}

	public static void posicao(int elemento) {
		if (raiz == null)
			return;

		No atual = raiz;
		int pos = 0;

		while (true) {
			int qtdNosEsquerda = qtdNos(atual.noEsquerdo);
			pos += qtdNosEsquerda + 1;
			if (atual.valor == elemento) {
				System.out.println(pos);
				break;
			} else if (atual.valor > elemento) {
				atual = atual.noEsquerdo;
				pos -= qtdNosEsquerda + 1;
			} else {
				atual = atual.noDireito;
			}
		}
	}

	public static int mediana() {

		int altura = qtdNos(raiz);
		int med;
		int elementoMediano1;
		int elementoMediano2;

		// se a árvore tem um número ímpar de elementos
		if (altura % 2 != 0) {
			med = (altura + 1) / 2;
			elementoMediano1 = enesimoElemento(med);
			return elementoMediano1;
		} else {
			med = (altura) / 2;
			elementoMediano1 = enesimoElemento(med);

			med = (int) Math.floor(altura / 2) + 1;
			elementoMediano2 = enesimoElemento(med);
			if (elementoMediano1 < elementoMediano2) {
				return elementoMediano1;
			} else {
				return elementoMediano2;
			}
		}
	}

	public static void preOrdem(No raiz) {
		System.out.print(raiz.valor + "  ");

		if (raiz.noEsquerdo != null) {
			preOrdem(raiz.noEsquerdo);
		}

		if (raiz.noDireito != null) {
			preOrdem(raiz.noDireito);
		}
	}

	/*
	 * Soma enquanto percorre a árvore em preOrdem
	 */
	public static double preOrdem(No raiz, double soma) {
		soma += raiz.valor;

		if (raiz.noEsquerdo != null) {
			soma = preOrdem(raiz.noEsquerdo, soma);
		}

		if (raiz.noDireito != null) {
			soma = preOrdem(raiz.noDireito, soma);
		}

		return soma;
	}

	public static double media(int valorRaiz) {
		if (raiz == null)
			return -1d;
		No atual = raiz;
		while (true) {
			if (valorRaiz == atual.valor) {
				break;
			} else if (valorRaiz < atual.valor) {
				if (atual.noEsquerdo != null) {
					atual = atual.noEsquerdo;
				} else {
					if (atual.noDireito == null) {
						break;
					} else if (valorRaiz < atual.noDireito.valor) {
						break;
					}
				}
			} else {
				if (atual.noDireito != null) {
					atual = atual.noDireito;
				} else {
					if (atual.noEsquerdo == null) {
						break;
					} else if (valorRaiz > atual.noEsquerdo.valor) {
						break;
					}
				}
			}
		}

		double media = preOrdem(atual, 0) / qtdNos(atual);

		return media;
	}

	/**
	 * Imprime uma árvore na forma de barras.
	 * 
	 * @param raiz    Árvore a ser impressa
	 * @param tracos  Traços que diferenciará em que nível está o nó.
	 * @param espacos Quantidade de espaços que serão imprimidos.
	 */
	public static void imprimirBarras(No raiz, int tracos, int blank) {
		if (raiz == null) {
			return;
		}

		if (raiz != null) {
			String vazios = "";
			// Tratamento para quanto um número tiver menos dígitos que os demais.
			if ((Integer.toString(raiz.valor).length()) < 2) {
				blank += Integer.toString(raiz.valor).length();
			}
			for (int i = 0; i < blank; i++) {
				vazios += " ";
			}

			System.out.print(vazios + raiz.valor);

			// Tratamento para quanto um número tiver mais dígitos que os demais.
			if ((Integer.toString(raiz.valor).length()) > 2) {
				tracos -= Integer.toString(raiz.valor).length() - 2;
			}
			for (int i = 0; i < tracos; i++) {
				System.out.print("-");
			}
			System.out.print("\n");

			imprimirBarras(raiz.noEsquerdo, tracos - 4, blank + 4);
			imprimirBarras(raiz.noDireito, tracos - 4, blank + 4);
		}
	}

	/**
	 * Imprime uma árvore na forma de parênteses.
	 * 
	 * @param raiz Árvore a ser impressa
	 * @return Recursivamente montando a String de saída com a árvore.
	 */
	public static String imprimirParenteses(No raiz) {
		if (raiz.altura != -1) {
			String saida = " (";
			saida += raiz.valor;

			if (raiz.noEsquerdo != null) {
				saida += imprimirParenteses(raiz.noEsquerdo);
			}
			if (raiz.noDireito != null) {
				saida += imprimirParenteses(raiz.noDireito);
			}
			saida += ")";
			return saida;
		} else if (raiz.noEsquerdo == null && raiz.noDireito != null) {
			return imprimirParenteses(raiz.noDireito);
		} else if (raiz.noDireito == null && raiz.noEsquerdo != null) {
			return imprimirParenteses(raiz.noEsquerdo);
		}
		return "";
	}
}
