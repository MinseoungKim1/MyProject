package kr.co.dong.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.dong.ad.NoticeVO;
import kr.co.dong.ad.OrderItemVO;
import kr.co.dong.ad.ProductVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
	private SqlSession sqlSession;

	private static final String nameSpace = "kr.co.dong.boardMapper";

	@Override
	public Map login(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(nameSpace + ".login", map);
	}

	@Override
	public int register(BoardDTO boardDTO) {
		// TODO Auto-generated method stub
		return sqlSession.insert(nameSpace + ".register", boardDTO);
	}

	@Override
	public int join(UserVO userVO) {
		return sqlSession.insert(nameSpace + ".join", userVO);
	}

	@Override
	public int productWrite(ProductVO productVO) {
		return sqlSession.insert(nameSpace + ".productWrite", productVO);
	}

	@Override
	public List<ProductVO> productAllList() {
		return sqlSession.selectList(nameSpace + ".productAllList");
	}

	@Override
	public List<String> productCategory() {
		return sqlSession.selectList(nameSpace + ".productCategory");
	}

	@Override
	public List<ProductVO> productTop10() {
		return sqlSession.selectList(nameSpace + ".productTop10");
	}

	@Override
	public ProductVO productByPno(int pno) {
		return sqlSession.selectOne(nameSpace + ".productByPno", pno);
	}

	@Override
	public int addCartItem(CartItemVO cartitemVO) {
		return sqlSession.insert(nameSpace + ".addCartItem", cartitemVO);
	}

	@Override
	public UserVO customerByCno(int cno) {
		return sqlSession.selectOne(nameSpace + ".customerByCno", cno);
	}

	@Override
	public UserVO customerByID(String userID) {
		return sqlSession.selectOne(nameSpace + ".customerByID", userID);
	}

	@Override
	public int getLastOid() {
		return sqlSession.selectOne(nameSpace + ".getLastOid");
	}

	@Override
	public int orderItemIns(OrderItemVO orderItemVO) {
		return sqlSession.insert(nameSpace + ".orderItemIns", orderItemVO);
	}

	@Override
	public List<OrderItemVO> orderItemByCno(int cno) {
		return sqlSession.selectList(nameSpace + ".orderItemByCno", cno);
	}

	@Override
	public OrderItemVO orderItemByOidAndPno(Map<String, Object> map) {
		return sqlSession.selectOne(nameSpace + ".orderItemByOidAndPno", map);
	}

	@Override
	public int orderDel(Map<String, Object> map) {
		return sqlSession.delete(nameSpace + ".orderDel", map);
	}

	@Override
	public List<OrderItemVO> orderPageList(Map<String, Object> map) {
		return sqlSession.selectList(nameSpace + ".orderPageList", map);
	}

	@Override
	public int productUpdateReadcnt(int pno) {
		return sqlSession.update(nameSpace + ".productUpdateReadcnt", pno);
	}

	@Override
	public int delete(int bno) {
		// TODO Auto-generated method stub
		return sqlSession.update(nameSpace + ".delete", bno);
	}

	@Override
	public int update(BoardDTO dto) {
		// TODO Auto-generated method stub
		return sqlSession.update(nameSpace + ".update", dto);
	}

	@Override
	public int reply(BoardReply boardReply) {
		// TODO Auto-generated method stub
		return sqlSession.insert(nameSpace + ".reply", boardReply);
	}

	@Override
	public BoardReply detailreply(int reno) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(nameSpace + ".detailReply", reno);
	}

	@Override
	public int replyupdate(BoardReply boardReply) {
		// TODO Auto-generated method stub
		return sqlSession.update(nameSpace + ".replyupdate", boardReply);
	}

	@Override
	public int replyDelete(int reno) {
		// TODO Auto-generated method stub
		return sqlSession.delete(nameSpace + ".replyDelete", reno);
	}

	// -------------------------------------------------------------------
	// min
	// 페이징
	@Override
	public List<ProductVO> list(int start, int ipageSIZE) {
		// TODO Auto-generated method stub
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("pageSIZE", ipageSIZE);
		return sqlSession.selectList(nameSpace + ".list", map);
	}

	// 페이징 처리 총 페이지수
	@Override
	public int product_totalRecord() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(nameSpace + ".product_totalRecord");
	}

	// product 카테고리
	@Override
	public List<ProductVO> cate(Map<String, String> params, int start, int ipageSIZE) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("pageSIZE", ipageSIZE);
		return sqlSession.selectList(nameSpace + ".adminList_r2", params);
	}

	// product status change
	@Override
	public void updatePdel(List<ProductVO> products) {
		sqlSession.update(nameSpace + ".updatePdel", products);
	}

	// product status change
	@Override
	public void updatePdel_1(List<ProductVO> products) {
		sqlSession.update(nameSpace + ".updatePdel_1", products);
	}

	// product delete
	@Override
	public void deleteProducts(List<Long> productIds) {
		sqlSession.delete(nameSpace + ".deleteProducts", productIds);
	}

	// product status change
	@Override
	public void updatePdelToG003(List<String> productIds) {
		sqlSession.update(nameSpace + ".updatePdelToG003", productIds);
	}


	// 공지사항 삽입
	@Override
	public void insertNotice(NoticeVO noticeVO) {
		sqlSession.insert(nameSpace + ".insertNotice", noticeVO);
	}
	// 공지사항 업데이트
	@Override
	public int Notice_Update(NoticeVO noticeVO) {
		return sqlSession.update(nameSpace + ".Notice_Update", noticeVO);
	}
	// 공지사항 폼
	@Override
	public int eventpageForm(NoticeVO noticeVO) {
		return sqlSession.insert(nameSpace + ".eventpageForm", noticeVO);
	}

	// 공지사항 id 갖고와서 공지사항 디테일
	@Override
	public NoticeVO getNoticeById(int eno) {
		return sqlSession.selectOne(nameSpace + ".getNoticeById", eno);
	}

	// 공지사항 추가한거 마지막 공지사항
	@Override
	public NoticeVO getLatestNotice() {
		return sqlSession.selectOne(nameSpace + ".getLatestNotice");
	}

	// 상품 업데이트
	@Override
	public int product_Update(ProductVO productVO) {
		return sqlSession.update(nameSpace + ".product_Update", productVO);
	}

	// 상세 상품 리스트
	@Override
	public ProductVO getProduct_detail(int pno) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(nameSpace + ".getProduct_detail", pno);
	}

	// notice 공지사항
	@Override
	public List<NoticeVO> noticelist() {
		// TODO Auto-generated method stub
		return sqlSession.selectList(nameSpace + ".noticelist");
	}

}
