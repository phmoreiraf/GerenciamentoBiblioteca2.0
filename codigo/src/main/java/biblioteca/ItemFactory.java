package biblioteca;

// Classe ItemFactory
public class ItemFactory {
    public Item criarItem(int tipo, String titulo, String autor, int ano) {
        switch (tipo) {
            case 1:
                return new Livro(titulo, autor, ano);
            case 2:
                return new Tese(titulo, autor, ano);
            case 3:
                return new Revista(titulo, autor, ano);
            case 4:
                return new CD(titulo, autor, ano);
            case 5:
                return new DVD(titulo, autor, ano);
            default:
                return null;
        }
    }
}