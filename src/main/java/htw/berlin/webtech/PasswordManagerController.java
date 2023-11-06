package htw.berlin.webtech;

import htw.berlin.webtech.Exceptions.PasswordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passwords")
public class PasswordManagerController {

    private final PasswordService passwordService;

    @Autowired
    public PasswordManagerController(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @GetMapping
    public ResponseEntity<List<Password>> getAllPasswords() {
        try {
            List<Password> passwords = passwordService.findAll();
            return ResponseEntity.ok(passwords);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Password> getPasswordById(@PathVariable Long id) {
        try {
            Password password = passwordService.get(id);
            return ResponseEntity.ok(password);
        } catch (PasswordNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Password> createPassword(@RequestBody Password password) {
        try {
            Password savedPassword = passwordService.save(password);
            return new ResponseEntity<>(savedPassword, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Password> updatePassword(@PathVariable Long id, @RequestBody Password passwordDetails) {
        try {
            Password existingPassword = passwordService.get(id);
            // Encrypt the username and description before updating
            String encryptedUsername = EncryptionUtils.encrypt(passwordDetails.getUsername());
            String encryptedDescription = EncryptionUtils.encrypt(passwordDetails.getDescription());
            existingPassword.setService(passwordDetails.getService());
            existingPassword.setUsername(encryptedUsername); // Set encrypted username
            existingPassword.setPassword(passwordDetails.getPassword()); // Set password to encrypt in service
            existingPassword.setDescription(encryptedDescription); // Set encrypted description
            Password updatedPassword = passwordService.save(existingPassword);
            return ResponseEntity.ok(updatedPassword);
        } catch (PasswordNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            // Log the exception (use a proper logger in production)
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePassword(@PathVariable Long id) {
        try {
            passwordService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (PasswordNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
