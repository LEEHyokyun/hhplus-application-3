package kr.hhplus.be.server.user.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.hhplus.be.server.user.domain.entity.User;

public interface UserRepository  extends JpaRepository<User, Long>{

}
