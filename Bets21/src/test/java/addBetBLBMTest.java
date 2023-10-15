import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Apustua;
import domain.Aukera;
import domain.Erregistratua;
import domain.Event;
import domain.Mugimendua;
import domain.Question;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class addBetBLBMTest {
     DataAccess dataAccess=Mockito.mock(DataAccess.class);
     Event mockedEvent=Mockito.mock(Event.class);
	
	@InjectMocks
	 BLFacade sut=new BLFacadeImplementation(dataAccess);
	 
	@Test
	public void test1() {
	    try {
	        // Datuak ezarri
	        Aukera auk = new Aukera(1,"Atletico",2);
	        Erregistratua erab = new Erregistratua("aa", "bb", "111111111A", 2);
	        Apustua apustua = new Apustua("aa", 1);

	        ArgumentCaptor<Aukera> aukCaptor = ArgumentCaptor.forClass(Aukera.class);
	        ArgumentCaptor<Apustua> apustuCaptor = ArgumentCaptor.forClass(Apustua.class);

	        // sut deia
	        sut.addBet(auk, apustua);

	        // Egiaztapenak
	        Mockito.verify(dataAccess, Mockito.times(1)).addBet(aukCaptor.capture(), apustuCaptor.capture());

	        // Corrección: Utiliza los métodos .getValue() para obtener los valores capturados
	        assertEquals(aukCaptor.getValue(), auk);
	        assertEquals(apustuCaptor.getValue(), apustua);
	    } catch (Exception a) {
	        a.printStackTrace();
	        fail();
	    }
	}
	
	//Ez du dirurik, beraz, ez du apusturik gehitu behar
	@Test
	public void test2() {
		try {
	        // Datuak ezarri
	        Aukera auk = new Aukera(1,"Atletico",2);
	        Erregistratua erab = new Erregistratua("aa", "bb", "111111111A", 2);
	        Apustua apustua = new Apustua("aa", 5);


	        // sut deia
	        sut.addBet(auk, apustua);

	        Mockito.verify(dataAccess, Mockito.times(1)).addBet(Mockito.any(Aukera.class), Mockito.any(Apustua.class));
	        assertEquals(2, erab.getKontuDirua(), 0.0001);
		}catch(Exception a) {
			fail();
		}
	}
	
	
	
	//Banneaturik dago, beraz, ez du apusturik gehitu behar
		@Test
		public void test3() {
			try {
		        // Datuak ezarri
		        Aukera auk = new Aukera(1,"Atletico",2);
		        Erregistratua erab = new Erregistratua("aa", "bb", "111111111A", 2);
		        erab.setBanned(true);
		        Apustua apustua = new Apustua("aa", 5);


		        // sut deia
		        sut.addBet(auk, apustua);

		        Mockito.verify(dataAccess, Mockito.times(1)).addBet(Mockito.any(Aukera.class), Mockito.any(Apustua.class));
		        assertEquals(2, erab.getKontuDirua(), 0.0001);
			}catch(Exception a) {
				fail();
			}
		}
	
		//Erabiltzailea ez dago erregistraturik, deia egingo da baina ez da gehituko apusturik
		@Test
		public void test4() {
		    try {
		        // Datuak ezarri
		        Aukera auk = new Aukera(1, "Atletico", 2);
		        Apustua apustua = new Apustua("aa", 5);

		        
		        // sut deia
		        sut.addBet(auk, apustua);

		        // Egiaztapena
		        Mockito.verify(dataAccess, Mockito.times(1)).addBet(Mockito.any(Aukera.class), Mockito.any(Apustua.class));
		    } catch (Exception e) {
		        e.printStackTrace();
		        fail();
		    }
		}

	
		@Test
		public void test5() {
		    try {
		        // Datuak ezarri
		        Aukera auk = new Aukera(1, "Atletico", 2);
		        Erregistratua erab = new Erregistratua("aa", "bb", "111111111A", 2);
		        Aukera auk2 = new Aukera(2, "Erreala", 2);
		        Apustua apustuak = new Apustua("aa", 5);
		        ArrayList<Aukera> lista = new ArrayList<Aukera>();
				lista.add(auk);
				lista.add(auk2);
				apustuak.setErantzunak(lista);

		        // sut deia
		        sut.addBet(auk, apustuak);

		        // addBet metodoa behin bakarrik exekutatu
		        Mockito.verify(dataAccess, Mockito.times(1)).addBet(Mockito.any(Aukera.class), Mockito.any(Apustua.class));

		        // Jasoriko parametroak gorde
		        ArgumentCaptor<Aukera> aukCaptor = ArgumentCaptor.forClass(Aukera.class);
		        ArgumentCaptor<Apustua> apustuCaptor = ArgumentCaptor.forClass(Apustua.class);

		        // Parametroak egiaztatu
		        Mockito.verify(dataAccess, Mockito.times(1)).addBet(aukCaptor.capture(), apustuCaptor.capture());


		    } catch (Exception e) {
		        e.printStackTrace();
		        fail();
		    }
		}







}


	