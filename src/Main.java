public class Main {
	public static void preOrdem(No raiz) {
		System.out.println(raiz.valor);

		if (raiz.noEsquerdo != null) {
			preOrdem(raiz.noEsquerdo);
		}

		if (raiz.noDireito != null) {
			preOrdem(raiz.noDireito);
		}
	}

	public static void inOrdem(No raiz) {
		if (raiz.noEsquerdo != null) {
			inOrdem(raiz.noEsquerdo);
		}

		System.out.print(raiz.valor + "  ");

		if (raiz.noDireito != null) {
			inOrdem(raiz.noDireito);
		}
	}

	public static void main(String[] args) {

		No.inserir(new No(50));
		No.inserir(new No(70));
		No.inserir(new No(35));
		No.inserir(new No(40));
		No.inserir(new No(25));
		No.inserir(new No(30));
		No.inserir(new No(65));
		No.inserir(new No(90));
		No.inserir(new No(80));

		/*
		 * Testes árvore cheia.
		 * No.inserir(new No(6));
		 * No.inserir(new No(3));
		 * No.inserir(new No(10));
		 * No.inserir(new No(1));
		 * No.inserir(new No(4));
		 * No.inserir(new No(7));
		 * No.inserir(new No(11));
		 * System.out.println(
		 * "Raiz: " + No.getRaiz() + " " + No.getRaiz().noEsquerdo.valor + " " +
		 * No.getRaiz().noDireito.valor);
		 */

		System.out.println(No.remover(35));
		System.out.println("Altura da raiz: " + No.calcAltura(No.getRaiz()));
		preOrdem(No.getRaiz());
		inOrdem(No.getRaiz());
		System.out.println("\nElemento n: ");
		No.enesimoElemento(No.getRaiz(), 5);
		System.out.println("\nÉ cheia? " + No.eCheia(No.getRaiz()));
	}
}
