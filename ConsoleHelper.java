package atm_emulator;

import atm_emulator.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * The class is responsible for reading messages from the console, printing them and returns the desired operation
 */
public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    //resource bundle to common_en.properties
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common");

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        String s = null;
        try {
            s = bis.readLine();
            if (s.equalsIgnoreCase("EXIT")) throw new InterruptOperationException();
        } catch (IOException ignored) {
        }
        return s;
    }

    //reads and returns the code of the entered currency
    public static String askCurrencyCode() throws InterruptOperationException {
        String currencyCode = "";
        while (true) {
            ConsoleHelper.writeMessage(res.getString("choose.currency.code"));
            currencyCode = readString();
            if (currencyCode != null) {
                Pattern p = Pattern.compile("[a-zA-Z]{3}");
                Matcher m = p.matcher(currencyCode);
                if (m.matches())break;
            } else {
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
            }
        }
        return currencyCode.toUpperCase();
    }

    //reads denomination and bills amount and returns a string array
    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        String digits = "";
        String[] strings;
        while (true) {
            ConsoleHelper.writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
            digits = readString();
            if (digits != null) {
                Pattern p = Pattern.compile("\\d+\\s\\d+");
                Matcher m = p.matcher(digits);
                if (m.matches()) {
                    strings = digits.split(" ");
                    if (Integer.parseInt(strings[0]) > 0 && Integer.parseInt(strings[1]) > 0) {
                        break;
                    } else ConsoleHelper.writeMessage(res.getString("invalid.data"));
                }
            } else {
                ConsoleHelper.writeMessage("Please specify valid data.");
            }
        }
        return strings;
    }

    //requests to choose an operation and returns it
    public static Operation askOperation() throws InterruptOperationException {
        while (true) {
            ConsoleHelper.writeMessage(res.getString("choose.operation"));
            ConsoleHelper.writeMessage("\t 1 - " + res.getString("operation.INFO"));
            ConsoleHelper.writeMessage("\t 2 - " + res.getString("operation.DEPOSIT"));
            ConsoleHelper.writeMessage("\t 3 - " + res.getString("operation.WITHDRAW"));
            ConsoleHelper.writeMessage("\t 4 - " + res.getString("operation.EXIT"));

            try {
                return Operation.getAllowableOperationByOrdinal(Integer.parseInt(readString()));
            } catch (IllegalArgumentException e) {
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
            }
        }
    }

    public static void printExitMessage() {
        ConsoleHelper.writeMessage("Terminated. Thank you for visiting JavaRush cash machine. Good luck.");
    }
}
