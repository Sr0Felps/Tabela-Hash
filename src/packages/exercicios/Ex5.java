package packages.exercicios;

import packages.classe.CriaHash;
import packages.hash.IntKeyExtractor;

public class Ex5 {

    private static final IntKeyExtractor<Integer> IDENTITY_EXTRACTOR = (Integer value) -> value;

    public static void main(String[] args) {
        System.out.println("===== Ex 5: Homem de Ferro (Inserção Sem Colisão) =====");

        // Tabela Hash de tamanho 10
        CriaHash<Integer> armaduras = new CriaHash<>(IDENTITY_EXTRACTOR, 10);

        // Chaves que colidem no índice 0 (10, 20), índice 1 (1, 11), índice 5 (5, 25)
        int[] serialNumbers = {
                10, // Indice 0: SUCESSO
                20, // Indice 0: FALHA (colide com 10)
                1,  // Indice 1: SUCESSO
                11, // Indice 1: FALHA (colide com 1)
                5,  // Indice 5: SUCESSO
                25, // Indice 5: FALHA (colide com 5)
        };

        for (int sn : serialNumbers) {
            int indexBase = sn % armaduras.capacity();
            // inserirSemColisao retorna false se o índice base já estiver ocupado por uma chave diferente
            boolean sucesso = armaduras.inserirSemColisao(sn);

            if (sucesso) {
                System.out.printf("SN %d inserido com sucesso. Índice base: %d\n", sn, indexBase);
            } else {
                System.err.printf("SN %d FALHOU. Colisão no índice base: %d (slot já ocupado por outra chave).\n", sn, indexBase);
            }
        }

        System.out.println("\nConteúdo Final da Tabela (Apenas os primeiros de cada índice devem estar presentes):");
        System.out.println(armaduras.tableContent());
    }
}