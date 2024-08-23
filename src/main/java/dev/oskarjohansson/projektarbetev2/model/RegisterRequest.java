package dev.oskarjohansson.projektarbetev2.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record RegisterRequest(@NotBlank @Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters") String username,@Size(min = 6, max= 30, message = "Password must be between 6 and 30 characters") @NotBlank String password, Roles role, @NotNull Boolean consent) {

    public static class Builder{
        private String userName;
        private String password;
        private Roles role;
        private Boolean consent;


        public Builder userName(String userName){
            this.userName = userName;
            return this;
        }

        public Builder password(String password){
            this.password = password;
            return this;
        }

        public Builder role(Roles role){
            this.role = role;
            return this;
        }

        public Builder consent(Boolean consent){
            this.consent = consent;
            return this;
        }

        public RegisterRequest build(){
            return new RegisterRequest(userName, password, role, consent);
        }

    }
}
