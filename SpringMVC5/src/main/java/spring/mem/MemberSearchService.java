package spring.mem;
import spring.exception.*;

public class MemberSearchService {
	
	private MemberDao memberDao;
	
	public MemberSearchService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public Member searchMember(String id, String password) throws MemberNotFoundException, DifferentPasswordException {
		Member member=memberDao.selectById(id);
		if(member==null) {//존재하지 않으면 Exception 발생
			throw new MemberNotFoundException();
		}else if(!member.getPassword().equals(password)) {
			throw new DifferentPasswordException();
		}
		return member;
	}
	
	public Member searchMemberById(String id) {
		Member member=memberDao.selectById(id);
		if(member==null) {//존재하지 않으면 Exception 발생
			throw new MemberNotFoundException();
		}
		return member;
	}
	
}
