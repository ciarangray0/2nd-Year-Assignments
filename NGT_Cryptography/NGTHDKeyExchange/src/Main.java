import java.util.Random;

public class Main {
    public void main(String[] args) {
        int min = 10000;
        int max = 100000;
        int prime = genPrime(min, max);
        int base = primRoot(prime);

        int personA = genPriv(prime);
        int personB = genPriv(prime);
    }

    public int genPrime(int min, int max) {
        int p = 0;

        Random random = new Random();

        do {
            p = random.nextInt((max - min) + 1) + min;
        }
        while (!isPrime(p));

        return p;
    }

    public boolean isPrime(int p) {
        for (int i = 2; i*i <= p; i++) {
            if (p % i == 0) {
                return false;
            }
        }
        return true;
    }

    public int primRoot(int p) {
        int a = 0;

        Random random = new Random();

        do {
            a = random.nextInt(p);
        }
        while (!isPrimRoot(p, a));

        return a;
    }

    public boolean isPrimRoot(int p, int a) {
        boolean ret = false;
        boolean ar[] = new boolean[p];

        for (int i = 1; i <= p; i++) {
            int index = (int) (Math.pow(a, i) % p);

            if (!ar[index]) {
                ar[index] = true;
            }
            else {
                ret = true;
                break;
            }
        }
        return ret;
    }

    public int genPriv(int p) {
        Random random = new Random();

        return random.nextInt(p - 1) + 1;
    }

    public int calcPub(int base, int privKey, int prime) {
        Random random = new Random();

        return modPow(base, privKey, prime);
    }

    public int calcSec(int pubKey, int privKey, int prime) {
        return modPow(pubKey, privKey, prime);
    }


    public int modPow(int base, int exponent, int modulus) {
        if (modulus == 1) {
            return 0;
        }

        int result = 1;

        base = base % modulus;

        while (exponent > 0) {
            if (exponent % 2 == 1) {
                result = (result * base) % modulus;
            }
            exponent = exponent >> 1;

            base = (base * base) % modulus;
        }
        return result;
    }
}