package org.example.lld.practice.stock_price_update_system;

import org.example.lld.practice.stock_price_update_system.models.Category;
import org.example.lld.practice.stock_price_update_system.models.StockPriceUpdate;
import org.example.lld.practice.stock_price_update_system.publishers.StockPricePublisher;
import org.example.lld.practice.stock_price_update_system.subscribers.AnalyticsServiceSubscriber;
import org.example.lld.practice.stock_price_update_system.subscribers.MobileAppSubscriber;
import org.example.lld.practice.stock_price_update_system.subscribers.Subscriber;
import org.example.lld.practice.stock_price_update_system.subscribers.TraderDashboardSubscriber;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        StockPricePublisher publisher = new StockPricePublisher();
        // Create subscribers with specific filters and priority
        Subscriber traderDashboard = new TraderDashboardSubscriber(
                "dashboard123",
                Set.of("AAPL", "GOOGL"),
                Set.of(Category.TECH),
                true
        );
        Subscriber mobileApp = new MobileAppSubscriber(
                "device456",
                Set.of("TSLA"),
                Set.of(Category.ENERGY),
                false
        );
        Subscriber analyticsService = new AnalyticsServiceSubscriber(
                "analytics789",
                Set.of("JPM"),
                Set.of(Category.FINANCE),
                true
        );

        // Subscribe
        publisher.subscribe(traderDashboard);
        publisher.subscribe(mobileApp);
        publisher.subscribe(analyticsService);

        // Broadcast stock price updates
        publisher.broadcastUpdate(new StockPriceUpdate("AAPL", 150.25, System.currentTimeMillis(), Category.TECH));
        publisher.broadcastUpdate(new StockPriceUpdate("TSLA", 700.50, System.currentTimeMillis(), Category.ENERGY));
        publisher.broadcastUpdate(new StockPriceUpdate("JPM", 120.75, System.currentTimeMillis(), Category.FINANCE));

        // Unsubscribe mobile app and broadcast again
        publisher.unsubscribe(mobileApp);
        publisher.broadcastUpdate(new StockPriceUpdate("TSLA", 710.00, System.currentTimeMillis(), Category.ENERGY));
    }
}
