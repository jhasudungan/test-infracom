package net.jeremi.studio.test_infracom.repository;

import net.jeremi.studio.test_infracom.entity.User;
import net.jeremi.studio.test_infracom.entity.UserContact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface UserContactRepository extends JpaRepository<UserContact,String> {

    Set<UserContact> findUserContactByUser(User user);

}
