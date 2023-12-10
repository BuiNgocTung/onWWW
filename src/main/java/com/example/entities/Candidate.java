package com.example.entities;

import jakarta.persistence.*;
import jdk.jfr.Name;

import java.util.List;
@Entity
@Table(name = "candidate")

public class Candidate {
    @Id
    @Column(name = "can_id")
    private  long id;
    @Column( columnDefinition = "varchar(15)")
    private String phone;
    @Column
    private String email;
    private String fullName;
    @OneToMany (mappedBy = "candidate")
    private List<Experience> experiences;


    public Candidate() {
    }

    public Candidate(long id) {
        this.id = id;
    }

    public Candidate(long id, String phone, String email, String fullName, List<Experience> experiences) {
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.fullName = fullName;
        this.experiences = experiences;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", experiences=" + experiences +
                '}';
    }
}
