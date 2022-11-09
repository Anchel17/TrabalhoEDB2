import java.util.LinkedList;
import java.util.List;

public class Arvore {

	public static No raiz = null;
	public static int quantNos = 0;

	/**
	 * Percorre a árvore em busca de um elemento inserido como parâmetro.
	 * 
	 * @param chave Elemento a ser buscado na árvore.
	 * @return Caso encontre o elemento, será retornado o acesso a este nó, senão,
	 *         retorna null.
	 */
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

	public static boolean remover(int chave) {
		No atual = raiz;
		No pai = null;
		List<No> elementosFilhos = new LinkedList<>();
		List<String> lado = new LinkedList<>();

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
					for (int i = 0; i < elementosFilhos.size(); i++) {
						if (lado.get(i).equals("esq")) {
							elementosFilhos.get(i).quantNosEsquerda--;
						} else {
							elementosFilhos.get(i).quantNosDireita--;
						}
					}
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
						for (int i = 0; i < elementosFilhos.size(); i++) {
							if (lado.get(i).equals("esq")) {
								elementosFilhos.get(i).quantNosEsquerda--;
							} else {
								elementosFilhos.get(i).quantNosDireita--;
							}
						}
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
						for (int i = 0; i < elementosFilhos.size(); i++) {
							if (lado.get(i).equals("esq")) {
								elementosFilhos.get(i).quantNosEsquerda--;
							} else {
								elementosFilhos.get(i).quantNosDireita--;
							}
						}
						return true;
					}
					// SE O NÓ A SER REMOVIDO POSSUI DUAS SUBÁRVORES
					else if (atual.noEsquerdo != null && atual.noDireito != null) {
						No paiMaiorEsquerdo = atual;
						No maiorEsquerdo = atual.noEsquerdo;

						List<No> elementosFilhos1 = new LinkedList<>();
						List<String> lado1 = new LinkedList<>();

						elementosFilhos1.add(atual);
						lado1.add("esq");

						while (maiorEsquerdo.noDireito != null) {
							elementosFilhos1.add(maiorEsquerdo);
							lado1.add("dir");
							paiMaiorEsquerdo = maiorEsquerdo;
							maiorEsquerdo = maiorEsquerdo.noDireito;
						}

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
						for (int i = 0; i < elementosFilhos1.size(); i++) {
							if (lado1.get(i).equals("esq")) {
								elementosFilhos1.get(i).quantNosEsquerda--;
							} else {
								elementosFilhos1.get(i).quantNosDireita--;
							}
						}
						return true;
					}
				}
			} else if (chave < atual.valor) {
				elementosFilhos.add(atual);
				lado.add("esq");
				if (atual.noEsquerdo != null) {
					pai = atual;
					atual = atual.noEsquerdo;
				} else {
					return false;
				}
			} else {
				elementosFilhos.add(atual);
				lado.add("dir");
				if (atual.noDireito != null) {
					pai = atual;
					atual = atual.noDireito;
				} else {
					return false;
				}
			}
		}
	}

	/**
	 * Retorna a raiz da árvore.
	 */
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

	/**
	 * Utiliza a fórmula (2^altura-1) <= n <= (2^n) - 1. Caso o n satisfaça a
	 * condição, retorna true.
	 * Utiliza a função calcAltura para atribuir aos nós o atributo de altura
	 * respectivo.
	 * 
	 * @param raiz Árvore onde será analisada se é completa.
	 * @return
	 */
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

	/**
	 * Percorre a árvore em busca de inserir o elemento em um local onde não quebre
	 * a organização de ABB.
	 * 
	 * @param no Nó contendo o elemento a ser inserido na árvore.
	 * @return Caso o elemento seja inserido com sucesso, retorna true e uma
	 *         mensagem, senão, retorna false e uma mensagem.
	 */
	public static boolean inserir(No no) {
		if (raiz == null) {
			raiz = no;
			System.out.println(no.valor + " adicionado");
			quantNos++;
			return true;
		}

		No atual = raiz;

		List<No> elementosFilhos = new LinkedList<>();
		List<String> lado = new LinkedList<>();
		while (true) {
			if (no.valor == atual.valor) {
				System.out.println(no.valor + " já está na árvore, não pode ser inserido");

				return false;
			} else if (no.valor < atual.valor) {
				lado.add("esq");
				elementosFilhos.add(atual);
				if (atual.noEsquerdo == null) {
					atual.noEsquerdo = no;
					quantNos++;
					System.out.println(no.valor + " adicionado");
					for (int i = 0; i < elementosFilhos.size(); i++) {
						if (lado.get(i).equals("esq")) {
							elementosFilhos.get(i).quantNosEsquerda++;
						} else {
							elementosFilhos.get(i).quantNosDireita++;
						}
					}
					return true;
				} else {
					atual = atual.noEsquerdo;
				}
			} else {
				lado.add("dir");
				elementosFilhos.add(atual);
				if (atual.noDireito == null) {
					atual.noDireito = no;
					quantNos++;
					System.out.println(no.valor + " adicionado");
					for (int i = 0; i < elementosFilhos.size(); i++) {
						if (lado.get(i).equals("dir")) {
							elementosFilhos.get(i).quantNosDireita++;
						} else {
							elementosFilhos.get(i).quantNosEsquerda++;
						}
					}
					return true;
				} else {
					atual = atual.noDireito;
				}
			}
		}

	}

	public static int enesimoElemento(int enesimo) {
		if (raiz.getQuantTotalFilhos() == 0 || enesimo < 1) {
			System.out.println("Árvore vazia ou digite um n > 0");
			return -1;
		}

		if (raiz.getQuantTotalFilhos() < enesimo) {
			System.out.println("O 'n' excede o número de nós da árvore");
			return -1;
		}

		No atual = raiz;

		while (true) {
			Integer qtdNosEsquerda = atual.quantNosEsquerda;
			if (qtdNosEsquerda + 1 == enesimo) {
				return atual.valor;
			} else if (qtdNosEsquerda + 1 > enesimo) {
				atual = atual.noEsquerdo;
			} else if (qtdNosEsquerda + 1 < enesimo) {
				atual = atual.noDireito;
				// Tira a qtd de nós á esquerda dos já percorridos
				enesimo -= qtdNosEsquerda + 1;
			}
		}
	}

	/**
	 * 
	 * @param elemento
	 */
	public static void posicao(int elemento) {
		if (raiz == null)
			return;

		No atual = raiz;
		int pos = 0;

		while (true) {
			Integer qtdNosEsquerda = atual.quantNosEsquerda;
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

	/**
	 * Obtém a mediana pelo formato inOrder da árvore.
	 * Caso a árvore tenha um valor par de elementos, irá retornar o menor
	 * elemento entre totalNos/2 e (totalNos/2) + 1
	 * 
	 * @return Mediana da árvore pelo formato inOrder.
	 */
	public static int mediana() {
		int nosTotais = raiz.getQuantTotalFilhos();
		if (nosTotais % 2 != 0) {
			return enesimoElemento((nosTotais / 2) + 1);
		} else {
			int ele1 = enesimoElemento(nosTotais / 2);
			int ele2 = enesimoElemento((nosTotais / 2) + 1);
			if (ele1 < ele2) {
				return ele1;
			} else {
				return ele2;
			}
		}
	}

	/**
	 * Percurso preOrdem recursivo.
	 * 
	 * @param raiz Árvore onde irá ocorrer o percurso.
	 */
	public static void preOrdem(No raiz) {
		System.out.print(raiz.valor + "  ");

		if (raiz.noEsquerdo != null) {
			preOrdem(raiz.noEsquerdo);
		}

		if (raiz.noDireito != null) {
			preOrdem(raiz.noDireito);
		}
	}

	public static void preOrdemFilhos(No raiz) {
		System.out.print(
				raiz.valor + "  " + "Esq: " + raiz.quantNosEsquerda + " - Dir: " + raiz.quantNosDireita + "\n");

		if (raiz.noEsquerdo != null) {
			preOrdemFilhos(raiz.noEsquerdo);
		}

		if (raiz.noDireito != null) {
			preOrdemFilhos(raiz.noDireito);
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

		double media = preOrdem(atual, 0) / atual.getQuantTotalFilhos();

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
	 * Recursivamente montando a String de saída com a árvore.
	 * 
	 * @param raiz Árvore a ser impressa
	 * @return String com a presentação de árvore.
	 */
	public static String imprimirParenteses(No raiz) {
		if (raiz.altura > -1) {
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
		}
		// } else if (raiz.noEsquerdo == null && raiz.noDireito != null) {
		// return imprimirParenteses(raiz.noDireito);
		// } else if (raiz.noDireito == null && raiz.noEsquerdo != null) {
		// return imprimirParenteses(raiz.noEsquerdo);
		// }
		return "";
	}
}
