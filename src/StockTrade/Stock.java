package StockTrade;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Represents a stock
 */
public class Stock {

    public static DecimalFormat money;

    private String name;
    private String ticker;
    private double lowestSellPriceDay;
    private double highestSellPriceDay;
    private int volume;
    private double lastPrice;

    //looked up some notation for comparators but really just pressed buttons until it worked.
    private PriorityQueue<TradeOrder> buy;
    private PriorityQueue<TradeOrder> sell;

    /**
     * Constructs a new stock with a given symbol, company name, and starting price. Sets low price, high price, and last price to the same opening price.
     * Sets "day" volume to zero. Initializes a priority qieue for sell orders to an empty PriorityQueue with a PriceComparator configured for comparing
     * orders in ascending order; initializes a priority qieue for buy orders to an empty PriorityQueue with a PriceComparator configured for comparing
     * orders in descending order.
     * @param ticker the stock ticker
     * @param name the name of the company
     * @param price the price of a share
     */
    public Stock(String ticker, String name, double price)
    {
        this.name = name;
        this.ticker = ticker;
        this.lowestSellPriceDay = price;
        this.highestSellPriceDay = price;
        this.volume = 0;
        this.lastPrice = price;

        buy = new PriorityQueue<>(
                Comparator.comparingDouble(TradeOrder::getPrice)
                        .reversed());

        sell = new PriorityQueue<>(
            Comparator.comparingDouble(TradeOrder::getPrice));
    }

    /**
     * Returns a quote string for this stock. The quote includes: the company name for this stock;
     * the stock symbol; last sale price; the lowest and highest day prices; the lowest price in a
     * sell order (or "market") and the number of shares in it (or "none" if there are no sell orders);
     * the highest price in a buy order (or "market") and the number of shares in it (or "none" if
     * there are no buy orders). For example:
     *
     *  Giggle.com (GGGL)
     *  Price: 10.00  hi: 10.00  lo: 10.00  vol: 0
     *  Ask: 12.75 size: 300  Bid: 12.00 size: 500
     *
     * Or: Giggle.com (GGGL) Price: 12.00 hi: 14.50 lo: 9.00 vol: 500 Ask: none Bid: 12.50 size: 200
     *
     * @return the afformentioned quote
     */
    public String getQuote()
    {
        String output = name + " " + "(" + ticker + ")" +
                " Price: " + lastPrice + " hi: " + highestSellPriceDay +
                " lo: " + lowestSellPriceDay + " vol: " + volume;
        if (sell.peek() == null )
        {
            System.out.println("Sell.peek is null");
            output += " Ask: " + "none" + " size: " + "none";
        }
        else if (sell.peek() != null)
        {
            output += " Ask: " + sell.peek().getPrice() + " size: " + sell.peek().getNumShares();
        }

        if (buy.peek() == null)
        {
            System.out.println("Buy.peek is null");
            output += " Bid: " + "none" + " size: " + "none";
        }
        else if (buy.peek() != null)
        {
            output += " Bid: " + buy.peek().getPrice() + " size: " + buy.peek().getNumShares();
        }

        return output;
    }

    /**
     * Places a trading order for this stock. Adds the order to the appropriate priority queue depending on whether this is
     * a buy or sell order. Notifies the trader who placed the order that the order has been placed, by sending a message
     * to that trader. For example:
     *
     *  New order:  Buy GGGL (Giggle.com)
     *  200 shares at $38.00
     * Or:
     *  New order:  Sell GGGL (Giggle.com)
     *  150 shares at market
     *
     * Executes pending orders by calling executeOrders.
     * @param order the order to be placed.
     */
    public void placeOrder(TradeOrder order)
    {
        Trader trader = order.getTrader();
        String buyOrSell = "Sell";
        if (order.isBuyOrder())
        {
            buyOrSell = "Buy";
        }

        String price = "market";
        if (!order.isMarket())
        {
            price = String.valueOf(order.getPrice());
        }

        trader.receiveMessage("New order: " + buyOrSell + " " + ticker
                            + " (" + name + ")\n" + order.getNumShares() +
                            " shares at " + price);

        if (order.isBuyOrder())
        {
            buy.add(order);
        }
        else {
            sell.add(order);
        }

        executeOrders();
    }

    private void executeOrders()
    {
        while (!buy.isEmpty() && !sell.isEmpty())
        {
            TradeOrder buyOrder = buy.peek();
            TradeOrder sellOrder = sell.peek();

            double buyPrice = Double.MAX_VALUE;
            if (!buyOrder.isMarket())
            {
                buyPrice = buyOrder.getPrice();
            }

            double sellPrice = 0.0;
            if (!sellOrder.isMarket())
            {
                sellPrice = sellOrder.getPrice();
            }

            if (buyPrice < sellPrice) {
                break;
            }

            int sharesTraded = Math.min(buyOrder.getNumShares(), sellOrder.getNumShares());

            double tradePrice;
            if (buyOrder.isMarket() && sellOrder.isMarket())
            {
                tradePrice = lastPrice;
            }
            else if (buyOrder.isMarket())
            {
                tradePrice = sellOrder.getPrice();
            }
            else if (sellOrder.isMarket())
            {
                tradePrice = buyOrder.getPrice();
            }
            else
            {
                tradePrice = sellOrder.getPrice();
            }

            lastPrice = tradePrice;
            if (lastPrice > highestSellPriceDay)
            {
                highestSellPriceDay = lastPrice;
            }

            if (lastPrice < lowestSellPriceDay)
            {
                lowestSellPriceDay = lastPrice;
            }
            volume += sharesTraded;

            buyOrder.getTrader().receiveMessage(
                    "Trade: " + sharesTraded + " shares of " + ticker + " at " + tradePrice + " for $" + sharesTraded * tradePrice);
            sellOrder.getTrader().receiveMessage(
                    "Trade: " + sharesTraded + " shares of " + ticker + " at " + tradePrice + " for $" + sharesTraded * tradePrice);

            buyOrder.subtractShares(sharesTraded);
            sellOrder.subtractShares(sharesTraded);

            if (buyOrder.getNumShares() == 0)
            {
                buy.remove();
            }
            if (sellOrder.getNumShares() == 0)
            {
                sell.remove();
            }
        }
    }
}