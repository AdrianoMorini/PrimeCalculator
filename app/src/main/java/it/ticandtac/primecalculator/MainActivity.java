package it.ticandtac.primecalculator;


import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.method.ArrowKeyMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    NumberPicker picker = null;
    public static final String sndOnOff = "sONOff";
    public static final String SHARED_PREFS = "shared_Prefs";
    private boolean OnOff;

    public static Context getMainActContx() {
        return mainActContx;
    }

    private static Context mainActContx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mainActContx = this;

        picker = (NumberPicker) findViewById(R.id.functions_);
        picker.setMinValue(0);
        picker.setMaxValue(5);
        picker.setDisplayedValues(new String[]{"è primo?","numeri primi minori di n",
                "fattorizzare n in numeri primi","funzione di eulero","n-esima coppia di numeri gemelli",
                "n-esimo primo"});



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        EditText editText = findViewById(R.id.editText);
        MyKeyboard keyboard = findViewById(R.id.keyboard);
        editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
        editText.setTextIsSelectable(true);

        InputConnection ic = editText.onCreateInputConnection(new EditorInfo());
        keyboard.setInputConnection(ic);


        editText.setTextIsSelectable(false);
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(false);
        editText.setClickable(false);
        editText.setLongClickable(true);
        editText.setMovementMethod(ArrowKeyMovementMethod.getInstance());
        editText.setText(editText.getText(), TextView.BufferType.SPANNABLE);



        loadData();
        updateViews();
    }

    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(sndOnOff, OnOff);
        editor.apply();
     }


    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        OnOff = sharedPreferences.getBoolean(sndOnOff, true);

    }

    public void updateViews(){




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {

            case R.id.Info:
                new AlertDialog.Builder(this)
                        .setTitle("Informazioni")
                        .setMessage("Regoli merdaaaaaa")
                        .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .show();
            case R.id.Instructions:
                Toast.makeText(this, "Item 3 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.soundOnOFF:
                if (OnOff){
                    OnOff = false;
                    saveData();
                    item.setIcon(R.drawable.ic_volume_off_);

                    Toast.makeText(this, "suono off", Toast.LENGTH_SHORT).show();

                }
                else {
                    OnOff = true;
                    saveData();
                    item.setIcon(R.drawable.ic_volume_up_);
                    Toast.makeText(this, "suono on", Toast.LENGTH_SHORT).show();
                }
                return true;
            case R.id.Crono:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

