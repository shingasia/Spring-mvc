package spring.mem;

public class Member {
	//id, password, name, phone, location, address
	private String id;
	private String password;
	private String name;
	private String phone;
	private String location;
	private String address;
	

	public Member(String id, String password, String name, String phone, String location, String address) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.location=location;
		this.address=address;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
//	//비밀번호 변경 메서드
//	public void modifyPassword(String oldPwd, String newPwd) {
//		if(!oldPwd.equals(newPwd)) {
//			throw new DifferentPasswordException(); //기존의 비밀번호와 일치하지 않아서 익셉션 발생
//		}
//		this.password=newPwd;
//	}
	
}
