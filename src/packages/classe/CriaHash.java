package packages.classe;

import packages.hash.*;
import packages.model.Pessoa;

import java.util.Objects;

public class CriaHash<T> {
    private static final double MAX_LOAD = 0.70;
    private HashTableEntry<T>[] table;
    private int size = 0;
    private int collisions = 0;

    private final IntKeyExtractor<T> keyExtractor;
    private final HashFunction hashFunction;

    @SuppressWarnings("unchecked")
    public CriaHash(IntKeyExtractor<T> keyExtractor, HashFunction hashFunction, int capacity ){
        this.keyExtractor = Objects.requireNonNull(keyExtractor);
        this.hashFunction = (hashFunction != null) ? hashFunction : new ModHashFunction();
        if(capacity < 4) capacity = 4;
        this.table = (HashTableEntry<T>[]) new HashTableEntry[capacity];
        for (int i=0;i< table.length;i++)
            table[i] = new HashTableEntry<>();
    }

    public CriaHash(IntKeyExtractor<T> keyExtractor, int capacity){
        this(keyExtractor, new ModHashFunction(), capacity);
    }

    public CriaHash(IntKeyExtractor<T> keyExtractor){
        this(keyExtractor, 16);
    }

    public T buscarSemColisao(int chave) {
        int id = baseIndex(chave);

        if(table[id].state == State.OCCUPIED && table[id].key == id){
            return table[id].value;
        }
        return null;
    }

    public T buscarSemColisao(T value){
        return buscarSemColisao(keyExtractor.getKey(value));
    }

    public boolean inserirSemColisao(T info) {
        int id = keyExtractor.getKey(info);
        ensureCapacity();
        int index = baseIndex(id);

        // NOVO: Verifica colisão (slot ocupado por uma CHAVE DIFERENTE)
        if(table[index].state == State.OCCUPIED && table[index].key != id){
            // Colisão detectada, a inserção 'falha'
            return false;
        }

        // Lógica original levemente ajustada
        if(table[index].state != State.OCCUPIED){
            size++;
        } else {
            System.out.println("ID existente: Substituindo Valor");
        }

        table[index].key = id;
        table[index].value = info;
        table[index].state = State.OCCUPIED;
        return true; // Inserção bem-sucedida ou substituição
    }

    public T buscarEnderecoAberto(int id) {
        int index = findSlotForSearch(id);
        return (index >=0) ? table[index].value : null;
    }

    public T buscarEnderecoAberto(T value){
        return buscarEnderecoAberto(keyExtractor.getKey(value));
    }

    public int inserirEnderecoAberto(T info) {
        int id = keyExtractor.getKey(info);
        ensureCapacity();
        int index = findSlotForInsert(id);
        if(index < 0) {
            rehash(table.length * 2);
            index = findSlotForInsert(id);
        }
        if(table[index].state != State.OCCUPIED){
            size++;
        } else {
            System.out.println("ID existente: Substituindo Valor");
        }
        table[index].key = id;
        table[index].value = info;
        table[index].state = State.OCCUPIED;

        return index; // <--- NOVO: Retorna o índice de inserção
    }

    public T removeEnderecoAberto(int id){
        int index = findSlotForSearch(id);
        if(index >= 0) {
            T old = table[index].value;
            table[index].value = null;
            table[index].state = State.DELETED;
            size--;
            return old;
        }
        return null;
    }

    public int size(){
        return size;
    }
    public int capacity(){
        return table.length;
    }

    public double loadFactory(){
        return (double) size() / capacity();
    }

    public String stats(){
        return "Hash {\n" +
                "\ttamanho = "+size()+"\n"+
                "\tcapacidade = "+capacity()+"\n"+
                "\tocupação = "+String.format("%.2f",loadFactory())+"\n"+
                "} ";
    }

    private int baseIndex(int chave) {
        return hashFunction.hash(chave, capacity());
    }

    public void ensureCapacity(){
        if(loadFactory() > MAX_LOAD) rehash(capacity()*2);
    }

    @SuppressWarnings("unchecked")
    private void rehash(int newCapacity) {
        HashTableEntry<T>[] old = table;
        table = (HashTableEntry<T>[]) new HashTableEntry[newCapacity];
        for(int i=0; i<capacity(); i++)
            table[i] = new HashTableEntry<>();
        size = 0;
        for(HashTableEntry<T> e : old){
            if(e.state == State.OCCUPIED){
                inserirEnderecoAberto(e.value);
            }
        }
    }

    private int sondagemLinear(int h, int i, int cap){
        return (h+i) % cap;
    }
    private int sondagemQuadratica(int h, int i, int cap){
        return (h+i*i) % cap;
    }

    private int findSlotForInsert(int id) {
        int cap = capacity();
        int h = baseIndex(id);
        int firstDeleted = -1;
        for(int i=0;i<cap;i++){
            int index = sondagemLinear(h, i, cap);
            State state = table[index].state;

            // NOVO: Lógica de contagem de colisões (i > 0 significa que uma sondagem ocorreu)
            if (i > 0 && state != State.EMPTY) {
                collisions++;
            }

            if(state == State.EMPTY)
                return (firstDeleted >=0) ? firstDeleted : index;
            else if (state == State.DELETED) {
                if(firstDeleted < 0) firstDeleted = index;
            } else {
                if(table[index].key == id) return index;
            }
        }
        return -1;
    }

    private int findSlotForSearch(int key){
        int cap = capacity();
        int h = baseIndex(key);
        for(int i=0;i<cap;i++){
            int index = sondagemLinear(h,i,cap);
            State state = table[index].state;
            if(state == State.EMPTY)
                return -1;
            if(state == State.OCCUPIED && table[index].key == key)
                return index;
        }
        return -1;
    }

    public boolean containsKey(int id){
        return buscarEnderecoAberto(id)!=null;
    }

    public void printPessoa(Pessoa pessoa){
        if(pessoa == null){
            System.out.println("Valor não Encontrado");
            return;
        }
        System.out.println(pessoa);
    }

    public void clearTable(){
        for (int i = 0;i<capacity();i++){
            table[i]= new HashTableEntry<>();
            size = 0;
        }
    }

    public int getCollisions() {
        return collisions;
    }

    public String tableContent() {
        StringBuilder sb = new StringBuilder("Hash Table Content:\n");
        for (int i = 0; i < capacity(); i++) {
            HashTableEntry<T> entry = table[i];
            String key = (entry.state == State.OCCUPIED || entry.state == State.DELETED) ? String.valueOf(entry.key) : "N/A";
            String value = (entry.value != null) ? entry.value.toString().replace("\n", "\n\t") : "N/A";
            sb.append(String.format("[%d]: State=%s, Key=%s, Value=%s\n",
                    i, entry.state, key, value));
        }
        return sb.toString();
    }
}