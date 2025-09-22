package StockTrade;

import java.util.LinkedList;
import java.util.Queue;

public class Trader
{

    private Brokerage brokerage;
    private String name;
    private String password;
    private TraderWindow myWindow;

    private Queue<String> mailbox = new LinkedList<>();

    public Trader(Brokerage brokerage, String name, String pswd)
    {
        this.brokerage = brokerage;
        this.name = name;
        this.password = pswd;
    }

    public String getName() {
        return name;
    }

    public String getPassword()
    {
        return password;
    }

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

    public boolean equals(Object other)
    {
        String tn = this.getName().toLowerCase();
        String on = ((Trader) other).getName().toLowerCase();

        return tn.equals(on);
    }

    public void openWindow()
    {
        myWindow = new TraderWindow(this);
        removeAndShow();
    }

    public boolean hasMessages()
    {
        return mailbox.isEmpty();
    }

    public void receiveMessage(String msg)
    {
        mailbox.add(msg);
        if (myWindow != null)
        {
            removeAndShow();
        }
    }

    public void getQuote(String symbol)
    {
        brokerage.getQuote(symbol, this);
    }

    public void placeOrder(TradeOrder order)
    {
        brokerage.placeOrder(order);
    }

    public void quit()
    {
        brokerage.logout(this);
        myWindow = null;
    }

    private void removeAndShow()
    {
        while(!mailbox.isEmpty())
        {
            myWindow.showMessage(mailbox.poll());
        }
    }
}
