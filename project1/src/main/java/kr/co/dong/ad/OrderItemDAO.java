package kr.co.dong.ad;

import java.util.List;
import java.util.Map;

public interface OrderItemDAO {

	public int purchase(OrderItemVO oiVO);
	
	List<Map<Object, Object>> getCategory();

	
	public int generateOid();

	List<OrderItemVO> orders();

	List<OrderItemVO> Mrevenue ();
	
	List<OrderItemVO> getMonthlyRevenue();

	public int up(OrderItemVO orderItemVO);
	
	public List<OrderItemVO> order(int oid);
	
	public int updateOrder(OrderItemVO orderItemVO);
	
	public List<OrderItemVO> orderDistinct();
	
	public List<OrderItemVO> loginBringData(int cno);
	
	public int countDeliv();
	
	public int countall();
	
	public int countD();
	
	public int countEd();

}
