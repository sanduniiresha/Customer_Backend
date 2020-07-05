package lk.gedaratama.backendserver.model;
import com.sun.istack.NotNull;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table (name = "CustomerDetail")
public class CustomerDetail {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    @NotNull
    private String username;
    @Column
    @NotNull
    private String email;
    @Column
    @NotNull
    private String password;

    @Column
    private String uuid;
    @Column
    @JsonIgnore
    private String role;



    public CustomerDetail(CustomerDetail user) {
        this.setUsername(user.getUsername());
        this.setEmail(user.getEmail());
        this.setRole(user.getRole());
        this.setPassword(user.getPassword());
        this.setUuid(user.getUuid());

    }

    public CustomerDetail() {

    }


    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }
    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getUuid() { return uuid; }

    public void setUuid(String uuid) { this.uuid = uuid; }
}
