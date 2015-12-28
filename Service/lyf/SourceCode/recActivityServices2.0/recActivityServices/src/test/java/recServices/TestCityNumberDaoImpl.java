package recServices;

import java.util.List;

import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.position.dao.CityNumberDao;
import com.position.dao.impl.ProNumberDaoImpl;
import com.position.pojo.CityNumber;
import com.position.pojo.ProvincialNumber;
/**
 * 城市信息dao层逻辑测试
 * 
 * @author Fei
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})  
@Transactional  
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class TestCityNumberDaoImpl {

	CityNumber city = new CityNumber();
	List<CityNumber> list ;
	@Resource
	private CityNumberDao cityNumberDaoImpl;

	// 通过id查询
	@Test
	public void testGetbyid() {
		System.out.println("************************"+cityNumberDaoImpl);
		city = cityNumberDaoImpl.getById(2);
		if (city != null) {
			System.out.println(city.getCity());
		} else {
			System.out.println("测试失败");
		}
	}

	// 通过城市名
	@Test
	public void testGetbyname() {
		city = cityNumberDaoImpl.getByName("北京市");
		if (city != null) {
			System.out.println(city.getPro().getProID());
		} else {
			System.out.println("测试失败");
		}
	}

	// 查找所有
	@Test
	public void testGetall() {
		list = cityNumberDaoImpl.getAll();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).getCity());
			}
		} else {
			System.out.println("测试失败");
		}
	}

	// 增加(一个参数)
	@Test
	public void testAdd() {
		city.setCity("测试");
		city.setUsable(1);
		ProNumberDaoImpl pro = new ProNumberDaoImpl();
		ProvincialNumber  num = pro.getById(2);
		city.setPro(num);
		try {
			cityNumberDaoImpl.add(city);
		} catch (Exception e) {
			System.out.println("出错");
		}
	}

	// 删除(隐藏式)
	@Test
	public void testdeletebyidhid() {
		try {
			cityNumberDaoImpl.deleteByIdHid(279);
		} catch (Exception e) {
			System.out.println("出错");
		}
	}

	// 删除（直接删除）
	@Test
	public void testdeletebyid() {
		try {
			cityNumberDaoImpl.deleteById(279);
		} catch (Exception e) {
			System.out.println("出错");
		}
	}

	// 恢复（隐藏式删除的反向）
	@Test
	public void testregainByDelete() {
		try {
			cityNumberDaoImpl.regainByDelete(279);
		} catch (Exception e) {
			System.out.println("出错");
		}
	}

	// 修改
	@Test
	public void testupdate() {
		city = cityNumberDaoImpl.getById(279);
		city.setUsable(0);
		if (city != null) {
			cityNumberDaoImpl.update(city);
		} else {
			System.out.println("测试失败");
		}
	}

	/************************get*****************************/

	public CityNumberDao getCityNumberDaoImpl() {
		return cityNumberDaoImpl;
	}

	public void setCityNumberDaoImpl(CityNumberDao cityNumberDaoImpl) {
		this.cityNumberDaoImpl = cityNumberDaoImpl;
	}
}
