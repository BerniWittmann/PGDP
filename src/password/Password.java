package password;

public class Password {
    private final int nrUpperShould;
    private final int nrLowerShould;
    private final int nrSpecialShould;
    private final int nrNumbersShould;
    private final int lengthShould;
    private final char[] illegalChars;

    public Password(int nrUpperShould, int nrLowerShould, int nrSpecialShould,
                    int nrNumbersShould, int lengthShould, char[] illegalChars) {
        this.nrUpperShould = nrUpperShould;
        this.nrLowerShould = nrLowerShould;
        this.nrSpecialShould = nrSpecialShould;
        this.nrNumbersShould = nrNumbersShould;
        this.lengthShould = lengthShould;
        this.illegalChars = illegalChars;
    }

    public void checkFormat(String pwd) throws IllegalCharExc, NotEnoughExc, NotLongEnoughExc {
        if (pwd.length() < lengthShould) {
            throw new NotLongEnoughExc(lengthShould, pwd.length());
        }
        int lowerCaseLetters = 0;
        int upperCaseLetters = 0;
        int digits = 0;
        for (int k = 0; k < pwd.length(); k++) {
            if (Character.isUpperCase(pwd.charAt(k))) upperCaseLetters++;
            if (Character.isLowerCase(pwd.charAt(k))) lowerCaseLetters++;
            if (Character.isDigit(pwd.charAt(k))) digits++;
            for (int i = 0; i < illegalChars.length; i++) {
                if (illegalChars[i] == pwd.charAt(k)) {
                    throw new IllegalCharExc(pwd.charAt(k));
                }
            }
        }

        if (lowerCaseLetters < nrLowerShould) {
            throw new NotEnoughLower(nrLowerShould, lowerCaseLetters);
        }
        if (upperCaseLetters < nrUpperShould) {
            throw new NotEnoughLower(nrUpperShould, upperCaseLetters);
        }
        if (digits < nrNumbersShould) {
            throw new NotEnoughNumber(nrNumbersShould, digits);
        }

        int special = pwd.length() - lowerCaseLetters - upperCaseLetters - digits;
        if (special < nrSpecialShould) {
            throw new NotEnoughSpecial(nrSpecialShould, special);
        }
    }

    public static void main(String[] args) {
        char[] illChars = {'\n', '\t', '\r', '\b', '\f', ' '};
        Password password = new Password(3, 3, 1, 1, 9, illChars);

        //VALID
        try {
            password.checkFormat("abc1ABC!d");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        //NOT LONG ENOUGH
        try {
            password.checkFormat("abc1ABC!");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        //NOT ENOUGH LOWER
        try {
            password.checkFormat("ABC1ABC!d");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        //NOT ENOUGH UPPER
        try {
            password.checkFormat("abc1Abc!d");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        //ILLEGAL CHAR
        try {
            password.checkFormat("abc\n1ABC!");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        try {
            password.checkFormat("abc1 ABC!d");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        //NOT ENOUGH NUMBER
        try {
            password.checkFormat("abcdABC!d");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        //NOT ENOUGH SPECIAL
        try {
            password.checkFormat("abc1ABCDd");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
