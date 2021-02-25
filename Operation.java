package atm_emulator;

/*
 * Contains all necessary ATM operations
 */
public enum Operation {
    LOGIN,
    INFO,
    DEPOSIT,
    WITHDRAW,
    EXIT;

    //returns a specific operation associated with its ordinal value
    public static Operation getAllowableOperationByOrdinal(Integer i) {
        Operation operation;
        switch (i) {
            case 0:
                throw new IllegalArgumentException();
            case 1:
                operation = Operation.INFO;
                break;
            case 2:
                operation = Operation.DEPOSIT;
                break;
            case 3:
                operation = Operation.WITHDRAW;
                break;
            case 4:
                operation = Operation.EXIT;
                break;
            default:
                throw new IllegalArgumentException();
        }
        return operation;
    }
}
