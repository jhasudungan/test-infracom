package net.jeremi.studio.test_infracom.repository;

import net.jeremi.studio.test_infracom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {

}
