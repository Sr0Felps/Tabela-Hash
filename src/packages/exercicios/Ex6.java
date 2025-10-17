package packages.exercicios;

import packages.classe.CriaHashEncadeamento;
import packages.hash.IntKeyExtractor;
import packages.model.Pessoa;

public class Ex6 {

    private static final IntKeyExtractor<Pessoa> PESSOA_KEY_EXTRACTOR = Pessoa::getId;

    public static void main(String[] args) {
        System.out.println("===== Ex 6: Cyborg (Encadeamento Separado) =====");

        CriaHashEncadeamento<Pessoa> tabelaCyborg = new CriaHashEncadeamento<>(PESSOA_KEY_EXTRACTOR, 5);

        Pessoa[] pessoas = {
                new Pessoa(10, "Victor Stone", "Metrópolis"),
                new Pessoa(15, "Silas Stone", "Metrópolis"),
                new Pessoa(2, "Sarah Charles", "Star Labs"),
                new Pessoa(7, "Jinx", "Torre Titan"),
                new Pessoa(4, "Gizmo", "Hive")
        };

        for (Pessoa p : pessoas) {
            tabelaCyborg.inserir(p);
            System.out.printf("Pessoa ID %d. Hash: %d. Inserido.\n", p.getId(), p.getId() % 5);
        }

        System.out.println("\n" + tabelaCyborg.tableContent());
    }
}