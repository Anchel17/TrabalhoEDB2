public class No {
    public int valor;
    public No noEsquerdo;
    public No noDireito;
    public int altura = 0;

    No(int valor) {
        this.valor = valor;
        this.noEsquerdo = null;
        this.noDireito = null;
    }
}
