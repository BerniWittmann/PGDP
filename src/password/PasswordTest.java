package password;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Berni on 15.10.17.
 */
public class PasswordTest {
    private char[] illChars =  {'\n', '\t', '\r', '\b', '\f', ' '};

    @Test(expected = NotLongEnoughExc.class)
    public void testInvalidLength() throws Exception {
        Password pw = new Password(0,0,0,0,10,illChars);
        pw.checkFormat("toshort");
    }

    @Test
    public void testValidLength() throws Exception {
        Password pw = new Password(0,0,0,0,10,illChars);
        pw.checkFormat("thisislongenough");
    }

}