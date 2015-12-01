package recActivityServices;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.position.dao.impl.ProNumberDaoImpl;
import com.position.pojo.ProvincialNumber;

/**
 * 省表Dao层方法测试
 * @author Fei
 *
 */
public class TextProNumberDaoImpl {

	ProNumberDaoImpl pro;
	ProvincialNumber  num;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	//通过id查询
	@Test
	public void testGetbycity() {
		pro = new ProNumberDaoImpl();
		num = pro.getById(1);
		if(num !=null){
			System.out.println(num.getProvincial());
		}else{
			System.out.println("测试失败");
		}
	}
	//通过名
	@Test
	public void testGetbyid() {
		pro = new ProNumberDaoImpl();
		num = pro.getByName("北京市");
		if(num !=null){
			System.out.println(num.getProID());
		}else{
			System.out.println("测试失败");
		}
	}
	
	//查找所有
	@Test
	public void testGetall(){
		pro = new ProNumberDaoImpl();
		List<ProvincialNumber> list = pro.getAll();
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
		pro = new ProNumberDaoImpl();
		ProvincialNumber num = new ProvincialNumber();
		num.setUsable(1);
		num.setProvincial("测试");
		try{
			pro.add(num);
		}catch(Exception e){
			System.out.println("出错");
		}
	}
	//删除(隐藏式)
	@Test
	public void testdeletebyidhid(){
		pro = new ProNumberDaoImpl();
		try{
			pro.deleteByIdHid(35);
		}catch(Exception e){
			System.out.println("出错");
		}
	}
	
	// 删除（直接删除）
	@Test
	public void testdeletebyid(){
		pro = new ProNumberDaoImpl();
		try{
			pro.deleteById(35);
		}catch(Exception e){
			System.out.println("出错");
		}
	}
	
	//恢复（隐藏式删除的反向）
	@Test
	public void testregainByDelete(){
		pro = new ProNumberDaoImpl();
		try{
			pro.regainByDelete(35);
		}catch(Exception e){
			System.out.println("出错");
		}
	}
	
	//修改
	@Test
	public void testupdate(){
		pro = new ProNumberDaoImpl();
		num = pro.getById(35);
		num.setUsable(0);
		if(num !=null){
			pro.update(num);
		}else{
			System.out.println("测试失败");
		}
	}

}
