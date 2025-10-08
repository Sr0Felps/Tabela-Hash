package packages.hash;

public class AsciiSumKeyExtractor implements IntKeyExtractor<String> {
    @Override
    public int getKey(String value) {
        int sum = 0;
        if (value == null) return 0;
        // Soma os códigos ASCII de cada caractere
        for (char c : value.toCharArray()) {
            sum += c;
        }
        return sum;
    }
}