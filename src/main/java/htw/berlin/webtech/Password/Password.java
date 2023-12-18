package htw.berlin.webtech.Password;

import jakarta.persistence.*;

@Entity
public class Password {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String service;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String description;

    protected Password() {
    }

    public Password(String service, String username, String password, String description) {
        this.service = service;
        this.username = username;
        this.password = password;
        this.description = description;
    }
        public void setService(String service) {
            this.service = service;
        }

        public String getService() {
            return service;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUsername() {
            return username;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPassword() {
            return password;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }


