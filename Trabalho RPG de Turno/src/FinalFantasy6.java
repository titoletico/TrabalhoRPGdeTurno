import java.util.*;

// Classe principal para iniciar o jogo
public class FinalFantasy6 {
    public static void main(String[] args) {
        List<Personagem> personagens = new ArrayList<>();
        personagens.add(new Terra());
        personagens.add(new Edgar());
        personagens.add(new Celes());
        personagens.add(new Locke());
        personagens.add(new Sabin());

        List<Inimigo> inimigos = new ArrayList<>();
        inimigos.add(new DragaoPreto());
        inimigos.add(new Vampiro());
        inimigos.add(new Cacto());
        inimigos.add(new LagartoGuerreiro());
        inimigos.add(new Bomba());

        Batalha batalha = new Batalha(personagens, inimigos);
        batalha.iniciarBatalha();
    }
}