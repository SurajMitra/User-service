package user.user_service.Entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "roles")
public class roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "role_name")
    private String role_name;

    @Column(name = "role_code")
    private String role_code;

    @Column(name = "status")
    private String status;

    @Column(name = "created_at")
    private Instant created_at;

    @Column(name = "updated_at")
    private Instant updated_at;

    public long getId() {
        return id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getRole_code() {
        return role_code;
    }

    public void setRole_code(String role_code) {
        this.role_code = role_code;
    }

    public String getRole_status() {
        return status;
    }

    public void setRole_status(String role_status) {
        this.status = role_status;
    }

    public Instant getRole_created_at() {
        return created_at;
    }

    public void setRole_created_at(Instant role_created_at) {
        this.created_at = role_created_at;
    }

    public Instant getRole_updated_at() {
        return updated_at;
    }

    public void setRole_updated_at(Instant role_updated_at) {
        this.updated_at = role_updated_at;
    }
}
