package it.ticandtac.primecalculator.AsyncTask;

import android.os.AsyncTask;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import it.ticandtac.primecalculator.OtherFunction.MyFuctions;
import it.ticandtac.primecalculator.ShowResultInterface;

public class AsyncTaskCalc extends AsyncTask<Void, Void, Object> {

    private  AsyncTaskPayload payload;
    private ShowResultInterface showResultInterface;

    public AsyncTaskCalc (AsyncTaskPayload payload, ShowResultInterface showResultInterface) {
        this.payload = payload;
        this.showResultInterface = showResultInterface;
    }

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

    @Override
    protected void onPostExecute(Object result){
        this.showResultInterface.showResult(result, this.payload.getFunction());

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.showResultInterface.onPreExecute();

    }
}
