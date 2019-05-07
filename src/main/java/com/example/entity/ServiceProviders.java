package com.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Illia Chenchak
 */

@Entity
@Table(name = "providers")
@Data
public class ServiceProviders{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "description")
    private String description;

    @Column(columnDefinition = "LONGTEXT", name = "photo_path")
    private String image;


}
