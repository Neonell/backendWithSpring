package ch.neonell.dao;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ch.neonell.model.User;

/**
 * DAO for the user table By extending the CRUD repository we automatically get
 * all the CRUD operation on the object. We just need to specify an interface
 * with spring-data. The implementation is automatically deduced. Either with
 * the extended interface, or by parsing method names or reading directly the
 * query in the corresponding annotation.
 * 
 * We can choose if we want to extend just Repository or CRUDRepository. If we
 * choose to extend the Repository then the CRUD method are not provided. This
 * could be useful if you want to restraint the operation permitted on the
 * table.
 * 
 * You can find some example under of how to define new queries :)
 * 
 * @author fnell
 *
 */
public interface UserRepository extends CrudRepository<User, Long> {

	List<User> findByEmail(String email);

	List<User> findByDate(Date date);

	// custom query example and return a stream
	@Query("select u from User u where u.email = :email")
	Stream<User> findByEmailReturnStream(@Param("email") String email);

}
