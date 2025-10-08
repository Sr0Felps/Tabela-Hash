package packages.exercicios;

import packages.classe.CriaHash;
import packages.hash.IntKeyExtractor;

import java.util.Random;

public class Ex3 {

    private static final IntKeyExtractor<Integer> IDENTITY_EXTRACTOR = (Integer value) -> value;

    public static void main(String[] args) {
        System.out.println("===== Ex 3: Flash (Contagem de Colisões) =====");

        // Tabela Hash de tamanho 11
        CriaHash<Integer> tabelaFlash = new CriaHash<>(IDENTITY_EXTRACTOR, 11);
        Random random = new Random();

        int totalNumeros = 20;
        System.out.printf("Inserindo %d números aleatórios na tabela de tamanho 11...\n", totalNumeros);

        for (int i = 0; i < totalNumeros; i++) {
            int numero = random.nextInt(1000);
            tabelaFlash.inserirEnderecoAberto(numero);
        }

        System.out.println("\nResultado:");
        System.out.println("Tamanho da Tabela (elementos únicos): " + tabelaFlash.size());
        System.out.println("Capacidade da Tabela: " + tabelaFlash.capacity());
        // getCollisions() usa a lógica implementada em CriaHash
        System.out.println("Total de Colisões Ocorridas (Sondagens): " + tabelaFlash.getCollisions());
    }
}