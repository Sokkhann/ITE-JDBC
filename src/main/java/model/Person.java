package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
//@Builder
@Accessors (chain = true)
public class Person {
    private int id;
    private String fullName;
    private String gender;
    private String email;
    private String address;

}
