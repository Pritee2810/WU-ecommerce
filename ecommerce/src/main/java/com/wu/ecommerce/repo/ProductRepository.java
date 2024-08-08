package com.wu.ecommerce.repo;

import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import com.wu.ecommerce.dto.Product;
import com.wu.ecommerce.exception.DataNotFoundException;
import com.wu.ecommerce.exception.IdNotFoundException;
import com.wu.ecommerce.exception.InvalidIdException;

public interface ProductRepository {
	public Product addProduct(Product product) throws SQLException;
	public Product getEmployeeByProductId(String id) throws Exception;
	
	
	public List<Product> getProducts() throws DataNotFoundException, SQLException, InvalidIdException;
	public List<Product> getAllProductsByCategory(String cat) throws DataNotFoundException, SQLException, InvalidIdException;
	
	public String removeProductByProductId(String id) throws Exception;
	public Product updateProductByProductId(String id, Product product) throws SQLException, InvalidIdException, IdNotFoundException;
	
	default void display() {
		System.out.println("hello from interface");
	}
}
