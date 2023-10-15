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
import domain.Event;
import domain.Question;
import domain.User;
import exceptions.QuestionAlreadyExist;
import test.dataAccess.TestDataAccess;

public class findQuestionDABTest {

	 //sut:system under test
	 static DataAccess sut=new DataAccess();
	 
	 //additional operations needed to execute the test 
	 static TestDataAccess testDA=new TestDataAccess();

	private Event ev;
	private String g;
	
	
	@Test
	//Ebentua null da.
	public void test1() {
		try {
			
			//define paramaters
			ev=null;
			g="Zenbat gol?";
			
			//invoke System Under Test (sut)  
			sut.findQuestion(ev, g);
			
			//verify the results
			fail();
			
		   }catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			// if the program goes to this point fail  
			assertTrue(true);
			}
			
				
				
	}
	@Test
	//Galdera null da
	public void test2() {
		try {
			int evZenb=14;
			String des="Barsa-Real Madrid";
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
			Date d=sdf.parse("2023/02/15");
			g=null;
			
			//configure the state of the system (create object in the dabatase)
			testDA.open();
			ev = testDA.addEvent(evZenb,des,d);
			testDA.close();			
			
			//invoke System Under Test (sut)  
			Question q=sut.findQuestion(ev,g);
			
			
			//verify the results
			assertTrue(q==null);
			
			
			//q datubasean dago
			testDA.open();
			boolean exist = testDA.existEvent(ev);
				
			assertTrue(exist);
			testDA.close();
			}catch(ParseException e) {
				e.getMessage();
			}catch (Exception a) {
				// TODO Auto-generated catch block
				a.printStackTrace();
			
			} finally {
				  //Remove the created objects in the database (cascade removing)   
				testDA.open();
				testDA.removeEvent(ev);
				testDA.close();
		      //System.out.println("Finally "+b);          
		    }
	}
	
	@Test
	//Ebentua ez dago DBan
	public void test3() {
		try {
			//define paramaters
			int evZenb=7;
			String des="Barsa-Real Madrid";
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
			Date d=sdf.parse("2023/02/15");
			g="Zenbat gol?";
			ev=new Event(evZenb,des,d);
			
			//invoke System Under Test (sut)  
			Question obtained=sut.findQuestion(ev, g);
			
			fail();


			
		   }catch(ParseException e) {
				e.getMessage();
		   }catch (NullPointerException e) {
			// TODO Auto-generated catch block
			// if the program goes to this point fail  
			assertTrue(true);
		   }
	}
	
	@Test
	//Ebentua DBan dago baina ez du galderarik.
	public void test4() {
		try {
			
			//define paramaters
			int evZenb=17;
			String des="Real-Real Madrid";
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
			Date d=sdf.parse("2023/04/15");
			
			g="Zenbat gol?";
			ev=new Event(evZenb,des,d);
			
			//configure the state of the system (create object in the dabatase)
			testDA.open();
			testDA.persistEvent(ev);
			testDA.close();	
			
			//invoke System Under Test (sut)  
			Question obtained=sut.findQuestion(ev, g);
			
			//verify the results
			assertTrue(obtained==null);
			assertTrue(ev.getQuestions().isEmpty());
			//ev datubasean dago
			testDA.open();
			boolean exist = testDA.existEvent(ev);
			assertTrue(exist);
			testDA.close();
			
		   } catch (Exception e) {
			// TODO Auto-generated catch block
			// if the program goes to this point fail  
			fail();
			} finally {
				  //Remove the created objects in the database (cascade removing)   
				  testDA.open();
		          testDA.removeEvent(ev);
		          testDA.close();
		      //     System.out.println("Finally "+b);          
		        }
	}

	@Test
	//Ebentua DBan dago baina galdera ez ebentuan.
	public void test5() {
		Question q1=new Question(100,"Zeinek irabazi?");
		try {
			
			//define paramaters
			int evZenb=11;
			String des="Real-Real Madrid";
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
			Date d=sdf.parse("2023/04/15");
			
			g="Zenbat gol?";
			ev=new Event(evZenb,des,d);
			ev.addQuestion2(q1);
			
			//configure the state of the system (create object in the dabatase)
			testDA.open();
			testDA.persistEvent(ev);
			testDA.close();	
			
			//invoke System Under Test (sut)  
			Question obtained=sut.findQuestion(ev, g);
			
			//verify the results
			assertTrue(obtained==null);
			
			//ev datubasean dago
			testDA.open();
			boolean exist = testDA.existEvent(ev);
			assertTrue(exist);
			testDA.close();
			
		   } catch (Exception e) {
			// TODO Auto-generated catch block
			// if the program goes to this point fail  
			fail();
			} finally {
				  //Remove the created objects in the database (cascade removing)   
				  testDA.open();
		          testDA.removeEvent(ev);
		          testDA.removeQuestion(q1);
		          testDA.close();
		      //     System.out.println("Finally "+b);          
		        }
	}
	
	@Test
	//Ebentua DBan dago eta galdera ebentuan agertzen da.
	public void test6() {
		Question q1=new Question(100,"Zeinek irabazi?");
		try {
			
			//define paramaters
			int evZenb=11;
			String des="Real-Real Madrid";
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
			Date d=sdf.parse("2023/04/15");
			
			g="Zeinek irabazi?";
			ev=new Event(evZenb,des,d);
			ev.addQuestion2(q1);
			
			//configure the state of the system (create object in the dabatase)
			testDA.open();
			testDA.persistEvent(ev);
			testDA.close();	
			
			//invoke System Under Test (sut)  
			Question obtained=sut.findQuestion(ev, g);
			
			//verify the results
			assertEquals(obtained.getQuestion(),g);
			
			//q datubasean dago
			testDA.open();
			boolean exist = testDA.existEvent(ev);
			assertTrue(exist);
			testDA.close();
			
		   } catch (Exception e) {
			// TODO Auto-generated catch block
			// if the program goes to this point fail  
			fail();
			} finally {
				  //Remove the created objects in the database (cascade removing)   
				  testDA.open();
		          testDA.removeEvent(ev);
		          testDA.removeQuestion(q1);
		          testDA.close();
		      //     System.out.println("Finally "+b);          
		        }
	}
}
