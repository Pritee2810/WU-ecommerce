package com.wu.ecommerce;

@FunctionalInterface
public interface FunctionalInterfaceDemo {
	public int add(int a, int b);
	default void test() {
		System.out.println("test");
	}
	default void test1() {
		System.out.println("test1");

	}
	default void test2() {
		System.out.println("test2");

	}
	
}
class Test{
	public static void main(String[] args) {
		getResult((a,b)->a+b);
	}

	private static void getResult(FunctionalInterfaceDemo demo) {
		// TODO Auto-generated method stub
		int result=demo.add(10, 20);
		System.out.println(result);
		
	}
}
