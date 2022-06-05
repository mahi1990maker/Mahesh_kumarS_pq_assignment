package com.payconiq.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.payconiq.exception.StockNotFoundException;
import com.payconiq.repository.Stock;
import com.payconiq.repository.StockRepository;


public class StockService {
	

    @Autowired
    private StockRepository stockRepository;

    /**
     * Initialize some stocks
     */
    @PostConstruct
    public void init() {
        for (long count=1; count <= 10; count++) {
        	HashMap<String,String> map = new HashMap<>();
        	map.put("name", "Test"+count);
        	map.put("price", String.valueOf(10*count));
        	map.put("lastUpdate", String.valueOf(System.currentTimeMillis()));
            createStock(map);
        }
    }


    public Stock getSingleStock(int id) {
    	final Stock stock = stockRepository.findOne(id);
        if (stock == null) {
            throw new StockNotFoundException(id);
        }
        return stock;
    }
    
    public List<Stock> getAllStock(Integer pageNo, 
            Integer pageSize,
            String sortBy) {
    	Sort sort = new Sort(new Sort.Order(Direction.ASC, sortBy.toString()));
    	Pageable paging = new PageRequest(pageNo,pageSize,sort);   
        Page<Stock> pagedResult = stockRepository.findAll(paging);
        return pagedResult.getContent();
    }
    
    public Stock createStock(Map<String, String> body) {
    	String name = body.get("name");
        String price = body.get("price");
        String lastUpdate = body.get("lastUpdate");
        return stockRepository.save(new Stock(name,Float.parseFloat(price),lastUpdate));
    }
    
    public Stock updateStock(final int id,Map<String, String> body) {
        Stock stock = stockRepository.findOne(id);
        if (stock == null) {
            throw new StockNotFoundException(id);
        }
        stock.setName(body.get("name"));
        stock.setPrice(Float.parseFloat(body.get("price")));
        stock.setLastUpdate(body.get("lastUpdate"));
        return stockRepository.save(stock);
    }
    
    public boolean delete(String id){
        int stockID = Integer.parseInt(id);
        stockRepository.delete(stockID);
        return true;
    }

}
