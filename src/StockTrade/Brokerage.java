package StockTrade;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Brokerage implements Login
{
    private StockExchange exchange;
    private TreeMap<String, Trader> userList;
    private Set<Trader> activeUsers;
    private TraderWindow window;

    public Brokerage(StockExchange exchange)
    {
        this.exchange = exchange;
        userList = new TreeMap<>();
        activeUsers = new HashSet<>();
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
        Trader tempUser = new Trader(this, userName, password);

        if (!userList.containsKey(userName))
        {
            return -1;
        }
        else if (!userList.get(userName).getPassword().equals(password))
        {
            return -2;
        }
        else if (activeUsers.contains(tempUser))
        {
            return -3;
        }

        //how do I open the window
        tempUser.openWindow();
        activeUsers.add(tempUser);
        return 0;
    }

    public void getQuote(String ticker, Trader trader)
    {
        trader.receiveMessage(trader.getName() +
                " placed an order for " + ticker + ".");
    }

    public void placeOrder(TradeOrder order)
    {
        this.exchange.placeOrder(order);
    }

    public void logout(Trader trader)
    {
        activeUsers.remove(trader);
    }
}
