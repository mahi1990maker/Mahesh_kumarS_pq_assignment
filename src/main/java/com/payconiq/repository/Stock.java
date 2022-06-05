package com.payconiq.repository;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Entity;

@Entity
public class Stock {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private int id; 	 
	   
		private String name;
		
	    public Stock(String name, float price, String lastUpdate) {
			super();
			this.name = name;
			this.price = price;
			this.lastUpdate = lastUpdate;
		}

		private float price;
	    private String lastUpdate;

	    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	 @Override
		public String toString() {
			return "StockRepository [id=" + id + ", name=" + name + ", price=" + price + ", lastUpdate=" + lastUpdate + "]";
		}

}
