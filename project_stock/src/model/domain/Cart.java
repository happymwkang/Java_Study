package model.domain;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
	private int amounts = 0;
	private ArrayList<String> names;
	private ArrayList<String> sizes;
	private ArrayList<Integer> prices;
	
	/* add나 del과 같은 메소드들이 mvc패턴의 구조에서 dto단계 보다는 service에서 구현되야 하지 않았었나.. */
	
	public void add (String name, String size, int price) {
		names.add(name);
		sizes.add(size);
		prices.add(price);
		amounts += price;
	}
	
	public void del (String name, String size, int price) {
		for (int i = (names.size()-1) ; i>=0 ; i--) {
			if (names.get(i).equals(name) && sizes.get(i).equals(size)) {
				names.remove(i);
				sizes.remove(i);
				amounts -=prices.get(i);
				prices.remove(i);
			}
		}
	}
	
	public void del (String name) {
		for (int i = (names.size()-1) ; i>=0 ; i--) {
			if (names.get(i).equals(name)) {
				names.remove(i);
				sizes.remove(i);
				amounts -=prices.get(i);
				prices.remove(i);
			}
		}
	}

	
	@Override
	public String toString() {
	      StringBuilder builder = new StringBuilder();
	      builder.append(" 1. Á¦Ç°¸í : ");
	      builder.append(names);
	      builder.append("\n 2. °¡°Ý : ");
	      builder.append(prices);
	      builder.append("\n 3. »çÀÌÁî : ");
	      builder.append(sizes);
	      builder.append("\n 4. ÃÑ¾× : ");
	      builder.append(amounts);
	      return builder.toString();
		}
}
