import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import dataAccess.DataAccess;
import domain.Admin;

import domain.Editorea;
import domain.Erregistratua;
import domain.User;

import test.dataAccess.TestDataAccess;

public class isLoginDAWTest {
	
	//sut:system under test
		static DataAccess sut=new DataAccess();
		 
		//additional operations needed to execute the test 
		static TestDataAccess testDA=new TestDataAccess();

		private User user;
		private String log;
		private String pas;

		
	//Userra ez da ez erregistratua ezta editorea
	@Test
	public void test1() {
		log="adminAmets1";
		pas="amets";
		user= new Admin (log,pas);
		User expected=user;
		try {
			testDA.open();
			testDA.persistAdmin((Admin) user);
			testDA.close();
			System.out.println("honaino bai");
			User obtained=sut.isLogin(log, pas);
			assertEquals(((Admin)expected).getUser(),((Admin)obtained).getUser());
		}catch(Exception a) {
			a.getMessage();
			fail("Not yet implemented");
		}finally {
			testDA.open();
			testDA.removeAdmin((Admin) user);
			testDA.close();
		}
	}
	
	// User editorea da
	@Test
	public void test2() {
		log="editorErik1";
		pas="erik";
		user= new Editorea (log,pas);
		User expected=user;
		try {
			testDA.open();
			testDA.persistEditor((Editorea) user);
			testDA.close();
			System.out.println("honaino bai");
			User obtained=sut.isLogin(log, pas);
			assertEquals(((Editorea)expected).getUser(),((Editorea)obtained).getUser());
		}catch(Exception a) {
			fail("Not yet implemented");
		}finally {
			testDA.open();
			testDA.removeEditor((Editorea) user);
			testDA.close();
		}
	}
	
	// User erregistratua da eta ez dago banneatua
	@Test
		public void test3() {
			log="intza1";
			pas="erab";
			String nan="12345678G";
			Double dirua=0.0;
			user= new Erregistratua (log,pas,nan,dirua);
			((Erregistratua) user).setBanned(false);
			User expected=user;
			try {
				testDA.open();
				testDA.persistErreg((Erregistratua) user);
				testDA.close();
				System.out.println("honaino bai");
				User obtained=sut.isLogin(log, pas);
				assertEquals(((Erregistratua)expected).getUser(),((Erregistratua)obtained).getUser());
				assertFalse(((Erregistratua)obtained).isBanned());
			}catch(Exception a) {
				a.getMessage();
				fail("Not yet implemented");
			}finally {
				testDA.open();
				testDA.removeErregistratua((Erregistratua) user);
				testDA.close();
			}
		}
		
		//User erregistratua da eta banneatua dago.Bere baneo data oraindik ez da igaro.
		@Test
		public void test4() {
			log="pello";
			pas="erab";
			String nan="12345687G";
			Double dirua=0.0;
			//Data hasieraketa
			try {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
				Date d1=sdf.parse("2024/07/15");
				user= new Erregistratua (log,pas,nan,dirua);
				((Erregistratua) user).setBanned(true);
				((Erregistratua) user).setBanEndDate(d1);
				User expected=user;
				
				
				testDA.open();
				testDA.persistErreg((Erregistratua) user);
				testDA.close();
				System.out.println("honaino bai");
				User obtained=sut.isLogin(log, pas);
				assertEquals(((Erregistratua)expected).getUser(),((Erregistratua)obtained).getUser());
				assertTrue(((Erregistratua)obtained).isBanned());
			}catch(ParseException e) {
				e.getMessage();
			}catch (Exception e) {
				fail("Not yet implemented");
			}
			finally {
				testDA.open();
				testDA.removeErregistratua((Erregistratua) user);
				testDA.close();
			}
		}
		
		//User erregistratua da eta banneatua dago.Bere baneo data igaro da.
			@Test
			public void test5() {
				log="patxi";
				pas="erab";
				String nan="12346587G";
				Double dirua=0.0;
				user= new Erregistratua (log,pas,nan,dirua);
				((Erregistratua) user).setBanned(true);
				try {
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
					Date d2=sdf.parse("2022/07/15");
					((Erregistratua) user).setBanEndDate(d2);
					User expected=user;
	
					
					testDA.open();
					testDA.persistErreg((Erregistratua) user);
					testDA.close();
					System.out.println("honaino bai");
					User obtained=sut.isLogin(log, pas);
					assertEquals(((Erregistratua)expected).getUser(),((Erregistratua)obtained).getUser());
					assertFalse(((Erregistratua)obtained).isBanned());
				}catch(ParseException e) {
					e.getMessage();
				}catch (Exception e) {
					fail("Not yet implemented");
				}finally {
					testDA.open();
					testDA.removeErregistratua((Erregistratua) user);
					testDA.close();
				}
			}

}