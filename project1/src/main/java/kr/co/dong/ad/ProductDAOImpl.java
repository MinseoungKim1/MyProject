package kr.co.dong.ad;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDAOImpl implements ProductDAO {
	@Inject
	SqlSession sqlSession;
	
	private final static String namespace="kr.co.dong.productMapper";
	
	@Override
	public List<ProductVO> plist() {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".plist");
	}

	@Override
	public List<ProductVO> admin_search(String pname) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace + ".admin_search",pname);
	}

	@Override
	public ProductVO admin_product(int pno) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".admin_product", pno);
	}

	@Override
	public ProductVO admin_edit(int pno) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".admin_edit", pno);
	}

	@Override
	public int adminEdit(ProductVO productVO) {
		// TODO Auto-generated method stub
		return sqlSession.update(namespace+".adminEdit", productVO);
	}

	@Override
	public int adminDelete(int pno) {
		// TODO Auto-generated method stub
		return sqlSession.update(namespace+".adminDelete", pno);
	}

	@Override
	public ProductVO shopDetail(int pno) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".shopDetail", pno);
	}



}
