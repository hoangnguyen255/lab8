package nguyennguyenhoang.repository;

import nguyennguyenhoang.entity.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	User getUserByUsername(String username);

}
