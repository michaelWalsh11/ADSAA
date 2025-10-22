package StockTrade;

import java.util.HashMap;

/**
 * Represents a stock exchange. A StockExchange keeps a HashMap of stocks, keyed by a stock symbol.
 * It has methods to list a new stock, request a quote for a given stock symbol, and to place a specified trade order.
 */
public class StockExchange {

    private HashMap<String, Stock> listedStocks;

    /**
     * Constructs a new stock exchange object.
     * Initializes listed stocks to an empty map (a HashMap).
     */
    public StockExchange()
    {
        listedStocks = new HashMap<>();
    }

    /**
     * Adds a new stock with given parameters to the listed stocks.
     * @param ticker stock symbol.
     * @param name name of company
     * @param price price of a share
     */
    public void listStock(String ticker, String name, double price)
    {
        listedStocks.put(ticker, new Stock(ticker, name, price));
    }

    /**
     * Places a trade order by calling stock.placeOrder
     * for the stock specified by the stock symbol in the trade order.
     * @param order the order to be placed with the stock exchange
     */
    public void placeOrder(TradeOrder order)
    {
        String ticker = order.getSymbol();
        Stock stock = listedStocks.get(ticker);

        if (stock != null)
        {
            stock.placeOrder(order);
        }
        else {
            Trader trader = order.getTrader();
            trader.receiveMessage(ticker + "not found");
        }
    }

    /**
     * Returns a quote for a given stock.
     * @param ticker stock symbol
     * @return a message that contains the quote.
     */
    public String getQuote(String ticker)
    {
        Stock stock = listedStocks.get(ticker);
        if (stock == null)
        {
            return ticker + "not found";
        }
        return stock.getQuote();
    }
}
