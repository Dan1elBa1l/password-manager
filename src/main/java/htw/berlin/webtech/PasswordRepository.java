package htw.berlin.webtech;

import org.springframework.data.repository.CrudRepository;

public interface PasswordRepository extends CrudRepository<Password, Long> {
    // Benutzerdefinierte Abfragen können hier hinzugefügt werden
}
