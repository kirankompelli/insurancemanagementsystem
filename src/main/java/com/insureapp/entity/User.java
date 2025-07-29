package com.insureapp.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "fullname cannot be blank")
    private String fullname;

    @Column(nullable = false, unique = true)
    @Email(message = "invalid email format")
    @NotBlank(message = "email cannot be blank")
    private String email;

    @NotBlank(message = "password cannot be blank")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    @Pattern(
    	    regexp = "^(?=.*[A-Za-z])(?=.*\\d).{6,}$",
    	    message = "Password must be at least 6 characters and contain at least one letter and one number"
    	)
    private String password;

    @Pattern(regexp = "\\d{10}", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    private String gender;

    @Column(length = 300)
    private String address;

    @Column(nullable = false)
    private Boolean isActive = true;

    @Column(nullable = false)
    private LocalDateTime createdat = LocalDateTime.now();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    // 🔨 Manual Builder Class
    public static class Builder {
        private final User user = new User();

        public Builder id(long id) {
            user.setId(id);
            return this;
        }

        public Builder fullname(String fullname) {
            user.setFullname(fullname);
            return this;
        }

        public Builder email(String email) {
            user.setEmail(email);
            return this;
        }

        public Builder password(String password) {
            user.setPassword(password);
            return this;
        }

        public Builder mobileNumber(String mobileNumber) {
            user.setMobileNumber(mobileNumber);
            return this;
        }

        public Builder gender(String gender) {
            user.setGender(gender);
            return this;
        }

        public Builder address(String address) {
            user.setAddress(address);
            return this;
        }

        public Builder isActive(Boolean isActive) {
            user.setIsActive(isActive);
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            user.setCreatedat(createdAt);
            return this;
        }

        public Builder roles(Set<Role> roles) {
            user.setRoles(roles);
            return this;
        }

        public User build() {
            return user;
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
