package atm_emulator.command;

import atm_emulator.CashMachine;
import atm_emulator.ConsoleHelper;
import atm_emulator.exception.InterruptOperationException;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginCommand implements Command {
    //resource bundle to verifiedCards.properties
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    //resource bundle to login_en.properties
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login");

    //Requests the credit card number and PIN and verifies if they are valid
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            String card = ConsoleHelper.readString();
            String pin = ConsoleHelper.readString();
            Pattern p = Pattern.compile("\\d+");
            Matcher m1 = p.matcher(card);
            Matcher m2 = p.matcher(pin);
            if (m1.matches() && card.length() == 12 && m2.matches() && pin.length() == 4) {
                if (validCreditCards.containsKey(card) && pin.equals(validCreditCards.getString(card))) {
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"), card));
                    break;
                } else {
                    ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), card));
                    ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                }
            } else {
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
            }
        }
    }
}
