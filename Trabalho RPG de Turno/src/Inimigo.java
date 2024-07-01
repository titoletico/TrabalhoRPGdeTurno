import java.util.*;

// Classe abstrata para inimigos
public abstract class Inimigo {
    private String nome;
    private int pontosVida;
    private int forca;
    private int defesa;
    private List<Habilidade> habilidades;
    private EstadoCombate estadoCombate;

    public Inimigo(String nome, int pontosVida, int forca, int defesa) {
        this.nome = nome;
        this.pontosVida = pontosVida;
        this.forca = forca;
        this.defesa = defesa;
        this.habilidades = new ArrayList<>();
        this.estadoCombate = EstadoCombate.NORMAL;
    }

    public void aprenderHabilidade(Habilidade habilidade) {
        habilidades.add(habilidade);
    }

    public void receberDano(int dano) {
        int danoRecebido = Math.max(dano - defesa, 0);
        pontosVida -= danoRecebido;
        System.out.println(nome + " recebeu " + danoRecebido + " de dano.");
        if (pontosVida <= 0) {
            System.out.println(nome + " foi derrotado!");
        }
    }

    public int calcularDano(int danoBase) {
        Random random = new Random();
        boolean critico = random.nextInt(100) < 20; // 20% de chance de golpe crítico
        int dano = critico ? (int) (danoBase * 1.5) : danoBase; // Golpe crítico dá 50% a mais de dano
        System.out.println(nome + " ataca com " + dano + " de dano" + (critico ? " (Crítico!)" : "") + ".");
        return dano;
    }

    public void aplicarEstadoCombate(EstadoCombate estado) {
        this.estadoCombate = estado;
    }

    public void removerEstadoCombate() {
        this.estadoCombate = EstadoCombate.NORMAL;
    }

    public String getNome() {
        return nome;
    }

    public int getPontosVida() {
        return pontosVida;
    }

    public EstadoCombate getEstadoCombate() {
        return estadoCombate;
    }

    public int getForca() {
        return forca;
    }

    public abstract int atacar();

    public List<Habilidade> getHabilidades() {
        return habilidades;
    }
}

// Classes derivadas para inimigos específicos
class DragaoPreto extends Inimigo {
    public DragaoPreto() {
        super("Dragao Preto", 400, 50, 40);
        aprenderHabilidade(new HabilidadeComum("Chama Negra", "Fogo", 60));
    }

    @Override
    public int atacar() {
        return getForca();
    }
}

class Vampiro extends Inimigo {
    public Vampiro() {
        super("Vampiro", 350, 45, 35);
        aprenderHabilidade(new HabilidadeComum("Mordida", "Físico", 50));
    }

    @Override
    public int atacar() {
        return getForca();
    }
}

class Cacto extends Inimigo {
    public Cacto() {
        super("Cacto", 300, 40, 30);
        aprenderHabilidade(new HabilidadeComum("Espinhos", "Físico", 45));
    }

    @Override
    public int atacar() {
        return getForca();
    }
}

class LagartoGuerreiro extends Inimigo {
    public LagartoGuerreiro() {
        super("Lagarto Guerreiro", 320, 42, 38);
        aprenderHabilidade(new HabilidadeComum("Garra Afiada", "Físico", 33));
    }

    @Override
    public int atacar() {
        return getForca();
    }
}

class Bomba extends Inimigo {
    public Bomba() {
        super("Bomba", 150, 25, 20);
        aprenderHabilidade(new HabilidadeComum("Explosão", "Fogo", 50));
    }

    @Override
    public int atacar() {
        return getForca();
    }
}