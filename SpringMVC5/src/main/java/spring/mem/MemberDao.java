package spring.mem;

import java.util.List;

public interface MemberDao {
	
	public void insert(Member member);//회원 정보 입력(회원 가입)
	public Member selectById(String id);//회원 아이디로 검색
	public List<Member> selectAll();//회원 전체 검색
	public void update(Member member);//회원 정보 수정(비밀번호 변경)
	public void delete(Member member);//회원 탈퇴 기능
	
}
