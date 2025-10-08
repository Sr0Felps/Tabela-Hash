package packages.model;

public class Peca {
    private final int id;
    private final int peso;

    public Peca(int id, int peso) {
        this.id = id;
        this.peso = peso;
    }

    public int getId() {
        return id;
    }

    // Método opcional, mas útil para o extrator
    public int getPeso() {
        return peso;
    }

    @Override
    public String toString() {
        return "Peca{ID=" + id + ", Peso=" + peso + "}";
    }
}