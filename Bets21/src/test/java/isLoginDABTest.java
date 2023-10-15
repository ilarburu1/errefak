import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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

public class isLoginDABTest {

	 //sut:system under test
	 static DataAccess sut=new DataAccess();
	 
	 //additional operations needed to execute the test 
	 static TestDataAccess testDA=new TestDataAccess();

	private User user;
	private String log;
	private String password;
	
	
	@Test
	//Izena eta pasahitza DBan daude.
	public void test1() {
		try {
			
			//define paramaters
			log="Manex";
			password="123";
			String nan="12345678A";
			double dirua=0.0;
			
			//configure the state of the system (create object in the dabatase)
			testDA.open();
			user=testDA.addErregistratua(log,password,nan,dirua);
			testDA.close();	
			
			//invoke System Under Test (sut)  
			User obtained=sut.isLogin(user.getUser(),user.getPassword());
			
			//verify the results
			assertTrue(obtained!=null);
			assertEquals(obtained.getUser(),user.getUser());
			assertEquals(obtained.getPassword(),user.getPassword());
			
			//q datubasean dago
			testDA.open();
			boolean exist = testDA.existErreg(((Erregistratua)user));
			assertTrue(exist);
			testDA.close();
			
		   } catch (Exception e) {
			// TODO Auto-generated catch block
			// if the program goes to this point fail  
			fail();
			} finally {
				  //Remove the created objects in the database (cascade removing)   
				  testDA.open();
		          testDA.removeErregistratua(((Erregistratua)user));
		          testDA.close();
		      //     System.out.println("Finally "+b);          
		        }
	}
	@Test
	//Izena eta pasahitza ez daude DBan
	public void test2() {
		try {
			//define paramaters
			log="Pello";
			password="13";
			String nan="12345687A";
			Double dirua=0.0;
			user=new Erregistratua(log,password,nan,dirua);
			
			//erreg ez dago datubasean
			testDA.open();
			boolean exist = testDA.existErreg(((Erregistratua)user));
			assertFalse(exist);
			testDA.close();
			
		   } catch (Exception e) {
			// TODO Auto-generated catch block
			// if the program goes to this point fail  
			fail();
			} finally {
				  //Remove the created objects in the database (cascade removing)   
		      //     System.out.println("Finally "+b);          
		    }
	}

	@Test
	//Izena eta pasahitza DBan daude eta admin motakoa da.
	public void test3() {
		try {
			
			//define paramaters
			log="AdminAmets";
			password="000";
			
			//configure the state of the system (create object in the dabatase)
			testDA.open();
			user=testDA.addAdmin(log,password);
			testDA.close();	
			
			//invoke System Under Test (sut)  
			User obtained=sut.isLogin(user.getUser(),user.getPassword());
			
			//verify the results
			assertTrue(obtained!=null);
			assertEquals(obtained.getUser(),user.getUser());
			assertEquals(obtained.getPassword(),user.getPassword());
			
			//q datubasean dago
			testDA.open();
			boolean exist = testDA.existAdmin(((Admin)user));
			assertTrue(exist);
			testDA.close();
			
		   } catch (Exception e) {
			// TODO Auto-generated catch block
			// if the program goes to this point fail  
			fail();
			} finally {
				  //Remove the created objects in the database (cascade removing)   
				  testDA.open();
		          testDA.removeAdmin(((Admin)user));
		          testDA.close();
		      //     System.out.println("Finally "+b);          
		        }
	}

	@Test
	//Izena eta pasahitza DBan daude eta editorea motakoa da.
	public void test4() {
		try {
			
			//define paramaters
			log="EditorErik";
			password="111";
			
			//configure the state of the system (create object in the dabatase)
			testDA.open();
			user=testDA.addEditorea(log,password);
			testDA.close();	
			
			//invoke System Under Test (sut)  
			User obtained=sut.isLogin(user.getUser(),user.getPassword());
			
			//verify the results
			assertTrue(obtained!=null);
			assertEquals(obtained.getUser(),user.getUser());
			assertEquals(obtained.getPassword(),user.getPassword());
			
			//datubasean dago
			testDA.open();
			boolean exist = testDA.existEditorea(((Editorea)user));
			assertTrue(exist);
			testDA.close();
			
		   } catch (Exception e) {
			// TODO Auto-generated catch block
			// if the program goes to this point fail  
			fail();
			} finally {
				  //Remove the created objects in the database (cascade removing)   
				  testDA.open();
		          testDA.removeEditor(((Editorea)user));
		          testDA.close();
		      //     System.out.println("Finally "+b);          
		        }
	}

