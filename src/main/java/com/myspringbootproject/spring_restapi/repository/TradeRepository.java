package com.myspringbootproject.spring_restapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.myspringbootproject.spring_restapi.model.Trade;
import com.myspringbootproject.spring_restapi.model.User;

public interface TradeRepository extends CrudRepository<Trade, Long>{

    List<Trade> findAllByOrderByIdAsc();
    Optional<List<Trade>> findByUserOrderByIdAsc(User user);
    Optional<List<Trade>> findBySymbolOrderByIdAsc(String symbol);
	Optional<List<Trade>> findByUserAndSymbolOrderByIdAsc(User user, String symbol);
}
