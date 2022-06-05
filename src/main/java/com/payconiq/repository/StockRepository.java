package com.payconiq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

    // custom query to search to stock post by id
    List<Stock> findByTitleContainingOrContentContaining(int id);    
}
