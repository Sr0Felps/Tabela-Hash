package packages.classe;

import packages.hash.HashFunction;
import packages.hash.IntKeyExtractor;
import packages.hash.ModHashFunction;

import java.util.LinkedList;
import java.util.Objects;
import java.util.List;

public class CriaHashEncadeamento<T> {
    private final List<T>[] table;
    private final IntKeyExtractor<T> keyExtractor;
    private final HashFunction hashFunction;

    @SuppressWarnings("unchecked")
    public CriaHashEncadeamento(IntKeyExtractor<T> keyExtractor, HashFunction hashFunction, int capacity) {
        this.keyExtractor = Objects.requireNonNull(keyExtractor);
        this.hashFunction = (hashFunction != null) ? hashFunction : new ModHashFunction();
        if (capacity < 4) capacity = 4;
        this.table = (List<T>[]) new List[capacity];
        for (int i = 0; i < capacity; i++) {
            // Inicializa cada índice com uma nova lista (encadeamento)
            table[i] = new LinkedList<>();
        }
    }

    public CriaHashEncadeamento(IntKeyExtractor<T> keyExtractor, int capacity) {
        this(keyExtractor, new ModHashFunction(), capacity);
    }

    public void inserir(T info) {
        int id = keyExtractor.getKey(info);
        // Calcula o índice base usando a função de hash
        int index = hashFunction.hash(id, table.length);

        // Insere o elemento na lista do índice calculado
        table[index].add(info);
    }

    // Método para Exibir o Conteúdo (para Ex. 6)
    public String tableContent() {
        StringBuilder sb = new StringBuilder("Tabela Hash (Encadeamento Separado):\n");
        for (int i = 0; i < table.length; i++) {
            sb.append(String.format("[%d]: %s\n", i, table[i].isEmpty() ? "Vazio" : table[i].toString()));
        }
        return sb.toString();
    }
}