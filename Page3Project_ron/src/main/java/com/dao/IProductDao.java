package com.dao;

import java.util.List;

import com.model.Product;

public interface IProductDao 
{
	
	int findPid(String pname,String generations,String type);
	int findPrice(int pid);
	void addProduct(Product product);
	List<Product> getAllProducts();
	void updateProduct(Product product);
	void deleteProduct(int pid);
	Product getProductById(int pid);
}
