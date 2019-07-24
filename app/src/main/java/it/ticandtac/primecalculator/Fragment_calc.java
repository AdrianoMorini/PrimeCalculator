package it.ticandtac.primecalculator;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


import static android.content.Context.MODE_PRIVATE;


public class Fragment_calc extends Fragment implements View.OnClickListener {

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

    public static Context getCalcContxt() {
        return calcContxt;
    }

    private static Context calcContxt;
    private CronoAdapter mAdapter;

    private FunctionEnum[] functions = FunctionEnum.values();
    DataBaseHelper myDb;





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calc, container, false);

        calcContxt = view.getContext();
        myDb = new DataBaseHelper(MainActivity.getMainCntxt());






        this.picker = view.findViewById(R.id.functions_);
        this.Number = view.findViewById(R.id.etNumber);
        this.TVres = view.findViewById(R.id.TVResult);
        this.keyboard = view.findViewById(R.id.keyboard);
        this.switch_= view.findViewById(R.id.sound);
        this.info = view.findViewById(R.id.info);

        beepSound = MediaPlayer.create(MainActivity.getMainCntxt(),R.raw.button1);

        String[] values = new String[this.functions.length];

        for (int i = 0; i < this.functions.length; i++){
            values[i] = getString(this.functions[i].getTitleRes());
        }


        picker.setMinValue(0);
        picker.setMaxValue(this.functions.length - 1);
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

        Typeface custom_font = Typeface.createFromAsset(MainActivity.getMainCntxt().getAssets(),  "fonts/LEDCalculator.ttf");
        TVres.setTypeface(custom_font);
        Number.setTypeface(custom_font);


        switch_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnOff = switch_.isChecked();
                saveData();
                if (OnOff){
                    Toast.makeText(MainActivity.getMainCntxt(), "Suono On", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.getMainCntxt(), "Suono Off", Toast.LENGTH_SHORT).show();
                }

            }

        });

        MyKeyboard.getButtonEnter().setOnClickListener(this);




        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.getMainCntxt())
                        .setTitle("Informazioni")
                        .setMessage("Gruppo TicAndTac:" + "\n\n" + "- Pinno" + "\n" + "- Drin Drin")
                        .setPositiveButton(android.R.string.yes,null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();


            }
        });

        MyKeyboard.getButtonFunc().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OnOff){
                    beepSound.start();
                }
                createAppInfoDialog(functions[picker.getValue()]).show();
            }
        });


        loadData();
        updateViews();

        return view;

    }

    private void showPrime(Object result) {
        boolean risultato = (boolean) result;
        if (risultato){
            TVres.setText("Il numero è primo.");
        }
        else{
            TVres.setText("Il numero non è primo.");
        }

    }

    private void showNumberPrimeLessN(Object result) {
        int risultato = (int) result;
    }

    private void showEul(Object result) {
        /*quello che deve tornare risultato = (quello che deve tornare) result;*/
    }

    private void showFact(Object result) {
      /*quello che deve tornare risultato = (quello che deve tornare) result;*/
    }

    private void showTwins(Object result) {
        /*quello che deve tornare risultato = (quello che deve tornare) result;*/
    }

    private void showNPrime(Object result) {
        /*quello che deve tornare risultato = (quello che deve tornare) result;*/
    }



    public void  saveData(){
        SharedPreferences sharedPreferences =  MainActivity.getMainCntxt().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(SWITCH, switch_.isChecked());
        editor.apply();

    }

    public void loadData(){
        SharedPreferences sharedPreferences = MainActivity.getMainCntxt().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        OnOff = sharedPreferences.getBoolean(SWITCH, true);

    }

    public void updateViews(){
        switch_.setChecked(OnOff);

    }

    public  Dialog createAppInfoDialog(FunctionEnum functionEnum) {
        View alertView = View.inflate(MainActivity.getMainCntxt(), R.layout.fragment_function, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.getMainCntxt());

        TextView title = alertView.findViewById(R.id.Title);
        TextView description = alertView.findViewById(R.id.Description);
        title.setText(functionEnum.getTitleRes());
        description.setText(functionEnum.getDesRes());

        builder.setView(alertView);
        builder.setCancelable(false);
        builder.setPositiveButton("ok", null);
        return builder.create();
    }


    //NON TOCCARE LA ONCLICK
    @Override
    public void onClick(View v) {
        if (OnOff){
           beepSound.start();

        }
        FunctionEnum functionEnum = functions[picker.getValue()];

        Method calcMethod = null;

        String value = Number.getText().toString();

        if (value.isEmpty()) return;

        Object result = null;
        long num = Long.valueOf(value);

        try {
            calcMethod = MyFuctions.class.getMethod(functionEnum.getFunctionName(), long.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        try {
            if (calcMethod != null)result = calcMethod.invoke(MyFuctions.class, num);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        Method showResult = null;

        try {
            showResult = this.getClass().getDeclaredMethod(functionEnum.getHandleResultFun(), Object.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        try {
            if (showResult != null && result!= null)showResult.invoke(this, result);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        boolean isInserted = myDb.insertData(Number.getText().toString(),
                TVres.getText().toString());
        if (isInserted = true){
            Toast.makeText(MainActivity.getMainCntxt(), "Inserted", Toast.LENGTH_SHORT).show();


        } else {
            Toast.makeText(MainActivity.getMainCntxt(), "NotInserted", Toast.LENGTH_SHORT).show();
        }


    }
}