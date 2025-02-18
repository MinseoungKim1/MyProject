package kr.co.dong.ad;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class OrderItemDAOImpl implements OrderItemDAO {

	@Inject
	SqlSession sqlSession;
	
	private final static String namespace = "kr.co.dong.checkoutMapper";

	@Override
	public int purchase(OrderItemVO oiVO) {
		// TODO Auto-generated method stub
		return sqlSession.insert(namespace+".purchase", oiVO);
	}

	@Override
	public List<Map<Object, Object>> getCategory() {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".getCategory");
	}
	
	@Override
	public int generateOid() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".getLastOid");
	}

	@Override
	public List<OrderItemVO> orders() {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".orders");
	}

	@Override
	public List<OrderItemVO> Mrevenue() {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".Mrevenue");
	}

	@Override
	public List<OrderItemVO> getMonthlyRevenue() {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".getMonthlyRevenue");
	}

	@Override
	public int up(OrderItemVO orderItemVO) {
		// TODO Auto-generated method stub
		return sqlSession.update(namespace+".up", orderItemVO);
	}

	@Override
	public List<OrderItemVO> order(int oid) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".order", oid);
	}

	@Override
	public int updateOrder(OrderItemVO orderItemVO) {
		// TODO Auto-generated method stub
		return sqlSession.update(namespace+".updateOrder", orderItemVO);
	}

	@Override
	public List<OrderItemVO> orderDistinct() {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".orderDistinct");
	}

	@Override
	public List<OrderItemVO> loginBringData(int cno) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".loginBringData", cno);
	}

	@Override
	public int countDeliv() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".countDeliv");
	}

	@Override
	public int countall() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".countall");
	}

	@Override
	public int countD() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".Dcount");
	}

	@Override
	public int countEd() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".Edcount");
	}

}
