import java.util.*;

// Classe para gerenciar itens
public class ItemManager {
    private List<String> itensLendarios;
    private List<String> itensComuns;

    public ItemManager() {
        itensLendarios = Arrays.asList("Espada Lendária", "Escudo Lendário", "Poção Lendária");
        itensComuns = Arrays.asList("Poção de Cura", "Poção de Mana", "Antídoto");
    }

    public void sortearItemLendario() {
        Random random = new Random();
        int index = random.nextInt(itensLendarios.size());
        String item = itensLendarios.get(index);
        System.out.println("Você encontrou um item lendário: " + item);
    }

    public void sortearItemComum() {
        Random random = new Random();
        int index = random.nextInt(itensComuns.size());
        String item = itensComuns.get(index);
        System.out.println("Você encontrou um item comum: " + item);
    }
}