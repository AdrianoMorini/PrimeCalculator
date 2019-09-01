package it.ticandtac.primecalculator;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import it.ticandtac.primecalculator.Fragment.Fragment_calc;
import it.ticandtac.primecalculator.Fragment.Fragment_crono;
import it.ticandtac.primecalculator.Fragment.Fragment_manual;


public class MainActivity extends AppCompatActivity {


    public static Context getMainCntxt() {
        return MainCntxt;
    }

    private static Context MainCntxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        MainCntxt = this;

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Fragment_calc()).commit();
        }
    }

    //Create Bottom Navigation menu.
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_calc:
                            selectedFragment = new Fragment_calc();
                            break;
                        case R.id.nav_cronology:
                            selectedFragment = new Fragment_crono();
                            break;
                        case R.id.nav_manual:
                            selectedFragment = new Fragment_manual();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };
}