package htw.berlin.webtech;

import htw.berlin.webtech.Exceptions.PasswordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PasswordService {

    private final PasswordRepository passwordRepository;

    @Autowired
    public PasswordService(PasswordRepository passwordRepository) {
        this.passwordRepository = passwordRepository;
    }

    public List<Password> findAll() {
        Iterable<Password> iterable = passwordRepository.findAll();
        List<Password> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }

    public Password save(Password password) {
        // Hier wurde die Verschlüsselungslogik entfernt
        return passwordRepository.save(password);
    }

    public Password get(Long id) {
        Optional<Password> password = passwordRepository.findById(id);
        if (password.isPresent()) {
            return password.get();
        } else {
            throw new PasswordNotFoundException("Password with id " + id + " not found");
        }
    }

    public void deleteById(Long id) {
        passwordRepository.deleteById(id);
    }
}
