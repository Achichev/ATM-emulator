package atm_emulator.command;

import atm_emulator.CashMachine;
import atm_emulator.ConsoleHelper;
import atm_emulator.CurrencyManipulator;
import atm_emulator.CurrencyManipulatorFactory;

import java.util.Collection;
import java.util.ResourceBundle;

class InfoCommand implements Command {
    //resource bundle to info_en.properties
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info");

    //displays information about the amount of money withdrawn or its absence for withdrawal
    @Override
    public void execute() {
        ConsoleHelper.writeMessage(res.getString("before"));
        boolean hasMoney = false;
        for (CurrencyManipulator manipulator : CurrencyManipulatorFactory.getAllCurrencyManipulators()) {
            if (manipulator.hasMoney()) {
                hasMoney = true;
                ConsoleHelper.writeMessage("\t" + manipulator.getCurrencyCode() + " - " + manipulator.getTotalAmount());
            }
        }

        if (!hasMoney) {
            ConsoleHelper.writeMessage(res.getString("no.money"));
        }
    }
}
