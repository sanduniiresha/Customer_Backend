package lk.gedaratama.backendserver.resource;

public class DeliveryResource {
    private String username;
    private String password;
    private String firstname;
    //private String lastname;
    private String contact;
    //private Date joindate;
    private String uuid;
    private String role;
    private String email;
    // private boolean active;

    public DeliveryResource() {
    }

    public DeliveryResource(DeliveryResource user) {
        this.setUsername(user.getUsername());
        this.setFirstname(user.getFirstname());
      //  this.setLastname(user.getLastname());
        this.setContact(user.getContact());
        this.setEmail(user.getEmail());
        this.setRole(user.getRole());
        this.setPassword(user.getPassword());
        this.setUuid(user.getUuid());
        // this.setActive(user.isActive());
    }

    private void setContact(String username) { this.firstname=firstname;
    }



    private void setFirstname(String username) {this.contact=contact;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   /* public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }*/

    public String getFirstname() { return firstname;
    }



    public String getContact() { return contact;
    }

}
