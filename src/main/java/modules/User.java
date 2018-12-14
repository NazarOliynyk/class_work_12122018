package modules;

import lombok.*;

@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode

public class User {

    private int id;
    private String name;
    private String surName;
    private int phoneNumber;
    private String email;
    private String login;
    private String password;

    public User(String name, String surName, int phoneNumber, String email, String login, String password) {
        this.name = name;
        this.surName = surName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.login = login;
        this.password = password;
    }
}
