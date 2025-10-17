package packages.exercicios;

import packages.classe.CriaHash;
import packages.hash.IntKeyExtractor;
import packages.hash.FoldingHashFunction;
import packages.model.Pessoa;

public class Ex9 {

    private static final IntKeyExtractor<Pessoa> PESSOA_KEY_EXTRACTOR = Pessoa::getId;

    public static void main(String[] args) {
        System.out.println("===== Ex 9: Lanterna Verde (Pessoa e Hash por Dobramento) =====");

        FoldingHashFunction hashDobramento = new FoldingHashFunction(3);
        CriaHash<Pessoa> tabelaLanternas = new CriaHash<>(PESSOA_KEY_EXTRACTOR, hashDobramento, 100);

        Pessoa[] lanternas = {
                new Pessoa(10500, "Hal Jordan", "Setor 2814"),
                new Pessoa(99801, "Sinestro", "Setor 1417"),
                new Pessoa(20010, "John Stewart", "Setor 2814"),
                new Pessoa(10400, "Guy Gardner", "Setor 2814")
        };

        for (Pessoa p : lanternas) {
            int id = p.getId();
            int hash = hashDobramento.hash(id, 100);
            int index = tabelaLanternas.inserirEnderecoAberto(p);

            System.out.printf("Pessoa ID %d. Hash Base: %d. Armazenada na posição: %d\n",
                    id, hash, index);
        }

        System.out.println("\nColisões (Sondagens) totais: " + tabelaLanternas.getCollisions());
        System.out.println("\n" + tabelaLanternas.tableContent());
    }
}