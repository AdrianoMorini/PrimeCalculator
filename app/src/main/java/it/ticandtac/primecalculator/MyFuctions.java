package it.ticandtac.primecalculator;

import android.support.v7.app.AppCompatActivity;
import android.util.SparseIntArray;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyFuctions extends AppCompatActivity {

    /**
     * TUTTE LE FUNZIONI DEVONO PRENDERE IN INPUT UN LONG E IN OUTPUT
     * QUELLO CHE CAZZO TI PARE
     * @param n
     * @return
     */


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

        // costruisco insieme  setPrimi = {2,...,n}

        int result = 0;

        Set<Integer> setPrimi = new HashSet<Integer>();
        for(int k=2 ; k <=n ; k++){
            setPrimi.add(k);
        }

// per ogni k in setPrimi, tolgo da setPrimi i multipli m di k tali che m < k <= n


        SparseIntArray notPrimi = new SparseIntArray();

        for(Integer k : setPrimi){
            if (notPrimi.get(k) != 0) continue;
            for(int m = 2*k ; m <= n ; m += k) {
                notPrimi.append(m,m);
            }
        }

        // in  setPrimi sono rimasti solamente i numeri primi p tali che 2 <= p <= n

        result = setPrimi.size() - notPrimi.size() + 1;

        return result;
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

    public static void NTwins(long n){
        //TODO
        return;
    }

    public static void NPrime(long n){
        //TODO
        return;
    }
}
