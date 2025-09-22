package StockTrade;

import java.util.HashMap;


public class StockExchange {

    private HashMap<String, Stock> listedStocks;

    public StockExchange()
    {
        listedStocks = new HashMap<>();
    }

    public void listStock(String ticker, String name, double price)
    {
        listedStocks.put(ticker, new Stock(ticker, name, price));
    }

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
