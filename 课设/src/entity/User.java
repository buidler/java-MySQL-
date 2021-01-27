package entity;

/**
 * @author 玉竹
 */
public class User {
	private String account;
	private String classNum;
	private String password;
	
	public User() {
		super();
	}
	public User(String account, String password,String classNum) {
		super();
		this.account = account;
		this.classNum = classNum;
		this.password = password;
	}
	public String getClassNum() {
		return classNum;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
