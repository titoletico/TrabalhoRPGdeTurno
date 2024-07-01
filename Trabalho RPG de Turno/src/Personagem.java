import java.util.*;

// Classe abstrata para personagens
public abstract class Personagem {
    private String nome;
    private int pontosVida;
    private int forca;
    private int defesa;
    private int experiencia;
    private int nivel;
    private List<Habilidade> habilidades;
    private List<Habilidade> habilidadesAprendidas;
    private EstadoCombate estadoCombate;

    public Personagem(String nome, int pontosVida, int forca, int defesa) {
        this.nome = nome;
        this.pontosVida = pontosVida;
        this.forca = forca;
        this.defesa = defesa;
        this.experiencia = 0;
        this.nivel = 1;
        this.habilidades = new ArrayList<>();
        this.habilidadesAprendidas = new ArrayList<>();
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

    public void ganharExperiencia(int quantidade) {
        experiencia += quantidade;
        verificarNivel();
    }

    private void verificarNivel() {
        int experienciaNecessaria = nivel * 100;
        while (experiencia >= experienciaNecessaria) {
            experiencia -= experienciaNecessaria;
            nivel++;
            aumentarAtributos();
            System.out.println(nome + " subiu para o nível " + nivel + "!");
        }
    }

    private void aumentarAtributos() {
        pontosVida += 20;
        forca += 5;
        defesa += 5;
    }

    public String getNome() {
        return nome;
    }

    public int getPontosVida() {
        return pontosVida;
    }

    public List<Habilidade> getHabilidadesAprendidas() {
        return habilidadesAprendidas;
    }

    public EstadoCombate getEstadoCombate() {
        return estadoCombate;
    }

    public int getForca() {
        return forca;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public int getNivel() {
        return nivel;
    }

    public abstract int atacar();

    public List<Habilidade> getHabilidades() {
        return habilidades;
    }
}

// Classes derivadas para personagens específicos
class Terra extends Personagem {
    public Terra() {
        super("Terra", 300, 40, 35);
        aprenderHabilidade(new HabilidadeComum("Magia de Terra", "Mágico", 50));
    }

    @Override
    public int atacar() {
        return getForca();
    }
}

class Edgar extends Personagem {
    public Edgar() {
        super("Edgar", 280, 38, 30);
        aprenderHabilidade(new HabilidadeComum("Ferramenta: Serra", "Físico", 45));
    }

    @Override
    public int atacar() {
        return getForca();
    }
}

class Celes extends Personagem {
    public Celes() {
        super("Celes", 270, 36, 28);
        aprenderHabilidade(new HabilidadeComum("Magia de Gelo", "Mágico", 45));
    }

    @Override
    public int atacar() {
        return getForca();
    }
}

class Locke extends Personagem {
    public Locke() {
        super("Locke", 260, 34, 25);
        aprenderHabilidade(new HabilidadeComum("Roubo", "Físico", 40));
    }

    @Override
    public int atacar() {
        return getForca();
    }
}

class Sabin extends Personagem {
    public Sabin() {
        super("Sabin", 320, 42, 38);
        aprenderHabilidade(new HabilidadeComum("Blitz: Soco Supremo", "Físico", 55));
    }

    @Override
    public int atacar() {
        return getForca();
    }
}