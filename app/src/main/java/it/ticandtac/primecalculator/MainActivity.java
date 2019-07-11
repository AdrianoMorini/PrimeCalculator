package it.ticandtac.primecalculator;


import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.method.ArrowKeyMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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
    private EditText etNumber1;
    private int result;
    private boolean op;
    private static TextView TVres;

    public static Context getMainActContx() {
        return mainActContx;
    }

    private static Context mainActContx;
    public static TextView tvResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String[] values = {"è primo?","numeri primi minori di n",
                "fattorizzare n in numeri primi","funzione di eulero","n-esima coppia di numeri gemelli",
                "n-esimo primo"};

        mainActContx = this;

        tvResult = findViewById(R.id.TVResult);
        etNumber1 = findViewById(R.id.etNumber);

        picker = (NumberPicker) findViewById(R.id.functions_);
        picker.setMinValue(0);
        picker.setMaxValue(5);
        picker.setDisplayedValues(values);
        picker.setWrapSelectorWheel(true);
        picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                //tvResult.setText("prova: " + values[newVal]);
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_menu_);
        toolbar.setOverflowIcon(drawable);

        TVres = findViewById(R.id.TVResult);

        EditText Number = findViewById(R.id.etNumber);
        MyKeyboard keyboard = findViewById(R.id.keyboard);
        Number.setRawInputType(InputType.TYPE_CLASS_TEXT);
        Number.setTextIsSelectable(true);

        InputConnection ic = Number.onCreateInputConnection(new EditorInfo());
        keyboard.setInputConnection(ic);


        Number.setTextIsSelectable(false);
        Number.setFocusable(true);
        Number.setFocusableInTouchMode(false);
        Number.setClickable(false);
        Number.setLongClickable(true);
        Number.setMovementMethod(ArrowKeyMovementMethod.getInstance());
        Number.setText(Number.getText(), TextView.BufferType.SPANNABLE);

        Number.setFilters(new InputFilter[]{new InputFilterMinMax("0","9999")});

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/LEDCalculator.ttf");

        TVres.setTypeface(custom_font);
        Number.setTypeface(custom_font);


        loadData();
        updateViews();

        MyKeyboard.getButtonEnter().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(picker.getValue() == 0){
                    Editable e = etNumber1.getText();
                    String s = e.toString();
                    int result = Integer.parseInt(s);
                    op = (boolean) MyFuctions.IsPrime(result);
                    if (!op){
                        tvResult.setText("Il numero è primo.");
                    }
                    else{
                        tvResult.setText("Il numero non è primo.");
                    }
                }
                else{
                    tvResult.setText("ciao");
                }
            }
        });
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

    public static TextView getTVres() {
        return TVres;
    }
}

