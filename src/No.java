import java.util.List;
import java.util.ArrayList;

public class No{
	public int valor;
	public No noEsquerdo;
	public No noDireito;
	public 	static No raiz = null;
	
	No(int valor){
		this.valor = valor;
		this.noEsquerdo = null;
		this.noDireito = null;
	}
	
	public static No busca(int chave){
		if(raiz == null){
			return null;
		}
		
		No atual = raiz;
		
		while(true){
			System.out.println(atual);
			if(chave == atual.valor){
				return atual;
			}
			else if(chave < atual.valor){
				if(atual.noEsquerdo != null){
					atual = atual.noEsquerdo;					
				}
				else{
					if(atual.noDireito == null){
						return null;
					}
					else if(chave < atual.noDireito.valor){
						return null;
					}
				}
			}
			else{
				if(atual.noDireito != null){
					atual = atual.noDireito;
				}
				else{
					if(atual.noEsquerdo == null){
						return null;
					}
					else if(chave > atual.noEsquerdo.valor){
						return null;
					}
				}
			}
		}
	}
	
	public static boolean inserir(No no){
		if(raiz == null){
			raiz = no;
			return true;
		}
		
		No atual = raiz;
		while(true){
			if(no.valor == atual.valor){
				return false;
			}
			else if(no.valor < atual.valor){
				if(atual.noEsquerdo == null){
					atual.noEsquerdo = no;
					return true;
				}
				else{
					atual = atual.noEsquerdo;
				}
			}
			else{
				if(atual.noDireito == null){
					atual.noDireito = no;
					return true;
				}
				else{
					atual = atual.noDireito;
				}
			}
		}
	}
	
	public static boolean remover(No no){	
		No atual = raiz;
		No pai = null;
		
		while(true){
			if(raiz == null){
				return false;
			}
			
			if(no.valor == atual.valor){
				//remoção de um nó folha
				if(atual.noEsquerdo == null && atual.noDireito == null){
					if(atual.valor < pai.valor){
						pai.noEsquerdo = null;
					}
					else if(atual.valor > pai.valor){
						pai.noDireito = null;
					}
					atual = null;
					return true;
				}
				else{
					//SE O NÓ A SER DELETADO TEM SUBARVORE À ESQUERDA OU À DIREITA
					if(atual.noEsquerdo != null && atual.noDireito == null){
						pai.noEsquerdo = atual.noEsquerdo;
						atual = null;
						return true;
					}
					else if(atual.noDireito != null && atual.noEsquerdo == null){
						if(atual.valor < pai.valor){
							pai.noEsquerdo = atual.noDireito;
						}
						else{
							pai.noDireito = atual.noDireito;
						}
						atual = null;
						return true;
					}
					//SE O NÓ A SER REMOVIDO POSSUI DUAS SUBÁRVORES
					else if(atual.noEsquerdo != null && atual.noDireito != null){
						No maiorEsquerda = atual.noEsquerdo;
						No paiMaiorEsquerda = atual;
						
						No menorDireita = atual.noDireito;
						No paiMenorDireita = atual;
						
						//maior nó à esquerda
						while(maiorEsquerda.noDireito != null){
							paiMaiorEsquerda = maiorEsquerda;
							maiorEsquerda = maiorEsquerda.noDireito;
						}
						
						//menor nó à direita
						while(menorDireita.noEsquerdo != null){
							paiMenorDireita = menorDireita;
							menorDireita = menorDireita.noEsquerdo;
						}
						
						//vê qual dos dois está mais próximo do valor a ser removido
						if(menorDireita.valor - atual.valor < atual.valor - maiorEsquerda.valor){
							/*AINDA FALTA FAZER ESSE DA SUBARVORE À DIREITA*/
							menorDireita.noEsquerdo = atual.noEsquerdo;
							if(paiMenorDireita != null && paiMenorDireita.valor != atual.valor){
								menorDireita.noDireito = atual.noDireito;	
							}						
							atual = menorDireita;
							paiMenorDireita.noEsquerdo = null;
							
							if(pai != null){
								pai.noEsquerdo = atual;
							}
							else{
								raiz = atual;
							}
							
							return true;
						}
						else{
							/*MAIOR DA SUBÁRVORE À ESQUERDA SEMIPRONTO/PRONTO*/
							maiorEsquerda.noDireito = atual.noDireito;
							//if(paiMaiorEsquerda != null && paiMaiorEsquerda.valor != atual.valor){
								maiorEsquerda.noEsquerdo = atual.noEsquerdo;
							//}
							atual = maiorEsquerda;
							paiMaiorEsquerda.noDireito = null;
							
							if(pai != null){
								pai.noDireito = atual;
							}
							else{
								raiz = atual;
							}
							return true;
						}
					}
				}
			}
			else if(no.valor < atual.valor){
				if(atual.noEsquerdo != null){
					pai = atual;
					atual = atual.noEsquerdo;
				}
			}
			else{
				if(atual.noDireito != null){
					pai = atual;
					atual = atual.noDireito;
				}
			}
		}
		
	}
	
	public static No getRaiz(){
		return raiz;
	}
	
	@Override
	public String toString(){
		return "" + valor;
	}
}
