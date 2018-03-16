package ch.neonell.dto;

import java.util.Date;

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
		
		public String toString(){
			return "name: " + name + ",email: " + email + ", date: " + date.toString();
		}

}
