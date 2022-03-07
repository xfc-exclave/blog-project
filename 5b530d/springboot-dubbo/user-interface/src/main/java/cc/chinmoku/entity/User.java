package cc.chinmoku.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class User implements Serializable {

    private int id;

    private String username;

    private String tel;

    private int age;
}
