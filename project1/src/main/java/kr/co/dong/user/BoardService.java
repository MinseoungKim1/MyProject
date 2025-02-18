package kr.co.dong.user;

import java.util.List;
import java.util.Map;

import kr.co.dong.ad.NoticeVO;
import kr.co.dong.ad.OrderItemVO;
import kr.co.dong.ad.ProductVO;

public interface BoardService {
	
	
	// 글추가 insert
	public int register(BoardDTO boardDTO);
	
	public int join(UserVO userVO);
	
	public int productWrite(ProductVO productVO);
	
	public List<ProductVO> productTop10();
	
	public ProductVO productByPno(int pno);
	
	public int addCartItem(CartItemVO cartItemVO);
	
	public UserVO customerByCno(int cno);
	
	public UserVO customerByID(String userID);
	
	public int getLastOid();
	
	public int orderItemIns(OrderItemVO orderItemVO);
	
	public List<OrderItemVO> orderItemByCno(int cno);
	
	public OrderItemVO orderItemByOidAndPno (Map<String,Object> map);
	
	public int orderDel (Map<String,Object> map);
	
	public List<OrderItemVO> orderPageList (Map<String,Object> map);
	
	public int productUpdateReadcnt (int pno);
	
	// 글삭제
	public int delete(int bno);
	
	// 슬수정
	public int update(BoardDTO boardDTO);
	
	//로그인 처리를 위한 메소드
	public Map login(Map<String, Object> map);
	
	//댓글 쓰기를 위한 메소드
	public int reply(BoardReply boardReply);
	
	// 댓글 수정보기를 위한 메소드
	public BoardReply detailreply(int reno);
	
	// 댓글 수정을 처리하기 위한 메소드
	public int replyupdate(BoardReply boardReply);
	
	// 댓글 삭제를 처리하기 위한 메소드
	public int replyDelete(int reno);
	//------------------------------------------------
	// min
	//페이징 처리 list
	public List<ProductVO> list(int start, int ipageSIZE);
	
	public int product_totalRecord();
	
	//페이징 처리 카테고리
	public List<ProductVO> cate(String cate1, String cate2, String cate3, int start, int ipageSIZE);
	
	//공지 사항 추가
	public void insertNotice(NoticeVO noticeVO);
	//공지사항 업데이트
	public int Notice_Update(NoticeVO noticeVO);
	//product change for pdel g002
	public void updatePdel(List<ProductVO> products); 
	//product change for pdel g001
	public void updatePdel_1(List<ProductVO> products);	
	//product delete
	public void deleteProducts(List<Long> productIds);
	//재고가 0 이 되었을 떄 pdel 값이 g002 로 변경
	public void updatePdelToG003(List<String> productIds);
	
	public List<ProductVO> productAllList();
	
	public List<String> productCategory();
	
	public NoticeVO getNoticeById(int eno);
	
	public NoticeVO getLatestNotice();
	
	public void addNotice(NoticeVO noticeVO);
	
	public ProductVO getProduct_detail(int pno);

	//notice 공지사항 처리
	public List<NoticeVO> noticelist();

	public int product_Update(ProductVO productVO);

	public int eventpageForm(NoticeVO noticeVO);

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
