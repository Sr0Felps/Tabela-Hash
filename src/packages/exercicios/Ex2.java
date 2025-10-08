package packages.exercicios;

import packages.classe.CriaHash;
import packages.hash.IntKeyExtractor;

public class Ex2 {

    private static final IntKeyExtractor<Integer> IDENTITY_EXTRACTOR = (Integer value) -> value;

    public static void main(String[] args) {
        System.out.println("===== Ex 2: Batman (Códigos de Heróis e Colisões) =====");

        // Tabela Hash de tamanho 10
        CriaHash<Integer> batcaverna = new CriaHash<>(IDENTITY_EXTRACTOR, 10);

        int[] codigos = {7, 12, 22, 32};

        for (int codigo : codigos) {
            int hash = codigo % 10;
            int index = batcaverna.inserirEnderecoAberto(codigo);

            System.out.printf("Código %d. Hash: %d. Inserido no índice: %d\n", codigo, hash, index);

            if (hash != index) {
                System.out.printf("  -> COLISÃO DETECTADA: Foi usada sondagem linear.\n");
            }
        }

        System.out.println("\nColisões (Sondagens) totais: " + batcaverna.getCollisions());
        System.out.println("\n" + batcaverna.tableContent());
    }
}