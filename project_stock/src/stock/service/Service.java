package stock.service;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.log4j.Logger;

import model.domain.Cart;
import model.domain.Clothes;
import model.domain.Customer;
import stock.exception.NotExistException;
import stock.model.DB.CustomerDB;
import stock.model.DB.StockDB;

public class Service {

	private static Logger logger = Logger.getLogger(Service.class.toString());
	private static Service instance = new Service();
	private StockDB stockList = StockDB.getInstance();
	private CustomerDB customerList = CustomerDB.getInstance();

	public static Service getInstance() {
		return instance;
	}
	// -----------------------��� ���� ���----------------------------
	
	// ��ǰ������ ��ǰ �˻�
	public Clothes getClothes(String name) {
		for (Clothes c : stockList.getStockList()) {
			if (name.equals(c.getName())) {
				logger.info("find product" + name);
				return c;
			}
		}
		logger.warn("fail to find product" + name);
		return null;
	}

	// ��� ǰ�� ��ȯ
	public ArrayList<Clothes> getAllClothes() {
		logger.info("print all products");
		return stockList.getStockList();
	}

	// �� ��ǰ �߰�
	public void insertClothes(Clothes c) {
		stockList.insertClothes(c);
	}

	// ǰ�� ���ݺ���
	public void changePrice(String name, int price) throws NotExistException {
		Clothes c = getClothes(name);
		if (c != null) {
			logger.info("change price of product " + name + " to " + price);
			c.setPrice(price);
		} else {
			logger.warn("fail to change price of product " + name);
			throw new NotExistException("��ǰ�� �������� �ʽ��ϴ�");
		}
	}

	// ��� ���� ����(����, ������)
	public void changeStock(String name, String size, int stock) throws NotExistException {
		Clothes c = getClothes(name);
		if (c != null) {
			ArrayList<Integer> s = c.getStock();
			ArrayList<String> sizes = c.getSize();
			s.set(sizes.indexOf(size), s.get(sizes.indexOf(size)) + stock);
			c.setStock(s);
			logger.info("change stock of product " + name + "(" + size + ")");
		} else {
			logger.warn("change stock of product " + name + "(" + size + ")");
			throw new NotExistException("��ǰ�� �������� �ʽ��ϴ�");
		}
	}

	// ��ǰ ��ü ����(�� ��ٱ��Ͽ����� ����)
	public void delClothes(String name) {
		Clothes c = getClothes(name);
		if (c != null) {
			stockList.getStockList().remove(c);
			for (Customer customer : customerList.getCustomerList()) {
				if (customer.getCart().getNames().contains(name)) {
					customer.getCart().del(name);
				}
			}
		}
	}

	// --------------------------�� ���� ���------------------------------

	// ��� �� ���� ���
	public ArrayList<Customer> getAllCustomer() {
		return customerList.getCustomerList();
	}

	// �̸����� �� �˻�
	public Customer getCustomer(String name) throws NotExistException {
		for (Customer c : customerList.getCustomerList()) {
			if (name.equals(c.getName())) {
				logger.info("find customer " + name);
				return c;
			}
		}
		logger.warn("fail to find customer " + name);
		throw new NotExistException("�������� �ʴ� ���Դϴ�");
	}

	// ��ٱ��� �߰�
	public void addCart(String name, String clothesName, String size) throws NotExistException {
		Clothes c = getClothes(clothesName);
		Customer customer = getCustomer(name);
		if (c != null && customer != null) {
			ArrayList<String> sizes = c.getSize();
			ArrayList<Integer> stocks = c.getStock();
			if (stocks.get(sizes.indexOf(size)) > 0) {
				Cart cart = customer.getCart();
				if (cart.getAmounts() == 0) {
					cart.setAmounts(c.getPrice());
					cart.setNames(new ArrayList<>(Arrays.asList(clothesName)));
					cart.setPrices(new ArrayList<>(Arrays.asList(c.getPrice())));
					cart.setSizes(new ArrayList<>(Arrays.asList(size)));
					System.out.println(cart);
					customer.setCart(cart);
					changeStock(clothesName, size, -1);
				} else {
					cart.add(clothesName, size, c.getPrice());
					System.out.println(cart);
					changeStock(clothesName, size, -1);
				}
			} else {
				throw new NotExistException("��� �����մϴ�");
			}
		} else {
			throw new NotExistException("���� �Ǵ� ��ǰ���� �߸��Ǿ����ϴ�");
		}
	}

	// ��ٱ��� ����
	public void delCart(String name, String clothesName, String size) throws NotExistException {
		Clothes c = getClothes(clothesName);
		Customer customer = getCustomer(name);
		if (c != null && customer != null) {
			Cart cart = customer.getCart();
			if (cart.getNames().contains(clothesName)) {
				cart.del(clothesName, size, c.getPrice());
				changeStock(clothesName, size, +1);
			} else {
				throw new NotExistException("�ش� ǰ���� ��ٱ��Ͽ� �����ϴ�");
			}
		} else {
			throw new NotExistException("���� �Ǵ� ��ǰ���� �߸��Ǿ����ϴ�");
		}
	}

	// �Ѿ� ���
	public int getAmounts(String name) throws NotExistException {
		Customer c = getCustomer(name);
		System.out.println(c);
		if (c != null) {
			if (c.getGrade().equals("Gold")) {
				logger.info("print amount of customer" + name);
				return (int) Math.round(getCustomer(name).getCart().getAmounts() / 100 * 0.8) * 100;
			} else if (c.getGrade().equals("Silver")) {
				logger.info("print amount of customer" + name);
				return (int) Math.round(getCustomer(name).getCart().getAmounts() / 100 * 0.9) * 100;
			} else if (c.getGrade().equals("Bronze")) {
				logger.info("print amount of customer" + name);
				return getCustomer(name).getCart().getAmounts();
			}
		}
		logger.warn("fail to print amount of customer" + name);
		throw new NotExistException("�������� �ʴ� ���Դϴ�");
	}

	// �� ��ٱ��� ��ȯ
	public Cart getCart(String name) throws NotExistException {
		Customer c = getCustomer(name);
		if (c != null) {
			return c.getCart();
		}
		throw new NotExistException("�������� �ʴ� ���Դϴ�");
	}

	// Ư����ǰ ��ٱ��Ͽ� ���� �� �˻�
	/*
	public ArrayList<Customer> searchCustomerWhoBought(String clothes) throws NotExistException {
		ArrayList<Customer> found = new ArrayList<Customer>();
		for (Customer customer : customerList.getCustomerList()) {
			if (customer.getCart().getNames().contains(clothes)) {
				found.add(customer);
			}
			throw new NotExistException("�˻��Ͻ� ǰ���� ��ٱ��Ͽ� ������ ���� �����ϴ�.");				
			
		}
		return found;
	}
	*/

}
