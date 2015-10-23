package recActivityServices;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.position.dao.PositionDao;
import com.position.dao.impl.CityNumberDaoImpl;
import com.position.dao.impl.CompanyDaoImpl;
import com.position.dao.impl.PositionDaoImpl;
import com.position.dao.impl.ProNumberDaoImpl;
import com.position.pojo.Business_Position;
import com.position.pojo.City_Number;

/**
 * 地点dao测试
 * @author Fei
 *
 */
public class TextPositionDaoImpl {

	private  PositionDao pos;
	private List<Business_Position> list;
	private Business_Position p;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	//通过城市id查询
	@Test
	public void testGetbycity() {
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
	//通过位置id查询
	@Test
	public void testGetbyid() {
		pos = new PositionDaoImpl();
		p = pos.getById(1);
		if(p!=null){
				System.out.println(p.getAddress());
		}else{
			System.out.println("测试失败");
		}
	}
	
	//通过公司id查询
	@Test
	public void testGetbyname() {
		pos = new PositionDaoImpl();
		p = pos.getByPosId(1);
		if(p!=null){
				System.out.println(p.getAddress());
		}else{
			System.out.println("测试失败");
		}
	}
	//查找所有
	@Test
	public void testGetall(){
		pos = new PositionDaoImpl();
		List<Business_Position> list = pos.getAll();
		if(list!=null){
			for(int i=0; i<list.size(); i++){
				System.out.println(list.get(i).getAddress());
			}
		}else{
			System.out.println("测试失败");
		}
	}
	//根据详尽地址查找（模糊查询）
	@Test
	public void testGetbyaddress(){
		pos = new PositionDaoImpl();
		List<Business_Position> list = pos.getByAddress("珠峰大街");
		if(list!=null){
			for(int i=0; i<list.size(); i++){
				System.out.println(list.get(i).getAddress());
			}
		}else{
			System.out.println("测试失败");
		}
	}
	
	//增加
	@Test
	public void testAdd(){
		Business_Position bus = new Business_Position();
		//bus.setId(9);
		bus.setAddress("test");
		CityNumberDaoImpl city = new  CityNumberDaoImpl();
		City_Number c  = city.getById(2);
		bus.setCity(c);
		CompanyDaoImpl com = new CompanyDaoImpl();
		bus.setCompany(com.getById(1));
		bus.setCompanyID(1);
		bus.setCounty(0);
		ProNumberDaoImpl pro = new ProNumberDaoImpl();
		bus.setProvince(pro.getById(1));
		bus.setUsable(1);
		pos = new PositionDaoImpl();
		try{
			pos.add(bus);
		}catch(Exception e){
			System.out.println("出错");
		}
	}
	//删除(隐藏式)
	@Test
	public void testdeletebyidhid(){
		pos = new PositionDaoImpl();
		try{
			pos.deleteByIdHid(9);
		}catch(Exception e){
			System.out.println("出错");
		}
	}
	
	// 删除（直接删除）
	@Test
	public void testdeletebyid(){
		pos = new PositionDaoImpl();
		try{
			pos.deleteById(9);
		}catch(Exception e){
			System.out.println("出错");
		}
	}
	
	//恢复（隐藏式删除的反向）
	@Test
	public void testregainByDelete(){
		pos = new PositionDaoImpl();
		try{
			pos.regainByDelete(9);
		}catch(Exception e){
			System.out.println("出错");
		}
	}
	
	//修改
	@Test
	public void testupdate(){
		pos = new PositionDaoImpl();
		p = pos.getById(9);
		p.setUsable(0);
		if(p!=null){
			pos.update(p);
		}else{
			System.out.println("测试失败");
		}
	}
}
