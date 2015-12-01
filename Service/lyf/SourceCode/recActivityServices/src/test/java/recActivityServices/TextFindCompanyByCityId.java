package recActivityServices;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.position.dao.PositionDao;
import com.position.dao.impl.PositionDaoImpl;
import com.position.pojo.Business_Position;
import com.position.pojo.Company;
import com.position.service.FindCompany;

/**
 * 地点dao测试
 * @author Fei
 *
 */
public class TextFindCompanyByCityId {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	//通过城市id查询
	@SuppressWarnings("unused")
	@Test
	public void testByCityId() {
		FindCompany find = new FindCompany();
		List<Company> list = find.FindCompanyByCityId(6);
		//System.out.println(list.size());
		if(list!=null){
			for(int i=0; i<list.size(); i++){
				//System.out.println(list.get(i));
				//System.out.println("11111");for(int i =0;i<listcom.size();i++){
				System.out.println(list.get(i).getId());
				System.out.println(list.get(i).getCompanyName());
				System.out.println(list.get(i).getUsable());
				System.out.println(list.get(i).getPos());
			
			}
		}else{
			System.out.println("查找失败");
		}
	}
	
	//传入字符串，解析并返回
	@Test
	public void testGetbyidForString() {
		FindCompany find = new FindCompany();
		String a = "6，7，2";
		List list = find.FindCompanyForString(a);
		if(list!=null){
			for(int i=0; i<list.size(); i++){
				System.out.println(list.get(i));
			}
		}else{
			System.out.println("查找失败");
		}
	}
	
	//通过id查询
	@Test
	public void testGetbyid() {
	}
	//通过名字查询
	@Test
	public void testGetbyname() {
	}

}