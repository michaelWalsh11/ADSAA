package StockTrade;

import java.util.HashMap;


public class StockExchange {

    private HashMap<String, Stock> exchange;

    public StockExchange()
    {
        exchange = new HashMap<>();
    }

    public void listStock(String ticker, String name, double price)
    {
        exchange.put(ticker, new Stock(ticker, name, price));
    }

    public void placeOrder(TradeOrder order)
    {
        exchange.get(order.getSymbol()).placeOrder(order);
    }

    public String getQuote(String ticker)
    {
        return exchange.get(ticker).getQuote();
    }
}
