public class Main {
	public static void main(String[] args) {
		No raiz = new No(20);
		
		No no10 = new No(10);
		raiz.noEsquerdo = no10;
		
		No no15 = new No(15);
		no10.noDireito = no15;
		
		No no40 = new No(40);
		raiz.noDireito = no40;
		
		No no30 = new No(30);
		no40.noEsquerdo = no30;
		
		No no50 = new No(50);
		no40.noDireito = no50;
		
		No no60 = new No(60);
		no50.noDireito = no60;
		
		/*
		No no14 = new No(14);
		No no9 = new No(9);
		No no31 = new No(31);
		
		System.out.println(raiz.inserir(no14, raiz));
		System.out.println(raiz.inserir(no9, raiz));
		System.out.println(raiz.inserir(no31, raiz));
		System.out.println(raiz.busca(31, raiz));
		*/
		System.out.println(raiz.busca(no10.valor, raiz));
		System.out.println(raiz.remover(no10, raiz));
		System.out.println(raiz.busca(no10.valor, raiz));
	}
}
