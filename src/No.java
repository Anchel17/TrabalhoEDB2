import java.util.List;
import java.util.ArrayList;

public class No{
	public int valor;
	public No noEsquerdo;
	public No noDireito;
	
	No(int valor){
		this.valor = valor;
		this.noEsquerdo = null;
		this.noDireito = null;
	}
	
	public No busca(int chave, No raiz){
		System.out.println(raiz.valor);
		if(chave == raiz.valor){
			return raiz;
		}
		else{
			if(chave < raiz.valor){
				if(raiz.noEsquerdo != null){
					raiz = raiz.noEsquerdo;
				}
				else{
					if(raiz.noDireito == null){
						return null;
					}
					if(chave < raiz.noDireito.valor){
						return null;
					}
				}
			}
			else{
				if(raiz.noDireito != null){
					raiz = raiz.noDireito;
				}
				else{
					if(raiz.noEsquerdo == null){
						return null;
					}
					else if(chave > raiz.noEsquerdo.valor){
						return null;
					}
				}
			}
		}
		
		return busca(chave, raiz);
	}
	
	public boolean inserir(No no, No raiz){
		if(no.valor == raiz.valor){
			return false;
		}
		
		if(no.valor < raiz.valor){
			if(raiz.noEsquerdo == null){
				raiz.noEsquerdo = no;
				return true;
			}
			else{
				raiz = raiz.noEsquerdo;
			}
		}
		else if(no.valor > raiz.valor){
			if(raiz.noDireito == null){
				raiz.noDireito = no;
				return true;
			}
			else{
				raiz = raiz.noDireito;
			}
		}
		
		return inserir(no, raiz);
	}
	
	public boolean remover(No no, No raiz){	
		No atual = raiz;
		No pai = null;
		
		while(true){
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
							menorDireita.noDireito = atual.noDireito;
							atual = menorDireita;
							paiMenorDireita.noEsquerdo = null;
							pai.noEsquerdo = atual;
							return true;
						}
						else{
							/*MAIOR DA SUBÁRVORE À ESQUERDA SEMIPRONTO/PRONTO*/
							maiorEsquerda.noDireito = atual.noDireito;
							if(paiMaiorEsquerda != null && paiMaiorEsquerda.valor != atual.valor){
								maiorEsquerda.noEsquerdo = atual.noEsquerdo;
							}
							atual = maiorEsquerda;
							paiMaiorEsquerda.noDireito = null;
							pai.noDireito = atual;
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
	
	@Override
	public String toString(){
		return "" + valor;
	}
}
