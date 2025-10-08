package packages.exercicios;

import packages.classe.CriaHash;
import packages.hash.AsciiSumKeyExtractor; // Usa o extrator AsciiSumKeyExtractor
import packages.hash.ModHashFunction;

public class Ex4 {

    public static void main(String[] args) {
        System.out.println("===== Ex 4: Mulher-Maravilha (Hash ASCII Sum) =====");

        AsciiSumKeyExtractor stringExtractor = new AsciiSumKeyExtractor();
        // Tabela Hash de tamanho 7
        CriaHash<String> tabelaInimigos = new CriaHash<>(stringExtractor, new ModHashFunction(), 7);

        String[] inimigos = {"Ares", "Cheetah", "Circe"};

        for (String nome : inimigos) {
            int asciiSum = stringExtractor.getKey(nome);
            int hash = asciiSum % tabelaInimigos.capacity();
            int index = tabelaInimigos.inserirEnderecoAberto(nome);

            System.out.printf("Nome: %-8s | Chave ASCII: %4d | Hash (%d): %d | Índice Final: %d\n",
                    nome, asciiSum, asciiSum, hash, index);
        }

        System.out.println("\n" + tabelaInimigos.tableContent());
    }
}