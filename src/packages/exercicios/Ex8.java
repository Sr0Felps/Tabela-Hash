package packages.exercicios;

import packages.classe.CriaHash;
import packages.hash.IntKeyExtractor;
import packages.hash.FoldingHashFunction;

public class Ex8 {

    private static final IntKeyExtractor<Integer> IDENTITY_EXTRACTOR = (Integer value) -> value;

    public static void main(String[] args) {
        System.out.println("===== Ex 8: Aquaman (Hash por Dobramento) =====");

        FoldingHashFunction hashDobramento = new FoldingHashFunction(2);
        CriaHash<Integer> tabelaAtlantes = new CriaHash<>(IDENTITY_EXTRACTOR, hashDobramento, 10);

        int[] chaves = {
                123456,
                654321,
                987650,
                100021
        };

        for (int chave : chaves) {
            int hashBase = hashDobramento.hash(chave, 10);
            int index = tabelaAtlantes.inserirEnderecoAberto(chave);

            System.out.printf("Chave: %d | Hash Dobramento (base 10): %d | Índice Final: %d\n",
                    chave, hashBase, index);
        }

        System.out.println("\nColisões (Sondagens) totais: " + tabelaAtlantes.getCollisions());
        System.out.println("\n" + tabelaAtlantes.tableContent());
    }
}