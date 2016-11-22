public class Main {
    private static String LC_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static String UC_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static char shift(char c, int k) {
        int i = LC_LETTERS.indexOf(c);
        int j = UC_LETTERS.indexOf(c);

        if (i == -1 && j == -1) {
            return c;
        }
        if (i < 0) {
            //Char is uppercase
            int alphabetlength = UC_LETTERS.length();
            if (k >= 0) {
                //calculate index with positive shift
            } else {
                //calculate index with negative shift
            }
            return UC_LETTERS.charAt(j);
        } else {
            //Char is lowercase
            int alphabetlength = LC_LETTERS.length();
            if (k >= 0) {
                //calculate index with positive shift
            } else {
                //calculate index with negative shift
            }
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
        String s = "Caesar";
        System.out.println(s);

        String encrypted = encrypt(s, 25);
        System.out.println(encrypted);

        String decrypted = decrypt(encrypted, 25);
        System.out.println(decrypted);
    }
}
