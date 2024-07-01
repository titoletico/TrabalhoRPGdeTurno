import java.util.*;

// Classe para gerenciar a batalha
public class Batalha {
    private List<Personagem> personagens;
    private List<Inimigo> inimigos;
    private ItemManager itemManager;

    public Batalha(List<Personagem> personagens, List<Inimigo> inimigos) {
        this.personagens = personagens;
        this.inimigos = inimigos;
        this.itemManager = new ItemManager();
    }

    public void iniciarBatalha() {
        while (!personagens.isEmpty() && !inimigos.isEmpty()) {
            Personagem personagem = personagens.get(0);
            Inimigo inimigo = inimigos.get(0);

            System.out.println("----- Novo turno -----");
            System.out.println(personagem.getNome() + " vs " + inimigo.getNome());

            realizarTurno(personagem, inimigo);
            if (inimigo.getPontosVida() <= 0) {
                inimigos.remove(inimigo);
                personagem.ganharExperiencia(50); // Ganhar experiência ao derrotar inimigos
                itemManager.sortearItemLendario();
                itemManager.sortearItemComum();
                continue;
            }

            realizarTurno(inimigo, personagem);
            if (personagem.getPontosVida() <= 0) {
                personagens.remove(personagem);
                continue;
            }
        }

        if (personagens.isEmpty()) {
            System.out.println("Todos os personagens foram derrotados. Fim da batalha.");
        } else {
            System.out.println("Todos os inimigos foram derrotados. Fim da batalha.");
        }

        verificarHabilidadesAprendidas();
        mostrarExperienciaGanha();
    }

    private void realizarTurno(Personagem atacante, Inimigo alvo) {
        aplicarEstadoCombate();
        Random random = new Random();
        if (random.nextInt(100) < 30 && !atacante.getHabilidades().isEmpty()) {
            // 30% de chance de usar uma habilidade
            Habilidade habilidade = atacante.getHabilidades().get(random.nextInt(atacante.getHabilidades().size()));
            int dano = habilidade.getDano();
            System.out.println(atacante.getNome() + " usa " + habilidade.getNome() + " causando " + dano + " de dano.");
            alvo.receberDano(dano);
        } else {
            int dano = atacante.calcularDano(atacante.getForca());
            alvo.receberDano(dano);
        }
    }

    private void realizarTurno(Inimigo atacante, Personagem alvo) {
        aplicarEstadoCombate();
        Random random = new Random();
        if (random.nextInt(100) < 30 && !atacante.getHabilidades().isEmpty()) {
            // 30% de chance de usar uma habilidade
            Habilidade habilidade = atacante.getHabilidades().get(random.nextInt(atacante.getHabilidades().size()));
            int dano = habilidade.getDano();
            System.out.println(atacante.getNome() + " usa " + habilidade.getNome() + " causando " + dano + " de dano.");
            alvo.receberDano(dano);
        } else {
            int dano = atacante.calcularDano(atacante.getForca());
            alvo.receberDano(dano);
        }
    }

    private void aplicarEstadoCombate() {
        Random random = new Random();
        for (Personagem personagem : personagens) {
            int chance = random.nextInt(100);
            if (chance < 20) {
                EstadoCombate estado = EstadoCombate.values()[random.nextInt(EstadoCombate.values().length)];
                personagem.aplicarEstadoCombate(estado);
                System.out.println(personagem.getNome() + " está " + estado.toString().toLowerCase());
            }
        }
        for (Inimigo inimigo : inimigos) {
            int chance = random.nextInt(100);
            if (chance < 20) {
                EstadoCombate estado = EstadoCombate.values()[random.nextInt(EstadoCombate.values().length)];
                inimigo.aplicarEstadoCombate(estado);
                System.out.println(inimigo.getNome() + " está " + estado.toString().toLowerCase());
            }
        }
    }

    private void verificarHabilidadesAprendidas() {
        for (Personagem personagem : personagens) {
            List<Habilidade> habilidadesAprendidas = personagem.getHabilidadesAprendidas();
            if (!habilidadesAprendidas.isEmpty()) {
                System.out.println(personagem.getNome() + " aprendeu as seguintes habilidades:");
                for (Habilidade habilidade : habilidadesAprendidas) {
                    System.out.println("- " + habilidade.getNome());
                }
            }
        }
    }

    private void mostrarExperienciaGanha() {
        for (Personagem personagem : personagens) {
            System.out.println(personagem.getNome() + " ganhou " + personagem.getExperiencia() + " pontos de experiência e está no nível " + personagem.getNivel() + ".");
        }
    }
}