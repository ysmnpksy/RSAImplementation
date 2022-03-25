import java.io.Serializable;
import java.math.BigInteger;
import java.util.Random;

public class KeySet implements Serializable {

    BigInteger p;    // First prime
    BigInteger q;    // Second prime
    BigInteger n;    // Product of p and q
    BigInteger r;    // Phi
    BigInteger d;    // Private key
    int e;           // Random number, 2<e<r, no factors with r

    public KeySet() {
        Random rand = new Random();
        p = BigInteger.probablePrime(2048, rand);
        q = BigInteger.probablePrime(2048, rand);

        n = q.multiply(p); // Public key
        r = (p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)));

        // Find e value
        boolean found = false;
        int counter = 3;
        while(!found){
            BigInteger val = r.gcd(BigInteger.valueOf(counter));
            if (val.equals(BigInteger.ONE)){
                found = true;
                e = counter;
            }
            else{
                counter++;
            }
        }

        // Find d using extended euclidean algorithm
        BigInteger TL = r; // Top left element of the column
        BigInteger TR = r; // Top right element of the column
        BigInteger BL = BigInteger.valueOf(e); // Bottom left element of the column
        BigInteger BR = BigInteger.ONE; // Bottom right element of the column

        while (!BL.equals(BigInteger.ONE)) {
            BigInteger tempVal = TL.divide(BL);

            BigInteger origBL = BL;
            BigInteger origBR = BR;

            BL = tempVal.multiply(BL);
            BR = tempVal.multiply(BR);

            BL = TL.subtract(BL);
            BR = TR.subtract(BR);

            // Checking if bottom left is less than 0, if so mod to positive number by r
            while (BR.compareTo(BigInteger.ZERO)==-1){
                BR = BR.mod(r);
            }

            // Setting new top left and right to old bottom values
            TL = origBL;
            TR = origBR;
        }
        d = BR; // Setting d

        // Printing d confirmation
        if (BigInteger.valueOf(e).multiply(d).mod(r).equals(BigInteger.ONE)) { // confirming
            System.out.println(" ");
        }
    }

    public BigInteger getP() {
        return p;
    }
    public void setP(BigInteger p) {
        this.p = p;
    }

    public BigInteger getQ() {
        return q;
    }
    public void setQ(BigInteger q) {
        this.q = q;
    }

    public BigInteger getN() {
        return n;
    }
    public void setN(BigInteger n) {
        this.n = n;
    }

    public BigInteger getR() {
        return r;
    }
    public void setR(BigInteger r) {
        this.r = r;
    }

    public BigInteger getD() {
        return d;
    }
    public void setD(BigInteger d) {
        this.d = d;
    }

    public int getE() {
        return e;
    }
    public void setE(int e) {
        this.e = e;
    }
}