package Model;

public class User {
	private int id;
	private String login;
	private String password;

	public User(int id, String login, String password) {
		this.id = id;
		this.login = login;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return this.id + ") User: " + this.login + " - password:" + this.password;

	}

}
