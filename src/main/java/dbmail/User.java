package dbmail;

public class User {
	
	private String userName;
	private String password;
	private String name;
	private String surname;
	
	public User(String userName, String password, String name, String surname) {
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.surname = surname;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSurname() {
		return surname;
	}

}
