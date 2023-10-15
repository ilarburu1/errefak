import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
public class isLoginBLBMTest {

	DataAccess dataAccess = Mockito.mock(DataAccess.class);
	Erregistratua mockedErreg = Mockito.mock(domain.Erregistratua.class);
	// Editorea mockedEditor=Mockito.mock(domain.Editorea.class);
	// Admin mockedAdmin=Mockito.mock(domain.Admin.class);

	// sut:system under test
	@InjectMocks
	BLFacade sut = new BLFacadeImplementation(dataAccess);

	private User expectedUser;
	private String log;
	private String password;

	@Test
	//izena eta pasahitza DBan daude.
	public void test1() {
		try {
			//define paramaters
			log = "Manex";
			password = "123";
			String nan = "12345678A";
			double dirua = 0.0;
			expectedUser = new Erregistratua(log, password, nan, dirua);

			//configure Mock
			Mockito.doReturn(expectedUser).when(dataAccess).isLogin(log, password);

			//invoke System Under Test (sut)
			User obtained = sut.isLogin(log, password);

			//verify the results
			Mockito.verify(dataAccess, Mockito.times(1)).isLogin(Mockito.any(String.class), Mockito.any(String.class));

			ArgumentCaptor<String> logCaptor = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> passwordCaptor = ArgumentCaptor.forClass(String.class);

			Mockito.verify(dataAccess, Mockito.times(1)).isLogin(logCaptor.capture(), passwordCaptor.capture());

			assertTrue(obtained != null);
			assertEquals(obtained.getUser(), expectedUser.getUser());
			assertEquals(obtained.getPassword(), expectedUser.getPassword());

			assertEquals(logCaptor.getValue(), log);
			assertEquals(passwordCaptor.getValue(), password);

		} catch (Exception e) {
			fail();
		}

	}

	@Test
	//Izena eta pasahitza ez daude DBan
	public void test2() {
		try {
			//define paramaters
			log = "Pello";
			password = "13";
			String nan = "22345678A";
			double dirua = 0.0;

			//configure Mock
			Mockito.doReturn(null).when(dataAccess).isLogin(log, password);

			//invoke System Under Test (sut)
			User obtained = sut.isLogin(log, password);

//verify the results
			Mockito.verify(dataAccess, Mockito.times(1)).isLogin(Mockito.any(String.class), Mockito.any(String.class));

			ArgumentCaptor<String> logCaptor = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> passwordCaptor = ArgumentCaptor.forClass(String.class);

			Mockito.verify(dataAccess, Mockito.times(1)).isLogin(logCaptor.capture(), passwordCaptor.capture());

			assertTrue(obtained == null);

			assertEquals(logCaptor.getValue(), log);
			assertEquals(passwordCaptor.getValue(), password);

		} catch (Exception e) {
			fail();
		}

	}

	@Test
//Izena eta pasahitza DBan daude eta admin motakoa da.
	public void test3() {
		try {
//define paramaters
			log = "AdminAmets";
			password = "000";
			expectedUser = new Admin(log, password);

//configure Mock
			Mockito.doReturn(expectedUser).when(dataAccess).isLogin(log, password);

//invoke System Under Test (sut)
			User obtained = sut.isLogin(log, password);

//verify the results
			Mockito.verify(dataAccess, Mockito.times(1)).isLogin(Mockito.any(String.class), Mockito.any(String.class));

			ArgumentCaptor<String> logCaptor = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> passwordCaptor = ArgumentCaptor.forClass(String.class);

			Mockito.verify(dataAccess, Mockito.times(1)).isLogin(logCaptor.capture(), passwordCaptor.capture());

			assertTrue(obtained != null);
			assertEquals(obtained.getUser(), expectedUser.getUser());
			assertEquals(obtained.getPassword(), expectedUser.getPassword());

			assertEquals(logCaptor.getValue(), log);
			assertEquals(passwordCaptor.getValue(), password);

		} catch (Exception e) {
			fail();
		}

	}

	@Test
//Izena eta pasahitza DBan daude eta editorea motakoa da.
	public void test4() {
		try {
//define paramaters
			log = "EditorErik";
			password = "111";
			expectedUser = new Editorea(log, password);

//configure Mock
			Mockito.doReturn(expectedUser).when(dataAccess).isLogin(log, password);

//invoke System Under Test (sut)
			User obtained = sut.isLogin(log, password);

//verify the results
			Mockito.verify(dataAccess, Mockito.times(1)).isLogin(Mockito.any(String.class), Mockito.any(String.class));

			ArgumentCaptor<String> logCaptor = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> passwordCaptor = ArgumentCaptor.forClass(String.class);

			Mockito.verify(dataAccess, Mockito.times(1)).isLogin(logCaptor.capture(), passwordCaptor.capture());

			assertTrue(obtained != null);
			assertEquals(obtained.getUser(), expectedUser.getUser());
			assertEquals(obtained.getPassword(), expectedUser.getPassword());

			assertEquals(logCaptor.getValue(), log);
			assertEquals(passwordCaptor.getValue(), password);

		} catch (Exception e) {
			fail();
		}

	}

