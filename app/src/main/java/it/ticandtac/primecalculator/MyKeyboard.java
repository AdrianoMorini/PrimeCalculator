package it.ticandtac.primecalculator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.thekhaeng.pushdownanim.PushDownAnim;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

public class MyKeyboard extends RelativeLayout implements View.OnClickListener {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button0;
    private Button buttonDelete;

    public static Button getButtonFunc() {
        return buttonFunc;
    }

    private static Button buttonFunc;

    public static Button getButtonEnter() {
        return buttonEnter;
    }

    private static Button buttonEnter;
    private Button buttonAC;
    private Button buttonOff;

    public static InputConnection getIc() {
        return ic;
    }

    private static InputConnection ic;

    private Button buttonSound;

    private SparseArray<String> keyValues = new SparseArray<>();
    private InputConnection inputConnection;

    public MyKeyboard(Context context) {
        this(context, null, 0);
    }

    public MyKeyboard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyKeyboard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @SuppressLint("CutPasteId")
    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.keyboard, this, true);
        button1 = (Button) findViewById(R.id.button_1);
        PushDownAnim.setPushDownAnimTo(button1).setScale( MODE_SCALE, 0.95f  ).setOnClickListener(this);
        button2 = (Button) findViewById(R.id.button_2);
        PushDownAnim.setPushDownAnimTo(button2).setScale( MODE_SCALE, 0.95f  ).setOnClickListener(this);
        button3 = (Button) findViewById(R.id.button_3);
        PushDownAnim.setPushDownAnimTo(button3).setScale( MODE_SCALE, 0.95f  ).setOnClickListener(this);
        button4 = (Button) findViewById(R.id.button_4);
        PushDownAnim.setPushDownAnimTo(button4).setScale( MODE_SCALE, 0.95f  ).setOnClickListener(this);
        button5 = (Button) findViewById(R.id.button_5);
        PushDownAnim.setPushDownAnimTo(button5).setScale( MODE_SCALE, 0.95f  ).setOnClickListener(this);
        button6 = (Button) findViewById(R.id.button_6);
        PushDownAnim.setPushDownAnimTo(button6).setScale( MODE_SCALE, 0.95f  ).setOnClickListener(this);
        button7 = (Button) findViewById(R.id.button_7);
        PushDownAnim.setPushDownAnimTo(button7).setScale( MODE_SCALE, 0.95f  ).setOnClickListener(this);
        button8 = (Button) findViewById(R.id.button_8);
        PushDownAnim.setPushDownAnimTo(button8).setScale( MODE_SCALE, 0.95f  ).setOnClickListener(this);
        button9 = (Button) findViewById(R.id.button_9);
        PushDownAnim.setPushDownAnimTo(button9).setScale( MODE_SCALE, 0.95f  ).setOnClickListener(this);
        button0 = (Button) findViewById(R.id.button_0);
        PushDownAnim.setPushDownAnimTo(button0).setScale( MODE_SCALE, 0.95f  ).setOnClickListener(this);
        buttonDelete = (Button) findViewById(R.id.button_delete);
        PushDownAnim.setPushDownAnimTo(buttonDelete).setScale( MODE_SCALE, 0.95f  ).setOnClickListener(this);
        buttonEnter = (Button) findViewById(R.id.button_enter);
        PushDownAnim.setPushDownAnimTo(buttonEnter).setScale( MODE_SCALE, 0.95f  ).setOnClickListener(this);
        buttonAC = (Button) findViewById(R.id.button_AC);
        PushDownAnim.setPushDownAnimTo(buttonAC).setScale( MODE_SCALE, 0.95f  ).setOnClickListener(this);

        buttonOff = (Button) findViewById(R.id.button_off);
        PushDownAnim.setPushDownAnimTo(buttonOff).setScale( MODE_SCALE, 0.95f  ).setOnClickListener(this);
        buttonFunc = (Button) findViewById(R.id.button_func);
        PushDownAnim.setPushDownAnimTo(buttonFunc).setScale( MODE_SCALE, 0.95f  ).setOnClickListener(this);


        keyValues.put(R.id.button_1, "1");
        keyValues.put(R.id.button_2, "2");
        keyValues.put(R.id.button_3, "3");
        keyValues.put(R.id.button_4, "4");
        keyValues.put(R.id.button_5, "5");
        keyValues.put(R.id.button_6, "6");
        keyValues.put(R.id.button_7, "7");
        keyValues.put(R.id.button_8, "8");
        keyValues.put(R.id.button_9, "9");
        keyValues.put(R.id.button_0, "0");






    }

    @Override
    public void onClick(View view) {

        if (Fragment_calc.getTVres().getText().length() != 0){
            Fragment_calc.getTVres().setText("");
            Fragment_calc.getNumber().setText("");

        }


        if (Fragment_calc.isOnOff()){
            Fragment_calc.getBeepSound().start();

        }

        if (inputConnection == null) {
            return;
        }

        if (view.getId() == R.id.button_off){
            new AlertDialog.Builder(MainActivity.getMainCntxt())
                    .setTitle("Exit")
                    .setMessage("Sicuro di voler uscire?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            System.exit(0);                        }
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();


            return;
        }

        if (view.getId() == R.id.button_AC){
            Fragment_calc.getTVres().setText("");
            Fragment_calc.getNumber().setText("");
            return;
        }




        if (view.getId() == R.id.button_func){
            if (Fragment_calc.isOnOff()){
                Fragment_calc.getBeepSound().start();

            }
            return;
        }


        if (view.getId() == R.id.button_delete) {
            CharSequence selectedText = inputConnection.getSelectedText(0);

            if (TextUtils.isEmpty(selectedText)) {
                inputConnection.deleteSurroundingText(1, 0);
            } else {
                inputConnection.commitText("", 1);
            }
        } else {
            if (Fragment_calc.getNumber().length() == 6){
                Toast.makeText(Fragment_calc.getCalcContxt(), "Max caracter", Toast.LENGTH_SHORT).show();

            }
            String value = keyValues.get(view.getId());
            inputConnection.commitText(value, 1);
        }
    }

    public void setInputConnection(InputConnection ic) {
        inputConnection = ic;
    }
}
