package spring.mem;
import org.springframework.transaction.annotation.Transactional;

import spring.exception.DifferentPasswordException;
import spring.exception.MemberNotFoundException;

public class MemberDeleteService {
	
	private MemberDao memberDao;

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	//인터셉터는 URL단위로 묶어서 처리하지만 트렌잭션은 메서드 단위로 묶어서 처리
	//별도의 설정을 추가하지 않으면 RuntimeException(UncheckedException)일 때 트랜잭션을 롤백한다.
	//@Transactional 의 속성으로 rollbackFor와 noRollbackFor가 있다
	@Transactional
	public Member deleteMember(String id, String password) {
		Member member=memberDao.selectById(id);
		if(member==null) {//존재하지 않으면 Exception 발생
			throw new MemberNotFoundException();
		}else if(!(member.getPassword().equals(password))) {//비밀번호가 일치하지 않으면
			throw new DifferentPasswordException();
		}
		memberDao.delete(member);
		return member;//삭제된 Member객체를 리턴
	}
	
}
