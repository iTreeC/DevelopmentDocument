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

import com.position.dao.ProNumberDao;
import com.position.dao.impl.ProNumberDaoImpl;
import com.position.pojo.ProvincialNumber;

/**
 * 省表Dao层方法测试
 * @author Fei
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})  
@Transactional  
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public class TestProNumberDaoImpl {

	@Resource
	private ProNumberDao proNumberDaoImpl;
	ProvincialNumber  num;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	//通过id查询
	@Test
	public void testGetbycity() {
		num = proNumberDaoImpl.getById(1);
		if(num !=null){
			System.out.println(num.getProvincial());
		}else{
			System.out.println("测试失败");
		}
	}
	//通过名
	@Test
	public void testGetbyid() {
		num = proNumberDaoImpl.getByName("北京市");
		if(num !=null){
			System.out.println(num.getProID());
		}else{
			System.out.println("测试失败");
		}
	}
	
	//查找所有
	@Test
	public void testGetall(){
		List<ProvincialNumber> list = proNumberDaoImpl.getAll();
		if(list!=null){
			for(int i=0; i<list.size(); i++){
				System.out.println(list.get(i).getProvincial());
			}
		}else{
			System.out.println("测试失败");
		}
	}
	
	//增加
	@Test
	public void testAdd(){
		ProvincialNumber num = new ProvincialNumber();
		num.setUsable(1);
		num.setProvincial("测试");
		try{
			proNumberDaoImpl.add(num);
		}catch(Exception e){
			System.out.println("出错");
		}
	}
	//删除(隐藏式)
	@Test
	public void testdeletebyidhid(){
		try{
			proNumberDaoImpl.deleteByIdHid(35);
		}catch(Exception e){
			System.out.println("出错");
		}
	}
	
	// 删除（直接删除）
	@Test
	public void testdeletebyid(){
		try{
			proNumberDaoImpl.deleteById(35);
		}catch(Exception e){
			System.out.println("出错");
		}
	}
	
	//恢复（隐藏式删除的反向）
	@Test
	public void testregainByDelete(){
		try{
			proNumberDaoImpl.regainByDelete(35);
		}catch(Exception e){
			System.out.println("出错");
		}
	}
	
	//修改
	@Test
	public void testupdate(){
		num = proNumberDaoImpl.getById(35);
		num.setUsable(0);
		if(num !=null){
			proNumberDaoImpl.update(num);
		}else{
			System.out.println("测试失败");
		}
	}


	/************************get*****************************/

	public ProNumberDao getProNumberDaoImpl() {
		return proNumberDaoImpl;
	}

	public void setProNumberDaoImpl(ProNumberDao proNumberDaoImpl) {
		this.proNumberDaoImpl = proNumberDaoImpl;
	}
}
