package packages.exercicios;

import packages.classe.CriaHash;
import packages.hash.IntKeyExtractor;
import packages.model.Peca; // Importa a nova classe Peca

public class Ex1 {

    // Extrator de chave que usa o ID da Peca como chave de hash
    private static final IntKeyExtractor<Peca> PECA_KEY_EXTRACTOR = Peca::getId;

    public static void main(String[] args) {
        System.out.println("===== Ex 1: Lex Luthor (ID -> Peso) =====");

        // Tabela Hash com capacidade 10 (id % tamanho)
        CriaHash<Peca> tabelaPecas = new CriaHash<>(PECA_KEY_EXTRACTOR, 10);

        Peca[] pecas = {
                new Peca(15, 50),
                new Peca(25, 65),
                new Peca(30, 100),
                new Peca(5, 10),
                new Peca(22, 80)
        };

        for (Peca peca : pecas) {
            int id = peca.getId();
            int hash = id % 10;
            // inserirEnderecoAberto retorna o índice final
            int index = tabelaPecas.inserirEnderecoAberto(peca);

            System.out.printf("Peça ID %d. Hash: %d. Armazenada na posição: %d\n",
                    id, hash, index);
        }

        System.out.println("\n" + tabelaPecas.tableContent());
    }
}