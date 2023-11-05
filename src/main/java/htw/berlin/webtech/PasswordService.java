package htw.berlin.webtech;

import htw.berlin.webtech.Exceptions.PasswordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PasswordService {

    private final PasswordRepository passwordRepository;

    @Autowired
    public PasswordService(PasswordRepository passwordRepository) {
        this.passwordRepository = passwordRepository;
    }

    public List<Password> findAll() throws Exception {
        List<Password> passwords = (List<Password>) passwordRepository.findAll();
        // Decrypt all passwords before returning
        return passwords.stream().map(password -> {
            try {
                String decryptedPassword = EncryptionUtils.decrypt(password.getPassword());
                password.setPassword(decryptedPassword);
            } catch (Exception e) {
                e.printStackTrace(); // Handle exception properly in production code
            }
            return password;
        }).collect(Collectors.toList());
    }

    public Password get(Long id) throws Exception {
        Password password = passwordRepository.findById(id)
                .orElseThrow(() -> new PasswordNotFoundException("Password with ID " + id + " not found."));
        // Decrypt the password before returning
        String decryptedPassword = EncryptionUtils.decrypt(password.getPassword());
        password.setPassword(decryptedPassword);
        return password;
    }

    public Password save(Password password) throws Exception {
        // Encrypt the password before saving
        String encryptedPassword = EncryptionUtils.encrypt(password.getPassword());
        password.setPassword(encryptedPassword);
        return passwordRepository.save(password);
    }

    public void deleteById(Long id) {
        if (!passwordRepository.existsById(id)) {
            throw new PasswordNotFoundException("Password with ID " + id + " not found.");
        }
        passwordRepository.deleteById(id);
    }
}
