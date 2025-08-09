package org.example.lld.practice.stock_price_update_system.subscribers;

import org.example.lld.practice.stock_price_update_system.models.Category;
import org.example.lld.practice.stock_price_update_system.models.StockPriceUpdate;

import java.util.Set;

public interface Subscriber {
    void update(StockPriceUpdate update);
    Set<String> getSubscribedSymbols();
    Set<Category> getSubscribedCategories();
    boolean isPremium();

}
