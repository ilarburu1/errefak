package test.dataAccess;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import configuration.ConfigXML;
import domain.Admin;
import domain.Aukera;
import domain.Editorea;
import domain.Erregistratua;
import domain.Event;
import domain.Question;
import domain.User;

public class TestDataAccess {
	protected  EntityManager  db;
	protected  EntityManagerFactory emf;

	ConfigXML  c=ConfigXML.getInstance();


	public TestDataAccess()  {
		
		System.out.println("Creating TestDataAccess instance");

		open();
		
	}

	
	public void open(){
		
		System.out.println("Opening TestDataAccess instance ");

		String fileName=c.getDbFilename();
		
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
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}

	public boolean removeEvent(Event ev) {
		System.out.println(">> DataAccessTest: removeEvent");
		Event e = db.find(Event.class, ev.getEventNumber());
		if (e!=null) {
			db.getTransaction().begin();
			db.remove(e);
			db.getTransaction().commit();
			return true;
		} else 
		return false;
    }
		
		public Event addEventWithQuestion(String desc, Date d, String question, float qty) {
			System.out.println(">> DataAccessTest: addEvent");
			Event ev=null;
				db.getTransaction().begin();
				try {
				    ev=new Event(desc,d);
				    ev.addQuestion(question, qty);
					db.persist(ev);
					db.getTransaction().commit();
				}
				catch (Exception e){
					e.printStackTrace();
				}
				return ev;
	    }
		public boolean existQuestion(Event ev,Question q) {
			System.out.println(">> DataAccessTest: existQuestion");
			Event e = db.find(Event.class, ev.getEventNumber());
			if (e!=null) {
				return e.DoesQuestionExists(q.getQuestion());
			} else 
			return false;
		}
		
		
		public void persistErreg(Erregistratua a) {
			db.getTransaction().begin();
			try {
				db.persist(a);
				db.getTransaction().commit();
			}catch (Exception e){
				e.printStackTrace();
				 if (db.getTransaction().isActive()) {
			            db.getTransaction().rollback();
			        }
			}
		}
		
		public void persistAdmin(Admin a) {
			db.getTransaction().begin();
			try {
				db.persist(a);
				System.out.println("hona bai");
				db.getTransaction().commit();
			}catch (Exception e){
				e.printStackTrace();
				 if (db.getTransaction().isActive()) {
			            db.getTransaction().rollback();
			        }
			}
		}
		
