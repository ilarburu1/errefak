import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;

import org.junit.Test;

import dataAccess.DataAccess;
import domain.Apustua;
import domain.Aukera;
import domain.Erregistratua;
import domain.Event;
import test.dataAccess.TestDataAccess;

public class addBetDABTest {

	//sut:system under test
		 static DataAccess sut=new DataAccess();
		 
		 //additional operations needed to execute the test 
		 static TestDataAccess testDA=new TestDataAccess();

		private Event ev;
		
		@Test
		//Apustua gehitu zaio DB-an erab erabiltzaileari.
		public void test() {
			Erregistratua erab = new Erregistratua("aa", "bb", "111111111A", 5); 
			Aukera auk = new Aukera(1,"Atletico",2);
			try {
				testDA.open();
				testDA.persistErreg((Erregistratua) erab);
				testDA.persistAuk((Aukera) auk);
				testDA.close();
				Apustua apustua = new Apustua("aa", 2);
				sut.addBet(auk, apustua);
				assertTrue(true);
			}catch(Exception a) {
				 a.printStackTrace();
				fail("Not yet implemented");
			}finally {
				testDA.open();
				testDA.removeErreg(erab);
				testDA.removeAuk(auk);
				testDA.close();
			}
		}
		
		@Test
		//DB-n apustua ez da gehitzen. Ez du dirurik.
		public void test2() {
			Erregistratua erab = new Erregistratua("aaa", "bb", "1111111111A", 2); 
			Aukera auk = new Aukera(1,"Atletico",2);
			try {
				testDA.open();
				testDA.persistErreg(erab);
				testDA.persistAuk(auk);
				testDA.close();
				Apustua apustua = new Apustua("aaa", 5);
				sut.addBet(auk, apustua);
				if(erab.getApustuak().isEmpty()) {
					assertTrue(true);
				}else {
					fail("Ez du apusturik izan behar");
				}
			}catch(Exception a) {
				a.printStackTrace();
				assertTrue(true);
			}finally {
				testDA.open();
				testDA.removeErregistratua(erab);
				testDA.removeAuk(auk);
				testDA.close();
			}
		}
		
		
		@Test
		//DB-n apustua ez da gehitzen. Banneaturik dago.
		public void test3() {
			Erregistratua erab = new Erregistratua("aaa", "bb", "1111111111A", 5); 
			Aukera auk = new Aukera(1,"Atletico",2);
			erab.setBanned(true);
			try {
				testDA.open();
				testDA.persistErreg(erab);
				testDA.persistAuk(auk);
				testDA.close();
				Apustua apustua = new Apustua("aaa", 2);
				sut.addBet(auk, apustua);
				if(erab.getApustuak().isEmpty()) {
					assertTrue(true);
				}else {
					fail("Ez du apusturik izan behar");
				}
			}catch(Exception a) {
				assertTrue(true);
			}finally {
				testDA.open();
				testDA.removeErregistratua(erab);
				testDA.removeAuk(auk);
				testDA.close();
			}
		}
		
		
		
		@Test
		//DB-n aldaketarik ez
		public void test4() {
			Erregistratua erab = new Erregistratua("aaaaa", "bb", "1111111A", 2);
			Aukera auk = new Aukera(1,"Atletico",2);
			Aukera auk2 = new Aukera(2, "Erreala", 2);
			try {
				testDA.open();
				testDA.persistAuk(auk);
				testDA.close();
				Apustua apustua = new Apustua("aaaaa", 1);
				ArrayList<Aukera> lista = new ArrayList<Aukera>();
				lista.add(auk);
				lista.add(auk2);
				apustua.setErantzunak(lista);
				auk.setAzkenaDa(false);
				sut.addBet(auk, apustua);
				fail("Ez du jarraitu behar");
			}catch(RollbackException a) {
				a.printStackTrace();
				assertTrue(true);
			}catch(NullPointerException b){
				assertTrue(true);
			}finally {
				testDA.open();
				testDA.removeAuk(auk);
				testDA.close();
			}
		}
		
		
		@Test
		//DB-n apustu ainzkoitza gehitzen da. Jarraitzailerik ez du
		public void test5() {
			Erregistratua erab = new Erregistratua("aaaaaa", "bb", "1111111A", 2);
			Aukera auk = new Aukera(1,"Atletico",2);
			try {
				testDA.open();
				testDA.persistErreg(erab);
				testDA.close();
				Apustua apustua = new Apustua("aaaaaa", 1);
				ArrayList<Aukera> lista = new ArrayList<Aukera>();
				lista.add(auk);
				apustua.setErantzunak(lista);
				apustua.setErantzunKop(2);
				auk.setAzkenaDa(true);
				sut.addBet(auk, apustua);
				fail();
			}catch(PersistenceException a) {
				assertTrue(true);
			}finally {
				testDA.open();
				testDA.removeErregistratua(erab);
				testDA.close();
			}
		}
		
		
		
		
		
		
		
	
}