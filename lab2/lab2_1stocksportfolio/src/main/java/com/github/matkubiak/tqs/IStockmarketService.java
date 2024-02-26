package com.github.matkubiak.tqs;

import java.util.NoSuchElementException;

public interface IStockmarketService {
     double lookUpPrice(String stockLabel) throws RuntimeException;
}
