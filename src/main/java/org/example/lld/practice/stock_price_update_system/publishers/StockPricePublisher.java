package org.example.lld.practice.stock_price_update_system.publishers;


import org.example.lld.practice.stock_price_update_system.models.Category;
import org.example.lld.practice.stock_price_update_system.models.StockPriceUpdate;
import org.example.lld.practice.stock_price_update_system.subscribers.Subscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class StockPricePublisher implements Publisher {

    private final List<Subscriber> premiumSubscribers = new ArrayList<>();
    private final List<Subscriber> regularSubscribers = new ArrayList<>();


    public void subscribe(Subscriber subscriber) {
        synchronized (this) {
            if (subscriber.isPremium()) {
                premiumSubscribers.add(subscriber);
            } else {
                regularSubscribers.add(subscriber);
            }
        }
    }

    public void unsubscribe(Subscriber subscriber) {
        synchronized (this) {
            if (subscriber.isPremium()) {
                premiumSubscribers.remove(subscriber);
            } else {
                regularSubscribers.remove(subscriber);
            }
        }
    }

    // Broadcast a stock price update to interested subscribers
    public void broadcastUpdate(StockPriceUpdate update) {
        synchronized (this) {
            System.out.println("Broadcasting update for " + update.getSymbol() + ": $" + update.getPrice() +
                    " (Category: " + update.getCategory() + ")");

            // Notify premium subscribers first
            notifySubscribers(premiumSubscribers, update, "premium");
            // Then notify regular subscribers
            notifySubscribers(regularSubscribers, update, "regular");
        }
    }

    private void notifySubscribers(List<Subscriber> subscribers, StockPriceUpdate update, String type) {
        for (Subscriber subscriber : subscribers) {
            Set<String> symbols = subscriber.getSubscribedSymbols();
            Set<Category> categories = subscriber.getSubscribedCategories();
            if (symbols.contains(update.getSymbol()) || categories.contains(update.getCategory())) {
                try {
                    subscriber.update(update);
                } catch (Exception e) {
                    System.err.println("Error notifying " + type + " subscriber: " + e.getMessage());
                }
            }
        }
    }
}
