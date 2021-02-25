package atm_emulator.command;

import atm_emulator.Operation;
import atm_emulator.exception.InterruptOperationException;

import java.util.HashMap;
import java.util.Map;

/*
 * The class fills the map<Operation, Command> with all known operations and commands
 * and allows you to interact with all commands.
 */

public class CommandExecutor {
    private final static Map<Operation, Command> allKnownCommandsMap = new HashMap<>();

    static {
        allKnownCommandsMap.put(Operation.LOGIN, new LoginCommand());
        allKnownCommandsMap.put(Operation.INFO, new InfoCommand());
        allKnownCommandsMap.put(Operation.DEPOSIT, new DepositCommand());
        allKnownCommandsMap.put(Operation.WITHDRAW, new WithdrawCommand());
        allKnownCommandsMap.put(Operation.EXIT, new ExitCommand());
    }

    private CommandExecutor() { }

    //invokes the appropriate execute method on the operation
    public static final void execute(Operation operation) throws InterruptOperationException {
        if (operation != null) {
            allKnownCommandsMap.get(operation).execute();
        }
    }

}
