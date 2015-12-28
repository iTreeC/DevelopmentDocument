package recServices;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.position.pojo.Company;
import com.position.service.FindCompany;
import com.position.service.impl.FindCompanyServices;

/**
 * 查找公司restful接口本地测试
 * 
 * @author Fei
 *
 */
/*@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})  
@Transactional  
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)*/
public class TestFindCompanyServices {

	private FindCompany findCompanyServices =  new FindCompanyServices();
	
	// 通过城市id查询
	@Test
	public void testByCityId() {
		List<Company> list = findCompanyServices.FindCompanyByCityId(6);
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).getId());
				System.out.println(list.get(i).getCompanyName());
				System.out.println(list.get(i).getUsable());
				System.out.println(list.get(i).getPos());
			}
		} else {
			System.out.println("查找失败");
		}
	}

	// 传入字符串，解析并返回
	@Test
	public void testGetbyidForString() {
		String a = "6,7,2";
		List list = findCompanyServices.FindCompanyForString(a);
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
		} else {
			System.out.println("查找失败");
		}
	}

	// 通过地址模糊查询
	@Test
	public void testByAddress() {
		List<Company> list = findCompanyServices.FindCompanyByaddress("珠峰大街");
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).getId());
				System.out.println(list.get(i).getCompanyName());
				System.out.println(list.get(i).getUsable());
				System.out.println(list.get(i).getPos());
			}
		} else {
			System.out.println("查找失败");
		}
	}
	
	/************************get*****************************/

	public FindCompany getFindCompanyServices() {
		return findCompanyServices;
	}

	public void setFindCompanyServices(FindCompany findCompanyServices) {
		this.findCompanyServices = findCompanyServices;
	}
}
