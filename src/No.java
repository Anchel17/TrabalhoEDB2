public class No {
    public int valor;
    public No noEsquerdo;
    public No noDireito;
    public int altura = 0;
    public Integer quantNosEsquerda = 0;
    public Integer quantNosDireita = 0;

    public Integer getQuantTotalFilhos() {
        return quantNosDireita + quantNosEsquerda + 1;
    }

    No(int valor) {
        this.valor = valor;
        this.noEsquerdo = null;
        this.noDireito = null;
    }
}
