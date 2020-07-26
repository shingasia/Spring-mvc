package spring.mem;

import spring.exception.DuplicationMemberException;

public class MemberJoinService {
	
	private MemberDaoImpl memberDao;
	
	public MemberJoinService(MemberDaoImpl memberDao) {
		this.memberDao=memberDao;
	}
	
	//전화번호가 정규 표현식에 일치하는지 검사
	public String memberJoin(JoinRequest req) {
		Member member=memberDao.selectById(req.getId());//해당 아이디가 이미 존재하는지 검색
		if(member!=null) {//null이 아니면 이미 존재하므로 null을 리턴
			throw new DuplicationMemberException();
		}
		Member newMember=new Member(
				req.getId(),
				req.getPassword(),
				req.getName(),
				req.getPhone(),
				req.getLocation(),
				req.getAddress());
		memberDao.insert(newMember);
		return newMember.getId();//새로운 멤버를 삽입하고 id를 리턴
	}
	
}
