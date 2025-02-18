package kr.co.dong.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.dong.ad.NoticeVO;
import kr.co.dong.ad.OrderItemVO;
import kr.co.dong.ad.ProductVO;

@Service
public class BoardServiceImpl implements BoardService {
	@Inject
	private BoardDAO boardDAO;

	@Override
	public Map login(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return boardDAO.login(map);
	}
	@Override
	public int delete(int bno) {
		// TODO Auto-generated method stub
		return boardDAO.delete(bno);
	}

	@Override
	public int register(BoardDTO boardDTO) {
		// TODO Auto-generated method stub
		return boardDAO.register(boardDTO);
	}

	@Override
	public int join(UserVO userVO) {
		return boardDAO.join(userVO);
	}

	@Override
	public List<ProductVO> productAllList() {
		return boardDAO.productAllList();
	}

	@Override
	public int productWrite(ProductVO productVO) {
		return boardDAO.productWrite(productVO);
	}

	@Override
	public List<String> productCategory() {
		return boardDAO.productCategory();
	}

	@Override
	public List<ProductVO> productTop10() {
		return boardDAO.productTop10();
	}

	@Override
	public ProductVO productByPno(int pno) {
		return boardDAO.productByPno(pno);
	}

	@Override
	public int addCartItem(CartItemVO cartItemVO) {
		return boardDAO.addCartItem(cartItemVO);
	}

	@Override
	public UserVO customerByCno(int cno) {
		return boardDAO.customerByCno(cno);
	}

	@Override
	public UserVO customerByID(String userID) {
		return boardDAO.customerByID(userID);
	}

	@Override
	public int getLastOid() {
		return boardDAO.getLastOid();
	}

	@Override
	public int orderItemIns(OrderItemVO orderItemVO) {
		return boardDAO.orderItemIns(orderItemVO);
	}

	@Override
	public List<OrderItemVO> orderItemByCno(int cno) {
		return boardDAO.orderItemByCno(cno);
	}

	@Override
	public OrderItemVO orderItemByOidAndPno(Map<String, Object> map) {
		return boardDAO.orderItemByOidAndPno(map);
	}

	@Override
	public int orderDel(Map<String, Object> map) {
		return boardDAO.orderDel(map);
	}

	@Override
	public List<OrderItemVO> orderPageList(Map<String, Object> map) {
		return boardDAO.orderPageList(map);
	}

	@Override
	public int productUpdateReadcnt(int pno) {
		return boardDAO.productUpdateReadcnt(pno);
	}

	@Override
	public int update(BoardDTO dto) {
		// TODO Auto-generated method stub
		return boardDAO.update(dto);
	}

	@Override
	public int reply(BoardReply boardReply) {
		// TODO Auto-generated method stub
		return boardDAO.reply(boardReply);
	}

	@Override
	public BoardReply detailreply(int reno) {
		// TODO Auto-generated method stub
		return boardDAO.detailreply(reno);
	}

	@Override
	public int replyupdate(BoardReply boardReply) {
		// TODO Auto-generated method stub
		return boardDAO.replyupdate(boardReply);
	}

	@Override
	public int replyDelete(int reno) {
		// TODO Auto-generated method stub
		return boardDAO.replyDelete(reno);
	}

	// ----------------------------------------------------
	// min
	// 페이징
	@Override
	public List<ProductVO> list(int start, int ipageSIZE) {
		// TODO Auto-generated method stub
		return boardDAO.list(start, ipageSIZE);
	}

	@Override
	public int product_totalRecord() {
		// TODO Auto-generated method stub
		return boardDAO.product_totalRecord();
	}

	@Override
	public List<ProductVO> cate(String cate1, String cate2, String cate3, int start, int ipageSIZE) {
		Map<String, String> params = new HashMap<>();
		params.put("cate1", cate1);
		params.put("cate2", cate2);
		params.put("cate3", cate3);
		return boardDAO.cate(params, start, ipageSIZE);
	}

	@Override
	public void updatePdel(List<ProductVO> products) {
		boardDAO.updatePdel(products);
	}

	@Override
	public void updatePdel_1(List<ProductVO> products) {
		boardDAO.updatePdel_1(products);
	}

	// product delete
	@Override
	public void deleteProducts(List<Long> productIds) {
		boardDAO.deleteProducts(productIds);
	}

	@Override
	public void updatePdelToG003(List<String> productIds) {
		boardDAO.updatePdelToG003(productIds);
	}

	// notice 공지사항
	@Override
	public List<NoticeVO> noticelist() {
		// TODO Auto-generated method stub
		return boardDAO.noticelist();
	}
	//공지사항 삽입
	@Override
	public void insertNotice(NoticeVO noticeVO) {
		boardDAO.insertNotice(noticeVO);
	}
	//공지사항 업데이트
	@Override
	public int Notice_Update(NoticeVO noticeVO) {
		return boardDAO.Notice_Update(noticeVO);
	}
	@Override
	public NoticeVO getNoticeById(int eno) {
		return boardDAO.getNoticeById(eno); // DAO 호출
	}

	@Override
	public NoticeVO getLatestNotice() {
		return boardDAO.getLatestNotice();
	}

	@Override
	public void addNotice(NoticeVO noticeVO) {
		boardDAO.insertNotice(noticeVO);
	}


	@Override
	public int product_Update(ProductVO productVO) {
		return boardDAO.product_Update(productVO);
	}

	// 공지사항
	@Override
	public int eventpageForm(NoticeVO noticeVO) {
		return boardDAO.eventpageForm(noticeVO);
	}

	@Override
	public ProductVO getProduct_detail(int pno) {
		// TODO Auto-generated method stub
		return boardDAO.getProduct_detail(pno);
	}
	
	

}
