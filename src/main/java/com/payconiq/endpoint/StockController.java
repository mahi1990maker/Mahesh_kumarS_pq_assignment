package com.payconiq.endpoint;

import com.payconiq.model.exception.StockNotFoundException;
import com.payconiq.repository.Stock;
import com.payconiq.service.StockService;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Common class for all the rest endpoint /api/stock interactions
 */
@Api("Payconiq Stock API")
@RestController
@RequestMapping(value = "/api/stocks")
public class StockController {

    @Autowired   
    private StockService stockService;

    /**
     * Get stock by id
     * @param id of the Stock
     * @return Stock that is found by id
     */
    @ApiOperation(value = "Return stock by id")
    @GetMapping(value = "/{id}", produces = "application/json")
    public Stock getStock(@PathVariable final int id) {
        return stockService.getSingleStock(id);
    }

    /**
     * return the stocks based on paging
     * @param page No
     * @param page size
     * @param sort based on ID
     * @return All the stocks
     */
    @ApiOperation(value = "Return the stocks")
    @GetMapping(produces = "application/json")
    public List<Stock> getStock(@RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        return stockService.getAllStock(pageNo, pageSize, sortBy);
    }

    /**
     * Create stock atomically if not exists otherwise return error
     * @param stock to be created
     * @return created Stock
     */
    @ApiOperation(value = "Create stock")
    @PostMapping(consumes = "application/json", produces = "application/json")
    public Stock createStock(@Valid @RequestBody Map<String, String> body) {
        return stockService.createStock(body);
    }

    /**
     * Update stockRequest if exists otherwise return error
     * @param stockRequest to be Updated
     * @return updated Stock
     */
    @ApiOperation(value = "Update existing stock")
    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public Stock updateStock(@PathVariable final int id, @Valid @RequestBody Map<String, String> body) {
        return stockService.updateStock(id, body);
    }
    
    /**
     * Delete stockRequest if exists otherwise return error
     * @param stockRequest to be deleted
     * @return true or false
     */
    
    @ApiOperation(value = "Update existing stock")
    @DeleteMapping(value = "/{id}")
    public boolean delete(@PathVariable String id){
    	 return stockService.delete(id);
    }
    

    /**
     * Catch the StockNotFoundException error and then convert it into more generic httpResponse
     */
    @ExceptionHandler(StockNotFoundException.class)
    public void stockNotFoundHandler(final HttpServletResponse response, final StockNotFoundException exception) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, exception.getMessage());
    }

}
