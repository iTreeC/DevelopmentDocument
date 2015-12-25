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

import com.position.dao.CityNumberDao;
import com.position.dao.CompanyDao;
import com.position.dao.PositionDao;
import com.position.dao.ProNumberDao;
import com.position.pojo.CompanyPosition;
import com.position.pojo.CityNumber;

/**
 * 地点表dao层测试
 * @author Fei
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})  
@Transactional  
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public class TestPositionDaoImpl {

	@Resource
	private  PositionDao positionDaoImpl;
	@Resource
	private CityNumberDao cityNumberDaoImpl;
	@Resource
	private CompanyDao companyDaoImpl;
	@Resource
	private ProNumberDao proNumberDaoImpl;
	
	private List<CompanyPosition> list;
	private CompanyPosition p;
	private CompanyDao company;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	//通过城市id查询
	@Test
	public void testGetbycity() {
		list = positionDaoImpl.getByCity(6);
		if(list!=null){
			for(int i=0; i<list.size(); i++){
				System.out.println(list.get(i).getAddress());
				System.out.println(list.get(i).getCompany().getId());
			}
		}else{
			System.out.println("测试失败");
		}
	}
	//通过位置id查询
	@Test
	public void testGetbyid() {
		p = positionDaoImpl.getById(1);
		if(p!=null){
				System.out.println(p.getAddress());
		}else{
			System.out.println("测试失败");
		}
	}
	
	//通过公司id查询
	@Test
	public void testGetbyname() {
		p = positionDaoImpl.getByPosId(1);
		if(p!=null){
				System.out.println(p.getAddress());
		}else{
			System.out.println("测试失败");
		}
	}
	//查找所有
	@Test
	public void testGetall(){
		List<CompanyPosition> list = positionDaoImpl.getAll();
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
		List<CompanyPosition> list = positionDaoImpl.getByAddress("珠峰大街");
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
		
		CompanyPosition bus = new CompanyPosition();
		//bus.setId(9);
		bus.setAddress("test");
		CityNumber c  = cityNumberDaoImpl.getById(2);
		bus.setCity(c);
		bus.setCompany(companyDaoImpl.getById(1));
		bus.setCompany(company.getById(1));
		bus.setCounty(0);
		bus.setProvince(proNumberDaoImpl.getById(1));
		bus.setUsable(1);
		try{
			positionDaoImpl.add(bus);
		}catch(Exception e){
			System.out.println("出错");
		}
	}
	//删除(隐藏式)
	@Test
	public void testdeletebyidhid(){
		try{
			positionDaoImpl.deleteByIdHid(9);
		}catch(Exception e){
			System.out.println("出错");
		}
	}
	
	// 删除（直接删除）
	@Test
	public void testdeletebyid(){
		try{
			positionDaoImpl.deleteById(9);
		}catch(Exception e){
			System.out.println("出错");
		}
	}
	
	//恢复（隐藏式删除的反向）
	@Test
	public void testregainByDelete(){
		try{
			positionDaoImpl.regainByDelete(9);
		}catch(Exception e){
			System.out.println("出错");
		}
	}
	
	//修改
	@Test
	public void testupdate(){
		p = positionDaoImpl.getById(9);
		p.setUsable(0);
		if(p!=null){
			positionDaoImpl.update(p);
		}else{
			System.out.println("测试失败");
		}
	}
	
	/************************get*****************************/
	public PositionDao getPositionDaoImpl() {
		return positionDaoImpl;
	}

	public void setPositionDaoImpl(PositionDao positionDaoImpl) {
		this.positionDaoImpl = positionDaoImpl;
	}

	public CityNumberDao getCityNumberDaoImpl() {
		return cityNumberDaoImpl;
	}

	public void setCityNumberDaoImpl(CityNumberDao cityNumberDaoImpl) {
		this.cityNumberDaoImpl = cityNumberDaoImpl;
	}

	public CompanyDao getCompanyDaoImpl() {
		return companyDaoImpl;
	}

	public void setCompanyDaoImpl(CompanyDao companyDaoImpl) {
		this.companyDaoImpl = companyDaoImpl;
	}

	public ProNumberDao getProNumberDaoImpl() {
		return proNumberDaoImpl;
	}

	public void setProNumberDaoImpl(ProNumberDao proNumberDaoImpl) {
		this.proNumberDaoImpl = proNumberDaoImpl;
	}
	
}
