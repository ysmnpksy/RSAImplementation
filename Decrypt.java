import java.math.BigInteger;

public class Decrypt {

    public static String decryptMessage(String ciphertext, BigInteger d, BigInteger n){

        BigInteger cipher = new BigInteger(ciphertext);
        BigInteger message = cipher.modPow(d, n);

        byte [] messageArr = message.toByteArray();

        return new String(messageArr);
    }
}