package dataAccess;

//hello
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Admin;
import domain.Apustua;
import domain.ApustuaInfo;
import domain.Aukera;
import domain.Editorea;
import domain.Erregistratua;
import domain.Event;
import domain.Mezua;
import domain.MezuaInfo;
import domain.Mugimendua;
import domain.Question;
import domain.User;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess  {
	protected static EntityManager  db;
	protected static EntityManagerFactory emf;


	ConfigXML c=ConfigXML.getInstance();
	

     public DataAccess(boolean initializeMode)  {
		
    	System.out.println("Creating DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

 		open(initializeMode);
	}

	public DataAccess()  {	
		this(false);
	}
	
	
	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){
		
		db.getTransaction().begin();
		try {

			
		   Calendar today = Calendar.getInstance();
		   
		   int month=today.get(Calendar.MONTH);
		   month+=1;
		   int year=today.get(Calendar.YEAR);
		   if (month==12) { month=0; year+=1;}  
	    
			Event ev1=new Event(1, "Atlético-Athletic", UtilDate.newDate(year,month,17));
			Event ev2=new Event(2, "Eibar-Barcelona", UtilDate.newDate(year,month,17));
			Event ev3=new Event(3, "Getafe-Celta", UtilDate.newDate(year,month,17));
			Event ev4=new Event(4, "Alavés-Deportivo", UtilDate.newDate(year,month,17));
			Event ev5=new Event(5, "Español-Villareal", UtilDate.newDate(year,month,17));
			Event ev6=new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year,month,17));
			Event ev7=new Event(7, "Malaga-Valencia", UtilDate.newDate(year,month,17));
			Event ev8=new Event(8, "Girona-Leganés", UtilDate.newDate(year,month,17));
			Event ev9=new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year,month,17));
			Event ev10=new Event(10, "Betis-Real Madrid", UtilDate.newDate(year,month,17));

			Event ev11=new Event(11, "Atletico-Athletic", UtilDate.newDate(year,month,1));
			Event ev12=new Event(12, "Eibar-Barcelona", UtilDate.newDate(year,month,1));
			Event ev13=new Event(13, "Getafe-Celta", UtilDate.newDate(year,month,1));
			Event ev14=new Event(14, "Alavés-Deportivo", UtilDate.newDate(year,month,1));
			Event ev15=new Event(15, "Español-Villareal", UtilDate.newDate(year,month,1));
			Event ev16=new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year,month,1));
			

			Event ev17=new Event(17, "Málaga-Valencia", UtilDate.newDate(year,month+1,28));
			Event ev18=new Event(18, "Girona-Leganés", UtilDate.newDate(year,month+1,28));
			Event ev19=new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year,month+1,28));
			Event ev20=new Event(20, "Betis-Real Madrid", UtilDate.newDate(year,month+1,28));
			
			Question q1;
			Question q3;
			Question q4;
			Question q5;
					
			String irabazlea = "¿Quién ganará el partido?";
			String winner = "Who will win the match?";
			if (Locale.getDefault().equals(new Locale("es"))) {
				q1=ev1.addQuestion(irabazlea,1);
				ev1.addQuestion("¿Quién meterá el primer gol?",2);
				q3=ev11.addQuestion(irabazlea,1);
				q4=ev11.addQuestion("¿Cuántos goles se marcarán?",2);
				q5=ev17.addQuestion(irabazlea,1);
				ev17.addQuestion("¿Habrá goles en la primera parte?",2);
			}
			else if (Locale.getDefault().equals(new Locale("en"))) {
				q1=ev1.addQuestion(winner,1);
				ev1.addQuestion(winner,2);
				q3=ev11.addQuestion(winner,1);
				q4=ev11.addQuestion("How many goals will be scored in the match?",2);
				q5=ev17.addQuestion("Who will win the match?",1);
				ev17.addQuestion("Will there be goals in the first half?",2);
			}			
			else {
				String irabazlea2 = "Zeinek irabaziko du partidua?";
				q1=ev1.addQuestion(irabazlea2,1);
				ev1.addQuestion("Zeinek sartuko du lehenengo gola?",2);
				q3=ev11.addQuestion(irabazlea2,1);
				q4=ev11.addQuestion("Zenbat gol sartuko dira?",2);
				q5=ev17.addQuestion(irabazlea2,1);
				ev17.addQuestion("Golak sartuko dira lehenengo zatian?",2);
				
			}
			
			
			Aukera a1 = new Aukera(1,"Atletico",2);
			Aukera a2 = new Aukera(2,"Athletic",2);
			Aukera aEmpate = new Aukera(7,"Empate",4);
			Aukera a3 = new Aukera(3,"Atletico",2);
			Aukera a4 = new Aukera(4,"Athletic",2);
			Aukera a5 = new Aukera(5,"Malaga",4);
			Aukera a6 = new Aukera(6,"Valencia",5);
			Aukera a8 = new Aukera(8,"2",4);
			Aukera a9 = new Aukera(9,"3",5);
			Aukera a10 = new Aukera(10,"4",6);
			
			q1.newAukera(a1);
			q1.newAukera(a2);
			q3.newAukera(a3);
			q3.newAukera(a4);
			q3.newAukera(aEmpate);
			q4.newAukera(a8);
			q4.newAukera(a9);
			q4.newAukera(a10);
			q5.newAukera(a5);
			q5.newAukera(a6);
			
			
			db.persist(a1);
			db.persist(a2);
			db.persist(a3);
			db.persist(a4);
			db.persist(a5);
			db.persist(a6);
			db.persist(aEmpate);
			db.persist(a8);
			db.persist(a9);
			db.persist(a10);
			
			
			db.persist(q1);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
	
	        
			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);	
			
			User u = new Erregistratua("intza","1","73262157G",200);
			db.persist(u);
			
			User u2 = new Erregistratua("ibai","1","12345678K",100);
	        db.persist(u2);
	        
			User admin = new Admin("adminAmets","1");
			db.persist(admin);

			User editor1 = new Editorea("editorErik","1");
			db.persist(editor1);
			
			
			
			
			db.getTransaction().commit();
			System.out.println("Db initialized");   
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws  QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= "+event+" question= "+question+" betMinimum="+betMinimum);
			
			Event ev = db.find(Event.class, event.getEventNumber());
			
			if (ev.DoesQuestionExists(question)) throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));
			
			db.getTransaction().begin();
			Question q = ev.addQuestion(question, betMinimum);
			//db.persist(q);
			db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
							// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
			db.getTransaction().commit();
			return q;
		
	}
	
	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();	
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1",Event.class);   
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
	 	 for (Event ev:events){
	 	   System.out.println(ev.toString());		 
		   res.add(ev);
		  }
	 	return res;
	}
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public Vector<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();	
		
		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);
				
		
		TypedQuery<Date> query = db.createQuery("SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN ?1 and ?2",Date.class);   
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
	 	 for (Date d:dates){
	 	   System.out.println(d.toString());		 
		   res.add(d);
		  }
	 	return res;
	}
	

