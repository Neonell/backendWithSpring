package ch.neonell.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entity class for the user table. Spring-data automatically scan this entity
 * and create or update the table if necessary in the database referenced
 * 
 * @author fnell
 *
 */
@Entity
@Table(name = "T_USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
	@SequenceGenerator(sequenceName = "user_seq", allocationSize = 1, name = "USER_SEQ")
	private Long id;

	private String name;

	private String email;

	@Column(name = "CREATED_DATE")
	private Date date;

	private User() {
	}

	public User(String name, String email) {
		this.name = name;
		this.email = email;
		this.date = Calendar.getInstance().getTime();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
