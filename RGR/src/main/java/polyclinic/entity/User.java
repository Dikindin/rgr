package polyclinic.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User extends AbstractEntity {

    @NotBlank(message = "Имя пользователя не может быть пустым")
    @Size(min = 3, max = 64, message = "Имя пользователя должно быть от 3 до 64 символов")
    @Column(unique = true, nullable = false)
    private String username;

    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 6, message = "Пароль должен быть не менее 6 символов")
    @Column(nullable = false)
    private String password;

    // Например, ROLE_GUEST, ROLE_MANAGER, ROLE_ADMIN
    @NotBlank(message = "Роль не может быть пустой")
    @Column(nullable = false)
    private String authority;

    // Геттеры и сеттеры
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
    public String getAuthority() {
        return authority;
    }
    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
