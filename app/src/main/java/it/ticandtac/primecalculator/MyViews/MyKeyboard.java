package it.ticandtac.primecalculator.MyViews;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.thekhaeng.pushdownanim.PushDownAnim;
import java.util.ArrayList;
import java.util.List;
import it.ticandtac.primecalculator.Fragment.Fragment_calc;
import it.ticandtac.primecalculator.MainActivity;
import it.ticandtac.primecalculator.R;
import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

//Keyboard functions.

public class MyKeyboard extends RelativeLayout implements View.OnClickListener {

    private List<Button> buttons;
    private static Button buttonDelete;

    public static Button getButtonFunc() {
        return buttonFunc;
    }

    private static Button buttonFunc;

    public static Button getButtonEnter() {
        return buttonEnter;
    }

    private static Button buttonEnter;

    public static Button getButtonAC() {
        return buttonAC;
    }

    private static Button buttonAC;
    private Button buttonOff;

    public static InputConnection getIc() {
        return ic;
    }

    private static InputConnection ic;


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

    public static Button getButtonDelete() {
        return buttonDelete;
    }

    @SuppressLint("CutPasteId")
    private void init(Context context, AttributeSet attrs) {
        buttons = new ArrayList<>();
        LayoutInflater.from(context).inflate(R.layout.keyboard, this, true);
        buttons.add((Button) findViewById(R.id.button_0));
        buttons.add((Button) findViewById(R.id.button_1));
        buttons.add((Button) findViewById(R.id.button_2));
        buttons.add((Button) findViewById(R.id.button_3));
        buttons.add((Button) findViewById(R.id.button_4));
        buttons.add((Button) findViewById(R.id.button_5));
        buttons.add((Button) findViewById(R.id.button_6));
        buttons.add((Button) findViewById(R.id.button_7));
        buttons.add((Button) findViewById(R.id.button_8));
        buttons.add((Button) findViewById(R.id.button_9));
        for (int i = 0; i < buttons.size(); i++){
            final int value = i;
            Button button = buttons.get(i);
            PushDownAnim.setPushDownAnimTo(button).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Fragment_calc.isOnOff()){
                        Fragment_calc.getBeepSound().start();
                    }

                    if (Fragment_calc.getTVres().getText() != "" && !Fragment_calc.getNumber().getText().toString().equals("")){
                        Fragment_calc.getTVres().setText("");
                        Fragment_calc.getNumber().setText("");
                    }

                    if (Fragment_calc.getNumber().getText().toString().equals("0")){
                        Fragment_calc.getNumber().setText("");

                    }
                    inputConnection.commitText(Integer.toString(value, 10), 1);
                }
            }).setScale(0.95f);
        }
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
            createAppExitInfoDialog().show();
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
        }
    }

    //Alert dialog to exit.

    public Dialog createAppExitInfoDialog() {
        View alertView = View.inflate(MainActivity.getMainCntxt(), R.layout.app_exit, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.getMainCntxt());

        TextView title = alertView.findViewById(R.id.exitTitle);
        TextView description = alertView.findViewById(R.id.Te1);
        title.setText(R.string.closeApp);
        description.setText(R.string.exit);
        builder.setNegativeButton(R.string.no,null);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.exit(0);
            }

        });


        builder.setView(alertView);
        builder.setCancelable(true);
        return builder.create();
    }

    public void setInputConnection(InputConnection ic) {
        inputConnection = ic;
    }
}
