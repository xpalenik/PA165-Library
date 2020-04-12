package cz.muni.fi.pa165.library.dto;

/**
 * @author Petr Janik 485122
 * @since 12.04.2020
 */
public class RoleDTO {
    private Long id;

    private String roleName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