		public void persistEditor(Editorea a) {
			db.getTransaction().begin();
			try {
				db.persist(a);
				System.out.println("hona bai");
				db.getTransaction().commit();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		
		
		public void persistAuk(Aukera a) {
			db.getTransaction().begin();
			try {
				db.persist(a);
				db.getTransaction().commit();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		
		public void persistEvent(Event ev) {
			db.getTransaction().begin();
			try {
				db.persist(ev);
				db.getTransaction().commit();
			}catch (Exception e){
				e.printStackTrace();
			}
		}


		public boolean removeErreg(Erregistratua erab) {
			Erregistratua e = db.find(Erregistratua.class, erab.getUser());
			if(e!=null) {
				db.getTransaction().begin();
				db.remove(e);
				db.getTransaction().commit();
				return true;
			}else {
				return false;
			}
		}
		
		public boolean removeUser(User erab) {
			User e = db.find(User.class, erab.getUser());
			if(e!=null) {
				db.getTransaction().begin();
				db.remove(e);
				db.getTransaction().commit();
				return true;
			}else {
				return false;
			}
		}
		
		public boolean removeAdmin(Admin erab) {
			Admin e = db.find(Admin.class, erab);
			if(e!=null) {
				db.getTransaction().begin();
				db.remove(e);
				db.getTransaction().commit();
				this.removeUser(erab);
				return true;
			}else {
				return false;
			}
		}
		
		public boolean removeEditor(Editorea erab) {
			Editorea e = db.find(Editorea.class, erab);
			if(e!=null) {
				db.getTransaction().begin();
				db.remove(e);
				db.getTransaction().commit();
				this.removeUser(erab);
				
				return true;
			}else {
				return false;
			}
		}
		
		public boolean removeErregistratua(Erregistratua erab) {
			Erregistratua e = db.find(Erregistratua.class, erab);
			if(e!=null) {
				db.getTransaction().begin();
				db.remove(e);
				db.getTransaction().commit();
				this.removeUser(erab);
				return true;
			}else {
				return false;
			}
		}
		
		public boolean removeAuk(Aukera auk) {
			Aukera a = db.find(Aukera.class, auk);
			if(a!=null) {
				db.getTransaction().begin();
				db.remove(a);
				db.getTransaction().commit();
				return true;
			}else {
				return false;
			}
		}
		
		public boolean removeQuestion(Question q) {
			System.out.println(">> DataAccessTest: removeQuestion");
			Question e = db.find(Question.class, q);
			if (e!=null) {
				db.getTransaction().begin();
				db.remove(e);
				db.getTransaction().commit();
				return true;
			} else 
			return false;
	    }
		
		public boolean existErreg(Erregistratua erreg) {
			System.out.println(">> DataAccessTest: existErreg");
			Erregistratua e = db.find(Erregistratua.class, erreg.getUser());
			if (e!=null) {
				return true;
			} else 
			return false;
		}
		
		public Erregistratua addErregistratua(String log,String password,String nan,Double dirua) {
			System.out.println(">> DataAccessTest: addErregistratua");
			Erregistratua erreg=null;
				db.getTransaction().begin();
				try {
				    erreg=new Erregistratua(log,password,nan,dirua);
					db.persist(erreg);
					db.getTransaction().commit();
				}
				catch (Exception e){
					e.printStackTrace();
				}
				return erreg;
	    }
		
		public Admin addAdmin(String log,String password) {
			System.out.println(">> DataAccessTest: addAdmin");
			Admin a=null;
				db.getTransaction().begin();
				try {
				    a=new Admin(log,password);
					db.persist(a);
					db.getTransaction().commit();
				}
				catch (Exception e){
					e.printStackTrace();
				}
				return a;
	    }
		

		public boolean existAdmin(Admin user) {
			System.out.println(">> DataAccessTest: existAdmin");
			Admin adm = db.find(Admin.class, user.getUser());
			if (adm!=null) {
				return true;
			} else {
				return false;
			}
		}
		
		public Editorea addEditorea(String log,String password) {
			System.out.println(">> DataAccessTest: addEditorea");
			Editorea ed=null;
				db.getTransaction().begin();
				try {
				    ed=new Editorea(log,password);
					db.persist(ed);
					db.getTransaction().commit();
				}
				catch (Exception e){
					e.printStackTrace();
				}
				return ed;
	    }
		

		public boolean existEditorea(Editorea user) {
			System.out.println(">> DataAccessTest: existEditorea");
			Editorea e = db.find(Editorea.class, user.getUser());
			if (e!=null) {
				return true;
			} else {
				return false;
			}
		}
		
		public Event addEvent(int zenb,String des,Date d) {
			System.out.println(">> DataAccessTest: addEditorea");
			Event ev=null;
				db.getTransaction().begin();
				try {
				    ev=new Event(zenb,des,d);
					db.persist(ev);
					db.getTransaction().commit();
				}
				catch (Exception e){
					e.printStackTrace();
				}
				return ev;
	    }
		
		public boolean existEvent(Event e) {
			System.out.println(">> DataAccessTest: existEvent");
			Event event = db.find(Event.class, e.getEventNumber());
			if (event!=null) {
				return true;
			} else {
				return false;
			}
		}

		    public <T> T find(Class<T> entityClass, Object primaryKey) {
		        return db.find(entityClass, primaryKey);
		    }

		    public void persist(Object entity) {
		        db.persist(entity);
		    }

		    public void beginTransaction() {
		        EntityTransaction transaction = db.getTransaction();
		        transaction.begin();
		    }

		    public void commitTransaction() {
		        EntityTransaction transaction = db.getTransaction();
		        transaction.commit();
		    }
		

}