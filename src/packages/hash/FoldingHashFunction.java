package packages.hash;

public class FoldingHashFunction implements HashFunction{
    private final int groupSize;

    public FoldingHashFunction(int groupSize){
        if(groupSize < 1) groupSize = 2;
        if(groupSize > 6) groupSize = 6;
        this.groupSize = groupSize;
    }

    @Override
    public int hash(int key, int capacity) {
        key = Math.abs(key);
        int sum = 0;
        int factor = (int)Math.pow(10,groupSize);
        while (key >0){
            sum += key%factor;
            key /= factor;
        }
        return sum % capacity;
    }
}