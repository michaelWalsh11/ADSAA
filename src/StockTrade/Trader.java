package StockTrade;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Represents a stock trader.
 */
public class Trader implements Comparable<Trader>
{

    private Brokerage brokerage;
    private String name;
    private String password;
    private TraderWindow myWindow;

    private Queue<String> mailbox;

    public Trader(Brokerage brokerage, String name, String pswd)
    {
        this.brokerage = brokerage;
        this.name = name;
        this.password = pswd;
        this.mailbox = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public String getPassword()
    {
        return password;
    }

    /**
     * Compares this trader to another by comparing their screen names case blind.
     * @param other the object to be compared.
     * @return
     */
    public int compareTo(Trader other)
    {
        String tn = this.getName().toLowerCase();
        String on = other.getName().toLowerCase();

        if (tn.compareTo(on) > 0)
        {
            return 1;
        }

        if (tn.compareTo(on) < 0)
        {
            return -1;
        }

        return 0;
    }

    /**
     * Indicates whether some other trader is "equal to" this one, based on comparing their screen names case blind.
     * @param other the other object to compare to
     * @return
     */
    public boolean equals(Object other)
    {
        String tn = this.getName().toLowerCase();
        String on = ((Trader) other).getName().toLowerCase();

        return tn.equals(on);
    }

    /**
     * Creates a new TraderWindow for this trader and saves a reference to it in myWindow.
     */
    public void openWindow()
    {
        myWindow = new TraderWindow(this);
        removeAndShow();
    }

    public boolean hasMessages()
    {
        return !mailbox.isEmpty();
    }

    /**
     * Adds msg to this trader's mailbox and displays all messages.
     * @param msg the message to add
     */
    public void receiveMessage(String msg)
    {
        mailbox.add(msg);
        if (myWindow != null)
        {
            removeAndShow();
        }
    }

    /**
     * Requests a quote for a given stock symbol from the brokerage by calling brokerage's getQuote.
     * @param symbol the ticker of the stock
     */
    public void getQuote(String symbol)
    {
        brokerage.getQuote(symbol, this);
    }

    /**
     * Places a given order with the brokerage by calling brokerage's placeOrder.
     * @param order
     */
    public void placeOrder(TradeOrder order)
    {
        brokerage.placeOrder(order);
    }

    /**
     * Logs out the trader
     */
    public void quit()
    {
        brokerage.logout(this);
        myWindow = null;
    }

    /**
     * Helper method that shows all the messages in the mailbox and shows them.
     */
    private void removeAndShow()
    {
        while(!mailbox.isEmpty())
        {
            myWindow.showMessage(mailbox.poll());
        }
    }
}
