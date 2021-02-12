package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;
import java.util.*;

/*
 * Stores information about specific currency
 */
public class CurrencyManipulator {
    //a currency code, USD, for example. Consists of three letters
    private String currencyCode;

    //Map<denomination, amount of denominations>
    private Map<Integer, Integer> denominations = new HashMap<>();

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    //emulates depositing funds into an ATM
    public void addAmount(int denomination, int count) {
        if (denominations.containsKey(denomination)) {
            count = count + denominations.get(denomination);
            denominations.put(denomination, count);
        } else denominations.put(denomination, count);

    }

    //returns the balance
    public int getTotalAmount() {
        int totalAmount = 0;
        for (Map.Entry<Integer, Integer> entry: denominations.entrySet()) {
            totalAmount += entry.getKey() * entry.getValue();
        }
        return totalAmount;
    }

    public boolean hasMoney() {
        return getTotalAmount() > 0;
    }

    //checks the availability of the requested amount of money in the ATM
    public boolean isAmountAvailable(int expectedAmount) {
        return expectedAmount <= getTotalAmount();
    }

    //emulates ATM withdrawal, returns the map with denominations as keys and the numbers of each denomination
    //as values, which withdrawn
    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        List<Integer> billList = new ArrayList<>(denominations.keySet());
        Collections.sort(billList);
        Collections.reverse(billList);
        //map with denominations as keys and numbers of each denomination as values to withdraw
        Map<Integer, Integer> resultMap = new HashMap<>();
        int leftAmount = expectedAmount;
        for (int bill: billList) {
            if (bill < leftAmount) {
                int amount = leftAmount/bill;
                resultMap.put(bill, amount);
                leftAmount -= bill * amount;
            } else if (bill == leftAmount) {
                resultMap.put(bill, 1);
                leftAmount = 0;
                break;
            }
        }
        if (leftAmount != 0) {
            throw new NotEnoughMoneyException();
        }

        //temporary map with denominations as keys and numbers of each denomination as values
        //left in ATM after withdrawal
        Map<Integer, Integer> map = new HashMap<>();
        for (int bill: billList) {
            if (resultMap.containsKey(bill)) {
                int amount = resultMap.get(bill);
                int totalAmount = denominations.get(bill);
                map.put(bill, totalAmount - amount);
            } else {
                map.put(bill, denominations.get(bill));
            }
        }
        map.entrySet().removeIf(e -> e.getValue() == 0);
        //transfering temporary map content to permanent map
        denominations.clear();
        denominations.putAll(map);
        return resultMap;
    }
}
