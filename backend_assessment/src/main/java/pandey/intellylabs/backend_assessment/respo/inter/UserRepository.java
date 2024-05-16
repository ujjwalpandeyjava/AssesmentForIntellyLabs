package pandey.intellylabs.backend_assessment.respo.inter;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pandey.intellylabs.backend_assessment.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByEmail(String emailID);
//	User findByEmail(String emailID);

}
