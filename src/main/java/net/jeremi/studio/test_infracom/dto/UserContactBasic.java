package net.jeremi.studio.test_infracom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.jeremi.studio.test_infracom.entity.UserContact;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserContactBasic {

    private String id;
    private String address;

    public UserContactBasic(UserContact userContact) {
        this.id = userContact.getId();
        this.address = userContact.getAddress();
    }
}
