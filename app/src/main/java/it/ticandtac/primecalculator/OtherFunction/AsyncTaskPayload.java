package it.ticandtac.primecalculator.OtherFunction;

public class AsyncTaskPayload {
    private FunctionEnum function;
    private long value;


    public AsyncTaskPayload(FunctionEnum function, long value) {
        this.function = function;
        this.value = value;
    }

    public FunctionEnum getFunction() {
        return function;
    }

    public void setFunction(FunctionEnum function) {
        this.function = function;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
