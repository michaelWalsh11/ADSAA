package StockTrade;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.PriorityQueue;

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
                    "Trade: " + sharesTraded + " shares of " + ticker + " at " + tradePrice);
            sellOrder.getTrader().receiveMessage(
                    "Trade: " + sharesTraded + " shares of " + ticker + " at " + tradePrice);

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