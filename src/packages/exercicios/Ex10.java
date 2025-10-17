package packages.exercicios;

import packages.classe.CriaHash;
import packages.hash.IntKeyExtractor;

public class Ex10 {

    private static final IntKeyExtractor<Integer> IDENTITY_EXTRACTOR = (Integer value) -> value;

    public static void main(String[] args) {
        System.out.println("===== Ex 10: Caçador de Marte (Operações Completas) =====");

        CriaHash<Integer> tabelaOperacoes = new CriaHash<>(IDENTITY_EXTRACTOR, 10);

        System.out.println("--- 1. INSERÇÃO ---");
        int[] chaves = {12, 22, 32, 5, 15, 25};

        for (int chave : chaves) {
            int index = tabelaOperacoes.inserirEnderecoAberto(chave);
            System.out.printf("Chave %d (Hash %d) inserida em: %d\n", chave, chave % 10, index);
        }
        System.out.println("\n" + tabelaOperacoes.tableContent());
        System.out.println(tabelaOperacoes.stats());
        System.out.println("Colisões após inserção: " + tabelaOperacoes.getCollisions());

        System.out.println("\n--- 2. BUSCA ---");
        int chaveParaBuscar = 32;
        Integer valorEncontrado = tabelaOperacoes.buscarEnderecoAberto(chaveParaBuscar);
        System.out.printf("Buscando chave %d: %s\n", chaveParaBuscar, (valorEncontrado != null ? "Encontrado " + valorEncontrado : "Não Encontrado"));
        chaveParaBuscar = 100;
        valorEncontrado = tabelaOperacoes.buscarEnderecoAberto(chaveParaBuscar);
        System.out.printf("Buscando chave %d: %s\n", chaveParaBuscar, (valorEncontrado != null ? "Encontrado " + valorEncontrado : "Não Encontrado"));

        System.out.println("\n--- 3. REMOÇÃO ---");
        int chaveParaRemover = 22;
        Integer valorRemovido = tabelaOperacoes.removeEnderecoAberto(chaveParaRemover);
        System.out.printf("Removendo chave %d: %s\n", chaveParaRemover, (valorRemovido != null ? "Removido " + valorRemovido : "Não Encontrado"));

        chaveParaRemover = 50;
        valorRemovido = tabelaOperacoes.removeEnderecoAberto(chaveParaRemover);
        System.out.printf("Tentando remover chave %d: %s\n", chaveParaRemover, (valorRemovido != null ? "Removido " + valorRemovido : "Não Encontrado"));

        System.out.println("\n" + tabelaOperacoes.tableContent());
        System.out.println(tabelaOperacoes.stats());

        System.out.println("\n--- 4. BUSCA APÓS REMOÇÃO ---");
        chaveParaBuscar = 32;
        valorEncontrado = tabelaOperacoes.buscarEnderecoAberto(chaveParaBuscar);
        System.out.printf("Buscando chave %d após remoção de 22: %s\n", chaveParaBuscar, (valorEncontrado != null ? "Encontrado " + valorEncontrado : "Não Encontrado"));
    }
}