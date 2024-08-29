package com.myspringbootproject.spring_restapi.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.myspringbootproject.spring_restapi.model.Trade;
import com.myspringbootproject.spring_restapi.model.User;
import com.myspringbootproject.spring_restapi.repository.TradeRepository;
import com.myspringbootproject.spring_restapi.repository.UserRepository;

@Service
public class TradeServiceImpl implements TradeService{

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<List<Trade>> getAllTrades() {
        return new ResponseEntity<>(tradeRepository.findAllByOrderByIdAsc(), HttpStatus.OK);
    }

    @Override
    public void eraseAllTrades() {
        tradeRepository.deleteAll();
        
    }

    @Override
    public ResponseEntity<Trade> createNewTrade(Trade trade) {
        Optional<Trade> featcheDTrade = tradeRepository.findById(trade.getId());
        if(!featcheDTrade.isPresent()){
            userRepository.save(trade.getUser());
            tradeRepository.save(trade);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @Override
    public ResponseEntity<Trade> getById(Long id) {
        if(tradeRepository.findById(id).isPresent())
            return new ResponseEntity<>(tradeRepository.findById(id).get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<Trade>> getTradeByUserId(Long userId) {
        Optional<User> featchedUser = userRepository.findById(userId);

        if(featchedUser.isPresent()){
            Optional<List<Trade>> featchedTrade = tradeRepository.findByUserOrderByIdAsc(featchedUser.get());
            if(featchedTrade.isPresent())
                return new ResponseEntity<>(featchedTrade.get(), HttpStatus.OK);
            else
                return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);

        
    }

    @Override
    public ResponseEntity<List<Trade>> getTradeByStockSymbol(String symbol) {
        Optional<List<Trade>> featchedTrade = tradeRepository.findBySymbolOrderByIdAsc(symbol);
        if(featchedTrade.isPresent())
            return new ResponseEntity<>(featchedTrade.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<Trade>> getTradeByUserIdAndStockSymbol(Long userId, String symbol) {
        Optional<User> featchedUser = userRepository.findById(userId);
        if(featchedUser.isPresent()){
            Optional<List<Trade>> featchedTrade = tradeRepository.findByUserAndSymbolOrderByIdAsc(featchedUser.get(), symbol);
            if(featchedTrade.isPresent())
                return new ResponseEntity<>(featchedTrade.get(), HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
