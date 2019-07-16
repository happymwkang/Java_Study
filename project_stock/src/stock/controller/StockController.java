package stock.controller;

import java.util.ArrayList;
import model.domain.*;
import stock.exception.NotExistException;
import stock.service.Service;
import stock.view.*;

public class StockController {
	private static StockController instance = new StockController(); 
	private Service service = Service.getInstance(); 

	private StockController() {
	}

	public static StockController getInstance() {
		return instance;
	}
	// -----------------------------To manage stock-------------------------------

	// Search all stock
	public void AllStockView() {
		ArrayList<Clothes> allStockList = service.getAllClothes();
		if (allStockList.size() != 0) {
			EndView.AllStockListView(allStockList);
		} else {
			EndView.messageView("��ǰ�� �ϳ��� �������� �ʽ��ϴ�.");
		}
	}

	// Search a stock
	public void OneStockView(String name) { // ��� ��� ���� �̸����� �˻�
		if (service.getClothes(name) != null) {
			EndView.stockView(service.getClothes(name));
		} else {
			EndView.messageView("�˻� ��û�Ͻ� ��ǰ�� �������� �ʽ��ϴ�.\n");
			// e.printStackTrace();
		}
	}

	// save new stock
	public void insertStockView(Clothes newStock) {
		service.insertClothes(newStock);
		System.out.println("�� ��ǰ�� ����Ǿ����ϴ�.");
	}

	// update size & amount stock
	public void updateStockView(String name, String size, int stock) {
		try {
			service.changeStock(name, size, stock);
			System.out.println("��ǰ ������ �����Ǿ����ϴ�.");
		} catch (NotExistException e) {
			FailView.failMessageView(e.getMessage());
			// e.printStackTrace();
		}
	}

	// update price of stock
	public void updatePriceView(String name, int price) {
		try {
			service.changePrice(name, price);
			System.out.println("��ǰ�� ������ �����Ǿ����ϴ�.");
		} catch (NotExistException e) {
			FailView.failMessageView(e.getMessage());
		}
	}

	// delete stock information
	public void deleteStockView(String Name) {
		service.delClothes(Name);
		System.out.println("��ǰ�� ���� ��ٱ��Ͽ��� �����Ǿ����ϴ�.");
	}

	// -------------------------------To manage customers-----------------------------------
	// search all customers
	public void allCustomerView() {
		ArrayList<Customer> allCustomerList = service.getAllCustomer();
		if (allCustomerList.size() != 0) {
			EndView.allCustomerView(allCustomerList);
		} else {
			EndView.messageView("��� �������� ����ֽ��ϴ�.");
		}
	}

	// search a customer
	public void searchCustomerView(String name) {
		try {
			EndView.messageView(service.getCustomer(name).toString());
		} catch (NotExistException e) {
			FailView.failMessageView(e.getMessage());
		}
	}

	// add product in cart
	public void addProductInCartView(String name, String clothesName, String size) {
		try {
			service.addCart(name, clothesName, size); // ���ܹ߻�
			System.out.println(name + "���� ��û�Ͻ� " + clothesName + " ��ǰ�� ��ٱ��Ͽ� �߰��߽��ϴ�.");
		} catch (NotExistException e) {
			FailView.failMessageView(e.getMessage());
			// e.printStackTrace();

		}
	}

	// delete a product from cart
	public void delProductInCartView(String name, String clothesName, String size) {
		try {
			service.delCart(name, clothesName, size);
			System.out.println(clothesName + "�� ��ٱ��ϸ�Ͽ��� �����ϴ�");
		} catch (NotExistException e) {
			FailView.failMessageView(e.getMessage());
		}
	}

	// make total price
	public void sumPriceView(String name) {
		try {
			//System.out.println(name + "������ ��ٱ��� ���� : \n" + service.getCart(name));
			System.out.println("����Ͻ� �ݾ��� ��� ���� �����ؼ� " + service.getAmounts(name) + "�� �Դϴ�.");
		} catch (NotExistException e) {
			FailView.failMessageView(e.getMessage());
		}
	}
	// Ư����ǰ ��ٱ��Ͽ� ���� �� �˻�
	/*
	public void searchSpecificCustomer(String clothes) {
		ArrayList<Customer> specificCustomer = new ArrayList<Customer>();
		try {
			specificCustomer = service.searchCustomerWhoBought(clothes);
			EndView.allCustomerView(specificCustomer);
		} catch (NotExistException e) {
			//e.printStackTrace();
		}
	}
	*/
}
