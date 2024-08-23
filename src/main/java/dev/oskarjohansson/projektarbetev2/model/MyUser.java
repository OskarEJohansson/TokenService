package dev.oskarjohansson.projektarbetev2.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;


@Document
public record MyUser(@MongoId String id,
                     @NotBlank(message = "Username must not be empty") @Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters") @Indexed(unique = true) String username,
                     @NotBlank(message = "Password must not be blank") @Size(min = 6, max = 30, message = "Password must be between 6 and 30 characters") String password,
                     Roles role, UserConsent userConsent) {

    public static class Builder {
        private String id;
        private String userName;
        private String password;
        private Roles role;
        private UserConsent userConsent;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder role(Roles role) {
            this.role = role;
            return this;
        }

        public Builder userConsent(UserConsent userConsent) {
            this.userConsent = userConsent;
            return this;
        }

        public MyUser build() {
            return new MyUser(id, userName, password, role, userConsent);
        }
    }
}
