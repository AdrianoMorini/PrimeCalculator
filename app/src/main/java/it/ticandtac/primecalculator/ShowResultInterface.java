package it.ticandtac.primecalculator;

import it.ticandtac.primecalculator.OtherFunction.FunctionEnum;

//Interface for reflection.

public interface ShowResultInterface {
    void showResult(Object result, FunctionEnum function);
    void onPreExecute();
    void onPostExecute();
}
