package it.ticandtac.primecalculator;

import android.support.v7.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;

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

        int result = 0;
        // costruisco insieme  setPrimi = {2,...,n}

        Set<Integer> setPrimi = new HashSet<Integer>();
        for(int k=2 ; k <=n ; k++){
            setPrimi.add(new Integer(k));
        }

// per ogni k in setPrimi, tolgo da setPrimi i multipli m di k tali che m < k <= n

        for(Integer k : setPrimi){
            for(int m = 2*k ; m <= n ; m += k)
                setPrimi.remove(new Integer(m));
        }

        // in  setPrimi sono rimasti solamente i numeri primi p tali che 2 <= p <= n

        for(Integer p : setPrimi)
             result += 1;

        return result;
    }
}
