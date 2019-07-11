package it.ticandtac.primecalculator;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MyFuctions extends AppCompatActivity {
    
    public static boolean IsPrime(int myNum){
        if(myNum % 2 == 0){
            return true;
        }
        else{
            return false;
        }
    }
}
