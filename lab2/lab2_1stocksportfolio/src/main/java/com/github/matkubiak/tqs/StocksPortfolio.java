package com.github.matkubiak.tqs;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class StocksPortfolio {
    private IStockmarketService stockmarket;
    private List<Stock> stocks;

    public StocksPortfolio(IStockmarketService stockmarket) {
        this.stockmarket = stockmarket;
        stocks = new ArrayList<Stock>();
    }

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public double totalValue() {
        double total = 0.0;
        for (Stock stock : stocks) {
            total += stock.getQuantity() * stockmarket.lookUpPrice(stock.getLabel());
        }
        return total;
    }
}
