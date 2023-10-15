package domain;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Editorea extends User{
	
	public Editorea() {
		super();
	}
	
	public Editorea(String user,String password) {
		super(user,password);
	}

	public void kuotaSortu(Aukera option, int i) {
		
	}
	
}