package it.ticandtac.primecalculator.OtherFunction;

import android.support.v7.app.AppCompatActivity;
import android.util.SparseIntArray;

import java.util.ArrayList;

import java.util.List;

import it.ticandtac.primecalculator.Fragment.Fragment_calc;
import it.ticandtac.primecalculator.R;


//Functions to perform.

public class MyFuctions extends AppCompatActivity {


    public static boolean IsPrime(long n) {
        if (n < 2) return false;
        if (n == 2 || n == 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        long sqrtN = (long) Math.sqrt(n) + 1;
        for (long i = 6L; i <= sqrtN; i += 6) {
            if (n % (i - 1) == 0 || n % (i + 1) == 0) return false;
        }
        return true;
    }

    public static int PrimeNumbersCount(long n) {
        SparseIntArray primeNumbers = new SparseIntArray();

        if (n == 0) {
            Fragment_calc.getTVres().setText(R.string.Zero);
        }


        boolean prime[] = new boolean[(int) (n + 1)];
        for (int i = 0; i < n; i++)
            prime[i] = true;

        for (int p = 2; p * p <= n; p++) {
            // If prime[p] is not changed, then it is a prime
            if (prime[p]) {
                // Update all multiples of p
                for (int i = p * p; i <= n; i += p)
                    prime[i] = false;
            }
        }

        for (int i = 2; i <= n; i++) {
            if (prime[i])
                primeNumbers.append(i, i);
        }

        int key = primeNumbers.size();

        return key;
    }

    public static List<String> FactPrimeN(long n) {

        List<String> list = new ArrayList<>();
        long resToFact;
        long divider;
        divider = 2;
        resToFact = n;
        while (resToFact > 1)  // continue if factorize is not complete
        {
            while (resToFact % divider == 0)  // each divider could be applied more times
            {
                // found divider
                if (resToFact == n) {     // first fact
                    list.add(Integer.toString((int) divider));
                }
                else {
                    list.add(" * ");
                    list.add(Integer.toString((int) divider));
                }
                resToFact = resToFact / divider;
            }
            divider++;
        }
        return list;
    }

    public static int Euler0(long n) {

        // Initialize result as n
        float result = n;

        // Consider all prime factors of n and for
        // every prime factor p, multiply result
        // with (1 - 1/p)
        for (int p = 2; p * p <= n; ++p) {
            // Check if p is a prime factor.
            if (n % p == 0) {
                // If yes, then update n and result
                while (n % p == 0)
                    n /= p;
                result *= (1.0 - (1.0 / (float) p));
            }
        }

        // If n has a prime factor greater than sqrt(n)
        // (There can be at-most one such prime factor)
        if (n > 1)
            result *= (1.0 - (1.0 / (float) n));

        return (int) result;
    }

    public static int NPrime(long n) {

        int num = 0;
        int index = 0;

        if (n == 0) {
            Fragment_calc.getTVres().setText("");
        }

        while (index != n) {
            num++;
            if (IsPrime(num)) {
                index++;
            }
        }

        return num;
    }

    public static int NTwins(long n) {

        int index = 1;
        int num = 1;

        if (n == 0) {
            Fragment_calc.getTVres().setText(R.string.Zero);
        }

        if (n == 1) return (int) n + 4;

        if(n == 2) return (int) n + 5;

        while (index != n) {
            num += 6;
            if (IsPrime(num) && IsPrime(num - 2)) {
                index++;
            }
        }

        return num;
    }
}