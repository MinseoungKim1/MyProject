package kr.co.dong.project.admincontroller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.dong.ad.NoticeVO;
import kr.co.dong.ad.OrderItemService;
import kr.co.dong.ad.OrderItemVO;
import kr.co.dong.ad.ProductService;
import kr.co.dong.ad.ProductVO;
import kr.co.dong.project.usercontroller.UserController;
import kr.co.dong.user.BoardService;

@Controller
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Inject
	ProductService productService;
	@Inject
	OrderItemService oiService;
	@Inject
	BoardService boardService;
	@Autowired
	private ServletContext servletContext;
	
	@RequestMapping(value = "admin_home", method = RequestMethod.GET)
	public String admin_home(OrderItemVO oiVO, Model model, ModelMap modelMap) {
		
		List<OrderItemVO> updatedlist = oiService.orders();
		int total = 0;
		for (OrderItemVO stats : updatedlist) {
		    if (stats != null) {
		        total += stats.getPprice();
		    } else {
		        System.out.println("Null OrderItemVO encountered");
		    }
		}
		System.out.println("total: "+ total);
		
		
		List<OrderItemVO> mtotal = oiService.Mrevenue();
		System.out.println(mtotal);
		int monthlytotal = 0;
		for (OrderItemVO mt : mtotal) {
		    if (mt != null) {
		    	System.out.println(mt.getPprice());
		        monthlytotal += mt.getPprice();
		    } else {
		        System.out.println("Null OrderItemVO encountered");
		    }
		}
		System.out.println("monthlytotal: " + monthlytotal);
		
		
		
		model.addAttribute("Arevenue", total);
		model.addAttribute("Mrevenue", monthlytotal);
		
		//spline chart
		List<OrderItemVO> MonthlyRevenue = oiService.getMonthlyRevenue();
		
		List<Map<String, Object>> dataPointsList = new ArrayList<>();
		for(OrderItemVO getMonthlyRevenue: MonthlyRevenue) {
		System.out.println(getMonthlyRevenue);
		Map<String, Object> point = new HashMap<>();
		point.put("x", getMonthlyRevenue.getMonth());
		point.put("y", getMonthlyRevenue.getRevenue());
		dataPointsList.add(point);
		System.out.println(dataPointsList);
		}
		model.addAttribute("dataPointsList",dataPointsList);
		System.out.println("AAAAAAAAAAAHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
		System.out.println(model);
		
		
		//pie chart
			 List<Map<Object, Object>> getCategory = oiService.getCategory();
			    System.out.println("Data: " + getCategory);
			
			    modelMap.addAttribute("Points", getCategory);
			    System.out.println("Points in model: " + getCategory);
		
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		
		int count = oiService.countDeliv();
		int all = oiService.countall();
		System.out.println(count);
		System.out.println(all);
		model.addAttribute("count", count);
		float per = (float) (all-count)/all*100;
		per = Math.round(per * 10) / 10f;
		System.out.println(per);
		model.addAttribute("per", per);
		
		int Dcount = oiService.countD();
		float perD = (float) Dcount/all*100;
		perD = Math.round(perD * 10) / 10f;
		System.out.println(perD);
		int Edcount = oiService.countEd();
		float perEd = (float) Edcount/all*100;
		perEd = Math.round(perEd * 10) / 10f;
		System.out.println(perEd);
		
		model.addAttribute("perD", perD);
		model.addAttribute("perEd", perEd);
		
		
		
		return "admin_home";
	}

	
	
	@RequestMapping(value="admin_write", method=RequestMethod.GET)
	public String productWrite() {
		return "productWrite";
	}
	
	@RequestMapping(value="admin_product", method=RequestMethod.GET)
	public String productdetail(@RequestParam("pno")int pno, Model model) {
		ProductVO product = productService.admin_product(pno);
		model.addAttribute("product", product);
		return "admin_product";
	}
	
	//direct to editing form
	/*
	 * @RequestMapping(value="adminEdit", method=RequestMethod.GET) public String
	 * productEdit(@RequestParam("pno")int pno, Model model) {
	 * System.out.println("??!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"); ProductVO edit =
	 * productService.admin_product(pno); model.addAttribute("pno", pno);
	 * model.addAttribute("edit", edit); return "admin_edit"; }
	 */
	/*
	 * //post -- updates the product info
	 * 
	 * @RequestMapping(value="adminEdit", method=RequestMethod.POST) public String
	 * adminEdit(ProductVO productVO, HttpServletRequest request) throws Exception{
	 * System.out.println("TEXTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
	 * request.setCharacterEncoding("UTF-8"); System.out.println(productVO); int r =
	 * productService.adminEdit(productVO);
	 * 
	 * if (r>0) { System.out.println("updated"); } return "redirect:adminListtest2";
	 * }
	 */
	
	@RequestMapping(value="adminDelete", method=RequestMethod.GET)
	public String adminDelete(@RequestParam("pno")int pno) throws Exception{
		System.out.println(pno);
		
		int r = productService.adminDelete(pno);
		
		if (r>0) {
			System.out.println("deleted");
		}
		return "redirect:admin_list?pno="+pno;
	}
	
	
	
	
	 @RequestMapping(value="admin_orderlist", method=RequestMethod.GET)
		public ModelAndView admin_orderlist(
				@RequestParam(value="option", required=false) String option,
				OrderItemVO oiVO, Model model) throws Exception {
		    ModelAndView mav = new ModelAndView();
		   
		    List<OrderItemVO> admin_orderlist = oiService.orderDistinct();
		   
		    List<OrderItemVO> pending = new ArrayList<>();
		    List<OrderItemVO> delivery = new ArrayList<>();
		   
		    for (OrderItemVO ordersMade : admin_orderlist) {
		        if ("D001".equalsIgnoreCase(ordersMade.getOstatus())) {
		            pending.add(ordersMade);
		        } else if ("D003".equalsIgnoreCase(ordersMade.getOstatus()) || "Delivered".equalsIgnoreCase(ordersMade.getOstatus())) {
		            delivery.add(ordersMade);
		        }
		    }
		   
		    model.addAttribute("pending", pending);
		    model.addAttribute("delivery", delivery);
		   
		    List<OrderItemVO> filteredOrders = new ArrayList<>();
		   
		    if (option == null || option.isEmpty()) {
		        filteredOrders = admin_orderlist;
		    } else {
		        for (OrderItemVO order : admin_orderlist) {
		            if (option.equalsIgnoreCase(order.getOstatus())) {
		                filteredOrders.add(order);
		            }
		        }
		    }
		    mav.addObject("admin_orderlist", filteredOrders);
		    mav.setViewName("admin_orderlist");
		   
		    return mav;
		}
		
		@RequestMapping(value="admin_orderlist", method=RequestMethod.POST)
		public String admin_orderlist (@RequestParam(value="chkBox", required=false) List<String> chkBox, OrderItemVO oiVO) {
			List<OrderItemVO> admin_orderlist = oiService.orderDistinct();
			System.out.println("UHHHHHHHHHH");
		    if (chkBox != null) {
		    	for (String oidStr : chkBox) {
		    		int oid = Integer.parseInt(oidStr);
		    		List<OrderItemVO> order = oiService.order(oid);
		    		for (OrderItemVO um: order) {
		    			um.setOstatus("On Delivery");
			    		int result = oiService.updateOrder(um);
			    		if (result>=1) {
			    			System.out.println("HELLYEA");
			    		}else {
			    			System.out.println("HELLNAW");
			    		}
		    		}
		    	}
		    } else {
	   		System.out.println("NOpe");
		    }return "redirect:admin_orderlist";
		}
		@RequestMapping(value="admin_orderDetail", method=RequestMethod.GET)
		public ModelAndView admin_orderDetail(@RequestParam("oid")int oid, OrderItemVO oiVO, Model model) {
			System.out.println("change?");
			ModelAndView mv = new ModelAndView();
			List<OrderItemVO> admin_order = oiService.order(oid);
			
			mv.addObject("admin_order", admin_order);
			mv.setViewName("admin_orderDetail");
			
			return mv;
		}
		
		@RequestMapping(value="admin_orderDetailSave", method=RequestMethod.POST)
		public String admin_orderDetailSave(@RequestParam("selectedOption")String selectedOption, OrderItemVO oiVO, Model model) {
			
			System.out.println("Is it in?");
			
			oiVO.setOstatus(selectedOption);
			
			int r = oiService.updateOrder(oiVO);
			
			if (r>=1) {
				System.out.println("updated");
			}else {
				System.out.println("TRY AGAIN");
			}
			
			return "redirect:admin_orderlist";
		}


		//admin list Ver paging
	@RequestMapping(value="generateReport", method=RequestMethod.GET)
		 public String generateReport(OrderItemVO oiVO, Model model, ModelMap modelMap) {
			 LocalDate currentDate = LocalDate.now();
		     System.out.println("Current Date: " + currentDate);
		     model.addAttribute("currentDate", currentDate);
		    
		     List<OrderItemVO> updatedlist = oiService.orders();
				int total = 0;
				for (OrderItemVO stats : updatedlist) {
				    if (stats != null) {
				        total += stats.getPprice();
				    } else {
				        System.out.println("Null OrderItemVO encountered");
				    }
				}
				System.out.println("total: "+ total);
				
				
				List<OrderItemVO> mtotal = oiService.Mrevenue();
				System.out.println(mtotal);
				int monthlytotal = 0;
				for (OrderItemVO mt : mtotal) {
				    if (mt != null) {
				    	System.out.println(mt.getPprice());
				        monthlytotal += mt.getPprice();
				    } else {
				        System.out.println("Null OrderItemVO encountered");
				    }
				}
				System.out.println("monthlytotal: " + monthlytotal);
				
				
				
				model.addAttribute("Arevenue", total);
				model.addAttribute("Mrevenue", monthlytotal);
				
				//spline chart
				List<OrderItemVO> MonthlyRevenue = oiService.getMonthlyRevenue();
				
				List<Map<String, Object>> dataPointsList = new ArrayList<>();
				for(OrderItemVO getMonthlyRevenue: MonthlyRevenue) {
				System.out.println(getMonthlyRevenue);
				Map<String, Object> point = new HashMap<>();
				point.put("x", getMonthlyRevenue.getMonth());
				point.put("y", getMonthlyRevenue.getRevenue());
				dataPointsList.add(point);
				System.out.println(dataPointsList);
				}
				model.addAttribute("dataPointsList",dataPointsList);
				System.out.println("AAAAAAAAAAAHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
				System.out.println(model);
				
				
				//pie chart
					 List<Map<Object, Object>> getCategory = oiService.getCategory();
					    System.out.println("Data: " + getCategory);
					
					    modelMap.addAttribute("Points", getCategory);
					    System.out.println("Points in model: " + getCategory);
				
				System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
				
				int count = oiService.countDeliv();
				int all = oiService.countall();
				System.out.println(count);
				System.out.println(all);
				model.addAttribute("count", count);
				float per = (float) (all-count)/all*100;
				per = Math.round(per * 10) / 10f;
				System.out.println(per);
				model.addAttribute("per", per);
				
				int Dcount = oiService.countD();
				float perD = (float) Dcount/all*100;
				perD = Math.round(perD * 10) / 10f;
				System.out.println(perD);
				int Edcount = oiService.countEd();
				float perEd = (float) Edcount/all*100;
				perEd = Math.round(perEd * 10) / 10f;
				System.out.println(perEd);
				
				model.addAttribute("perD", perD);
				model.addAttribute("perEd", perEd);
		    
		    
		    
			 return "generateReport";
		 }
			
	//-------------------------------------------------------------------------------------------------------------
	// min
	
	
	   public static int ipageSIZE = 10; // 한 페이지에 담을 게시글의 개수
	   public static int itotalRecord = 0;
	   public static int itotalPage = 1;
	   
	   public static int istartPage = 1;
	   public static int iendPage = 10;
	   public static int ipageListSIZE = 10;
	   
	   @RequestMapping(value = "adminListtest2", method = RequestMethod.GET)
	   public ModelAndView adminListtest2(@RequestParam(value = "pageNUM", defaultValue = "1") int pageNUM,
	         @RequestParam(value = "pageListNUM", defaultValue = "1") int pageListNUM) {

	      itotalRecord = boardService.product_totalRecord(); // del=0인 게시글의 총 개수
	      itotalPage = itotalRecord / ipageSIZE;
	      if (itotalRecord % ipageSIZE != 0) {
	         itotalPage++;
	      } // 결국 totalRecord ÷ pageSIZE 를 올림처리한 것과 동일함

	      int start = (pageNUM - 1) * ipageSIZE; //start 페이지는 인덱스 값 0 
	      	
	      istartPage = (pageListNUM - 1) * ipageListSIZE + 1;
	      iendPage = istartPage + ipageListSIZE - 1;
	      if (iendPage > itotalPage) {
	         iendPage = itotalPage;
	      }

	      // int end = start + pageSIZE - 1;
	      // 해당 pageNUM 을 가진 페이지에는 bno가 start 부터 end 까지인 게시글들이 보여짐

	      ModelAndView mav = new ModelAndView();
	      mav.addObject("list", boardService.list(start, ipageSIZE));
	      mav.addObject("totalPage", itotalPage);
	      mav.addObject("startPage", istartPage);
	      mav.addObject("pageListNUM", pageListNUM);
	      mav.addObject("endPage", iendPage);
	      mav.setViewName("adminListtest2");
	      return mav;

	   }
	   
	   @RequestMapping(value = "category", method = RequestMethod.POST)
	   public ModelAndView getcategory(@RequestParam(value = "pageNUM", defaultValue = "1") int pageNUM,
	         @RequestParam(value = "pageListNUM", defaultValue = "1") int pageListNUM,
	         @RequestParam(required = false) String cate1,
				@RequestParam(required = false) String cate2, @RequestParam(required = false) String cate3){
		   
		// Default 값 처리
			cate1 = (cate1 == null || cate1.isEmpty()) ? "default" : cate1;
			cate2 = (cate2 == null || cate2.isEmpty()) ? "default" : cate2;
			cate3 = (cate3 == null || cate3.isEmpty()) ? "default" : cate3;


		   

	      itotalRecord = boardService.product_totalRecord(); // del=0인 게시글의 총 개수
	      itotalPage = itotalRecord / ipageSIZE;
	      if (itotalRecord % ipageSIZE != 0) {
	         itotalPage++;
	      } // 결국 totalRecord ÷ pageSIZE 를 올림처리한 것과 동일함

	      int start = (pageNUM - 1) * ipageSIZE; //start 페이지는 인덱스 값 0 
	      	
	      istartPage = (pageListNUM - 1) * ipageListSIZE + 1;
	      iendPage = istartPage + ipageListSIZE - 1;
	      if (iendPage > itotalPage) {
	         iendPage = itotalPage;
	      }

	      // int end = start + pageSIZE - 1;
	      // 해당 pageNUM 을 가진 페이지에는 bno가 start 부터 end 까지인 게시글들이 보여짐

	      ModelAndView mav = new ModelAndView();
	      mav.addObject("list", boardService.cate(cate1,cate2, cate3, start, ipageSIZE));
	      mav.addObject("totalPage", itotalPage);
	      mav.addObject("startPage", istartPage);
	      mav.addObject("pageListNUM", pageListNUM);
	      mav.addObject("endPage", iendPage);
	      mav.setViewName("adminListtest2");
	      return mav;

	   }
	   
	   //delete product
	   @RequestMapping(value = "/product/deleteProducts", method = RequestMethod.POST)
	    public ResponseEntity<String> deleteProducts(@RequestBody List<Long> productIds) {
	        if (productIds == null || productIds.isEmpty()) {
	            return ResponseEntity.badRequest().body("error");
	        }

	        boardService.deleteProducts(productIds);
	        return ResponseEntity.ok("success");
	    }
	   
	    //제품 상태 변경 
		@RequestMapping(value = "/product/updateToG003", method = RequestMethod.POST)
	    public ResponseEntity<String> updateToG003(@RequestBody List<String> productIds) {
	        try {
	            boardService.updatePdelToG003(productIds);
	            return ResponseEntity.ok("Success");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
	        }
	    }
		
		//제품 상태 변경 
		@RequestMapping(value = "/product/updatePdel_1", method = RequestMethod.POST)
		public String updatePdel_1(@RequestBody List<ProductVO> products) {
			try {
				boardService.updatePdel_1(products);

				System.out.println("success");
				return "redirect:/";
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("error");
				return "redirect:/";
			}
		}
		
		//제품 상태 변경 
		@RequestMapping(value = "/product/updatePdel", method = RequestMethod.POST)
		public String updatePdel(@RequestBody List<ProductVO> products) {
			try {
				boardService.updatePdel(products);

				System.out.println("success");
				return "redirect:/";
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("error");
				return "redirect:/";
			}
		}
		
		//상품 업데이트
		 @RequestMapping(value = "admin_Editupload", method = RequestMethod.GET)
			public String admin_Editupload(@RequestParam("pno") int pno, Model model) {
			   ProductVO productVO = boardService.getProduct_detail(pno);
			   model.addAttribute("product", productVO);
			   
				return "admin_Editupload";
			}
		 
		 //공지사항
		 @RequestMapping(value = "eventpage", method = RequestMethod.GET)
		   public ModelAndView eventpage() {
				ModelAndView mav = new ModelAndView();

				List<NoticeVO> noticelist = boardService.noticelist();
				mav.addObject("noticelist", noticelist);
				mav.setViewName("eventpage");
				return mav;
			}
		  @RequestMapping(value="eventpageForm",method= RequestMethod.GET)
			public String eventpageForm() {
				
				return "eventpageForm";
			}
		 	//상품 등록
		  	  @RequestMapping(value="/productWrite",method= RequestMethod.POST) 
		  	  public String productWrite(ProductVO productVO, @RequestParam("multiFile")
			  List<MultipartFile> multiFileList, HttpServletRequest
			  request,RedirectAttributes rttr) throws Exception {
			  request.setCharacterEncoding("utf-8");
			  
			  // 1. 전송받은 파일 및 파일설명 값 가져오기 \
			  System.out.println("multiFileList:" + multiFileList); System.out.println(multiFileList.size());
			  /*
			  // 2. 저장할 경로 가져오기 //V0.1 // 
			  String path = "C:\\workspace\\10.testspring_project\\src\\main\\webapp\\resources\\static\\img";
			  
			  //V0.2 (배포한경에서 사용해야 할 코드) // 
			  ServletContext context =  request.getSession().getServletContext(); // 
			  String path = context.getRealPath("/resources/static/img");
			  */
			  
			  //V0.3 (개발환경에서 사용해야 할 코드) 
			  String realpath = servletContext.getRealPath("/resources/static/img/");
			  System.out.println("realpath:" + realpath); String modifiedpath =  realpath.replaceAll("\\\\.metadata.*?wtpwebapps", ""); 
			  String path = modifiedpath.replace("resources", "src\\main\\webapp\\resources");
			  System.out.println("path:" + path); 
			  String root = path + "\\uploadFiles";
			
			  // DB에 저장할 경로 
			  String DBroot = "img/uploadFiles/";
			  
			  
			  File file = new File(root);
			  
			  // 만약 uploadFiles 폴더가 없으면 생성해라 
			  if(!file.exists()) file.mkdirs();
			  
			  List<Map <String, String>> fileList = new ArrayList<>();
			  
			  // 업로드할 폴더 설정 
			  for (int i = 0; i<multiFileList.size(); i++) { 
				  String originFileName = multiFileList.get(i).getOriginalFilename(); //확장자 뽑기 
				  String ext = originFileName.substring(originFileName.lastIndexOf(".")); 
				  String ranFileName = UUID.randomUUID().toString() + "_" + originFileName;
			  
			  Map<String, String> map = new HashMap<>(); 
			  map.put("originFile", originFileName);
			  map.put("ranFileName", ranFileName);
			  
			  fileList.add(map); 
			  } // 파일 업로드 
			  try { 
			  for (int i = 0; i < multiFileList.size(); i++) {
			  
			  File uploadFile = new File(root + "\\" + fileList.get(i).get("ranFileName"));
			  multiFileList.get(i).transferTo(uploadFile);
			  
			  switch(i) 
			  { 
			  case 0: productVO.setPimg1(DBroot + fileList.get(i).get("ranFileName")); 
			  		  break; 
			  case 1: productVO.setPimg2(DBroot + fileList.get(i).get("ranFileName")); 
			  		  break; 
			  case 2: productVO.setPimg3(DBroot + fileList.get(i).get("ranFileName")); 
			  		  break; 
			  case 3: productVO.setPimg4(DBroot + fileList.get(i).get("ranFileName")); 
			  		  break;
			  case 4: productVO.setPimg5(DBroot + fileList.get(i).get("ranFileName"));
			  		  break; 
			  case 5: productVO.setPimg6(DBroot + fileList.get(i).get("ranFileName")); 
			  	 	  break; 
			  case 6: productVO.setPimg7(DBroot + fileList.get(i).get("ranFileName")); 
			  		  break; 
			  case 7: productVO.setPimg8(DBroot + fileList.get(i).get("ranFileName")); 
			  		  break; 
			  case 8: productVO.setPimg9(DBroot + fileList.get(i).get("ranFileName")); 
			  		  break;
			  case 9: productVO.setPimg10(DBroot + fileList.get(i).get("ranFileName"));
			  		  break; 
			 default: 
				 break; 
				 }
			  
			  } System.out.println("다중 파일 업로드 성공"); 
			  } catch (IllegalStateException | IOException e) 
			  {
				  System.out.println("다중 파일 업로드 실패");
				  // 만약 업로드 실패하면 파일 삭제 
			for (int i = 0; i < multiFileList.size(); i++) { 
				new File(root + "\\" + fileList.get(i).get("changeFile")).delete(); 
			} 
			  e.printStackTrace(); 
			  }
	
			  int r = boardService.productWrite(productVO);
			  
			  if(r>0) { rttr.addFlashAttribute("msg","추가에 성공하였습니다."); 
			  } 
			  return "redirect:/admin_home"; 
			  }
			 
		   
		   // 공지사항 업로드 처리
		    @RequestMapping(value="eventpageForm",method= RequestMethod.POST)
			public String eventpageForm(NoticeVO noticeVO, @RequestParam("multiFile") List<MultipartFile> multiFileList,
					HttpServletRequest request,RedirectAttributes rttr) throws Exception {
				request.setCharacterEncoding("utf-8");
				
				// 1. 전송받은 파일 및 파일설명 값 가져오기
				System.out.println("multiFileList:" + multiFileList);
				System.out.println(multiFileList.size());
				
				// 2. 저장할 경로 가져오기
				//V0.1		
				//		String path = "C:\\workspace\\10.testspring_projects\\src\\main\\webapp\\resources\\static\\img";

				//V0.2 (배포한경에서 사용해야 할 코드)
//				ServletContext context = request.getSession().getServletContext();
//				String path = context.getRealPath("/resources/static/img");
				
				//V0.3 (개발환경에서 사용해야 할 코드)
				String realpath = servletContext.getRealPath("/resources/static/img/");
				System.out.println("realpath:" + realpath);
				String modifiedpath = realpath.replaceAll("\\\\.metadata.*?wtpwebapps", "");
				String path = modifiedpath.replace("resources", "src\\main\\webapp\\resources");
				System.out.println("path:" + path);
				String root = path + "\\uploadFiles";
				
				// DB에 저장할 경로
				String DBroot = "img/uploadFiles/";
			
				
				File file = new File(root);
				
				// 만약 uploadFiles 폴더가 없으면 생성해라
				if(!file.exists()) file.mkdirs();
				
				List<Map <String, String>> fileList = new ArrayList<>();
				
				// 업로드할 폴더 설정
				for (int i = 0; i<multiFileList.size(); i++) {
					String originFileName = multiFileList.get(i).getOriginalFilename();
					//확장자 뽑기
					String ext = originFileName.substring(originFileName.lastIndexOf("."));
					String ranFileName = UUID.randomUUID().toString() + "_" + originFileName;
					
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
						
						switch(i) {
						case 0:
							noticeVO.setNimg(DBroot + fileList.get(i).get("ranFileName"));
							break;
						
						
						default:
							break;
						}
						
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
				int r = boardService.eventpageForm(noticeVO);
				
				if(r>0) {
					rttr.addFlashAttribute("msg","추가에 성공하였습니다.");
				}
				return "redirect:/admin_home";
				//redirect:/
			}	
		    //공지사항 디테일
		    @RequestMapping(value = "noticeDetail", method = RequestMethod.GET)
		    public String noticeDetail(@RequestParam("eno") int eno, Model model) {
		        NoticeVO noticeVO = boardService.getNoticeById(eno); // ID로 공지사항 가져오기
		        model.addAttribute("notice", noticeVO); // JSP에 전달
		        return "noticeDetail"; // noticeDetail.jsp로 이동
		    }
		   /*
			  @RequestMapping(value = "eventpageForm", method = RequestMethod.POST)
			  public String uploadNotice(
					  @ModelAttribute NoticeVO noticeVO,
					  @RequestParam("nimg") List<MultipartFile> nimg, 
					  Model model) {
			  
				  if (!nimg.isEmpty()) { 
					  String originalFilename = ((MultipartFile)nimg).getOriginalFilename(); 
					  String uuid = UUID.randomUUID().toString().replace("-", "");
					  String savedFilename = uuid + "_" + originalFilename; 
					  String uploadPath = "C:/uploads/"; 
					  // 파일이 저장될 경로 설정
					  
					  File dest = new File(uploadPath + savedFilename);
					  try { 
						  ((MultipartFile)nimg).transferTo(dest); 
						  noticeVO.setNimg(savedFilename); 
					  } catch (IOException e) { 
						  e.printStackTrace(); 
						  model.addAttribute("message", "파일 업로드 실패"); 
					  return "eventpageForm"; 
					  } 
				  }
		
			  }
			 */
		    
		    
		   //상품 업데이트
		    @RequestMapping(value="product_Update", method = RequestMethod.POST)
		    public String product_Update(ProductVO productVO, @RequestParam("multiFile") List<MultipartFile> multiFileList,
		          HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		       request.setCharacterEncoding("utf-8");

		       // 파일 저장 경로 설정
		       String realpath = servletContext.getRealPath("/resources/static/img/");
		       String modifiedpath = realpath.replaceAll("\\\\.metadata.*?wtpwebapps", "");
		       String path = modifiedpath.replace("resources", "src\\main\\webapp\\resources");
		       String root = path + "\\uploadFiles";

		       // DB에 저장할 경로
		       String DBroot = "img/uploadFiles/";

		       File file = new File(root);
		       if (!file.exists()) file.mkdirs();

		       List<Map<String, String>> fileList = new ArrayList<>();

		       // 파일 업로드를 위한 처리
		       for (int i = 0; i < multiFileList.size(); i++) {
		          String originFileName = multiFileList.get(i).getOriginalFilename();
		          if (originFileName != null && !originFileName.isEmpty()) {
		             // 파일명과 확장자 처리
		             String ext = originFileName.substring(originFileName.lastIndexOf("."));
		             String ranFileName = UUID.randomUUID().toString() + "_" + originFileName;

		             Map<String, String> map = new HashMap<>();
		             map.put("originFile", originFileName);
		             map.put("ranFileName", ranFileName);
		             fileList.add(map);
		          } else {
		             // 기존 파일을 유지하는 로직
		             Map<String, String> map = new HashMap<>();
		             map.put("originFile", null);
		             map.put("ranFileName", null);
		             fileList.add(map);
		          }
		       }

		       try {
		          for (int i = 0; i < multiFileList.size(); i++) {
		             String ranFileName = fileList.get(i).get("ranFileName");

		             if (ranFileName != null) {
		                // 새로운 파일 업로드
		                File uploadFile = new File(root + "\\" + ranFileName);
		                multiFileList.get(i).transferTo(uploadFile);

		                switch (i) {
		                   case 0:
		                      productVO.setPimg1(DBroot + ranFileName);
		                      break;
		                   case 1:
		                      productVO.setPimg2(DBroot + ranFileName);
		                      break;
		                   case 2:
		                      productVO.setPimg3(DBroot + ranFileName);
		                      break;
		                   case 3:
		                      productVO.setPimg4(DBroot + ranFileName);
		                      break;
		                   case 4:
		                      productVO.setPimg5(DBroot + ranFileName);
		                      break;
		                   case 5:
		                      productVO.setPimg6(DBroot + ranFileName);
		                      break;
		                   case 6:
		                      productVO.setPimg7(DBroot + ranFileName);
		                      break;
		                   case 7:
		                      productVO.setPimg8(DBroot + ranFileName);
		                      break;
		                   case 8:
		                      productVO.setPimg9(DBroot + ranFileName);
		                      break;
		                   case 9:
		                      productVO.setPimg10(DBroot + ranFileName);
		                      break;
		                }
		             } else {
		                // 파일이 업로드되지 않은 경우 기존 값을 유지
		                switch (i) {
		                   case 0:
		                      productVO.setPimg1(productVO.getPimg1());
		                      break;
		                   case 1:
		                      productVO.setPimg2(productVO.getPimg2());
		                      break;
		                   case 2:
		                      productVO.setPimg3(productVO.getPimg3());
		                      break;
		                   case 3:
		                      productVO.setPimg4(productVO.getPimg4());
		                      break;
		                   case 4:
		                      productVO.setPimg5(productVO.getPimg5());
		                      break;
		                   case 5:
		                      productVO.setPimg6(productVO.getPimg6());
		                      break;
		                   case 6:
		                      productVO.setPimg7(productVO.getPimg7());
		                      break;
		                   case 7:
		                      productVO.setPimg8(productVO.getPimg8());
		                      break;
		                   case 8:
		                      productVO.setPimg9(productVO.getPimg9());
		                      break;
		                   case 9:
		                      productVO.setPimg10(productVO.getPimg10());
		                      break;
		                }
		             }
		          }
		          System.out.println("다중 파일 업로드 성공");
		       } catch (IllegalStateException | IOException e) {
		          System.out.println("다중 파일 업로드 실패");
		          e.printStackTrace();
		       }

		       // DB 업데이트
		       int r = boardService.product_Update(productVO);

		       if (r > 0) {
		          rttr.addFlashAttribute("msg", "수정에 성공하였습니다.");
		       }
		       return "redirect:/admin_home";
		    }
		   /*
		 // 공지사항 업로드 처리
		    @RequestMapping(value="Notice_Update",method= RequestMethod.POST)
			public String Notice_Update(NoticeVO noticeVO, @RequestParam("multiFile") List<MultipartFile> multiFileList,
					HttpServletRequest request,RedirectAttributes rttr) throws Exception {
				request.setCharacterEncoding("utf-8");
				
				// 1. 전송받은 파일 및 파일설명 값 가져오기
				System.out.println("multiFileList:" + multiFileList);
				System.out.println(multiFileList.size());
				
				//V0.3 (개발환경에서 사용해야 할 코드)
				String realpath = servletContext.getRealPath("/resources/static/img/");
				System.out.println("realpath:" + realpath);
				String modifiedpath = realpath.replaceAll("\\\\.metadata.*?wtpwebapps", "");
				String path = modifiedpath.replace("resources", "src\\main\\webapp\\resources");
				System.out.println("path:" + path);
				String root = path + "\\uploadFiles";
				
				// DB에 저장할 경로
				String DBroot = "img/uploadFiles/";
			
				
				File file = new File(root);
				
				// 만약 uploadFiles 폴더가 없으면 생성해라
				if(!file.exists()) file.mkdirs();
				
				List<Map <String, String>> fileList = new ArrayList<>();
				
				// 업로드할 폴더 설정
				for (int i = 0; i<multiFileList.size(); i++) {
					String originFileName = multiFileList.get(i).getOriginalFilename();
					//확장자 뽑기
					String ext = originFileName.substring(originFileName.lastIndexOf("."));
					String ranFileName = UUID.randomUUID().toString() + "_" + originFileName;
					
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
						
						switch(i) {
						case 0:
							noticeVO.setNimg(DBroot + fileList.get(i).get("ranFileName"));
							break;
						default:
							break;
						}
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
				int r = boardService.Notice_Update(noticeVO);
				
				if(r>0) {
					rttr.addFlashAttribute("msg","추가에 성공하였습니다.");
				}
				
				return "redirect:/admin_home";
				//redirect:/
			}	
		   */
		   
		    @RequestMapping(value="Notice_Update",method= RequestMethod.POST)
		    public String Notice_Update(NoticeVO noticeVO) throws Exception {
				
				
				return "redirect:/";
		    }
		   
		   
		   
		   
		   
		   
		   
}
