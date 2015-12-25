package recServices;

import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.position.dao.CompanyDao;
import com.position.pojo.Company;
/**
 * 公司表dao层测试(表结构没有确定，只进行了查找的测试)
 * @author Fei
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})  
@Transactional  
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public class TestCompanyDaoImpl {

	private List<Company> list;
	private Company comp;
	@Resource
	private CompanyDao companyDaoImpl;
	
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	//查询所有
	@Test
	public void testGetall() {
		list = companyDaoImpl.getAll();
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
		comp = companyDaoImpl.getById(5);
		System.out.println(comp.getCompanyName());
	}
	//通过名字查询
	@Test
	public void testGetbyname() {
		comp = companyDaoImpl.getByName("测试数据1");
		if(comp != null){
				System.out.println(comp.getId());
		}else{
			System.out.println("测试失败");
		}
	}
	
	/************************get*****************************/
	public CompanyDao getCompanyDaoImpl() {
		return companyDaoImpl;
	}

	public void setCompanyDaoImpl(CompanyDao companyDaoImpl) {
		this.companyDaoImpl = companyDaoImpl;
	}
}
