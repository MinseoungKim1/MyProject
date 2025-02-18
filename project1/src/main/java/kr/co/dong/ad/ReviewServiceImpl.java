package kr.co.dong.ad;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Inject
	ReviewDAO reviewDAO;

	@Override
	public List<ReviewVO> showReview(int pno) {
		// TODO Auto-generated method stub
		return reviewDAO.showReview(pno);
	}

	@Override
	public int writeReview(ReviewVO rVO) {
		// TODO Auto-generated method stub
		return reviewDAO.writeReview(rVO);
	}
	
	@Override
	public int updateReview(ReviewVO rVO) {
		// TODO Auto-generated method stub
		return reviewDAO.updateReview(rVO);
	}

	@Override
	public ReviewVO bringOne(int rid) {
		// TODO Auto-generated method stub
		return reviewDAO.bringOne(rid);
	}

	@Override
	public int deleteReview(int rid) {
		// TODO Auto-generated method stub
		return reviewDAO.deleteReview(rid);
	}

	@Override
	public int avgReview(int pno) {
		// TODO Auto-generated method stub
		return reviewDAO.avgReview(pno);
	}

	@Override
	public int countReview(int pno) {
		// TODO Auto-generated method stub
		return reviewDAO.countReview(pno);
	}


}
