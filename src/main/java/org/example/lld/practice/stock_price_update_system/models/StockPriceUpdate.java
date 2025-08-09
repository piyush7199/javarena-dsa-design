package org.example.lld.practice.stock_price_update_system.models;

public class StockPriceUpdate {
    private final String symbol;
    private final double price;
    private final long timestamp;
    private final Category category;

    public StockPriceUpdate(String symbol, double price, long timestamp, Category category) {
        if (symbol == null || price < 0 || timestamp < 0 || category == null) {
            throw new IllegalArgumentException("Invalid stock price update data");
        }
        this.symbol = symbol;
        this.price = price;
        this.timestamp = timestamp;
        this.category = category;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Category getCategory() {
        return category;
    }
}