	@Test
//Izena eta pasahitza DBan daude, erregistratua motakoa da eta ez dago baneatua.
	public void test5() {
		try {
//define paramaters
			log = "Patxi";
			password = "7";
			String nan = "42345678A";
			double dirua = 0.0;

			expectedUser = new Erregistratua(log, password, nan, dirua);
			((Erregistratua) expectedUser).setBanned(false);

//configure Mock
			Mockito.doReturn(expectedUser).when(dataAccess).isLogin(log, password);

//invoke System Under Test (sut)
			User obtained = sut.isLogin(log, password);

//verify the results
			Mockito.verify(dataAccess, Mockito.times(1)).isLogin(Mockito.any(String.class), Mockito.any(String.class));

			ArgumentCaptor<String> logCaptor = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> passwordCaptor = ArgumentCaptor.forClass(String.class);

			Mockito.verify(dataAccess, Mockito.times(1)).isLogin(logCaptor.capture(), passwordCaptor.capture());

			assertTrue(obtained != null);
			assertEquals(obtained.getUser(), expectedUser.getUser());
			assertEquals(obtained.getPassword(), expectedUser.getPassword());
			assertEquals(((Erregistratua) obtained).isBanned(), false);

			assertEquals(logCaptor.getValue(), log);
			assertEquals(passwordCaptor.getValue(), password);

		} catch (Exception e) {
			fail();
		}

	}

	@Test
//Izena eta pasahitza DBan daude, erregistratua motakoa da eta baneatua dago baina ez da pasa baneoa kentzeko data.
	public void test6() {
		try {
//define paramaters
			log = "Joxe";
			password = "8";
			String nan = "52345678A";
			double dirua = 0.0;

			expectedUser = new Erregistratua(log, password, nan, dirua);
			((Erregistratua) expectedUser).setBanned(true);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Date d1 = sdf.parse("2024/07/15");
			((Erregistratua) expectedUser).setBanEndDate(d1);

//configure Mock
			Mockito.doReturn(expectedUser).when(dataAccess).isLogin(log, password);

//invoke System Under Test (sut)
			User obtained = sut.isLogin(log, password);

//verify the results
			Mockito.verify(dataAccess, Mockito.times(1)).isLogin(Mockito.any(String.class), Mockito.any(String.class));

			ArgumentCaptor<String> logCaptor = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> passwordCaptor = ArgumentCaptor.forClass(String.class);

			Mockito.verify(dataAccess, Mockito.times(1)).isLogin(logCaptor.capture(), passwordCaptor.capture());

			assertTrue(obtained != null);
			assertEquals(obtained.getUser(), expectedUser.getUser());
			assertEquals(obtained.getPassword(), expectedUser.getPassword());
			assertEquals(((Erregistratua) obtained).isBanned(), true);

			assertEquals(logCaptor.getValue(), log);
			assertEquals(passwordCaptor.getValue(), password);

		} catch (ParseException e) {
			e.printStackTrace();

		} catch (Exception e) {
			fail();
		}

	}

	@Test
//Izena eta pasahitza DBan daude, erregistratua motakoa da eta baneatua dago, gainera pasa da baneoa kentzeko data.
	public void test7() {
		try {
//define paramaters
			log = "Jon";
			password = "9";
			String nan = "62345678A";
			double dirua = 0.0;

			expectedUser = new Erregistratua(log, password, nan, dirua);
			((Erregistratua) expectedUser).setBanned(true);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Date d1 = sdf.parse("2022/07/15");
			((Erregistratua) expectedUser).setBanEndDate(d1);

//configure Mock
			Mockito.doReturn(mockedErreg).when(dataAccess).isLogin(log, password);
			Mockito.doReturn(false).when(mockedErreg).isBanned();
			Mockito.doReturn(log).when(mockedErreg).getUser();
			Mockito.doReturn(password).when(mockedErreg).getPassword();

//invoke System Under Test (sut)
			User obtained = sut.isLogin(log, password);

//verify the results
			Mockito.verify(dataAccess, Mockito.times(1)).isLogin(Mockito.any(String.class), Mockito.any(String.class));

			ArgumentCaptor<String> logCaptor = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> passwordCaptor = ArgumentCaptor.forClass(String.class);

			Mockito.verify(dataAccess, Mockito.times(1)).isLogin(logCaptor.capture(), passwordCaptor.capture());

			assertTrue(obtained != null);
			assertEquals(obtained.getUser(), expectedUser.getUser());
			assertEquals(obtained.getPassword(), expectedUser.getPassword());
			assertEquals(((Erregistratua) obtained).isBanned(), false);

			assertEquals(logCaptor.getValue(), log);
			assertEquals(passwordCaptor.getValue(), password);

		} catch (ParseException e) {
			e.printStackTrace();

		} catch (Exception e) {
			fail();
		}

	}

	@Test
//Izena null da. (log == null)
	public void test8() {
		try {
//define paramaters
			log = null;
			password = "1234";

//configure Mock
			Mockito.doThrow(new IllegalArgumentException()).when(dataAccess).isLogin(log, password);

//invoke System Under Test (sut)
			User obtained = sut.isLogin(log, password);

			fail();

		} catch (IllegalArgumentException e) {
			assertTrue(true);
		} catch (Exception e) {
			e.getMessage();
		}

	}

	@Test
//Pasahitza null da. (password == null)
	public void test9() {
		try {
//define paramaters
			log = "jose";
			password = null;

//configure Mock
			Mockito.doReturn(null).when(dataAccess).isLogin(log, password);

//invoke System Under Test (sut)
			User obtained = sut.isLogin(log, password);

			assertTrue(obtained == null);

		} catch (Exception e) {
			fail();
		}

	}

}