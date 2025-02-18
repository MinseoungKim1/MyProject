package kr.co.dong.project.usercontroller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.dong.ad.OrderItemService;
import kr.co.dong.ad.OrderItemVO;
import kr.co.dong.ad.ProductService;
import kr.co.dong.ad.ProductVO;
import kr.co.dong.ad.ReviewService;
import kr.co.dong.ad.ReviewVO;
import kr.co.dong.user.BoardService;
import kr.co.dong.user.CartItemVO;
import kr.co.dong.user.UserVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Inject
	BoardService boardService;
	@Inject
	ProductService productService;
	@Inject
	OrderItemService oiService;
	@Inject
	ReviewService reviewService;

	@Autowired
	private ServletContext servletContext;
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(Model model) {
		
		List<ProductVO> productList = boardService.productAllList();
		List<String> productCategory = boardService.productCategory();
		List<ProductVO> productTop10 = boardService.productTop10();
		/*
		 * for (String category : productCategory) { logger.info(category); }
		 */
		model.addAttribute("productList", productList);
		model.addAttribute("productCategory", productCategory);
		model.addAttribute("productTop10", productTop10);
		return "main";
	}
	
	
	@RequestMapping(value="/shopGrid", method = RequestMethod.GET)
	public String shop_grid(Model model) {
		List<ProductVO> productList = boardService.productAllList();
		List<String> productCategory = boardService.productCategory();
		List<ProductVO> productTop10 = boardService.productTop10();
		
		model.addAttribute("productList", productList);
		model.addAttribute("productCategory", productCategory);
		model.addAttribute("productTop10", productTop10);
		
		return "shopGrid";
	}
	
	@RequestMapping(value="/shopDetails", method = RequestMethod.GET)
	public String shopDetails() {
		
		return "shopDetails";
	}
	
	@RequestMapping(value="/shopDetail", method = RequestMethod.GET)
	public String shopDetail(@RequestParam("pno") int pno, Model model,
			HttpSession session) throws Exception {
		
		ProductVO productByPno = boardService.productByPno(pno);
		int r = boardService.productUpdateReadcnt(pno);
		Map<String, String> productImages = new HashMap<>();
		for (int i = 1; i <= 10; i++) {
			String key = "pimg" + i;
			Method getter = ProductVO.class.getMethod("get" + Character.toUpperCase(key.charAt(0)) + key.substring(1));
			String value = (String) getter.invoke(productByPno);
			productImages.put(key, value);
		}
		
		model.addAttribute("productImages", productImages);
		model.addAttribute("productByPno", productByPno);
		
		session.setAttribute("pno", pno);
		
		List<ReviewVO> reviews = reviewService.showReview(pno);
		System.out.println(reviews);
		model.addAttribute("reviews", reviews);
		
		int avg = reviewService.avgReview(pno);
		model.addAttribute("avg", avg);
		int count = reviewService.countReview(pno);
		model.addAttribute("count", count);
		model.addAttribute("pno", pno);
		System.out.println("avg: " + avg + ", count: "+ count + ", pno: "+ pno);

		
		
		return "shopDetails";
	}
	
	@RequestMapping(value="/getReviews", method = RequestMethod.GET)
	@ResponseBody  
	public Map<String, Object> getReviews(@RequestParam("pno") int pno) {
	    List<ReviewVO> reviews = reviewService.showReview(pno);
	    Map<String, Object> response = new HashMap<>();
	    response.put("reviews", reviews);  
	    return response;  
	}

	
	@RequestMapping(value="/shopDetail", method = RequestMethod.POST)
	public String shopDetail(
			@RequestParam("rating")int rating,
			@RequestParam("rcontext")String rcontext,
			@RequestParam("pno") int pno,
			Model model, ReviewVO reviewVO, HttpSession session,
			RedirectAttributes rttr) {
		Map user = (Map) session.getAttribute("user");
		if (user != null) {
		    Object cnoObj = user.get("cno");
		    Object cidObj = user.get("cid");
		    String cid = (String) cidObj;
		    if (cnoObj instanceof Integer) {
		        int cno = (int) cnoObj;
		       
		        session.setAttribute("cno", cno);
		        System.out.println("CNMOTHERFUCKINO IS: " + cno);
		        ReviewVO review = new ReviewVO();
				review.setPno(pno);
				review.setCno(cno);
				review.setCid(cid);
				review.setRating(rating);
				review.setRcontext(rcontext);
				
				int r = reviewService.writeReview(review);
					if (r>=1) {
					}else {
						}
		       
		    } else {
		    	
		    }
		} else {
	        rttr.addFlashAttribute("msg", "Please login first");
	        System.out.println("Flash Attribute added");
		    return "redirect:/projectLogin";
		}
		return "redirect:/shopDetail?pno="+pno;
	}

	
	@RequestMapping(value="editReview", method=RequestMethod.POST)
	public String editReview(
			@RequestParam("pno") int pno,
			@RequestParam("rid") int rid,
			@RequestParam("rating")int rating,
			@RequestParam("rcontext")String rcontext,
			RedirectAttributes redirectAttributes,
			HttpSession session,
			RedirectAttributes rttr) throws Exception {
		
		System.out.println("rid: " + rid);
		System.out.println("rating: "+ rating);
		System.out.println("rcontext: " + rcontext);
		
		ReviewVO oneReview = reviewService.bringOne(rid);
		System.out.println(oneReview);
		Map user = (Map) session.getAttribute("user");
		Object cnoObj = user.get("cno");
		int ogcno = (int) cnoObj;
		
		int cno = oneReview.getCno();
		
		if (ogcno == cno) {
			ReviewVO edit = new ReviewVO();
			edit.setRid(rid);
			edit.setRating(rating);
			edit.setRcontext(rcontext);
					
			int r = reviewService.updateReview(edit);
					
			if (r>=1) {
					System.out.println("Updated");
			}else {
					System.out.println("HELL NAH");}
			} else {
				System.out.println("NO");
			    rttr.addFlashAttribute("msg", "Please login first.");
			    return "redirect:/projectLogin";
			}
			return "redirect:/shopDetail?pno="+pno;
		}
		@RequestMapping(value="/deleteReview", method=RequestMethod.POST)
		public String deleteReview(@RequestParam("rid") int rid,
				@RequestParam("pno")int pno,
				HttpSession session,
				RedirectAttributes rttr) {
		    System.out.println("Received rid: " + rid);
		    ReviewVO oneReview = reviewService.bringOne(rid);
			System.out.println(oneReview);
			
		    Map user = (Map) session.getAttribute("user");
			Object cnoObj = user.get("cno");
			int ogcno = (int) cnoObj;
			System.out.println(ogcno);
			int cno = oneReview.getCno();
			System.out.println(cno);
			
			if (cno == ogcno || cno == 1) {
		    reviewService.deleteReview(rid);
		    System.out.println(rid);
		} else {
			System.out.println("No can do");
			rttr.addFlashAttribute("msg", "Please login first");
		 return "redirect:/projectLogin";
		} 
		return "redirect:/shopDetail?pno="+pno; 
	}

	
	
	@ResponseBody
	@RequestMapping(value="/updateCart", method = RequestMethod.POST)
	public void updateCart(
			@RequestBody List<CartItemVO> quantities,
			HttpServletRequest request,
			HttpServletResponse response
			) throws Exception {
				// 1. 기존 쿠키에서 장바구니 데이터 가져오기
				String cartData = null;
				Cookie[] cookies = request.getCookies();
				if (cookies != null) {
					for (Cookie cookie : cookies) {
						if ("cart".equals(cookie.getName())) {
							cartData = cookie.getValue();
							break;
						}
					}
				}
				
				// 2. JSON으로 장바구니 데이터 파싱
				List<CartItemVO> cartItems = new ArrayList<>();
				ObjectMapper objectMapper = new ObjectMapper(); // JSON 처치 라이브러리
				String decodedCartData = null;
				
				if (cartData != null) {
					try {
						decodedCartData = URLDecoder.decode(cartData, "UTF-8");
						cartItems = objectMapper.readValue(decodedCartData, new TypeReference<List<CartItemVO>>() {});
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					}
				}
				
				
				
				
				 for (CartItemVO item : cartItems) { 
					 for (int i=0; i < quantities.size(); i++) {
					 if (item.getPno() == quantities.get(i).getPno()) { 
						 item.setCartQuant(quantities.get(i).getCartQuant()); 
						 }
					 }
				 }
				 
				// 4. 업데이트된 데이터를 쿠키에 저장
					try {
						String updatedCartData = objectMapper.writeValueAsString(cartItems);
						String encodedCartData = URLEncoder.encode(updatedCartData, "UTF-8");
						
						Cookie cartCookie = new Cookie("cart", encodedCartData);
						
						cartCookie.setPath("/"); // 쿠키의 유효 경로
						cartCookie.setMaxAge(7 * 24 * 60 * 60);
						response.addCookie(cartCookie);
					} catch (JsonProcessingException e) {
						e.printStackTrace(); // 직렬화 실패 시 로그
					}
				 
				 
				
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteCartItem", method=RequestMethod.POST)
	public void deleteCartItem(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("pno") int pno,
			@RequestParam("cno") int cno
			) throws Exception {
		
		String cartData = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("cart".equals(cookie.getName())) {
					cartData = cookie.getValue();
					break;
				}
			}
		}
		
		// 2. JSON으로 장바구니 데이터 파싱
		List<CartItemVO> cartItems = new ArrayList<>();
		ObjectMapper objectMapper = new ObjectMapper(); // JSON 처치 라이브러리
		String decodedCartData = null;
		
		if (cartData != null) {
			try {
				decodedCartData = URLDecoder.decode(cartData, "UTF-8");
				cartItems = objectMapper.readValue(decodedCartData, new TypeReference<List<CartItemVO>>() {});
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		
		cartItems.removeIf(item -> item.getPno() == pno && item.getCno() == cno);
		
		// 업데이트 된 리스트를 JSON 문자열로 반환
		String updatedCartData = objectMapper.writeValueAsString(cartItems);
		String encodedCartData = URLEncoder.encode(updatedCartData, "UTF-8");
		
		// 업데이트 된 데이터를 쿠키에 저장
		Cookie cartCookie = new Cookie("cart", encodedCartData);
		cartCookie.setPath("/");
		cartCookie.setMaxAge(7 * 24 * 60 * 60);
		response.addCookie(cartCookie);
		
		
		
	}
	
	@ResponseBody
	@RequestMapping(value="/addCartItem", method = RequestMethod.POST)
	public void addCartItem(
			@RequestParam("pno") int pno,
			@RequestParam("quantity") int quantity,
			@RequestParam("cno") int cno,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) throws Exception {
		
		// 1. 기존 쿠키에서 장바구니 데이터 가져오기
		String cartData = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("cart".equals(cookie.getName())) {
					cartData = cookie.getValue();
					break;
				}
			}
		}
		
		// 2. JSON으로 장바구니 데이터 파싱
		List<CartItemVO> cartItems = new ArrayList<>();
		ObjectMapper objectMapper = new ObjectMapper(); // JSON 처치 라이브러리
		String decodedCartData = null;
		
		if (cartData != null) {
			try {
				decodedCartData = URLDecoder.decode(cartData, "UTF-8");
				cartItems = objectMapper.readValue(decodedCartData, new TypeReference<List<CartItemVO>>() {});
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		// 3. 새로운 항목 추가
		CartItemVO newItem = new CartItemVO();
		newItem.setPno(pno);
		newItem.setCartQuant(quantity);
		newItem.setCno(cno);
		
		
		
		
		
		// 기존 장바구니에 동일 상품이 있는지 확인
		boolean exists = false;
		for (CartItemVO item : cartItems) {
			if (item.getPno() == newItem.getPno() && item.getCno()== cno) {
				item.setCartQuant(item.getCartQuant() + quantity);
				exists = true;
				break;
			}
		}
		
		
		// 동일 상품이 없으면 추가
		if (exists == false) {
			cartItems.add(newItem);
		}
		
		
		
		// 4. 업데이트된 데이터를 쿠키에 저장
		try {
			String updatedCartData = objectMapper.writeValueAsString(cartItems);
			String encodedCartData = URLEncoder.encode(updatedCartData, "UTF-8");
			
			Cookie cartCookie = new Cookie("cart", encodedCartData);
			
			cartCookie.setPath("/"); // 쿠키의 유효 경로
			cartCookie.setMaxAge(7 * 24 * 60 * 60);
			response.addCookie(cartCookie);
		} catch (JsonProcessingException e) {
			e.printStackTrace(); // 직렬화 실패 시 로그
		}
		
	}
	
	@RequestMapping(value="/shoppingCart", method = RequestMethod.GET)
	public String shoppingCart(Model model,
			HttpSession session,
			HttpServletRequest request,
			HttpServletResponse response,
			RedirectAttributes rttr) throws Exception {
		
		Map<String,Object> user = (Map<String,Object>)session.getAttribute("user");
		
		String cartData = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("cart".equals(cookie.getName())) {
					cartData = cookie.getValue();
					break;
				}
			}
		}
		
		List<CartItemVO> cartItems = new ArrayList<>();
		ObjectMapper objectMapper = new ObjectMapper(); // JSON 처치 라이브러리
		String decodedCartData = null;
		
		if (cartData != null) {
			try {
				decodedCartData = URLDecoder.decode(cartData, "UTF-8");
				cartItems = objectMapper.readValue(decodedCartData, new TypeReference<List<CartItemVO>>() {});
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		
		
		List<ProductVO> CartList = new ArrayList<>();
		for (int i=0; i < cartItems.size(); i++) {
			if (user.get("cno").equals(cartItems.get(i).getCno())) {
				ProductVO productByPno = boardService.productByPno(cartItems.get(i).getPno());
				CartList.add(productByPno);
			}
		}
		

		if (user != null) {
		    Object cnoObj = user.get("cno");

		    if (cnoObj instanceof Integer) {
		        int cno = (int) cnoObj;
		        session.setAttribute("cno", cno);
		        System.out.println("CNMOTHERFUCKINO IS: " + cno);
		    } else {
		        System.out.println("cno is not of type Integer");
		    }
		} else {
		    rttr.addFlashAttribute("msg", "로그인을 먼저해주세요.");
		    System.out.println("NO CNO");
		    return "redirect:/projectLogin";
		}

		
		model.addAttribute("cartItems", cartItems);
		model.addAttribute("CartList", CartList);
		
		
		return "shoppingCart";
	}
	
	
	@RequestMapping(value="/checkout", method = RequestMethod.POST)
	public String checkout(
			HttpSession session,
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("subtotal") String subtotal,
			@RequestParam("alltotal") String alltotal,
			Model model
			) throws Exception {
		
		
		
		String cartData = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("cart".equals(cookie.getName())) {
					cartData = cookie.getValue();
					break;
				}
			}
		}
		
		// 2. JSON으로 장바구니 데이터 파싱
		List<CartItemVO> cartItems = new ArrayList<>();
		ObjectMapper objectMapper = new ObjectMapper(); // JSON 처치 라이브러리
		String decodedCartData = null;
		
		if (cartData != null) {
			try {
				decodedCartData = URLDecoder.decode(cartData, "UTF-8");
				cartItems = objectMapper.readValue(decodedCartData, new TypeReference<List<CartItemVO>>() {});
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		
		Map<String,Object> user = (Map<String,Object>)session.getAttribute("user");
		
		List<ProductVO> CartList = new ArrayList<>();
		for (int i=0; i < cartItems.size(); i++) {
			if (user.get("cno").equals(cartItems.get(i).getCno())) {
				ProductVO productByPno = boardService.productByPno(cartItems.get(i).getPno());
				CartList.add(productByPno);
			}
		}
		
		model.addAttribute("CartList", CartList);
		model.addAttribute("cartItems", cartItems);
		model.addAttribute("subtotal", subtotal);
		model.addAttribute("alltotal", alltotal);
		/*
		 * UserVO customerByCno = boardService.customerByID(value);
		 * 
		 * System.out.println(customerByCno);
		 */
		
		
		return "checkout";
	}
	
	
	
	
	
	@RequestMapping(value="/blogDetails", method = RequestMethod.GET)
	public String blogDetails() {
		
		return "blogDetails";
	}
	
	@RequestMapping(value="/blog", method = RequestMethod.GET)
	public String blog() {
		
		return "blog";
	}
	
	@RequestMapping(value="/contact", method = RequestMethod.GET)
	public String contact() {
		
		return "contact";
	}
	
	@RequestMapping(value="/projectLogin", method = RequestMethod.GET)
	public String projectLogin() {
		
		return "projectLogin";
	}
	
	@RequestMapping(value="/wishList", method = RequestMethod.GET)
	public String wishList() {
		
		return "wishList";
	}
	
	
	@RequestMapping(value="projectLogin",method= RequestMethod.POST)
	public String login(@RequestParam Map<String,Object> map,
			HttpServletRequest request,HttpServletResponse response,HttpSession session, RedirectAttributes rttr) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		Map user = boardService.login(map);
		if (user == null) {
			// 로그인 실패
			rttr.addFlashAttribute("msg", "로그인에 실패하였습니다.");
			return "redirect:projectLogin";
		}
		Object cnoObj = user.get("cno");
	    int cno = (int) cnoObj;
	    session.setAttribute("cno", cno);
	    
	    Object cidObj = user.get("cid");
	    String cid = (String) cidObj;
	    session.setAttribute("cid", cid);

	    	
			// 로그인 성공
			rttr.addFlashAttribute("msg", "로그인 성공");
			session.setAttribute("user", user);
			return "redirect:/";
		
	}
	
	@RequestMapping(value="projectLogin/join", method= RequestMethod.POST)
	public String register(UserVO userVO, HttpServletRequest request,RedirectAttributes rttr) throws Exception {
		request.setCharacterEncoding("UTF-8");
		logger.info("내용" + userVO);
		int r = boardService.join(userVO);
		
		if(r>0) {
			rttr.addFlashAttribute("msg","추가에 성공하였습니다.");
		}
		return "redirect:/";
	}	
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping(value="/myPage", method = RequestMethod.GET)
	public String myPage() {
		
		return "myPage";
	}
	
	@RequestMapping(value="/review", method = RequestMethod.GET)
	public String review() {
		
		return "review";
	}
	
	@RequestMapping(value="/myEdit", method = RequestMethod.GET)
	public String myEdit() {
		
		return "myEdit";
	}
	
	public static int opageSize = 10; // 한 페이지에 담을 게시글의 개수
	public static int ototalRecord = 0; // 기본 게시글 셋팅
	public static int ototalPage = 1; // 기본 페이지 셋팅
	
	public static int ostartPage = 1; // 시작페이지
	public static int oendPage = 10; // 끝나는 페이지
	public static int opageListSize = 10; // 표시할 페이지 총 갯수
	
	
	
	
	@RequestMapping(value="/orderList", method = RequestMethod.GET)
	public String orderList(
			@RequestParam(value="pageNUM", defaultValue = "1") int pageNUM,
			@RequestParam(value="pageListNUM", defaultValue= "1") int pageListNUM,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session,
			Model model
			) {
		Map<String,Object> user = (Map<String,Object>)session.getAttribute("user");
		
		int cno = (Integer)user.get("cno");
		
		List<OrderItemVO> orderItemVOList = new ArrayList<>();
		orderItemVOList = boardService.orderItemByCno(cno);
		
		model.addAttribute("orderItemVOList", orderItemVOList);
		
		
		//페이징
		ototalRecord = orderItemVOList.size(); // 게시글의 총 갯수
		ototalPage = ototalRecord / opageSize; // 총 페이지 = 게시글 총 갯수 / 페이지당 개시글
		if (ototalRecord % opageSize != 0) {
			ototalPage++;
		} // 결국 totalRecord / pageSize를 올림처리 한 것과 동일함.
		
		int start = (pageNUM - 1) * opageSize; // 0 * 10 = 0
		
		ostartPage = (pageListNUM - 1) * opageListSize + 1; // 1-1 * 10 + 1 = 11
		oendPage = ostartPage + opageListSize -1; // 11 + 10 - 1 == 20
		if (oendPage > ototalPage) {
			oendPage = ototalPage;
		}
		
		Map<String,Object> map = new HashMap<>();
		map.put("cno",cno);
		map.put("start", start);
		map.put("pageSize", opageSize);
		
		model.addAttribute("orderPageList", boardService.orderPageList(map));
		model.addAttribute("totalPage", ototalPage);
		model.addAttribute("startPage", ostartPage);
		model.addAttribute("pageListNUM", pageListNUM);
		model.addAttribute("endPage", oendPage);
		
		
		return "orderList";
	}
	
	@RequestMapping(value="orderList", method=RequestMethod.POST)
	   public String orderList (@RequestParam("selectedOption")String selectedOption, OrderItemVO oiVO, RedirectAttributes rttr, HttpSession session) {
	      if ("D003".equalsIgnoreCase(selectedOption)) {
	         Map user = (Map) session.getAttribute("user");
	         int cno = (int) user.get("cno");
	         
	         if ("D003".equalsIgnoreCase(selectedOption)) {
	            List<OrderItemVO> mine = oiService.loginBringData(cno);
	            for (OrderItemVO order : mine) {
	               order.setOstatus("D004");
	               int r = oiService.updateOrder(order);
	               if (r>=1) {
	                  rttr.addFlashAttribute("msg", "배송 완료 처리되었습니다. 감사합니다!");
	                  return "redirect:orderList";
	               }else {
	                  rttr.addFlashAttribute("msg", "실패");
	               }
	            }
	         }
	         
	         }else {
	            rttr.addFlashAttribute("msg", "배송되지 않았습니다.");
	            }
	      return "redirect:orderList";
	}
	
	@RequestMapping(value="/orderDetail", method = RequestMethod.GET)
	public String orderDetail(
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session,	
			@RequestParam("oid") int oid,
			@RequestParam("pno") int pno,
			Model model
				
			) {
		Map<String,Object> map = new HashMap<>();
		map.put("oid", oid);
		map.put("pno", pno);
		
		
		
		OrderItemVO orderItemByOidAndPno = boardService.orderItemByOidAndPno(map);
		ProductVO product = boardService.productByPno(pno);
		
		
		model.addAttribute("product", product);
		model.addAttribute("orderitem", orderItemByOidAndPno);
		
		
		return "orderDetail";
	}
	
	@RequestMapping(value="orderDel", method = RequestMethod.GET)
	public String orderDel(
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session,
			@RequestParam("oid") int oid,
			@RequestParam("pno") int pno,
			RedirectAttributes rttr
			
			) {
		
		Map<String,Object> map = new HashMap<>();
		map.put("oid", oid);
		map.put("pno", pno);
		
		int r = boardService.orderDel(map);
		
		if ( r > 0 ) {
			rttr.addFlashAttribute("msg", "구매 취소가 완료되었습니다.");
		}
		return "redirect:orderList";
	}
	
	
	@RequestMapping(value="/orderProduct", method = RequestMethod.POST)
	public String orderProduct(
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session,
			Model model,
			@RequestParam("ostatus") String ostatus,
			@RequestParam("odate") String odate,
			RedirectAttributes rttr
			
			) throws Exception {
		
		String cartData = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("cart".equals(cookie.getName())) {
					cartData = cookie.getValue();
					break;
				}
			}
		}
		
		// 2. JSON으로 장바구니 데이터 파싱
		List<CartItemVO> cartItems = new ArrayList<>();
		ObjectMapper objectMapper = new ObjectMapper(); // JSON 처치 라이브러리
		String decodedCartData = null;
		
		if (cartData != null) {
			try {
				decodedCartData = URLDecoder.decode(cartData, "UTF-8");
				cartItems = objectMapper.readValue(decodedCartData, new TypeReference<List<CartItemVO>>() {});
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		
		Map<String,Object> user = (Map<String,Object>)session.getAttribute("user");
		
		List<ProductVO> CartList = new ArrayList<>();
		for (int i=0; i < cartItems.size(); i++) {
			if (user.get("cno").equals(cartItems.get(i).getCno())) {
				ProductVO productByPno = boardService.productByPno(cartItems.get(i).getPno());
				CartList.add(productByPno);
			}
		}
		
		
		
		int LastOid = boardService.getLastOid()+1;
		
		
		for (ProductVO product: CartList) {
			for (int i=0; i < cartItems.size(); i++) {
				if (product.getPno() == cartItems.get(i).getPno()) {
					OrderItemVO orderItemVO = new OrderItemVO();
					orderItemVO.setCno((Integer)user.get("cno"));
					orderItemVO.setOid(LastOid);
					orderItemVO.setOno("ORDER00" + LastOid);
					orderItemVO.setPno(product.getPno());
					orderItemVO.setPname(product.getPname());
					orderItemVO.setOrderquan(cartItems.get(i).getCartQuant());
					orderItemVO.setPprice(product.getPprice());
					orderItemVO.setOstatus(ostatus);
					orderItemVO.setPcat(product.getPcat());
					int price = product.getPprice();
					int quant = cartItems.get(i).getCartQuant();
					orderItemVO.setOtotal(price * quant);
					
					boardService.orderItemIns(orderItemVO);
				}
			}	
		}
		
		
		
		int cno = (Integer)user.get("cno");
		cartItems.removeIf(item -> item.getCno() == cno);
		
		// 업데이트 된 리스트를 JSON 문자열로 반환
		String updatedCartData = objectMapper.writeValueAsString(cartItems);
		String encodedCartData = URLEncoder.encode(updatedCartData, "UTF-8");
		
		// 업데이트 된 데이터를 쿠키에 저장
		Cookie cartCookie = new Cookie("cart", encodedCartData);
		cartCookie.setPath("/");
		cartCookie.setMaxAge(7 * 24 * 60 * 60);
		response.addCookie(cartCookie);
		
		
		rttr.addFlashAttribute("msg", "구매 완료~!");
		
		return "redirect:/orderList";
	}
	
	
	@RequestMapping(value="/CustomerService", method = RequestMethod.GET)
	public String CustomerService() {
		
		return "CustomerService";
	}
	
	@RequestMapping(value="/RecentlyView", method = RequestMethod.GET)
	public String RecentlyView() {
		
		return "RecentlyView";
	}
	
	@RequestMapping(value="/productWrite", method = RequestMethod.GET)
	public String productWrite() {
		
		return "productWrite";
	}
	
	@ResponseBody
	@RequestMapping(value="/productAllList", method = RequestMethod.POST)
	public List<ProductVO> productAllList() {
		return boardService.productAllList();
	}
	
	/*
	 * @RequestMapping(value="/productWrite",method= RequestMethod.POST) public
	 * String productWrite(ProductVO productVO, @RequestParam("multiFile")
	 * List<MultipartFile> multiFileList, HttpServletRequest
	 * request,RedirectAttributes rttr) throws Exception {
	 * request.setCharacterEncoding("utf-8");
	 * 
	 * // 1. 전송받은 파일 및 파일설명 값 가져오기 System.out.println("multiFileList:" +
	 * multiFileList); System.out.println(multiFileList.size());
	 * 
	 * // 2. 저장할 경로 가져오기 //V0.1 // String path =
	 * "C:\\workspace\\10.testspring_project\\src\\main\\webapp\\resources\\static\\img";
	 * 
	 * //V0.2 (배포한경에서 사용해야 할 코드) // ServletContext context =
	 * request.getSession().getServletContext(); // String path =
	 * context.getRealPath("/resources/static/img");
	 * 
	 * //V0.3 (개발환경에서 사용해야 할 코드) String realpath =
	 * servletContext.getRealPath("/resources/static/img/");
	 * System.out.println("realpath:" + realpath); String modifiedpath =
	 * realpath.replaceAll("\\\\.metadata.*?wtpwebapps", ""); String path =
	 * modifiedpath.replace("resources", "src\\main\\webapp\\resources");
	 * System.out.println("path:" + path); String root = path + "\\uploadFiles";
	 * 
	 * // DB에 저장할 경로 String DBroot = "img/uploadFiles/";
	 * 
	 * 
	 * File file = new File(root);
	 * 
	 * // 만약 uploadFiles 폴더가 없으면 생성해라 if(!file.exists()) file.mkdirs();
	 * 
	 * List<Map <String, String>> fileList = new ArrayList<>();
	 * 
	 * // 업로드할 폴더 설정 for (int i = 0; i<multiFileList.size(); i++) { String
	 * originFileName = multiFileList.get(i).getOriginalFilename(); //확장자 뽑기 String
	 * ext = originFileName.substring(originFileName.lastIndexOf(".")); String
	 * ranFileName = UUID.randomUUID().toString() + "_" + originFileName;
	 * 
	 * Map<String, String> map = new HashMap<>(); map.put("originFile",
	 * originFileName); map.put("ranFileName", ranFileName);
	 * 
	 * fileList.add(map); } // 파일 업로드 try { for (int i = 0; i <
	 * multiFileList.size(); i++) {
	 * 
	 * File uploadFile = new File(root + "\\" + fileList.get(i).get("ranFileName"));
	 * multiFileList.get(i).transferTo(uploadFile);
	 * 
	 * switch(i) { case 0: productVO.setPimg1(DBroot +
	 * fileList.get(i).get("ranFileName")); break; case 1: productVO.setPimg2(DBroot
	 * + fileList.get(i).get("ranFileName")); break; case 2:
	 * productVO.setPimg3(DBroot + fileList.get(i).get("ranFileName")); break; case
	 * 3: productVO.setPimg4(DBroot + fileList.get(i).get("ranFileName")); break;
	 * case 4: productVO.setPimg5(DBroot + fileList.get(i).get("ranFileName"));
	 * break; case 5: productVO.setPimg6(DBroot +
	 * fileList.get(i).get("ranFileName")); break; case 6: productVO.setPimg7(DBroot
	 * + fileList.get(i).get("ranFileName")); break; case 7:
	 * productVO.setPimg8(DBroot + fileList.get(i).get("ranFileName")); break; case
	 * 8: productVO.setPimg9(DBroot + fileList.get(i).get("ranFileName")); break;
	 * case 9: productVO.setPimg10(DBroot + fileList.get(i).get("ranFileName"));
	 * break; default: break; }
	 * 
	 * } System.out.println("다중 파일 업로드 성공"); } catch (IllegalStateException |
	 * IOException e) { System.out.println("다중 파일 업로드 실패"); // 만약 업로드 실패하면 파일 삭제 for
	 * (int i = 0; i < multiFileList.size(); i++) { new File(root +
	 * "\\" + fileList.get(i).get("changeFile")).delete(); } e.printStackTrace(); }
	 * 
	 * 
	 * 
	 * int r = boardService.productWrite(productVO);
	 * 
	 * if(r>0) { rttr.addFlashAttribute("msg","추가에 성공하였습니다."); } return
	 * "redirect:/"; }
	 */
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 실험실
	@RequestMapping(value="/uploadform", method = RequestMethod.GET)
	public String uploadform() {
		
		return "uploadform";
	}
	
	@RequestMapping(value="/uploadform", method = RequestMethod.POST)
	public void uploadform_upload(MultipartFile[] uploadfile, Model model) {
		String uploadfolder = "C:\\upload";
		for (MultipartFile multipartFile : uploadfile) {
			
			
			logger.info("----------------");
			logger.info("Upload File Name : " + multipartFile.getOriginalFilename());
			logger.info("Upload File Size : " + multipartFile.getSize());
			
			File savefile = new File(uploadfolder, multipartFile.getOriginalFilename());
			
			try {
				multipartFile.transferTo(savefile);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
	}
	
	@RequestMapping(value="/single-file", method = RequestMethod.POST)
	public String singleFileUpload(@RequestParam String fileContent, @RequestParam MultipartFile singleFile, HttpServletRequest request) {
		
		// 1. 전송받은 파일 및 파일설명 값 가져오기
		System.out.println("singleFile:" + singleFile);
		System.out.println("fileContent:" + fileContent);
		
		// 2. 저장할 경로 가져오기
		String path = request.getSession().getServletContext().getRealPath("resources");
		System.out.println("path:" + path);
		String root = path + "\\uploadFiles";
		
		File file = new File(root);
		
		// 만약 uploadFiles 폴더가 없으면 생성해라
		if(!file.exists()) file.mkdirs();
		
		// 업로드할 폴더 설정
		String originFileName = singleFile.getOriginalFilename();
		String ext = originFileName.substring(originFileName.lastIndexOf("."));
		String ranFileName = UUID.randomUUID().toString() + "_" + originFileName + ext;
		
		File changeFile = new File(root + "\\" + ranFileName);
		
		// 파일 업로드
		try {
			singleFile.transferTo(changeFile);
			System.out.println("파일 업로드 성공");
		} catch (IllegalStateException | IOException e) {
			System.out.println("파일 업로드 실패");
			e.printStackTrace();
		}
		
		return "redirect:/";
	}
	
	
	@RequestMapping(value="/multi-file", method = RequestMethod.POST)
	public String multiFileUpload(@RequestParam("multiFile") List<MultipartFile> multiFileList, @RequestParam String fileContent, HttpServletRequest request) {
		
		// 1. 전송받은 파일 및 파일설명 값 가져오기
		System.out.println("multiFileList:" + multiFileList);
		System.out.println("fileContent:" + fileContent);
		
		// 2. 저장할 경로 가져오기
		String path = request.getSession().getServletContext().getRealPath("resources");
		System.out.println("path:" + path);
		String root = path + "\\uploadFiles";
		
		File file = new File(root);
		
		// 만약 uploadFiles 폴더가 없으면 생성해라
		if(!file.exists()) file.mkdirs();
		
		List<Map <String, String>> fileList = new ArrayList<>();
		
		// 업로드할 폴더 설정
		for (int i = 0; i<multiFileList.size(); i++) {
			String originFileName = multiFileList.get(i).getOriginalFilename();
			//확장자 뽑기
			String ext = originFileName.substring(originFileName.lastIndexOf("."));
			String ranFileName = UUID.randomUUID().toString() + "_" + originFileName + ext;
			
			Map<String, String> map = new HashMap<>();
			map.put("originFile", originFileName);
			map.put("ranFileName", ranFileName);
			
			fileList.add(map);
		}
		// 파일 업로드
		try {
			for (int i = 0; i < multiFileList.size(); i++) {
				File uploadFile = new File(root + "\\" + fileList.get(i).get("ranFileName"));
				multiFileList.get(i).transferTo(uploadFile);
				
			}
			System.out.println("다중 파일 업로드 성공");
		} catch (IllegalStateException | IOException e) {
			System.out.println("다중 파일 업로드 실패");
			// 만약 업로드 실패하면 파일 삭제
			for (int i = 0; i < multiFileList.size(); i++) {
				new File(root + "\\" + fileList.get(i).get("changeFile")).delete();
			}
			e.printStackTrace();
		}
		
		return "redirect:/";
	}
	
}







