package com.myspringbootproject.spring_restapi.service;

import java.util.List;
import org.springframework.http.ResponseEntity;

import com.myspringbootproject.spring_restapi.model.Trade;


public interface TradeService {
    public ResponseEntity<List<Trade>> getAllTrades();
    public void eraseAllTrades();
    public ResponseEntity<Trade> createNewTrade(Trade trade);
    public ResponseEntity<Trade> getById(Long id);
    public ResponseEntity<List<Trade>> getTradeByUserId(Long userId);
    public ResponseEntity<List<Trade>> getTradeByStockSymbol(String symbol);
    public ResponseEntity<List<Trade>> getTradeByUserIdAndStockSymbol(Long userId, String symbol);

}
