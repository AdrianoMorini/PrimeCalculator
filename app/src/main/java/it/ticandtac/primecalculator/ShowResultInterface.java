package it.ticandtac.primecalculator;

import it.ticandtac.primecalculator.OtherFunction.FunctionEnum;

public interface ShowResultInterface {
    void showResult(Object result, FunctionEnum function);
    void onPreExecute();
}
