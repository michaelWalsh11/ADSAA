package StockTrade;

/**
 * HONOR PLEDGE: All work here is honestly obtained and is my own.  Signed:  Michael Walsh
 * @author walshm
 * Date of Completion:  9/21/2025
 * <p>
 * Assignment:   	SafeTrade
 * <p>
 * Attribution: I worked with pretty much everone in the class at some point, but mostly Aubrey and Solee
 *            until Sunday when i kind of branched out and figured out some problems on my own.
 * <p>
 * General Description:   This uses a GUI given to us and creates the entire backend for the GUI operating
 *                        the basic functions of a stockMarket from creating stocks that manage buy and sell
 *                        orders on them to create a stockExchange which manages all the stocks. It also includes
 *                        a brokerage which manages the people interacting with the market and holds a list of all
 *                        the traders, which is another class that was created to essentially act as a person who
 *                        interacts with the market and holds messages to give the users to inform them about their
 *                        interactions with the market. That is a basic summary of the StockMarket project
 */

import javax.swing.JFrame;
import java.util.Comparator;

public class SafeTrade
{
  public static void main(String[] args)
  {
    StockExchange exchange = new StockExchange();
    exchange.listStock("ELB", "ElevensLab", 12.33);
    exchange.listStock("NSTL", "Nasty Loops Inc.", 0.25);
    exchange.listStock("GGGL", "Giggle.com", 28.00);
    exchange.listStock("MATI", "M and A Travel Inc.", 28.20);
    exchange.listStock("DDLC", "Dulce De Leche Corp.", 57.50);
    exchange.listStock("SAFT", "SafeTrade.com Inc.", 322.45);

    Brokerage safeTrade = new Brokerage(exchange);
    safeTrade.addUser("stockman", "sesame");
    safeTrade.login("stockman", "sesame");
    safeTrade.addUser("mstrade", "bigsecret");
    safeTrade.login("mstrade", "bigsecret");

    LoginWindow window = new LoginWindow("Safe Trade", safeTrade);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setBounds(0, 0, 360, 140);
    window.setVisible(true);
  }
}
