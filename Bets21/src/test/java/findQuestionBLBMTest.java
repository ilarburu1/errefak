import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import org.junit.Test;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Admin;
import domain.Editorea;
import domain.Erregistratua;
import domain.Event;
import domain.Question;
import domain.User;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class findQuestionBLBMTest {

     DataAccess dataAccess=Mockito.mock(DataAccess.class);
     

   //sut:system under test
@InjectMocks
BLFacade sut=new BLFacadeImplementation(dataAccess);

private Event ev;
private String g;
private float min = 0;
private Question expectedQuestion;

@Test
//ebentua null da.
public void test1() {
try {
//define paramaters
ev=null;
g="Zenbat gol?";


//configure Mock
Mockito.doReturn(new IllegalArgumentException()).when(dataAccess).findQuestion(ev, g);


//invoke System Under Test (sut)
Question obtained = sut.findQuestion(ev,g,min);

fail();

  } catch (IllegalArgumentException e) {
  assertTrue(true);
}catch (Exception e) {
e.getMessage();
}

}

@Test
//Galdera null da.
public void test2() {
try {
//define paramaters
int evZenb=14;
String des="Barsa-Real Madrid";
SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
Date d=sdf.parse("2023/04/15");
ev= new Event(evZenb,des,d);
g=null;


//configure Mock
Mockito.doReturn(null).when(dataAccess).findQuestion(ev, g);


//invoke System Under Test (sut)
Question obtained = sut.findQuestion(ev,g,min);

assertTrue(obtained==null);


  } catch (ParseException e) {
  e.getMessage();
}catch (Exception e) {
fail();
}

}

@Test
//Ebentua ez dago DBan
public void test3() {
try {
//define paramaters
int evZenb=11;
String des="Barsa-Real Madrid";
SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
Date d=sdf.parse("2023/02/15");
ev= new Event(evZenb,des,d);
g="Zenbat gol?";


//configure Mock
Mockito.doReturn(new NullPointerException()).when(dataAccess).findQuestion(ev, g);


//invoke System Under Test (sut)
Question obtained = sut.findQuestion(ev,g,min);

fail();

  } catch (NullPointerException e) {
  assertTrue(true);
}catch (ParseException e) {
      e.getMessage();
       }catch (Exception e) {
e.getMessage();
}

        }

@Test
//ebentua DBan dago baina ez du galderarik
public void test4() {
try {
//define paramaters
int evZenb=17;
String des="Real-Real Madrid";
SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
Date d=sdf.parse("2023/04/15");
ev= new Event(evZenb,des,d);
Vector<Question> galderak = new Vector<Question>();
ev.setQuestions(galderak);
g="Zenbat gol?";


//configure Mock
Mockito.doReturn(null).when(dataAccess).findQuestion(ev, g);


//invoke System Under Test (sut)
Question obtained = sut.findQuestion(ev,g,min);

//verify the results
Mockito.verify(dataAccess,Mockito.times(1)).findQuestion(Mockito.any(Event.class),Mockito.any(String.class));


ArgumentCaptor<Event> eventCaptor = ArgumentCaptor.forClass(Event.class);
ArgumentCaptor<String> questionCaptor = ArgumentCaptor.forClass(String.class);

Mockito.verify(dataAccess,Mockito.times(1)).findQuestion(eventCaptor.capture(),questionCaptor.capture());

assertTrue(obtained==null);

assertEquals(eventCaptor.getValue(),ev);
assertEquals(questionCaptor.getValue(), g);


  } catch (ParseException e) {
  e.getMessage();
}catch (Exception e) {
fail();
}

}


@Test
//Ebentua DBan dago baina galdera ez ebentuan.
public void test5() {
try {
//define paramaters
int evZenb=11;
String des="Real-Real Madrid";
SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
Date d=sdf.parse("2023/04/15");
ev= new Event(evZenb,des,d);
Question q1=new Question(100,"Zeinek irabazi?");
Vector<Question> galderak = new Vector<Question>();
ev.setQuestions(galderak);
ev.addQuestion2(q1);
g="Zenbat gol?";


//configure Mock
Mockito.doReturn(null).when(dataAccess).findQuestion(ev, g);


//invoke System Under Test (sut)
Question obtained = sut.findQuestion(ev,g,min);

//verify the results
Mockito.verify(dataAccess,Mockito.times(1)).findQuestion(Mockito.any(Event.class),Mockito.any(String.class));


ArgumentCaptor<Event> eventCaptor = ArgumentCaptor.forClass(Event.class);
ArgumentCaptor<String> questionCaptor = ArgumentCaptor.forClass(String.class);

Mockito.verify(dataAccess,Mockito.times(1)).findQuestion(eventCaptor.capture(),questionCaptor.capture());

assertTrue(obtained==null);

assertEquals(eventCaptor.getValue(),ev);
assertEquals(questionCaptor.getValue(), g);


  } catch (ParseException e) {
  e.getMessage();
}catch (Exception e) {
fail();
}

}

@Test
//Ebentua DBan dago eta galdera ebentuan agertzen da.
public void test6() {
try {
//define paramaters
int evZenb=11;
String des="Real-Real Madrid";
SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
Date d=sdf.parse("2023/04/15");
ev= new Event(evZenb,des,d);
Vector<Question> galderak = new Vector<Question>();
ev.setQuestions(galderak);
expectedQuestion = new Question(100,"Zeinek irabazi?");
ev.addQuestion2(expectedQuestion);
g="Zeinek irabazi?";



//configure Mock
Mockito.doReturn(expectedQuestion).when(dataAccess).findQuestion(ev, g);


//invoke System Under Test (sut)
Question obtained = sut.findQuestion(ev,g,min);

//verify the results
Mockito.verify(dataAccess,Mockito.times(1)).findQuestion(Mockito.any(Event.class),Mockito.any(String.class));


ArgumentCaptor<Event> eventCaptor = ArgumentCaptor.forClass(Event.class);
ArgumentCaptor<String> questionCaptor = ArgumentCaptor.forClass(String.class);

Mockito.verify(dataAccess,Mockito.times(1)).findQuestion(eventCaptor.capture(),questionCaptor.capture());

assertTrue(obtained!=null);
assertEquals(obtained.getQuestion(),expectedQuestion.getQuestion());

assertEquals(eventCaptor.getValue(),ev);
assertEquals(questionCaptor.getValue(), g);


  } catch (ParseException e) {
  e.getMessage();
}catch (Exception e) {
fail();
}

      }



}
