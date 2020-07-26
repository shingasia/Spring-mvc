package spring.mem;

import java.sql.SQLException;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import spring.exception.MemberNotFoundException;
import spring.exception.WrongPasswordException;

public class ModifyPasswordService {
	
	private MemberDao memberDao;

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	//인터셉터는 URL단위로 묶어서 처리하지만 트렌잭션은 메서드 단위로 묶어서 처리
	//별도의 설정을 추가하지 않으면 RuntimeException(UncheckedException)일 때 트랜잭션을 롤백한다.
	//@Transactional 의 속성으로 rollbackFor와 noRollbackFor가 있다
	@Transactional(rollbackFor= {SQLException.class})
	public String modifyPassword(String id, String oldPwd, String newPwd){
		Member member=memberDao.selectById(id);//해당 아이디를 가진 회원이 있는지 검색
		if(member==null) {//존재하지 않으면 Exception 발생
			throw new MemberNotFoundException();
		}else if(!oldPwd.equals(member.getPassword())) {
			throw new WrongPasswordException();
		}
		member.setPassword(newPwd);
		memberDao.update(member);
		return member.getId();//ID를 리턴
	}
	
	
}
