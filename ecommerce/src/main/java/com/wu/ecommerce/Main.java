package com.wu.ecommerce;

import java.util.Optional;
import java.util.Scanner;

import com.wu.ecommerce.dto.Product;
import com.wu.ecommerce.exception.DataNotFoundException;
import com.wu.ecommerce.exception.IdNotFoundException;
import com.wu.ecommerce.exception.InvalidIdException;
import com.wu.ecommerce.exception.InvalidPriceException;
import com.wu.ecommerce.service.ProductService;
import com.wu.ecommerce.service.ProductServiceImpl;

public class Main {

//	public static void main(String[] args) {
//		//int res = test();
//		//System.out.println("res "+ res);
//		
//		try {
//			Product product1=new Product("ab", "abc",0, "cat1");
//			ProductService productService=ProductServiceImpl.getInstance();
//			productService.addProduct(product1);
//			//Product optional=productService.getEmployeeByProductId("ab");
//			/*if(optional.isPresent()) {
//				System.out.println(optional.get());
//			}*/
//			
//			
//			System.out.println("------------");
//			System.out.println(product1);
//			System.out.println(product1.toString());
//			System.out.println(product1.hashCode());
//			System.out.println(Integer.toHexString(product1.hashCode()));
//			System.out.println(product1.getCategoryName());
//			
//		}
//		catch(InvalidIdException e){
//			e.printStackTrace();
//		}
//		catch(InvalidPriceException e) {
//			e.printStackTrace();
//		}
//		
//	}

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		while (true) {
			System.out.println("enter the choice");
			choice = sc.nextInt();
			Product product1 = new Product("1", "abc", 0, "cat1");
			Product product2 = new Product("2", "xyz", 0, "cat2");
			Product product3 = new Product("3", "pqr", 0, "cat1");
			Product product4 = new Product("4", "ert", 0, "cat2");
			Product product5 = new Product("5", "ghj", 0, "cat1");
			ProductService productService = ProductServiceImpl.getInstance();
			switch (choice) {
			case 1:
				// add new product
				productService.addProduct(product1);
				productService.addProduct(product2);
				productService.addProduct(product3);

				break;
			case 2:
				// getEmployeeByProductId
				/*
				 * Product optional=productService.getEmployeeByProductId("ab");
				 * if(optional.isPresent()) { System.out.println(optional.get()); }
				 */
				System.out.println(productService.getEmployeeByProductId("3"));
				break;
			case 3:
				// getProducts
				System.out.println(productService.getProducts());
				break;
			case 4:
				// getAllProductsByCategory
				System.out.println(productService.getAllProductsByCategory("cat1"));
				break;
			case 5:
				// removeProductByProductId
				System.out.println(productService.removeProductByProductId("2"));
				break;
			case 6:
				System.out.println(productService.updateProductByProductId("9", new Product("9", "SSD", 0, "Tech")));
				break;
			default:
				break;
			}
		}

	}

	private static char[] updateProductByProductId(String string, Product product) {
		// TODO Auto-generated method stub
		return null;
	}
}
