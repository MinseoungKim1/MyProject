package kr.co.dong.ad;
import java.util.List;
public interface ReviewService {
	public List<ReviewVO> showReview(int pno);
	
	public int writeReview(ReviewVO rVO);
	
	public ReviewVO bringOne(int rid);
	
	public int updateReview(ReviewVO rVO);
	
	public int deleteReview(int rid);
	
	public int avgReview(int pno);
	
	public int countReview(int pno);
}
