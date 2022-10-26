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
					/*
					 * tem que rever, tem algo errado, Isso dá certo pra remover o
					 * nó 50, mas no 10 ele falha
					*/
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
						System.out.println("Entrou");
						return true;
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
