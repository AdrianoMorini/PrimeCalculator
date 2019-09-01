package it.ticandtac.primecalculator.AsyncTask;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.view.View;
import com.agrawalsuneet.dotsloader.loaders.TrailingCircularDotsLoader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import it.ticandtac.primecalculator.Fragment.Fragment_calc;
import it.ticandtac.primecalculator.MyViews.MyKeyboard;
import it.ticandtac.primecalculator.OtherFunction.MyFuctions;
import it.ticandtac.primecalculator.ShowResultInterface;

//AsyncTask for execution.

public class AsyncTaskCalc extends AsyncTask<Void, Void, Object> {

    private  AsyncTaskPayload payload;
    private ShowResultInterface showResultInterface;

    public AsyncTaskCalc (AsyncTaskPayload payload, ShowResultInterface showResultInterface) {
        this.payload = payload;
        this.showResultInterface = showResultInterface;
    }

    //Background reflection.

    @Override
    protected Object doInBackground(Void... voids) {

        Method calcMethod = null;
        Object result = null;

        try {
            calcMethod = MyFuctions.class.getMethod(payload.getFunction().getFunctionName(), long.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        try {
            if (calcMethod != null)result = calcMethod.invoke(MyFuctions.class, payload.getValue());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }

    TrailingCircularDotsLoader trailingCircularDotsLoader = new TrailingCircularDotsLoader(
            Fragment_calc.getCalcContxt(), 24, ContextCompat.getColor(Fragment_calc.getCalcContxt(), android.R.color.holo_blue_dark),
            100,
            5);

    //Enable buttons after execution.

    @Override
    protected void onPostExecute(Object result){
        this.showResultInterface.showResult(result, this.payload.getFunction());
        Fragment_calc.getPicker().setBackgroundColor(Color.parseColor("#363636"));
        Fragment_calc.getBackgroundView().setVisibility(View.INVISIBLE);
        Fragment_calc.getMyTLdots().setVisibility(View.INVISIBLE);
        Fragment_calc.getMyTLdots().removeView(trailingCircularDotsLoader);
        Fragment_calc.getMyTLdots().clearAnimation();
        Fragment_calc.getPicker().setEnabled(true);
        MyKeyboard.getButtonEnter().setClickable(true);
        MyKeyboard.getButtonAC().setClickable(true);
        MyKeyboard.getButtonDelete().setClickable(true);

    }

    //Disable buttons before execution.

    @Override
    protected void onPreExecute() {
        Fragment_calc.getPicker().setBackgroundColor(Color.parseColor("#10000000"));
        Fragment_calc.getBackgroundView().setVisibility(View.VISIBLE);
        Fragment_calc.getMyTLdots().setVisibility(View.VISIBLE);
        trailingCircularDotsLoader.setAnimDuration(1200);
        trailingCircularDotsLoader.setAnimDelay(200);
        Fragment_calc.getMyTLdots().addView(trailingCircularDotsLoader);
        Fragment_calc.getPicker().setEnabled(false);
        MyKeyboard.getButtonEnter().setClickable(false);
        MyKeyboard.getButtonAC().setClickable(false);
        MyKeyboard.getButtonDelete().setClickable(false);
        super.onPreExecute();
        this.showResultInterface.onPreExecute();
    }
}
