package it.ticandtac.primecalculator;

public enum FunctionEnum {

    ISPRIME(R.string.isPrime, R.string.isPrimeDes, "IsPrime", "showPrime",0),
    PRIMENUMBERSCOUNT(R.string.numberPrimeLessN, R.string.primeNumCountDes,"PrimeNumbersCount","showNumberPrimeLessN",R.string.primeNumCountID),
    FACTPRIMEN(R.string.FactPrimeN, R.string.factPrimeDes, "FactPrimeN", "showFact",R.string.showFactID),
    EULERO(R.string.Eulero, R.string.euleroDes,"Euler0","showEul",R.string.euleroID),
    NTWINS(R.string.NTwins, R.string.nTwinsDes, "NTwins", "showNTwins",R.string.nTwinsID),
    NPRIME(R.string.NPrime, R.string.nPrimeDes, "NPrime", "showNPrime",R.string.nPrimeID);

    private int titleRes;
    private int desRes;
    private String functionName;
    private String handleResultFun;
    private int functionID;


    private FunctionEnum(int titleRes, int desRes, String functionName, String handleResultFun, int functionID){
        this.titleRes = titleRes;
        this.desRes = desRes;
        this.functionName = functionName;
        this.handleResultFun = handleResultFun;
        this.functionID = functionID;
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
    public int getFunctionID() {
        return functionID;
    }


}
