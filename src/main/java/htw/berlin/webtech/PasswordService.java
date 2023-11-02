package htw.berlin.webtech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasswordService {

    private final PasswordRepository passwordRepository;

    @Autowired
    public PasswordService(PasswordRepository passwordRepository) {
        this.passwordRepository = passwordRepository;
    }

    public List<Password> findAll() {
        return (List<Password>) passwordRepository.findAll();
    }

    public Password get(Long id) {
        return passwordRepository.findById(id)
                .orElseThrow(() -> new PasswordNotFoundException("Password with ID " + id + " not found."));
    }

    public Password save(Password password) {
        return passwordRepository.save(password);
    }

    public void deleteById(Long id) {
        if (!passwordRepository.existsById(id)) {
            throw new PasswordNotFoundException("Cannot delete, Password with ID " + id + " not found.");
        }
        passwordRepository.deleteById(id);
    }
}
