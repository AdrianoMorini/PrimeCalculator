package it.ticandtac.primecalculator;

import android.support.v7.app.AppCompatActivity;
import android.util.SparseIntArray;

import java.util.ArrayList;

import java.util.List;


public class MyFuctions extends AppCompatActivity {


    public static boolean IsPrime(long n){
        if(n < 2) return false;
        if(n == 2 || n == 3) return true;
        if(n%2 == 0 || n%3 == 0) return false;
        long sqrtN = (long)Math.sqrt(n)+1;
        for(long i = 6L; i <= sqrtN; i += 6) {
            if(n%(i-1) == 0 || n%(i+1) == 0) return false;
        }
        return true;
    }

    public static int PrimeNumbersCount(long n){
        SparseIntArray primeNumbers = new SparseIntArray();

        if (n == 0){
            Fragment_calc.getTVres().setText("Inserire un numero maggiore di 0.");
        }


        boolean prime[] = new boolean[(int) (n+1)];
        for(int i=0;i<n;i++)
            prime[i] = true;

        for(int p = 2; p*p <=n; p++)
        {
            // If prime[p] is not changed, then it is a prime
            if(prime[p] == true)
            {
                // Update all multiples of p
                for(int i = p*p; i <= n; i += p)
                    prime[i] = false;
            }
        }

        for(int i = 2; i <= n; i++)
        {
            if(prime[i] == true)
                primeNumbers.append(i,i);
        }

        int key = primeNumbers.size();

        return key;
    }

    public static List<String> FactPrimeN(long n){

        List<String> list = new ArrayList<>();
        long residuoDaFattorizzare;
        long divisore;
        divisore = 2;
        residuoDaFattorizzare = n;
        while(residuoDaFattorizzare > 1)  // continua se la fattorizzazione non e' completa
        {
            while(residuoDaFattorizzare % divisore == 0)  // ciascun divisore potrebbe essere applicato piu' volte
            {
                // sicuramente ho trovato un divisore
                if(residuoDaFattorizzare == n) {     // in questo caso si tratta del primo fattore
                    list.add(Integer.toString((int)divisore));
                }                       // stampo senza il simbolo di moltiplicazione
                else{
                    list.add(" * "); // stampo premettendo il simbolo di moltiplicazione
                    list.add(Integer.toString((int)divisore));
                }
                residuoDaFattorizzare = residuoDaFattorizzare / divisore;
            }

            // ho esaurito l'applicazione del divisore
            divisore++;
        }

        return list;
    }

    public static int Euler0(long n){

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
                    result *= (1.0 - (1.0 / (float)p));
                }
            }

            // If n has a prime factor greater than sqrt(n)
            // (There can be at-most one such prime factor)
            if (n > 1)
                result *= (1.0 - (1.0 / (float)n));

            return (int)result;
    }

    public static int NPrime(long n){

        int num = 0;
        int index = 0;

        if (n == 0){
            Fragment_calc.getTVres().setText("Inserire un numero maggiore di 0.");
        }

        while(index != n){
            num++;
            if(IsPrime(num)){
                index++;
            }
        }

        return num;
    }

    public static int NTwins(long n){

        int index = 0;
        int num = 0;

        if (n == 0){
            Fragment_calc.getTVres().setText("Inserire un numero maggiore di 0.");
        }

        if (n==1) return (int)n+4;

        while(index != n){
            num++;
            if(IsPrime(num) && IsPrime(num - 2)){
                index++;
            }
        }

        return num;
    }
}
