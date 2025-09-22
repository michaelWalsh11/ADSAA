package StockTrade;

import java.util.*;

public class Brokerage implements Login
{
    private StockExchange exchange;
    private Map<String, Trader> userList;
    private Set<Trader> activeUsers;

    public Brokerage(StockExchange exchange)
    {
        this.exchange = exchange;
        userList = new TreeMap<>();
        activeUsers = new TreeSet<>();
    }

    public int addUser(String userName, String password)
    {
        if (userName.length() < 4 || userName.length() > 10)
        {
            return -1;
        }
        else if (password.length() < 2 || password.length() > 10)
        {
            return -2;
        }
        else if (userList.containsKey(userName))
        {
            return -3;
        }

        userList.put(userName, new Trader(this, userName, password));
        return 0;
    }

    public int login(String userName, String password)
    {
        Trader user = userList.get(userName);

        if (!userList.containsKey(userName))
        {
            return -1;
        }
        else if (!userList.get(userName).getPassword().equals(password))
        {
            return -2;
        }
        else if (activeUsers.contains(user))
        {
            return -3;
        }

        if (!user.hasMessages())
        {
            user.receiveMessage("Welcome to SafeTrade!");
        }

        //how do I open the window
        user.openWindow();
        activeUsers.add(user);
        return 0;
    }

    public void getQuote(String ticker, Trader trader)
    {
        trader.receiveMessage(exchange.getQuote(ticker));
    }

    public void placeOrder(TradeOrder order)
    {
        exchange.placeOrder(order);
    }

    public void logout(Trader trader)
    {
        activeUsers.remove(trader);
    }
}
