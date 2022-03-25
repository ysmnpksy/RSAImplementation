import java.math.BigInteger;

public class Encrypt {

    public static BigInteger encryptMessage(String plaintext, BigInteger n, int e){
        byte [] plainArr = plaintext.getBytes(); // turn message to array of bytes

        BigInteger plainBigInt = new BigInteger(plainArr);

        return plainBigInt.modPow(BigInteger.valueOf(e), n);
    }
}