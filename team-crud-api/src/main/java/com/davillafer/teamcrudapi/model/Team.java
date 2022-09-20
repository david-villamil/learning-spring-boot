package com.davillafer.teamcrudapi.model;

import javax.persistence.*;

@Entity // annotation indicates that the class is a persistent Java class
@Table(name = "teams") // annotation provides the table that maps this entity.
public class Team {

    @Id  // annotation for the primary key.
    @GeneratedValue(strategy = GenerationType.AUTO) // annotation used to define generation strategy for the primary
    private long id;

    @Column(name = "name") // annotation is used to define the column in database that maps annotated field.
    private String name;

    @Column(name = "season")
    private String season;

    @Column(name = "category")
    private String category;

    public Team() {
    }

    public Team(String name, String season, String category) {
        this.name = name;
        this.season = season;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", season='" + season + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
