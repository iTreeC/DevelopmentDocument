package recActivityServices;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.position.utils.SessionUtils;
/**
 * 测试hibernate的连通性，以及同步数据库结构
 * @author Fei
 *
 */
public class EntityTest {

	private Session session;
	private Transaction transaction;
	@Before
	public void init(){
		
	}
	
	@After
	public void destroy(){
	}
	
	@Test
	public void test1() {
		
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		
		transaction.commit();
	}
	
}
