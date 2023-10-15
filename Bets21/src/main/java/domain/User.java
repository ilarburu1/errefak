package domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSeeAlso;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Erregistratua.class,Admin.class,Editorea.class})
public abstract class User {
	
	@Id
	@XmlID
	private String user;
	private String password;
	
	public User() {
		super();
	}
	
	public User(String user, String password) {
		this.user = user;
		this.password = password;
	}
	
	public String getPassword(){
		return password;
	}
	
	public String getUser() {
		return user;
	}

}
