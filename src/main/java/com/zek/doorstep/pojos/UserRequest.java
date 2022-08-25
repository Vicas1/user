package com.zek.doorstep.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author EK
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private Long id;
    private String firstName;
    private String lastName;
    private String mobile;
    private String address;
    /*private String userName;
    private String password;
    private String email;
    private Long roleId;*/

}
