package it.ticandtac.primecalculator;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.method.ArrowKeyMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

public class Fragment_calc extends Fragment {

    private NumberPicker picker;

    public static TextView getTVres() {
        return TVres;
    }

    private static TextView TVres;
    private  MyKeyboard keyboard;
    private  Switch switch_;

    public static MediaPlayer getBeepSound() {
        return beepSound;
    }

    private static MediaPlayer beepSound;

    public static boolean isOnOff() {
        return OnOff;
    }

    private  static boolean OnOff;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String SWITCH = "switch_";

    public static EditText getNumber() {
        return Number;
    }

    private static EditText Number;
    private int result;
    private static boolean op;
    private  Button info;


    final String[] values = {"è primo?","numeri primi minori di n",
            "fattorizzare n in numeri primi","funzione di eulero","n-esima coppia di numeri gemelli",
            "n-esimo primo"};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calc, container, false);

        this.picker = view.findViewById(R.id.functions_);
        this.Number = view.findViewById(R.id.etNumber);
        this.TVres = view.findViewById(R.id.TVResult);
        this.keyboard = view.findViewById(R.id.keyboard);
        this.switch_= view.findViewById(R.id.sound);
        this.info = view.findViewById(R.id.info);

        beepSound = MediaPlayer.create(MainActivity.getMainActContx(),R.raw.button1);


        picker.setMinValue(0);
        picker.setMaxValue(5);
        picker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        picker.setDisplayedValues(values);
        picker.setWrapSelectorWheel(true);
        picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                //tvResult.setText("prova: " + values[newVal]);
            }
        });


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
        Number.setFilters(new InputFilter[]{new InputFilterMinMax("0","999999")});

        Typeface custom_font = Typeface.createFromAsset(MainActivity.getMainActContx().getAssets(),  "fonts/LEDCalculator.ttf");
        TVres.setTypeface(custom_font);
        Number.setTypeface(custom_font);


        switch_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnOff = switch_.isChecked();
                saveData();
                if (OnOff){
                    Toast.makeText(MainActivity.getMainActContx(), "Suono On", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.getMainActContx(), "Suono Off", Toast.LENGTH_SHORT).show();
                }

            }

        });

        MyKeyboard.getButtonEnter().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(picker.getValue() == 0){
                    Editable e = Number.getText();
                    String s = e.toString();
                    int result = Integer.parseInt(s);
                    op = MyFuctions.IsPrime(result);
                    if (op){
                        TVres.setText("Il numero è primo.");
                    }
                    else{
                        TVres.setText("Il numero non è primo.");
                    }
                }
                else{
                    TVres.setText("ciao");
                }
            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.getMainActContx())
                        .setTitle("Informazioni")
                        .setMessage("Gruppo TicAndTac:" + "\n\n" + "- Pinno" + "\n" + "- Drin Drin")
                        .setPositiveButton(android.R.string.yes,null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();


            }
        });

        loadData();
        updateViews();

        return view;

    }

    public void  saveData(){
        SharedPreferences sharedPreferences =  MainActivity.getMainActContx().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(SWITCH, switch_.isChecked());
        editor.apply();

    }

    public void loadData(){
        SharedPreferences sharedPreferences = MainActivity.getMainActContx().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        OnOff = sharedPreferences.getBoolean(SWITCH, true);

    }

    public void updateViews(){
        switch_.setChecked(OnOff);

    }
}
