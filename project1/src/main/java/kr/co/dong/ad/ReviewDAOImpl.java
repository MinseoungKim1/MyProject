package kr.co.dong.ad;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewDAOImpl implements ReviewDAO{

	@Inject
	SqlSession sqlSession;
	
	private final static String namespace="kr.co.dong.reviewMapper";

	@Override
	public List<ReviewVO> showReview(int pno) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".showReview", pno);
	}

	@Override
	public int writeReview(ReviewVO rVO) {
		// TODO Auto-generated method stub
		return sqlSession.insert(namespace+".writeReview", rVO);
	}

	@Override
	public int updateReview(ReviewVO rVO) {
		// TODO Auto-generated method stub
		return sqlSession.update(namespace+".updateReview", rVO);
	}

	@Override
	public ReviewVO bringOne(int rid) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".bringOne", rid);
	}

	@Override
	public int deleteReview(int rid) {
		// TODO Auto-generated method stub
		return sqlSession.update(namespace+".deleteReview", rid);
	}

	@Override
	public int avgReview(int pno) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".avgReview", pno);
	}

	@Override
	public int countReview(int pno) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".countReview", pno);
	}
	
	
}
