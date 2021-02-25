package atm_emulator;

import atm_emulator.command.CommandExecutor;
import atm_emulator.exception.InterruptOperationException;

import java.util.Locale;

public class CashMachine {
    //the variable contains path to resources package
    public static final String RESOURCE_PATH = CashMachine.class.getPackage().getName() + ".resources.";

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        try {
            Operation loginOperation = Operation.LOGIN;
            CommandExecutor.execute(loginOperation);
            Operation operation;
            do {
                operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            } while (operation != Operation.EXIT);
        } catch (InterruptOperationException e) {
            ConsoleHelper.printExitMessage();
        }
    }
}
