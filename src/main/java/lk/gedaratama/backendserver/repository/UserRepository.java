package lk.gedaratama.backendserver.repository;

import lk.gedaratama.backendserver.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sashini Tharuka on 5/31/20
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByEmail(String email);

}