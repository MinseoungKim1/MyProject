package kr.co.dong.ad;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl implements OrderItemService {

	@Inject
	OrderItemDAO oiDAO;

	@Override
	public int purchase(OrderItemVO oiVO) {
		// TODO Auto-generated method stub
		return oiDAO.purchase(oiVO);
	}


	@Override
	public List<Map<Object, Object>> getCategory() {
		// TODO Auto-generated method stub
		return oiDAO.getCategory();
	}
	
	@Override
	public int generateOid() {
		// TODO Auto-generated method stub
		return oiDAO.generateOid();
	}

	@Override
	public List<OrderItemVO> orders() {
		// TODO Auto-generated method stub
		return oiDAO.orders();
	}

	@Override
	public List<OrderItemVO> Mrevenue() {
		// TODO Auto-generated method stub
		return oiDAO.Mrevenue();
	}

	@Override
	public List<OrderItemVO> getMonthlyRevenue() {
		// TODO Auto-generated method stub
		return oiDAO.getMonthlyRevenue();
	}

	@Override
	public int up(OrderItemVO orderItemVO) {
		// TODO Auto-generated method stub
		return oiDAO.up(orderItemVO);
	}

	@Override
	public List<OrderItemVO> order(int oid) {
		// TODO Auto-generated method stub
		return oiDAO.order(oid);
	}

	@Override
	public int updateOrder(OrderItemVO orderItemVO) {
		// TODO Auto-generated method stub
		return oiDAO.updateOrder(orderItemVO);
	}

	@Override
	public List<OrderItemVO> orderDistinct() {
		// TODO Auto-generated method stub
		return oiDAO.orderDistinct();
	}

	@Override
	public List<OrderItemVO> loginBringData(int cno) {
		// TODO Auto-generated method stub
		return oiDAO.loginBringData(cno);
	}

	@Override
	public int countDeliv() {
		// TODO Auto-generated method stub
		return oiDAO.countDeliv();
	}

	@Override
	public int countall() {
		// TODO Auto-generated method stub
		return oiDAO.countall();
	}

	@Override
	public int countD() {
		// TODO Auto-generated method stub
		return oiDAO.countD();
	}

	@Override
	public int countEd() {
		// TODO Auto-generated method stub
		return oiDAO.countEd();
	}

	
}
