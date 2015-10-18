package recActivityServices;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.position.dao.CompanyDao;
import com.position.dao.impl.CompanyDaoImpl;
import com.position.pojo.Company;
/**
 * 公司dao测试
 * @author Fei
 *
 */
public class TextCompanyDaoImpl {

	private List<Company> list;
	private Company comp;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	//查询所有
	@Test
	public void testGetall() {
		CompanyDao com = new CompanyDaoImpl();
		list = com.getAll();
		if(list!=null){
			for(int i=0; i<list.size(); i++){
				System.out.println(list.get(i).getId());
			}
		}else{
			System.out.println("测试失败");
		}
	}
	//通过id查询
	@Test
	public void testGetbyid() {
		CompanyDao com = new CompanyDaoImpl();
		comp = com.getById(5);
		System.out.println(comp.getCompanyName());
	}
	//通过名字查询
	@Test
	public void testGetbyname() {
		CompanyDao com = new CompanyDaoImpl();
		comp = com.getByName("测试数据1");
		if(comp != null){
				System.out.println(comp.getId());
		}else{
			System.out.println("测试失败");
		}
	}

}
