package recActivityServices;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.position.pojo.Company;
import com.position.service.FindCompany;
import com.position.service.impl.FindCompanyServices;

/**
 * 查找公司restful接口本地测试
 * 
 * @author Fei
 *
 */
public class TextFindCompany {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	// 通过城市id查询
	@Test
	public void testByCityId() {
		FindCompany find = new FindCompanyServices();
		List<Company> list = find.FindCompanyByCityId(6);
		// System.out.println(list.size());
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				// System.out.println(list.get(i));
				// System.out.println("11111");for(int i
				// =0;i<listcom.size();i++){
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
		FindCompany find = new FindCompanyServices();
		String a = "6，7，2";
		List list = find.FindCompanyForString(a);
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
		FindCompany find = new FindCompanyServices();
		List<Company> list = find.FindCompanyByaddress("珠峰大街");
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

}
