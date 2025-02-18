package kr.co.dong.ad;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

	@Inject
	ProductDAO productDAO;
	
	@Override
	public List<ProductVO> plist() {
		// TODO Auto-generated method stub
		return productDAO.plist();
	}

	@Override
	public List<ProductVO> admin_search(String pname) {
		// TODO Auto-generated method stub
		return productDAO.admin_search(pname);
	}

	@Override
	public ProductVO admin_product(int pno) {
		// TODO Auto-generated method stub
		return productDAO.admin_product(pno);
	}

	@Override
	public ProductVO admin_edit(int pno) {
		// TODO Auto-generated method stub
		return productDAO.admin_edit(pno);
	}

	@Override
	public int adminEdit(ProductVO productVO) {
		// TODO Auto-generated method stub
		return productDAO.adminEdit(productVO);
	}

	@Override
	public int adminDelete(int pno) {
		// TODO Auto-generated method stub
		return productDAO.adminDelete(pno);
	}

	@Override
	public ProductVO shopDetail(int pno) {
		// TODO Auto-generated method stub
		return productDAO.shopDetail(pno);
	}

}
