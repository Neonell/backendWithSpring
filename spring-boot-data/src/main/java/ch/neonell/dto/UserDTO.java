package ch.neonell.dto;

import java.util.Date;

/**
 * Data transfer object for passing user data to the front-end and vis-versa
 * 
 * I like to use DTO like this we can control the data we want to transmit or
 * extend the data with other value needed by the view
 * 
 * 
 * @author fnell
 *
 */
public class UserDTO {
	 	private Long id;

	    private String name;

		private String email;

	    private Date date;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
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
		
//		public String toString(){
//			return "name: " + name + ",email: " + email + ", date: " + date.toString();
//		}

}
