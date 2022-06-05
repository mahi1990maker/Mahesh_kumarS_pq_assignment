package com.payconiq.exception;

public class StockNotFoundException extends RuntimeException{
	
	 public StockNotFoundException(final int id)  {
	        super("Stock not found by " + id);
	    }

}
