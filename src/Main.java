import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void teste(No raiz) {
		if (raiz.noEsquerdo != null) {
			teste(raiz.noEsquerdo);
		}

		System.out.println(raiz.valor);

		if (raiz.noDireito != null) {
			teste(raiz.noDireito);
		}
	}

	public static void arvoreEntrada(String arquivo) throws FileNotFoundException {
		File arvoreEntrada = new File(arquivo);
		Scanner sc = new Scanner(arvoreEntrada);
		sc.useDelimiter(" ");
		while (sc.hasNext()) {
			int linha = Integer.parseInt(sc.next());
			Arvore.inserir(new No(linha));
		}
		sc.close();
	}

	public static void comandos(String arquivo) throws IOException {
		File comandos = new File(arquivo);
		FileReader leitura = new FileReader(comandos);
		BufferedReader bufferedReader = new BufferedReader(leitura);
		String linha = "";

		while ((linha = bufferedReader.readLine()) != null) {
			String[] entrada = new String[2];
			entrada[0] = linha;
			if (linha.contains(" ")) {
				entrada = linha.split(" ");
			}
			switch (entrada[0]) {
				case "CHEIA":
					if (Arvore.eCheia(Arvore.getRaiz())) {
						System.out.println("A árvore é cheia");
					} else {
						System.out.println("A árvore não é cheia");
					}
					break;

				case "COMPLETA":
					if (Arvore.eCompleta(Arvore.getRaiz())) {
						System.out.println("A árvore é completa");
					} else {
						System.out.println("A árvore não é completa");
					}
					break;
				case "ENESIMO":
					System.out.println(Arvore.enesimoElemento(Integer.parseInt(entrada[1])));
					break;
				case "INSIRA":
					Arvore.inserir(new No(Integer.parseInt(entrada[1])));
					break;
				case "PREORDEM":
					Arvore.preOrdem(Arvore.getRaiz());
					System.out.println("");
					break;
				case "IMPRIMA":
					if (entrada[1].equals("1")) {
						Arvore.imprimirBarras(Arvore.getRaiz(), 20, 0);
					} else if (entrada[1].equals("2")) {
						System.out.println(Arvore.imprimirParenteses(Arvore.getRaiz()));
					}
					break;
				case "REMOVA":
					if (Arvore.remover(Integer.parseInt(entrada[1]))) {
						System.out.println(entrada[1] + " removido");
					} else {
						System.out.println(entrada[1] + " não está na árvore, não pode ser removido");
					}
					break;
				case "POSICAO":
					Arvore.posicao(Integer.parseInt(entrada[1]));
					break;
				case "MEDIANA":
					System.out.println(Arvore.mediana());
					break;
				case "MEDIA":
					System.out.println(Arvore.media(Arvore.getRaiz().valor));
					break;
				case "BUSCAR":
					if (Arvore.busca(Integer.parseInt(entrada[1])) == null) {
						System.out.println("Chave não encontrada");
					}
					break;
				default:
					break;
			}
		}
		bufferedReader.close();
	}

	public static void main(String[] args) throws IOException {
		System.out.println("INSERINDO ELEMENTOS DO ARQUIVO NA ÁRVORE INICIAL");
		arvoreEntrada(args[0]);

		System.out.println("\nRODANDO ARQUIVO CONTENDO OS COMANDOS...");
		comandos(args[1]);
	}
}
