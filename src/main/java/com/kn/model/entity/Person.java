package com.kn.model.entity;

import com.kn.model.IEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "person")
public class Person implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "photo_url")
    private String photoUrl;


}
