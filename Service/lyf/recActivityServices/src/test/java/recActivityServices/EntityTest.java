package recActivityServices;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
/**
 * 测试hibernate的连通性，以及同步数据库结构
 * @author Fei
 *
 */
public class EntityTest {


	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	@Before
	public void init(){
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = 
				new ServiceRegistryBuilder().applySettings(configuration.getProperties())
											.buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry); 
		
		//打开session
		session = sessionFactory.openSession();
		
		//开启事物
		transaction = session.beginTransaction();
	}
	
	@After
	public void destroy(){
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	
	@org.junit.Test
	public void test() {
		
	}
	
}
