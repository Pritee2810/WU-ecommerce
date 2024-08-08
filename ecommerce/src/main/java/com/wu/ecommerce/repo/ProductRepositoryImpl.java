package com.wu.ecommerce.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.wu.ecommerce.dto.Product;
import com.wu.ecommerce.exception.DataNotFoundException;
import com.wu.ecommerce.exception.IdNotFoundException;
import com.wu.ecommerce.exception.InvalidIdException;
import com.wu.ecommerce.utils.DBUtils;
import com.wu.ecommerce.utils.IdComparator;

public class ProductRepositoryImpl implements ProductRepository {
private ProductRepositoryImpl() {
	// TODO Auto-generated constructor stub
}
//private Product[] products=new Product[10];
//private TreeSet<Product> products=new TreeSet<Product>((arg0, arg1)->arg0.getProductName().compareTo(arg1.getProductName()));
public int compare(Product arg0, Product arg1) {
	return arg0.getProductName().compareTo(arg1.getProductName());
}



//private TreeSet<Product> products=new TreeSet<Product>((arg0, arg1)->arg0.getProductName().compareTo(arg1.getProductName()));
private TreeSet<Product> products=new TreeSet<Product>(new Comparator<Product>() {

	@Override
	public int compare(Product o1, Product o2) {
		// TODO Auto-generated method stub
		return o1.getProductName().compareTo(o2.getProductName());
	}
});
private static ProductRepository productRepository;
private static int counter=-1;

public static ProductRepository getInstance() {
	if(productRepository ==null) {
		//create the object
		productRepository = new ProductRepositoryImpl();
		
	}
	return productRepository;
}

@Override
public Product addProduct(Product product) throws SQLException {
	// TODO Auto-generated method stub
	String insertStatement ="insert into Product (productId, productName, price, categoryName) values(?,?,?,?)";
	Connection connection = DBUtils.getInstance().getConnection();
	//prepared statement
	PreparedStatement preparedstatement =connection.prepareStatement(insertStatement);
	preparedstatement.setString(1, product.getProductId());
	preparedstatement.setString(2, product.getProductName());
	preparedstatement.setFloat(3, product.getPrice());
	preparedstatement.setString(4, product.getCategoryName());
	
	int result=preparedstatement.executeUpdate();
	if(result>0) {
		return product;
	}
	else {
		return null;
	}
	
	
	/*boolean result=products.add(product);
	if(result)
		return product;
	
	else return null;
	*/
	
}


@Override
public List<Product> getProducts() throws DataNotFoundException, SQLException, InvalidIdException {
	// TODO Auto-generated method stub
	String query= "select * from product";
	Connection connection=DBUtils.getInstance().getConnection();
	PreparedStatement preparedStatement=connection.prepareStatement(query);
	ResultSet resultSet = preparedStatement.executeQuery();	
	
	List<Product> products=new ArrayList<Product>();
	while(resultSet.next()) {
		Product product=new Product();
		product.setProductId(resultSet.getString("productId"));
		product.setCategoryName(resultSet.getString("productName"));
		product.setPrice(resultSet.getFloat("price"));
		product.setCategoryName(resultSet.getString("categoryName"));
		products.add(product);
	}
	
	return products;
	
	
	
	
//	return Optional.ofNullable(products.stream().collect(Collectors.toList()).filter(l-> !l.isEmpty()).orElseThrow(()->new DataNotFoundException("no data")));
//	return (List<Product>) Optional.ofNullable(products).filter(l -> !l.isEmpty()).orElseThrow(()->new DataNotFoundException("Data not found"));
	
}

@Override
public List<Product> getAllProductsByCategory(String cat) throws DataNotFoundException, SQLException, InvalidIdException {
	// TODO Auto-generated method stub
	String query="select * from Product where categoryName=?";
	Connection connection=DBUtils.getInstance().getConnection();
	PreparedStatement preparedStatement=connection.prepareStatement(query);
	preparedStatement.setString(1, cat);
	ResultSet resultSet = preparedStatement.executeQuery();
	List<Product> products=new ArrayList<Product>();
	
	while(resultSet.next()) {
		Product product=new Product();
		product.setProductId(resultSet.getString("productId"));
		product.setCategoryName(resultSet.getString("productName"));
		product.setPrice(resultSet.getFloat("price"));
		product.setCategoryName(resultSet.getString("categoryName"));
		products.add(product);
	}
	
	return Optional.ofNullable(products).filter(l -> !l.isEmpty()).orElseThrow(()->new DataNotFoundException("Data not found"));
	/*List<Product> temp = (List<Product>) products.stream().filter(e-> e.getCategoryName().equals(cat)).collect(Collectors.toList());
	return (List<Product>) Optional.ofNullable(temp).filter(l -> !l.isEmpty()).orElseThrow(()->new DataNotFoundException("Data not found"));
	*/
}

@Override
public String removeProductByProductId(String id) throws Exception {
	// TODO Auto-generated method stub
	Product product=this.getEmployeeByProductId(id);
	boolean result =products.remove(product);
	if(result)
		return "success";
	else
		return "fail";
	
}

@Override
public Product updateProductByProductId(String id, Product product) throws SQLException, InvalidIdException, IdNotFoundException {
	// TODO Auto-generated method stub
	String query="update Product set productName=?, price=?, categoryName=? WHERE productId =?";
	Connection connection=DBUtils.getInstance().getConnection();
	PreparedStatement preparedstatement=connection.prepareStatement(query);
	
	preparedstatement.setString(1, product.getProductName());
	preparedstatement.setFloat(2, product.getPrice());
	preparedstatement.setString(3, product.getCategoryName());
	preparedstatement.setString(4, id);
	
	int res = preparedstatement.executeUpdate();
	if(res> 0)
		
		System.out.println("updated successfully");
	else
		throw new IdNotFoundException("ID Not Found");
	return product;
//	return products.
//			stream().
//			filter(e->e.getProductId().equals(id)).findFirst().orElseThrow(()->new IdNotFoundException("id not found"));
	
}

@Override
public Product getEmployeeByProductId(String id) throws Exception {
	// TODO Auto-generated method stub
	String query="select * from Product where productId=?";
	Connection connection=DBUtils.getInstance().getConnection();
	PreparedStatement preparedStatement=connection.prepareStatement(query);
	preparedStatement.setString(1, id);
	ResultSet resultSet = preparedStatement.executeQuery();
	List<Product> products=new ArrayList<Product>();
	
	while(resultSet.next()) {
		Product product=new Product();
		product.setProductId(resultSet.getString("productId"));
		product.setCategoryName(resultSet.getString("productName"));
		product.setPrice(resultSet.getFloat("price"));
		product.setCategoryName(resultSet.getString("categoryName"));
		products.add(product);
	}
	
	return products.
			stream().
			filter(e->e.getProductId().equals(id)).findFirst().orElseThrow(()->new IdNotFoundException("id not found"));
//	return null;
}


}
