package cc.chinmoku.springcloudcommon.entity;

import lombok.Data;

@Data
public class User {

    private String id;

    private String name;

    private String address;

    private String salt;

    private int age;
}
