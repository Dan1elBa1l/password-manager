package htw.berlin.webtech;

import htw.berlin.webtech.Exceptions.PasswordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Password> getAllPasswords() {
        return passwordService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Password> getPasswordById(@PathVariable Long id) {
        try {
            Password password = passwordService.get(id);
            return ResponseEntity.ok(password);
        } catch (PasswordNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Password createPassword(@RequestBody Password password) {
        return passwordService.save(password);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Password> updatePassword(@PathVariable Long id, @RequestBody Password passwordDetails) {
        try {
            Password existingPassword = passwordService.get(id);
            existingPassword.setService(passwordDetails.getService());
            existingPassword.setUsername(passwordDetails.getUsername());
            existingPassword.setPassword(passwordDetails.getPassword());
            existingPassword.setDescription(passwordDetails.getDescription());
            return ResponseEntity.ok(passwordService.save(existingPassword));
        } catch (PasswordNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePassword(@PathVariable Long id) {
        try {
            passwordService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (PasswordNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
