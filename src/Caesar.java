public class Caesar {

    private static char shift(char c, int k) {
        String LC_LETTERS = "abcdefghijklmnopqrstuvwxyz";
        String UC_LETTERS = LC_LETTERS.toUpperCase();
        int ALPHABET_LENGTH = LC_LETTERS.length();

        // index of char
        int i = LC_LETTERS.indexOf(c);
        int j = UC_LETTERS.indexOf(c);

        //check if char is transformable
        if (i == -1 && j == -1) {
            //return non-transformable chars as they are
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
            //return char at shifted position
            return UC_LETTERS.charAt(j);
        } else {
            //Char is lowercase
            if (k < 0) {
                //adapt shift amout if negative
                k = ALPHABET_LENGTH + (k % ALPHABET_LENGTH);
            }
            //calculate new shifted index
            i = (i + k) % ALPHABET_LENGTH;
            //return char at shifted position
            return LC_LETTERS.charAt(i);
        }
    }

    private static String encrypt(String s, int k) {
        String encrypted = "";
        //loop through array
        for (int i = 0; i < s.length(); i++) {
            //shift each single char
            encrypted += shift(s.charAt(i), k);
        }
        return encrypted;
    }

    private static String decrypt(String s, int k) {
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
