package user.user_service.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RolesResponseDTO {

    @JsonProperty("role_id")
    private long role_id;

    @JsonProperty("role_name")
    private String role_name;

    @JsonProperty("role_code")
    private String role_code;

    @JsonProperty("role_status")
    private String role_status;


    public RolesResponseDTO(long role_id, String role_name, String role_code, String role_status) {
        this.role_id = role_id;
        this.role_name = role_name;
        this.role_code = role_code;
        this.role_status = role_status;
    }

    public long getRole_id() {
        return role_id;
    }

    public void setRole_id(long role_id) {
        this.role_id = role_id;
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
        return role_status;
    }

    public void setRole_status(String role_status) {
        this.role_status = role_status;
    }

}
