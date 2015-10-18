package recActivityServices;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.position.dao.CompanyDao;
import com.position.dao.impl.CompanyDaoImpl;
import com.position.pojo.Company;

public class TextCompanyDaoImpl {

	List<Company> list;
	Company comp;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetall() {
		CompanyDao com = new CompanyDaoImpl();
		list = com.getAll();
		for(int i=0; i<list.size(); i++){
			System.out.println(list.get(i));
		}
	}
	
	@Test
	public void testGetbyid() {
		CompanyDao com = new CompanyDaoImpl();
		comp = com.getById(1);
		System.out.println(comp);
	}
	
	@Test
	public void testGetbyname() {
		CompanyDao com = new CompanyDaoImpl();
		comp = com.getByName("测试数据1");
		System.out.println(comp);
	}

}
