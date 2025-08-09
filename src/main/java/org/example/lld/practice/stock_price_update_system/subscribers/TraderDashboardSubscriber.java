package org.example.lld.practice.stock_price_update_system.subscribers;

import org.example.lld.practice.stock_price_update_system.models.Category;
import org.example.lld.practice.stock_price_update_system.models.StockPriceUpdate;

import java.util.HashSet;
import java.util.Set;

public class TraderDashboardSubscriber implements Subscriber {
    private final String dashboardId;
    private final Set<String> subscribedSymbols;
    private final Set<Category> subscribedCategories;
    private final boolean isPremium;

    public TraderDashboardSubscriber(String dashboardId, Set<String> symbols, Set<Category> categories, boolean isPremium) {
        this.dashboardId = dashboardId;
        this.subscribedSymbols = new HashSet<>(symbols);
        this.subscribedCategories = new HashSet<>(categories);
        this.isPremium = isPremium;
    }

    @Override
    public void update(StockPriceUpdate update) {
        System.out.println("Trader Dashboard [" + dashboardId + "] received: " + update.getSymbol() +
                " $" + update.getPrice() + " at " + update.getTimestamp());
    }

    @Override
    public Set<String> getSubscribedSymbols() {
        return subscribedSymbols;
    }

    @Override
    public Set<Category> getSubscribedCategories() {
        return subscribedCategories;
    }

    @Override
    public boolean isPremium() {
        return isPremium;
    }
}
