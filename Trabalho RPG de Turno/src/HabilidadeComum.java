// Classe concreta para habilidades comuns
public class HabilidadeComum implements Habilidade {
    private String nome;
    private String tipo;
    private int dano;

    public HabilidadeComum(String nome, String tipo, int dano) {
        this.nome = nome;
        this.tipo = tipo;
        this.dano = dano;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getTipo() {
        return tipo;
    }

    @Override
    public int getDano() {
        return dano;
    }
}





