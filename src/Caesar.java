public class Caesar {
    private static String LC_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static String UC_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static int ALPHABET_LENGTH = 26;

    public static char shift(char c, int k) {
        // index of char
        int i = LC_LETTERS.indexOf(c);
        int j = UC_LETTERS.indexOf(c);

        if (i == -1 && j == -1) {
            return c;
        }
        if (i < 0) {
            //Char is uppercase
            if (k < 0) {
                //adapt shift amout if negative
                k = ALPHABET_LENGTH + (k % ALPHABET_LENGTH);
            }
            //calculate new shifted index
            j = (j + k) % ALPHABET_LENGTH;
            return UC_LETTERS.charAt(j);
        } else {
            //Char is lowercase
            if (k < 0) {
                //adapt shift amout if negative
                k = ALPHABET_LENGTH + (k % ALPHABET_LENGTH);
            }
            //calculate new shifted index
            i = (i + k) % ALPHABET_LENGTH;
            return LC_LETTERS.charAt(i);
        }
    }

    public static String encrypt(String s, int k) {
        String encrypted = "";
        for (int i = 0; i < s.length(); i++) {
            encrypted += shift(s.charAt(i), k);
        }
        return encrypted;
    }

    public static String decrypt(String s, int k) {
        return encrypt(s, -(k % 26));
    }

    public static void main(String[] args) {
        String s = "Caesar ist toll! Das wäre ein Test. Kann er auch ein scharfes-S ß?";
        System.out.println(s);

        String encrypted = encrypt(s, 10);
        System.out.println(encrypted);

        String decrypted = decrypt(encrypted, 10);
        System.out.println(decrypted);
    }
}
