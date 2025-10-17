package packages.exercicios;

import packages.classe.CriaHash;
import packages.hash.IntKeyExtractor;
import packages.hash.MultiplicativeHashFunction;

public class Ex7 {

    private static final IntKeyExtractor<Integer> IDENTITY_EXTRACTOR = (Integer value) -> value;

    public static void main(String[] args) {
        System.out.println("===== Ex 7: Arqueiro Verde (Hash Multiplicativo) =====");

        MultiplicativeHashFunction hashMultiplicativo = new MultiplicativeHashFunction();
        CriaHash<Integer> tabelaFlechas = new CriaHash<>(IDENTITY_EXTRACTOR, hashMultiplicativo, 16);

        int[] chaves = {265, 301, 742, 10, 20};

        for (int chave : chaves) {
            int hashBase = hashMultiplicativo.hash(chave, 16);
            int index = tabelaFlechas.inserirEnderecoAberto(chave);

            System.out.printf("Chave: %d | Hash Multiplicativo (base 16): %d | Índice Final: %d\n",
                    chave, hashBase, index);
        }

        System.out.println("\nColisões (Sondagens) totais: " + tabelaFlechas.getCollisions());
        System.out.println("\n" + tabelaFlechas.tableContent());
    }
}