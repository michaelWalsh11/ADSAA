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
    private double volume;
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
        this.volume = 0.0;
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
        if (order.isBuyOrder())
        {
            TradeOrder topSell = sell.peek();
            if (topSell != null && order.getPrice() >= topSell.getPrice())
            {
                lastPrice = order.getPrice();
                int traded = Math.min(order.getNumShares(), topSell.getNumShares());

                order.subtractShares(traded);
                topSell.subtractShares(traded);

                volume += traded;

                if (topSell.getNumShares() == 0)
                {
                    sell.poll();
                }

                if (order.getNumShares() > 0)
                {
                    placeOrder(order);
                }
            }
            else
            {
                buy.add(order);
            }
        }
        else
        {
            TradeOrder topBuy = buy.peek();
            if (topBuy != null && order.getPrice() <= topBuy.getPrice())
            {
                lastPrice = order.getPrice();
                int traded = Math.min(order.getNumShares(), topBuy.getNumShares());

                order.subtractShares(traded);
                topBuy.subtractShares(traded);

                volume += traded;

                if (topBuy.getNumShares() == 0)
                {
                    buy.poll();
                }

                if (order.getNumShares() > 0)
                {
                    placeOrder(order);
                }
            }
            else
            {
                sell.add(order);
            }
        }

        System.out.println(getQuote());
    }
}