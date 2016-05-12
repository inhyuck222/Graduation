package kr.ac.hansung.dao;

public class Offer {

	private int id;
	private String email;
	private String password;
	private String company;

	
	public Offer(){
			
		
	}
	
	public Offer(int id, String email, String password, String company) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.company = company;
	}
	
	public Offer(String email, String password, String company) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.company = company;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	
	@Override
	public String toString() {
		return "Offer [id=" + id + ", email=" + email + ", password=" + password + ", company=" + company + "]";
	}
	
	
	
}
