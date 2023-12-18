package htw.berlin.webtech.Password;

import htw.berlin.webtech.Password.Password;
import org.springframework.data.repository.CrudRepository;

public interface PasswordRepository extends CrudRepository<Password, Long> {
}
