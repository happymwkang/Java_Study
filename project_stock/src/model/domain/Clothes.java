package model.domain;

import java.util.ArrayList;
import lombok.Data;
@Data
public class Clothes {
	public Clothes() {}
	public Clothes(String name, int price, ArrayList<String> size, String category, String color,
			ArrayList<Integer> stock) {
		super();
		this.name = name;
		this.price = price;
		this.size = size;
		this.category = category;
		this.color = color;
		this.stock = stock;
	}
	private String name;
	private int price;
	private ArrayList<String> size;
	private String category;
	private String color;
	private ArrayList<Integer> stock;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("1. ��ǰ�� : ");
		builder.append(name);
		builder.append("\n2. ���� : ");
		builder.append(price);
		builder.append("\n3. ������: ");
		builder.append(size);
		builder.append("\n4. ��� ����: ");
		builder.append(stock);
		builder.append("\n5. ���� : ");
		builder.append(color);
		builder.append("\n6. �з� : ");
		builder.append(category);
		return builder.toString(); 
	}
}
