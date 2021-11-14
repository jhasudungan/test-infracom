package net.jeremi.studio.test_infracom.dto;

import lombok.*;
import net.jeremi.studio.test_infracom.entity.User;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserBasic {

    private String id;
    private String name;
    private Integer age;

    public UserBasic(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.age = user.getAge();
    }
}
