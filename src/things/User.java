package things;

public class User {
	private String username;
    private String password;
    private int power;
    private String Class_id;
    private int firstLogin;

    public User(String username, String password, int power, String class_id,int firstLogin) {
        this.username = username;
        this.password = password;
        this.power = power;
        Class_id = class_id;
        this.firstLogin = firstLogin;
    }

    public int getFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(int firstLogin) {
		this.firstLogin = firstLogin;
	}

	public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getClass_id() {

        return Class_id;
    }

    public void setClass_id(String class_id) {
        Class_id = class_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