public void open(boolean initializeMode){
		
		System.out.println("Opening DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		String fileName=c.getDbFilename();
		if (initializeMode) {
			fileName=fileName+";drop";
			System.out.println("Deleting the DataBase");
		}
		
		if (c.isDatabaseLocal()) {
			  emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			  db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			  properties.put("javax.persistence.jdbc.user", c.getUser());
			  properties.put("javax.persistence.jdbc.password", c.getPassword());

			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			  db = emf.createEntityManager();
    	   }
		
	}


public boolean existQuestion(Event event, String question) {
	System.out.println(">> DataAccess: existQuestion=> event= "+event+" question= "+question);
	Event ev = db.find(Event.class, event.getEventNumber());
	return ev.DoesQuestionExists(question);
	
}
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}
	
	
	//KLASEAN EGINDAKOA*************************************************************************
	
	
	

public User isLogin(String log, String password) {
	    
		
		User user = db.find(domain.Erregistratua.class, log );
		db.getTransaction().begin();
		if(user!=null){
			if( ((Erregistratua)user).isBanned()) {
				Date data = new Date();
				if( ((Erregistratua)user).getBanEndDate().compareTo(data)<=0) {
					((Erregistratua)user).setBanned(false);
				}
			}
			db.getTransaction().commit();
			return user;
		}else {
			user = db.find(domain.Editorea.class, log);
			if(user!=null){
				db.getTransaction().commit();
				return user;
			}else {
				user = db.find(domain.Admin.class, log);
			}
		}
		db.getTransaction().commit();
		return user;
	}
	
	
     public void Register(String log, String password, String NAN, int kontuDirua) {

    	db.getTransaction().begin();

 			Erregistratua erregistratua = new Erregistratua(log,password,NAN,kontuDirua);
 			Date data= new Date();
 			String deskribapena="Kontua sortu. Dirua: +";
 			Mugimendua m= new Mugimendua(data,kontuDirua,deskribapena);
 			db.persist(m);
 			erregistratua.addMovement(m);
 		    db.persist(erregistratua);
 		    
 		db.getTransaction().commit();
 
 		
       }
	
     
     
     
     public boolean UserExistitzenDa(String user) {
    	 
    	 db.getTransaction().begin();
    	 boolean emaitza;
    	 User u = db.find(domain.Erregistratua.class, user);
 		if(u!=null) {
 			emaitza=true;
 		}else {
 			emaitza=false;	 
 		}
    	 db.getTransaction().commit();
    	 return emaitza;
     }
     

     public Question findQuestion(Event event, String galdera) {
 		Event event1 = db.find(domain.Event.class, event);
 		Vector<Question> galderak = event1.getQuestions();
 		if(galderak.isEmpty()) {
 			return null;
 		}else{
 			for(Question question:galderak) {
 				if(question.getQuestion().equals(galdera)) return question;
 			}
 		}
 		return null;
 	}
     
     public void newKuota(Question q, Aukera a) {
 		Question g = db.find(domain.Question.class, q);
 		db.getTransaction().begin();
 		g.newAukera(a);
 		db.persist(a);
 		db.getTransaction().commit();
 	}
     
     
     public Vector<Aukera> getAukerak(Question q) {
 		Question g = db.find(domain.Question.class, q);
 		return g.getAukerak();
 	}
     
     
     public void createEvent(Integer eventNumber, String description,Date eventDate) {
    	 db.getTransaction().begin();
    	 Event event = new Event(eventNumber,description,eventDate);
    	 db.persist(event);	    
    	 db.getTransaction().commit();
  	}

	public void addMoney(String log, double diruKop) {
		Erregistratua erreg=db.find(domain.Erregistratua.class, log);
		db.getTransaction().begin();
		erreg.setKontuDirua(erreg.getKontuDirua()+ diruKop);
		Date data= new Date();
		String deskribapena="Dirua sartu. Dirua: +";
		Mugimendua m= new Mugimendua(data,diruKop,deskribapena);
		db.persist(m);
		erreg.addMovement(m);
		db.getTransaction().commit();
	}
	
	public void addBet(Aukera hautatutakoAukera, Apustua apustu) {
		Erregistratua erreg = db.find(domain.Erregistratua.class, apustu.getLog());
		db.getTransaction().begin();
		if(apustu.getErantzunKop()<=1) {
			apustuBakarra(apustu, erreg);	
		}else { //apustu Anitza da
			if(hautatutakoAukera.isAzkenaDa() == true) {
				apustuAnitza(apustu, erreg);
			}
		}
		db.getTransaction().commit();
	}

	private void apustuAnitza(Apustua apustu, Erregistratua erreg) {
		Date data=new Date();
		double zenbatekoa=apustu.getZenbatekoa();
		String deskribapena="Apustu Anitza egin. Dirua: -";
		Mugimendua m=new Mugimendua(data,zenbatekoa,deskribapena);
		db.persist(m);
		erreg.addMovement(m);
		erreg.setKontuDirua(erreg.getKontuDirua() - apustu.getZenbatekoa());
		erreg.addApustua(apustu);
		for(String jarraitzailea: erreg.getJarraitzaileak()) {
			ApustuaInfo a2 = new ApustuaInfo(apustu, data, zenbatekoa, m, jarraitzailea);
			jarraitzaileApustuAnitza(a2);
		}
		db.persist(apustu);
	}

	private void apustuBakarra(Apustua apustu, Erregistratua erreg) {
		Date data=new Date();
		double zenbatekoa=apustu.getZenbatekoa();
		String deskribapena="Apustua egin. Dirua: -";
		Mugimendua m=new Mugimendua(data,zenbatekoa,deskribapena);
		db.persist(m);
		erreg.addMovement(m);
		erreg.setKontuDirua(erreg.getKontuDirua() - apustu.getZenbatekoa());
		erreg.addApustua(apustu);
		for(String jarraitzailea: erreg.getJarraitzaileak()) {
			ApustuaInfo a = new ApustuaInfo(apustu, data, zenbatekoa, m, jarraitzailea);
			jarraitzaileenApustuBakarra(a);
		}
		db.persist(apustu);
	}

	private void jarraitzaileApustuAnitza(ApustuaInfo a) {
		Erregistratua jarraitzaileaErreg = db.find(domain.Erregistratua.class, a.getJarraitzailea());
		if(jarraitzaileaErreg.getKontuDirua()>=a.getZenbatekoa() && jarraitzaileaErreg.isBanned()==false) {
		jarraitzaileaErreg.addMovement(a.getMove());
		jarraitzaileaErreg.setKontuDirua(jarraitzaileaErreg.getKontuDirua() - a.getApustua().getZenbatekoa());
		Apustua jarraitzaileApustua = new Apustua((String)a.getJarraitzailea(),a.getApustua().getZenbatekoa());
		jarraitzaileApustua.setErantzunak(a.getApustua().getErantzunak());
		jarraitzaileApustua.setErantzunKop(a.getApustua().getErantzunak().size());
		jarraitzaileaErreg.addApustua(jarraitzaileApustua);
		}else {
			String deskribapena2=("Ezin izan da Apustua kopiatu. ");
			Mugimendua m2=new Mugimendua(a.getData(),0,deskribapena2);
			jarraitzaileaErreg.addMovement(m2);
		}
	}

	private void jarraitzaileenApustuBakarra(ApustuaInfo a) {
		Erregistratua jarraitzaileaErreg = db.find(domain.Erregistratua.class, a.getJarraitzailea());
		if(jarraitzaileaErreg.getKontuDirua()>=a.getZenbatekoa() && jarraitzaileaErreg.isBanned()==false) {
			jarraitzaileaErreg.addMovement(a.getMove());
			jarraitzaileaErreg.setKontuDirua(jarraitzaileaErreg.getKontuDirua() - a.getApustua().getZenbatekoa());
			Apustua jarraitzaileApustua = new Apustua((String) a.getJarraitzailea(),(double)a.getApustua().getZenbatekoa());
			jarraitzaileApustua.setErantzunak(a.getApustua().getErantzunak());
			jarraitzaileApustua.setErantzunKop(1);
			jarraitzaileaErreg.addApustua(jarraitzaileApustua);
		}else {
			String deskribapena2=("Ezin izan da Apustua kopiatu. ");
			Mugimendua m2=new Mugimendua(a.getData(),0,deskribapena2);
			jarraitzaileaErreg.addMovement(m2);
		}
	}

	
	public Erregistratua erregistratuaItzuli(String log) {
		Erregistratua erreg=db.find(domain.Erregistratua.class, log);
		System.out.println(erreg);
		return erreg;
	}
	
	public void emaitzaGehitu(Question q, String emaitza) {
		db.getTransaction().begin();
		System.out.println(">> DataAccess: getAllBets");
		TypedQuery<Apustua> query = db.createQuery("SELECT ap FROM Apustua ap",Apustua.class);   
		List<Apustua> apustuLista = query.getResultList();
		this.apustuakAztertu(apustuLista, q, emaitza);
		System.out.println(">> DataAccess: getAllErreg");
		TypedQuery<Erregistratua> query2 = db.createQuery("SELECT erreg FROM Erregistratua erreg",Erregistratua.class);   
		List<Erregistratua> ErregistratuLista = query2.getResultList();
		for(Erregistratua erreg:ErregistratuLista) {
			for(Apustua apos:erreg.getApustuak()) {
				if(apos.getErantzunKop()==1 && apos.isAsmatua()==true && apos.isBukatua()==false) {
					erreg.setKontuDirua(erreg.getKontuDirua()+ (apos.getZenbatekoa()*apos.getErantzunak().get(0).getKuota()));
					Date data=new Date();
					String deskribapena="Apustua irabazi. Dirua= +";
					double zenbatekoa=apos.getZenbatekoa()*apos.getErantzunak().get(0).getKuota();
					Mugimendua m=new Mugimendua(data,zenbatekoa,deskribapena);
					db.persist(m);
					erreg.addMovement(m);
					apos.setBukatua(true);
				}else if(apos.getErantzunKop()>1 && apos.isBukatua()==false) { //apustu Anitza da
					this.emaitzaGehituApustuAnitza(apos, erreg);
				}//else if
			}//for
		}//for
		db.getTransaction().commit();
	}

	public void apustuakAztertu(List<Apustua> apustuLista, Question q, String emaitza) {
		for(Apustua ap:apustuLista) {
			if(ap.isBukatua()==false) {
				for(Aukera auk:ap.getErantzunak()) {
					apustukoAukerakAztertu(q, emaitza, ap, auk);
			      }
			}	
		}
	}

	private void apustukoAukerakAztertu(Question q, String emaitza, Apustua ap, Aukera auk) {
		for(Aukera auke:q.getAukerak()) {
			if(auke.getAukeraID()==auk.getAukeraID() &&  auk.getAukeraIzena().equals(emaitza)) {
				auk.setEgoera("win");
			    if(ap.getErantzunKop()==1) {
				  ap.setAsmatua(true);
			    }
		    }
		  }
	}
	
	
	public void emaitzaGehituApustuAnitza(Apustua apos, Erregistratua erreg) {
		boolean denakAsmatuak=true;
		float biderkatzaileTotala=0;
		for(Aukera aukera:apos.getErantzunak()) {
			if(aukera.getEgoera().equals("win")==false) {
				denakAsmatuak=false;
			}
			biderkatzaileTotala = biderkatzaileTotala + aukera.getKuota();
		 }
		if(denakAsmatuak==true) {
			apos.setAsmatua(true);
		}if(apos.isAsmatua()==true && apos.isBukatua()==false) {
			erreg.setKontuDirua(erreg.getKontuDirua() + (apos.getZenbatekoa()* biderkatzaileTotala));
			Date data=new Date();
			double zenbatekoa=apos.getZenbatekoa()* biderkatzaileTotala;
			String deskribapena="apustu Anitza irabazi. Dirua=+";
			Mugimendua m=new Mugimendua(data,zenbatekoa,deskribapena);
			db.persist(m);
			erreg.addMovement(m);
			apos.setBukatua(true);
		} //if
	}
	
	
	public User userItzuli(String log) {
		db.getTransaction().begin();
		User user = db.find(domain.Erregistratua.class, log );
		
		if(user!=null){
			return user;
		}else {
			user = db.find(domain.Editorea.class, log);
			if(user!=null){
				return user;
			}else {
				user = db.find(domain.Admin.class, log);
			}
		}

		return null;
	}
	
	String adminAmets="adminAmets";
	public Admin adminaItzuli() {
		db.getTransaction().begin();
		Admin a=db.find(domain.Admin.class,adminAmets);
		db.getTransaction().commit();
		return a;
	}
	
	public void gehituJarraitzailea(Erregistratua bilatua,Erregistratua unekoa) {
		db.getTransaction().begin();
		Erregistratua gehitzeko=db.find(domain.Erregistratua.class, bilatua.getUser());
		Erregistratua momentukoa=db.find(domain.Erregistratua.class, unekoa.getUser());
		gehitzeko.addJarraitzailea(momentukoa.getUser());
		db.persist(gehitzeko);
		db.getTransaction().commit();
	}
	
	public void ezabatuJarraitzailea(Erregistratua bilatua,Erregistratua unekoa) {
		db.getTransaction().begin();
		Erregistratua ezabatzeko=db.find(domain.Erregistratua.class, bilatua.getUser());
		Erregistratua momentukoa=db.find(domain.Erregistratua.class, unekoa.getUser());
		ezabatzeko.removeJarraitzailea(momentukoa.getUser());
		db.persist(ezabatzeko);
		db.getTransaction().commit();
	}
	
	public void mezuaBidaliAdminari(Date data,Erregistratua bidaltzailea,String mezua) {
		db.getTransaction().begin();
		Erregistratua erreg=db.find(domain.Erregistratua.class, bidaltzailea.getUser());
		Admin a=db.find(domain.Admin.class,adminAmets);
		Mezua m=new Mezua(data,erreg.getUser(),mezua);
		a.addMezua(m);
		db.persist(m);
		db.getTransaction().commit();
	}
	
	
	
	public void mezuaErantzunErregistratuari(MezuaInfo m) {
		db.getTransaction().begin();
		System.out.println(m.getBidaltzailea());
		Erregistratua erreg=db.find(domain.Erregistratua.class, m.getBidaltzailea());
		Mezua mezua=new Mezua(m.getData(),m.getAdmin().getUser(),m.getMezua());
		erreg.addMezua(mezua);
		db.persist(m);
		db.getTransaction().commit();
	}
	
	public Vector<Event> getAllEvents() {
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();	
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev",Event.class);   
		List<Event> events = query.getResultList();
	 	 for (Event ev:events){
	 	   System.out.println(ev.toString());		 
		   res.add(ev);
		  }
	 	return res;
	}
	
	public void duplicateEvent(int eventNumBerria, Event event1,Date eventDate) {
   	 db.getTransaction().begin();
   	 Event event=db.find(domain.Event.class, event1.getEventNumber());
   	 Event berria=new Event(eventNumBerria,event.getDescription(),eventDate);
   	 berria.setQuestions(event.getQuestions());
   	 db.persist(berria);	    
   	 db.getTransaction().commit();
 	}
     
	
	public void banUser(String erabiltzailea, Date blokeoBukaeraData) {
	   	 db.getTransaction().begin();
	   	 Erregistratua erreg=db.find(Erregistratua.class, erabiltzailea);
	   	 erreg.setBanned(true);
	   	 erreg.setBanEndDate(blokeoBukaeraData);	    
	   	 db.getTransaction().commit();
	 	}

	public Object find(Class<Erregistratua> class1, String log) {
		Erregistratua a = (Erregistratua) db.find(Erregistratua.class, log);
		return a;
	}
	
	
	
	
}