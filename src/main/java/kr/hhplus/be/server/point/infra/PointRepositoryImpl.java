package kr.hhplus.be.server.point.infra;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kr.hhplus.be.server.point.application.PointMapper;
import kr.hhplus.be.server.point.domain.model.PointDTO;
import kr.hhplus.be.server.user.application.UserMapper;
import kr.hhplus.be.server.user.infra.UserRepository;

public class PointRepositoryImpl implements PointRepository{
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	UserRepository userRepository;
	
	/*
	 * 비즈니스 상황에 유연하게 대응할 수 있는 return값을 설정하며,
	 * 영속성 계층과 서비스 로직의 반환값의 동일한 설정은 웬만해선 지양한다.
	 * */
	@Override
	public PointDTO charge(PointDTO pointDTO) throws Exception {
		// TODO Auto-generated method stub
		return PointMapper.toPointDomainFromUserEntity(userRepository.save(UserMapper.toUserEntityFromPointDomain(pointDTO)));
	}
}
