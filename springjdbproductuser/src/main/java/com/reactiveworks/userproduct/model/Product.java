package com.reactiveworks.userproduct.model;

import java.util.List;

public class Product {
	private String productId;
	private String productName;
	private String productCategory;
	private Double price;
	List<String> availableCity;
	/************************ Setters/Getters ****************************/
	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * @return the productCategory
	 */
	public String getProductCategory() {
		return productCategory;
	}
	/**
	 * @param productCategory the productCategory to set
	 */
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	/**
	 * @return the availableCity
	 */
	public List<String> getAvailableCity() {
		return availableCity;
	}
	/**
	 * @param availableCity the availableCity to set
	 */
	public void setAvailableCity(List<String> availableCity) {
		this.availableCity = availableCity;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productCategory="
				+ productCategory + ", price=" + price + ", availableCity=" + availableCity + "]";
	}
	
}
