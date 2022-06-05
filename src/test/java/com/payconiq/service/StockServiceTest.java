package com.payconiq.service;

import com.payconiq.model.exception.StockNotFoundException;
import com.payconiq.repository.Stock;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * Check in memory storage service {@link StockService}
 */
public class StockServiceTest {

    private static final StockService stockService = new StockService();

    @BeforeClass
    public static void setup() {
        stockService.init();
        final List<Stock> responses = stockService.getAllStock(0, 10, "id");
        Assert.assertEquals("Initial stocks count not correct", 10, responses.size());
    }

    @Test
    public void test_Given_InitialStocksCreated_Then_GetStockSuccessfully() {
        final Stock stockResponse = stockService.getSingleStock(1);
        Assert.assertNotNull(stockResponse.getLastUpdate());
        Stock expectedStockResponse = new Stock("Test1",1*10,stockResponse.getLastUpdate());
        Assert.assertEquals("Get stock is not working", expectedStockResponse, stockResponse);
    }


    @Test
    public void test_Given_ValidStockCreateRequest_Then_CreateStockSuccessfully() {
        final BigDecimal currentPrice = new BigDecimal("12.45");
        HashMap<String,String> map = new HashMap<>();
        map.put("name", "Test11");
    	map.put("price", String.valueOf(12.45));
    	map.put("lastUpdate", String.valueOf(System.currentTimeMillis()));
        Stock stockResponse = stockService.createStock(map);
        Assert.assertNotNull("lastUpdate should not be null", stockResponse.getLastUpdate());
        Assert.assertNotNull("id should not be null", stockResponse.getId());
        Assert.assertEquals("currentPrice is wrong", currentPrice, stockResponse.getPrice());
        Assert.assertEquals("name is wrong", "Test11", stockResponse.getName());
    }

    @Test(expected = StockNotFoundException.class)
    public void test_Given_NonExistingStockUpdateRequest_Then_UpdateShouldFail() {   
    	HashMap<String,String> map = new HashMap<>();
        map.put("name", "Test11");
    	map.put("price", String.valueOf(12.45));
    	map.put("lastUpdate", String.valueOf(System.currentTimeMillis()));
        stockService.updateStock(55, map);
    }

}
