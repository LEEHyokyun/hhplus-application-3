package kr.hhplus.be.server.user.infra.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.hhplus.be.server.user.domain.entity.User;

@Repository
public interface UserWriterRepository  extends JpaRepository<User, Long>{

}
