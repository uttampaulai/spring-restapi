package com.myspringbootproject.spring_restapi.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.myspringbootproject.spring_restapi.service.TradeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.myspringbootproject.spring_restapi.model.Trade;


@RestController
@RequestMapping(value = "/trades")
public class TradesController {

    @Autowired
    private TradeService tradeService;

    @GetMapping
    public ResponseEntity<List<Trade>> getAllTrades(){
        return tradeService.getAllTrades();
    }

    @PostMapping
    public ResponseEntity<Trade> createTrade(@RequestBody Trade trade){
        return tradeService.createNewTrade(trade);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Trade> getTradeById(@PathVariable(value="id") Long id ){
        return tradeService.getById(id);
    }

    @GetMapping(value="/users/{userId}")
    public ResponseEntity<List<Trade>> getTradeByUserId(@PathVariable(value="userId") Long userId){
        return tradeService.getTradeByUserId(userId);
    }

    @GetMapping(value="/stocks/{stockSymbol}" )
    public ResponseEntity<List<Trade>> getTradeBySymbol(@PathVariable(value="stockSymbol") String symbol) {
        return tradeService.getTradeByStockSymbol(symbol);
    }

    @GetMapping(value= "/users/{UserId}/stocks/{stockSymbol}")
    public ResponseEntity<List<Trade>> getTradeByUserIdAndSymbol(@PathVariable(value="UserId") Long id, @PathVariable(value="stockSymbol") String symbol){
		return tradeService.getTradeByUserIdAndStockSymbol(id,symbol);
    }
    
    


}