	@Test
	//Izena eta pasahitza DBan daud, erregistratua motakoa da eta ez dago baneatua.
	public void test5() {
		try {
			
			//define paramaters
			log="Patxi";
			password="7";
			String nan="12345678A";
			Double dirua=0.0;
			
			//configure the state of the system (create object in the dabatase)
			testDA.open();
			user=testDA.addErregistratua(log,password,nan,dirua);
			((Erregistratua)user).setBanned(false);
			testDA.close();
			
			//invoke System Under Test (sut)  
			User obtained=sut.isLogin(user.getUser(),user.getPassword());
			
			//verify the results
			assertTrue(obtained!=null);
			assertEquals(obtained.getUser(),user.getUser());
			assertEquals(obtained.getPassword(),user.getPassword());
			assertFalse(((Erregistratua)obtained).isBanned());
			
			//datubasean dago
			testDA.open();
			boolean exist = testDA.existErreg(((Erregistratua)user));
			assertTrue(exist);
			testDA.close();
			
		   } catch (Exception e) {
			// TODO Auto-generated catch block
			// if the program goes to this point fail  
			fail();
			} finally {
				  //Remove the created objects in the database (cascade removing)   
				  testDA.open();
		          testDA.removeErreg(((Erregistratua)user));
		          testDA.close();
		      //     System.out.println("Finally "+b);          
		        }
	}
	
	@Test
	//Izena eta pasahitza DBan daude, erregistratua motakoa da eta baneatua dago baina ez da pasa baneoa kentzeko data.
	public void test6() {
		try {
			
			//define paramaters
			log="Joxe";
			password="8";
			String nan="12345678A";
			Double dirua=0.0;
			

			//configure the state of the system (create object in the dabatase)
			user= new Erregistratua(log,password,nan,dirua);
			((Erregistratua)user).setBanned(true);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
			Date d1=sdf.parse("2024/07/15");
			((Erregistratua)user).setBanEndDate(d1);
			testDA.open();
			testDA.persistErreg(((Erregistratua)user));
			testDA.close();
			
			//invoke System Under Test (sut)  
			User obtained=sut.isLogin(user.getUser(),user.getPassword());
			
			//verify the results
			assertTrue(obtained!=null);
			assertEquals(obtained.getUser(),user.getUser());
			assertEquals(obtained.getPassword(),user.getPassword());
			assertEquals(((Erregistratua)obtained).isBanned(),true);
			
			//datubasean dago
			testDA.open();
			boolean exist = testDA.existErreg(((Erregistratua)user));
			assertTrue(exist);
			testDA.close();
			
		   }catch(ParseException e) {
				e.getMessage();
		   }catch (Exception e) {
			// TODO Auto-generated catch block
			// if the program goes to this point fail  
			fail();
			} finally {
				  //Remove the created objects in the database (cascade removing)   
				  testDA.open();
		          testDA.removeErreg(((Erregistratua)user));
		          testDA.close();
		      //     System.out.println("Finally "+b);          
		     }
	}
	
	@Test
	//Izena eta pasahitza DBan daude, erregistratua motakoa da eta baneatua dago baina ez da pasa baneoa kentzeko data.
	public void test7() {
		try {
			
			//define paramaters
			log="Jon";
			password="9";
			String nan="12345678A";
			Double dirua=0.0;
			

			//configure the state of the system (create object in the dabatase)
			user= new Erregistratua(log,password,nan,dirua);
			((Erregistratua)user).setBanned(true);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
			Date d1=sdf.parse("2022/07/15");
			((Erregistratua)user).setBanEndDate(d1);
			testDA.open();
			testDA.persistErreg(((Erregistratua)user));
			testDA.close();
			
			//invoke System Under Test (sut)  
			User obtained=sut.isLogin(user.getUser(),user.getPassword());
			
			//verify the results
			assertTrue(obtained!=null);
			assertEquals(obtained.getUser(),user.getUser());
			assertEquals(obtained.getPassword(),user.getPassword());
			assertEquals(((Erregistratua)obtained).isBanned(),false);
			
			//datubasean dago
			testDA.open();
			boolean exist = testDA.existErreg(((Erregistratua)user));
			assertTrue(exist);
			testDA.close();
			
		   }catch(ParseException e) {
				e.getMessage();
		   }catch (Exception e) {
			// TODO Auto-generated catch block
			// if the program goes to this point fail  
			fail();
			} finally {
				  //Remove the created objects in the database (cascade removing)   
				  testDA.open();
		          testDA.removeErreg(((Erregistratua)user));
		          testDA.close();
		      //     System.out.println("Finally "+b);          
		     }
	}
	@Test
	//Izena null da.
	public void test8() {
		try {
			
			//define paramaters
			log=null;
			password="1234";

			
			//invoke System Under Test (sut)  
			sut.isLogin(log,password);
			
			//verify the results
			fail();
			
		   }catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			// if the program goes to this point fail  
			assertTrue(true);
			}
	}
	@Test
	//Pasahitza null da.
	public void test9() {
		try {
			
			//define paramaters
			log="Jose";
			password=null;
			String nan="12345678A";
			Double dirua=0.0;
			user= new Erregistratua(log,password,nan,dirua);

			//invoke System Under Test (sut)  
			User obtained=sut.isLogin(user.getUser(), user.getPassword());
			
			
			//verify the results
			assertTrue(obtained==null);
			

		   } catch (Exception e) {
			// TODO Auto-generated catch block
			// if the program goes to this point fail  
			fail();
			} finally {
				  //Remove the created objects in the database (cascade removing)   
				testDA.open();
		        testDA.removeErregistratua(((Erregistratua)user));
		        testDA.close();
		      //     System.out.println("Finally "+b);          
		        }
		   }
}
