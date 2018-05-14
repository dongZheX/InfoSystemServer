package things;


/**
 * Created by ASUS on 2018/4/12.
 */

public class UserX {
    private String Username;
    private String User_name;
    private String Class_id;
    private String User_sex;
    private String User_phone;
    private String User_address;
    private String User_QQ;
    private String User_image; 
    private String birth = null;

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public UserX(String username, String user_name, String user_sex, String user_phone) {
		super();
		Username = username;
		User_name = user_name;
		User_sex = user_sex;
		User_phone = user_phone;
	}

	public UserX(String username, String user_name, String class_id, String user_sex, String user_phone, String birth) {
        Username = username;
        User_name = user_name;
        Class_id = class_id;
        User_sex = user_sex;
        User_phone = user_phone;
        this.birth = birth;
    }

    public UserX(String username, String user_name, String class_id, String user_sex, String user_phone, String user_address, String user_QQ, String user_image, String birth) {
        Username = username;
        User_name = user_name;
        Class_id = class_id;
        User_sex = user_sex;
        User_phone = user_phone;
        User_address = user_address;
        User_QQ = user_QQ;
        User_image = user_image;
        this.birth = birth;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getUser_name() {
        return User_name;
    }

    public void setUser_name(String user_name) {
        User_name = user_name;
    }

    public String getClass_id() {
        return Class_id;
    }

    public void setClass_id(String class_id) {
        Class_id = class_id;
    }

    public String getUser_sex() {
        return User_sex;
    }

    public void setUser_sex(String user_sex) {
        User_sex = user_sex;
    }

    public String getUser_phone() {
        return User_phone;
    }

    public void setUser_phone(String user_phone) {
        User_phone = user_phone;
    }

    public String getUser_address() {
        return User_address;
    }

    public void setUser_address(String user_address) {
        User_address = user_address;
    }

    public String getUser_QQ() {
        return User_QQ;
    }

    public void setUser_QQ(String user_QQ) {
        User_QQ = user_QQ;
    }

    public String getUser_image() {
        return User_image;
    }

    public void setUser_image(String user_image) {
        User_image = user_image;
    }
}
