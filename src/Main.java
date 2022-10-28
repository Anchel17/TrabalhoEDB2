public class Main {
	public static void preOrdem(No raiz){
		System.out.println(raiz.valor);
		
		if(raiz.noEsquerdo != null){
			preOrdem(raiz.noEsquerdo);
		}

		if(raiz.noDireito != null){
			preOrdem(raiz.noDireito);
		}
	}
	
	public static void main(String[] args) {
		No raiz = new No(20);
		
		No.inserir(raiz);
		
		No no10 = new No(10);
		//raiz.noEsquerdo = no10;
		No.inserir(no10);
		
		No no15 = new No(15);
		//no10.noDireito = no15;
		No.inserir(no15);
		
		No no40 = new No(40);
		//raiz.noDireito = no40;
		No.inserir(no40);
		
		No no30 = new No(30);
		//no40.noEsquerdo = no30;
		No.inserir(no30);
		
		No no50 = new No(50);
		//no40.noDireito = no50;
		No.inserir(no50);
		
		No no60 = new No(60);
		//no50.noDireito = no60;
		No.inserir(no60);

		//No.inserir(new No(29));
		//No.inserir(new No(22));
		No.inserir(new No(19));
		No.inserir(new No(18));
		No.remover(raiz);
		
		//No.inserir(new No(31));
		//No.inserir(new No(32));
		//System.out.println(raiz.busca(15, raiz));
		//System.out.println(raiz.remover(no40));
		//System.out.println(No.busca(60));
		preOrdem(No.getRaiz());
	}
}
