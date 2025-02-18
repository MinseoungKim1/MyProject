package kr.co.dong.ad;

import java.util.List;

public interface ProductService {
	public List<ProductVO> plist();
	
	public List<ProductVO> admin_search(String pname);
	
	public ProductVO admin_product(int pno);
	
	public ProductVO admin_edit(int pno);
	
	public int adminEdit(ProductVO productVO);
	
	public int adminDelete(int pno);
	
	public ProductVO shopDetail(int pno);
}
