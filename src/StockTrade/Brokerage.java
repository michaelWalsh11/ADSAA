package StockTrade;

import java.util.*;

/**
 * Represents a brokerage
 */
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

    /**
     * Tries to register a new trader with a given screen name and password. If successful, creates a Trader object for this
     * trader and adds this trader to the map of all traders (using the screen name as the key).
     * @param userName the screen name of the user.
     * @param password the password for the user.
     *
     * @return 0 if successful, or an error code (a negative integer) if failed:
     * -1 -- invalid screen name (must be 4-10 chars)
     * -2 -- invalid password (must be 2-10 chars)
     * -3 -- the screen name is already taken.
     */
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

    /**
     * Tries to log in a trader with a given screen name and password. If no messages are waiting for the trader, sends a "Welcome to SafeTrade!"
     * message to the trader. Opens a dialog window for the trader by calling trader's openWindow() method. Adds the trader to the set of all logged-in traders.
     * @param userName the screen name of the user.
     * @param password the password for the user.
     *
     * @return 0 if successful, or an error code (a negative integer) if failed:
     * -1 -- screen name not found
     * -2 -- invalid password
     * -3 -- user is already logged in.
     */
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

        user.openWindow();
        activeUsers.add(user);
        return 0;
    }

    /**
     * Requests a quote for a given stock from the stock exachange and passes it along to the trader by calling
     * trader's receiveMessage method.
     * @param ticker the stock ticker
     * @param trader the trader requesting the quote
     */
    public void getQuote(String ticker, Trader trader)
    {
        trader.receiveMessage(exchange.getQuote(ticker));
    }

    /**
     * Places an order at the stock exchange.
     * @param order the order to be placed.
     */
    public void placeOrder(TradeOrder order)
    {
        exchange.placeOrder(order);
    }

    /**
     * Removes a specified trader from the set of logged-in traders.
     * @param trader the trader being logged out.
     */
    public void logout(Trader trader)
    {
        activeUsers.remove(trader);
    }
}
