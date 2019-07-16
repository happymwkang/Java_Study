package stock.view;

import java.util.ArrayList;
import java.util.Arrays;
import model.domain.Clothes;
import stock.controller.StockController;

public class StartView {

	public static void main(String[] args) {
		// public���� ����� ��ü Ÿ�Ե��� �̿��� starview�� �ʿ��� ���ο� ������ ����
				StockController controller = StockController.getInstance();
				
				//���ο� ��ǰ
				Clothes stockNew = new Clothes("��ؼ���", 25900, new ArrayList<>(Arrays.asList("90", "95","100")),
						"Top", "White", new ArrayList<Integer>(Arrays.asList(2,3,1)));
				
				
			//----------------------��ǰ ���� ��� Test------------------------
				
				System.out.println("-------------��� ��ǰ ��� ���-------------");
				controller.AllStockView();
				
				System.out.println("-------------�˻� ��ǰ ��� ���-------------");
				controller.OneStockView("�������μ���");
				controller.OneStockView("���ۿ��̵����"); //���ܹ߻�
				
				System.out.println("-------------�� ��ǰ �߰� �� ��ü ��� ���-------------");
				controller.insertStockView(stockNew);
				controller.AllStockView();
				
				//System.out.println("-------------��ǰ ���� �� ��ü ��� ���-------------");
				//controller.deleteStock(name);
				//controller.AllStockView();
				
				//System.out.println("-------------��ǰ ���� �� ��ü ��� ���-------------");
				//controller.updateStockView("�������μ���");
				
				//System.out.println("------------��ǰ������ ���� ��ٱ��Ͽ��� �ش��ǰ ����-----------");
				//controller.deleteStockView("�������μ���");
				
			//-----------------�� ���� �� īƮ ���� ��� Test-------------------
				System.out.println("----------��� �� ���----------");
				controller.allCustomerView();
				
				System.out.println("------------�� �˻�------------");
				controller.searchCustomerView("�۽���");
				
				System.out.println("-----------��ٱ��� ���� �߰� �� Ȯ��--------");
				controller.addProductInCartView("�谨��", "�������μ���", "100"); //���ܹ߻�
				controller.addProductInCartView("�۽���", "�������μ���", "95");
				//controller.searchCustomerView("�۽���");
				
				//System.out.println("-----------��ٱ��� ���� ���� �� Ȯ��--------");
				//controller.delProductInCartView("�۽���", "�������μ���", "95");
				//controller.searchCustomerView("�۽���");
				
				
				System.out.println("-----------�� ��ٱ��� ���� ��� �� ���------------");
				controller.sumPriceView("�۽���");
				
				//System.out.println("----------Ư�� ��ǰ ������ �� �˻�------------");
				//controller.searchSpecificCustomer("�������μ���");
	}
}