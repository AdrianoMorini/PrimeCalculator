package it.ticandtac.primecalculator.Fragment;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.text.InputType;
import android.text.method.ArrowKeyMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.agrawalsuneet.dotsloader.loaders.TrailingCircularDotsLoader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import it.ticandtac.primecalculator.DB.DataBaseHelper;
import it.ticandtac.primecalculator.AsyncTask.AsyncTaskCalc;
import it.ticandtac.primecalculator.AsyncTask.AsyncTaskPayload;
import it.ticandtac.primecalculator.OtherFunction.FunctionEnum;
import it.ticandtac.primecalculator.OtherFunction.InputFilterMinMax;
import it.ticandtac.primecalculator.MainActivity;
import it.ticandtac.primecalculator.MyViews.MyKeyboard;
import it.ticandtac.primecalculator.R;
import it.ticandtac.primecalculator.ShowResultInterface;
import static android.content.Context.MODE_PRIVATE;

//Fragment for execution.

public class Fragment_calc extends Fragment implements View.OnClickListener, ShowResultInterface {


    public static TextView getTVres() {
        return TVres;
    }

    private static TextView TVres;
    private static NumberPicker picker;
    private static MyKeyboard keyboard;
    private  Switch switch_;

    public static MediaPlayer getBeepSound() {
        return beepSound;
    }

    private static MediaPlayer beepSound;

    public static boolean isOnOff() {
        return OnOff;
    }

    private  static boolean OnOff;
    private static boolean appInfoShowed = false;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String SWITCH = "switch_";
    public static final String CHECKBOX = "checkbox_";


    public static EditText getNumber() {
        return Number;
    }

    private static EditText Number;
    private  Button info;

    public static Context getCalcContxt() {
        return calcContxt;
    }

    private static Context calcContxt;
    private FunctionEnum[] functions = FunctionEnum.values();
    DataBaseHelper myDb;

    public static View getBackgroundView() {
        return backgroundView;
    }

    public static View backgroundView;

    public static TrailingCircularDotsLoader getMyTLdots() {
        return myTLdots;
    }

    private static TrailingCircularDotsLoader myTLdots;


    public static NumberPicker getPicker() {
        return picker;
    }

    public static MyKeyboard getKeyboard() {
        return keyboard;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calc, container, false);

        calcContxt = view.getContext();
        myDb = new DataBaseHelper(MainActivity.getMainCntxt());




        myTLdots = view.findViewById(R.id.TLdots);

        this.picker = view.findViewById(R.id.functions_);
        this.Number = view.findViewById(R.id.etNumber);
        this.TVres = view.findViewById(R.id.TVResult);
        this.keyboard = view.findViewById(R.id.keyboard);
        this.switch_= view.findViewById(R.id.sound);
        this.info = view.findViewById(R.id.info);
        this.backgroundView = view.findViewById(R.id.Vbackground);
        backgroundView.setVisibility(View.INVISIBLE);

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

        Typeface custom_font = Typeface.createFromAsset(MainActivity.getMainCntxt().getAssets(),  "fonts/LEDCalculator.ttf");
        TVres.setTypeface(custom_font);
        Number.setTypeface(custom_font);
        Number.setFilters(new InputFilter[]{new InputFilterMinMax("0","9999999")});


        switch_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnOff = switch_.isChecked();
                saveData();
                if (OnOff){
                    Toast.makeText(MainActivity.getMainCntxt(), R.string.soundOn, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.getMainCntxt(), R.string.soundOff, Toast.LENGTH_SHORT).show();
                }

            }

        });

        MyKeyboard.getButtonEnter().setOnClickListener(this);



        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createInfoDialog().show();
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
            TVres.setText(R.string.NumberP);
        }
        else{
            TVres.setText(R.string.NumberNotP);
        }

    }

    private void showNumberPrimeLessN(Object result) {
        int risultato = (int) result;
        TVres.setText(String.valueOf(risultato));
    }

    private void showEul(Object result) {
        int risultato = (int) result;
        TVres.setText(String.valueOf(risultato));
    }

    private void showFact(Object result) {
      String res = "";
      List<String> risultato = (List<String>) result;
      int size = risultato.size();
      for(int i=0; i < size; i++){
          res += risultato.get(i);
      }
      TVres.setText(res);

    }

    private void showNTwins(Object result) {
        int risultato = (int) result;
        TVres.setText(String.valueOf(risultato-2) + ","+ String.valueOf(risultato));
    }

    private void showNPrime(Object result) {
        int risultato = (int) result;
        TVres.setText(String.valueOf(risultato));
    }



    public void  saveData(){
        SharedPreferences sharedPreferences =  calcContxt.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(SWITCH, switch_.isChecked());
        editor.apply();

    }

    public void loadData(){
        SharedPreferences sharedPreferences = calcContxt.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        OnOff = sharedPreferences.getBoolean(SWITCH, true);
        appInfoShowed = sharedPreferences.getBoolean(CHECKBOX,false) || appInfoShowed;



    }

    public void updateViews(){
        switch_.setChecked(OnOff);
        if (!appInfoShowed){
            createAppInfoDialog().show();
        }


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

    public  Dialog createInfoDialog() {
        View alertView = View.inflate(MainActivity.getMainCntxt(), R.layout.app_info, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.getMainCntxt());

        TextView title = alertView.findViewById(R.id.infoTitle);
        TextView gName = alertView.findViewById(R.id.T1);
        TextView name1 = alertView.findViewById(R.id.T2);
        TextView name2 = alertView.findViewById(R.id.T3);

        title.setText(R.string.Information);
        name1.setText(R.string.AP);
        name2.setText(R.string.AM);
        gName.setText(R.string.ticandtac_group);

        builder.setView(alertView);
        builder.setCancelable(false);
        builder.setPositiveButton("ok", null);
        return builder.create();
    }

    //Execution of AsyncTask with reflection.

    @Override
    public void onClick(View v) {

        if (OnOff){
           beepSound.start();
        }

        FunctionEnum functionEnum = functions[picker.getValue()];

        String value = Number.getText().toString();
        if (value.equals("")) {
            Toast.makeText(Fragment_calc.getCalcContxt(), R.string.excp, Toast.LENGTH_SHORT).show();
            return;
        }

        long num = Long.valueOf(value);

        AsyncTaskPayload payload = new AsyncTaskPayload(functionEnum, num);

        AsyncTaskCalc asyncTaskCalc = new AsyncTaskCalc(payload, this);

        asyncTaskCalc.execute();

    }

    public void showResult(Object result, FunctionEnum function) {

        Method showResult = null;

        try {
            showResult = this.getClass().getDeclaredMethod(function.getHandleResultFun(), Object.class);
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
                TVres.getText().toString(), function.toString());

    }

    @Override
    public void onPreExecute() {
    }

    @Override
    public void onPostExecute(){
    }

    //Show dialog.

    public Dialog createAppInfoDialog() {
        View alertView = View.inflate(calcContxt, R.layout.app_alert, null);
        final CheckBox checkBox = alertView.findViewById(R.id.checkbox);
        final SharedPreferences sharedPreferences = calcContxt.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        AlertDialog.Builder builder = new AlertDialog.Builder(calcContxt);

        builder.setView(alertView);
        builder.setCancelable(false);
        builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (checkBox.isChecked()) {
                    sharedPreferences.edit().putBoolean(CHECKBOX,checkBox.isChecked()).apply();

                }
                appInfoShowed = true;

            }
        });
        return builder.create();
    }
}
