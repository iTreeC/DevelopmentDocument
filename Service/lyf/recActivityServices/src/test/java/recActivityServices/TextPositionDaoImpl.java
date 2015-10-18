package recActivityServices;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.position.dao.PositionDao;
import com.position.dao.impl.PositionDaoImpl;
import com.position.pojo.Business_Position;
import com.position.pojo.Company;

/**
 * 地点dao测试
 * @author Fei
 *
 */
public class TextPositionDaoImpl {

	private  PositionDao pos;
	private List<Business_Position> list;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	//通过城市id查询
	@Test
	public void testGetbyposId() {
		pos = new PositionDaoImpl();
		List<Business_Position> list = pos.getByCity(6);
		if(list!=null){
			for(int i=0; i<list.size(); i++){
				System.out.println(list.get(i).getAddress());
				System.out.println(list.get(i).getCompanyID());
			}
		}else{
			System.out.println("测试失败");
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
