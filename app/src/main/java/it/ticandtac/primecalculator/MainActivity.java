package it.ticandtac.primecalculator;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.method.ArrowKeyMovementMethod;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Switch switch_;
    NumberPicker picker = null;

    public  static MediaPlayer getBeepSound() {
        return beepSound;
    }

    private  static MediaPlayer beepSound;


    public static boolean isOnOff() {
        return OnOff;
    }



    private static boolean OnOff;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String SWITCH = "switch_";

    public static EditText getEtNumber1() {
        return etNumber1;
    }

    private static EditText etNumber1;
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

        beepSound = MediaPlayer.create(this, R.raw.beep_);

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


        switch_ = (Switch) findViewById(R.id.sound);
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



        loadData();
        updateViews();

        switch_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnOff = switch_.isChecked();
                if (OnOff){
                    Toast.makeText(MainActivity.this, "Suono On", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Suono Off", Toast.LENGTH_SHORT).show();
                }
                saveData();
            }
        });
    }

    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(SWITCH, switch_.isChecked());
        editor.apply();

    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        OnOff = sharedPreferences.getBoolean(SWITCH, true);

    }

    public void updateViews(){
        switch_.setChecked(OnOff);

    }


    public static TextView getTVres() {
        return TVres;
    }
}

