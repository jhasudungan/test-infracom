package net.jeremi.studio.test_infracom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.jeremi.studio.test_infracom.entity.User;
import net.jeremi.studio.test_infracom.entity.UserContact;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserWithContacts {

    private String id;
    private String name;
    private Integer age;
    private Set<UserContactBasic> contacts = new HashSet<>();

    public UserWithContacts(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.age = user.getAge();
        for (UserContact contact : user.getContacts()) {
            this.contacts.add(new UserContactBasic(contact));
        }
    }
}
