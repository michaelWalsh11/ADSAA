package StockTrade;

public class TradeOrder {
    private Trader trader;
    private String ticker;
    private boolean buyOrder;
    private boolean marketOrder;
    private int numShares;
    private double price;

    public TradeOrder(Trader myTrader, String symbol, boolean buyOrder,
                      boolean marketOrder, int numShares, double price) {
        this.trader = myTrader;
        this.ticker = symbol;
        this.buyOrder = buyOrder;
        this.marketOrder = marketOrder;
        this.numShares = numShares;
        this.price = price;
    }

    public Trader getTrader() {
        return trader;
    }
    public String getSymbol() {
        return ticker;
    }
    public boolean isBuyOrder() {
        return buyOrder;
    }
    public boolean isLimit() {
        return !marketOrder;
    }
    public boolean isMarket() {return marketOrder;}
    public int getNumShares() {
        return numShares;
    }
    public double getPrice() {
        return price;
    }
    public boolean isSell(){return !buyOrder;}

    public void subtractShares(int shares)
    {
        numShares -= shares;
    }
}
