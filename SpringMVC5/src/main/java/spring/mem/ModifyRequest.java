package spring.mem;

//요청 파라미터를 위한 클래스, 커맨드 객체로도 사용 가능(setter와 getter가 있으면서 기본 생성자가 있는 클래스)
public class ModifyRequest {
	
	private String id;//아이디
	private String currentPassword;//현재 비밀번호
	private String newPassword;//새 비밀번호 확인
	private String confirmNewPassword;//새 비밀번호 확인
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCurrentPassword() {
		return currentPassword;
	}
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}
	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}
	
	
	
}
