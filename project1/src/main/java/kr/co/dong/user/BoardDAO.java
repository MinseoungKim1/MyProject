/* CRUD 기능별
 * 메소드 원형 작업
 * 
 */
package kr.co.dong.user;

import java.util.List;
import java.util.Map;

import kr.co.dong.ad.NoticeVO;
import kr.co.dong.ad.OrderItemVO;
import kr.co.dong.ad.ProductVO;

public interface BoardDAO {
	// 삽입, 삭제, 갱신(수정) 메소드의 리턴타입은 되도록이면 int
	
	// 글추가 insert
	public int register(BoardDTO boardDTO);
	
	// 회원 추가
	public int join(UserVO userVO);
	
	public List<ProductVO> productAllList();
	
	// 상품 추가
	public int productWrite(ProductVO productVO);
	//--------------------------------------------
	public List<String> productCategory();
	
	public List<ProductVO> productTop10();
	
	public ProductVO productByPno(int pno);
	
	public int addCartItem(CartItemVO cartitemVO);
	
	public UserVO customerByCno(int cno);
	
	public UserVO customerByID(String userID);
	
	public int getLastOid();
	
	public int orderItemIns(OrderItemVO orderItemVO);
	
	public List<OrderItemVO> orderItemByCno (int cno);
	
	public OrderItemVO orderItemByOidAndPno (Map<String,Object> map);
	
	public int orderDel (Map<String,Object> map);
	
	public List<OrderItemVO> orderPageList (Map<String,Object> map);
	
	public int productUpdateReadcnt (int pno);
	
	// 글삭제
	public int delete(int bno);
	
	// 슬수정
	public int update(BoardDTO boardDTO);
	
	//로그인 처리를 위한 메소드
	public Map login(Map<String,Object> map);
	
	//댓글 쓰기를 위한 메소드
	public int reply(BoardReply boardReply);
	

	// 댓글 수정보기를 위한 메소드
	public BoardReply detailreply(int reno);
	
	// 댓글 수정을 처리하기 위한 메소드
	public int replyupdate(BoardReply boardReply);
	
	// 댓글 삭제를 처리하기 위한 메소드
	public int replyDelete(int reno);
	
	
	
	
	// --------------------------------------------------------------
	
	//min
	//페이징 
	public List<ProductVO> list(int start, int ipageSIZE);
	//총 페이지수
	public int product_totalRecord();
	//페이징 처리 카테고리
	public List<ProductVO> cate(Map<String , String> params, int start, int ipageSIZE);
	//update for pdel = 'g002'
	public void updatePdel(List<ProductVO> products);
	//update for pdel = 'g001'
	public void updatePdel_1(List<ProductVO> products);
	//update for pdel = 'g003'
	public void updatePdelToG003(List<String> productIds);
	//product delete for aminlist
	public void deleteProducts(List<Long> productIds);
	//id로 공지사항 조회
	public NoticeVO getNoticeById(int eno);
	//공지사항 main 으로 뜨우기
	public NoticeVO getLatestNotice();
	//상품 업데이트
	public int product_Update(ProductVO productVO);
	//notice 공지사항 업로드
	public int eventpageForm(NoticeVO noticeVO);
	//해당 상품 갖고 오기
	public ProductVO getProduct_detail(int pno);
	//공지사항을 가져오는 메소드
	public List<NoticeVO> noticelist();
	//공지사항 추가
	public void insertNotice(NoticeVO noticeVO);
	//공지사항 수정
	public int Notice_Update(NoticeVO noticeVO);
	
	
	
	
	
	
	
	
	
	
}





