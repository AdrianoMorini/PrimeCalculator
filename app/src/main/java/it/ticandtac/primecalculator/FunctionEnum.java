package it.ticandtac.primecalculator;

public enum FunctionEnum {

    ISPRIME(R.string.isPrime, R.string.isPrimeDes, "IsPrime", "showPrime"),
    PRIMENUMBERSCOUNT(R.string.numberPrimeLessN, 0,"PrimeNumbersCount","showNumberPrimeLessN"),
    FACTPRIMEN(R.string.FactPrimeN, 0, "FactPrimeN", "showFact"),
    EULERO(R.string.Eulero, 0,"Euler0","showEul"),
    NTWINS(R.string.NTwins, 0, "NTwins", "showNTwins"),
    NPRIME(R.string.NPrime, 0, "NPrime", "showNPrime");

    private int titleRes;
    private int desRes;
    private String functionName;
    private String handleResultFun;
    private FunctionEnum(int titleRes, int desRes, String functionName, String handleResultFun){
        this.titleRes = titleRes;
        this.desRes = desRes;
        this.functionName = functionName;
        this.handleResultFun = handleResultFun;
    }

    public int getTitleRes(){
        return titleRes;
    }

    public int getDesRes() {
        return desRes;
    }

    public String getFunctionName(){
        return functionName;
    }

    public String getHandleResultFun() {
        return handleResultFun;
    }
}
