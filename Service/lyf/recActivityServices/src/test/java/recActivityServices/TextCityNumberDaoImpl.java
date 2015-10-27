package recActivityServices;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.position.dao.impl.CityNumberDaoImpl;
import com.position.dao.impl.ProNumberDaoImpl;
import com.position.pojo.City_Number;
import com.position.pojo.Provincial_Number;

public class TextCityNumberDaoImpl {

	City_Number city = new City_Number();
	List<City_Number> list ;
	CityNumberDaoImpl c = new CityNumberDaoImpl();
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	// 通过id查询
	@Test
	public void testGetbyid() {
		city = c.getById(2);
		if (city != null) {
			System.out.println(city.getCity());
		} else {
			System.out.println("测试失败");
		}
	}

	// 通过城市名
	@Test
	public void testGetbyname() {
		city = c.getByName("北京市");
		if (city != null) {
			System.out.println(city.getPro());
		} else {
			System.out.println("测试失败");
		}
	}

	// 查找所有
	@Test
	public void testGetall() {
		list = c.getAll();
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
		Provincial_Number  num = pro.getById(2);
		city.setPro(num);
		try {
			c.add(city);
		} catch (Exception e) {
			System.out.println("出错");
		}
	}

	// 删除(隐藏式)
	@Test
	public void testdeletebyidhid() {
		try {
			c.deleteByIdHid(279);
		} catch (Exception e) {
			System.out.println("出错");
		}
	}

	// 删除（直接删除）
	@Test
	public void testdeletebyid() {
		try {
			c.deleteById(279);
		} catch (Exception e) {
			System.out.println("出错");
		}
	}

	// 恢复（隐藏式删除的反向）
	@Test
	public void testregainByDelete() {
		try {
			c.regainByDelete(279);
		} catch (Exception e) {
			System.out.println("出错");
		}
	}

	// 修改
	@Test
	public void testupdate() {
		city = c.getById(279);
		city.setUsable(0);
		if (city != null) {
			c.update(city);
		} else {
			System.out.println("测试失败");
		}
	}
}
