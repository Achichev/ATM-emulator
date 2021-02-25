package atm_emulator.command;

import atm_emulator.CashMachine;
import atm_emulator.ConsoleHelper;
import atm_emulator.exception.InterruptOperationException;

import java.util.ResourceBundle;

class ExitCommand implements Command {
    //resource bundle to exit_en.properties
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "exit");

    //asks the user to confirm the end of the session
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("exit.question.y.n"));
        String reply = ConsoleHelper.readString();
        if (reply.equals("y")) ConsoleHelper.writeMessage(res.getString("thank.message"));

    }
}
