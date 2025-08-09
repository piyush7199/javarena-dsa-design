package org.example.lld.practice.stock_price_update_system.publishers;

import org.example.lld.practice.stock_price_update_system.models.StockPriceUpdate;
import org.example.lld.practice.stock_price_update_system.subscribers.Subscriber;

public interface Publisher {
    void subscribe(Subscriber subscriber);

    void unsubscribe(Subscriber subscriber);

    void broadcastUpdate(StockPriceUpdate stockPriceUpdate);
}
