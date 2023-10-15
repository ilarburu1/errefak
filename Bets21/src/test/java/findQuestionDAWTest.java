import static org.junit.Assert.*;

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

import test.dataAccess.TestDataAccess;

public class findQuestionDAWTest {
	
	//sut:system under test
		static DataAccess sut=new DataAccess();
		 
		//additional operations needed to execute the test 
		static TestDataAccess testDA=new TestDataAccess();

		private Event ev;
		private Integer eventNumber;
		private String description; 
		private Date eventDate;
		private Question expected;
		private Question obtained;

		
	//Ebentuak ez ditu galderak
	@Test
	public void test1() {
		eventNumber=11;
		description="Real-Real Madrid";
		expected=null;
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
			eventDate=sdf.parse("2023/04/15");
			ev= new Event(eventNumber,description,eventDate);
			
			testDA.open();
			testDA.persistEvent(ev);
			testDA.close();
			System.out.println("honaino bai");
			obtained=sut.findQuestion(ev, "Zenbat gol?");
			assertTrue(ev.getQuestions().isEmpty());
			assertNull(obtained);
		}catch(ParseException e) {
			e.getMessage();
		}catch(Exception a) {
			a.getMessage();
			fail("Not yet implemented");
		}finally {
			testDA.open();
			testDA.removeEvent(ev);
			testDA.close();
		}
	}
	
	@Test
	public void test2() {
		eventNumber=7;
		description="Barsa-Real Madrid";
		expected=new Question(1,"Nork irabaziko du");
		Question galdera= new Question(2,"Zenbat gol sartuko dira?");
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
			eventDate=sdf.parse("2023/02/15");
			ev= new Event(eventNumber,description,eventDate);
			ev.addQuestion2(galdera);
			
			testDA.open();
			testDA.persistEvent(ev);
			testDA.close();
			System.out.println("honaino bai");
			obtained=sut.findQuestion(ev, "Nork irabaziko du?");
			assertNull(obtained);
		}catch(ParseException e) {
			e.getMessage();
		}catch(Exception a) {
			a.getMessage();
			fail("Not yet implemented");
		}finally {
			testDA.open();
			testDA.removeEvent(ev);
			testDA.removeQuestion(expected);
			testDA.removeQuestion(galdera);
			testDA.close();
		}
	}
	
	//Galdera ebentuan dago
	@Test
	public void test3() {

		eventNumber=17;
		description="Barsa-Real Madrid";
		expected=new Question(1,"Zenbat gol?");
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
			eventDate=sdf.parse("2023/01/15");
			ev= new Event(eventNumber,description,eventDate);
			ev.addQuestion2(expected);
				
			testDA.open();
			testDA.persistEvent(ev);
			testDA.close();
			System.out.println("honaino bai");
			obtained=sut.findQuestion(ev, "Zenbat gol?");
			assertEquals(expected.getQuestion(),obtained.getQuestion());
		}catch(ParseException e) {
			e.getMessage();
		}catch(Exception a) {
			a.getMessage();
			fail("Not yet implemented");
		}finally {
			testDA.open();
			testDA.removeEvent(ev);
			testDA.removeQuestion(expected);
			testDA.close();
		}
	}
	
	@Test
	public void test4() {
		eventNumber=16;
		description="Barsa-Real Madrid";
		expected=new Question(1,"Nork irabaziko du");
		Question galdera= new Question(2,"Zenbat gol?");
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
			eventDate=sdf.parse("2023/02/15");
			ev= new Event(eventNumber,description,eventDate);
			ev.addQuestion2(galdera);
			
			testDA.open();
			testDA.persistEvent(ev);
			testDA.close();
			System.out.println("honaino bai");
			obtained=sut.findQuestion(ev, "Nork irabaziko du?");
			assertNull(obtained);
		}catch(ParseException e) {
			e.getMessage();
		}catch(Exception a) {
			a.getMessage();
			fail("Not yet implemented");
		}finally {
			testDA.open();
			testDA.removeEvent(ev);
			testDA.removeQuestion(expected);
			testDA.removeQuestion(galdera);
			testDA.close();
		}
	}

}