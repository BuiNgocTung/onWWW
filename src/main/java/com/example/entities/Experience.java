package com.example.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "experience")
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exp_id")
    private long id;
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fromDate;

    @Column( columnDefinition = "varchar(400)")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String workDescripton;
    @Column

    @Enumerated(EnumType.ORDINAL)
    private Roles role;
    @Column(name = "companny", columnDefinition = "varchar(120)")
    private String compannyName;
    @Column
    private LocalDate toDate;
    @ManyToOne
    @JoinColumn(name = "can_id")
    private Candidate candidate;

    public Experience( LocalDate fromDate, String workDescripton, Roles role, String compannyName, LocalDate toDate, Candidate candidate) {

        this.fromDate = fromDate;
        this.workDescripton = workDescripton;
        this.role = role;
        this.compannyName = compannyName;
        this.toDate = toDate;
        this.candidate = candidate;
    }

    public Experience(Candidate candidate) {
        this.candidate = candidate;
    }

    public Experience() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public String getWorkDescripton() {
        return workDescripton;
    }

    public void setWorkDescripton(String workDescripton) {
        this.workDescripton = workDescripton;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public String getCompannyName() {
        return compannyName;
    }

    public void setCompannyName(String compannyName) {
        this.compannyName = compannyName;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    @Override
    public String toString() {
        return "Experience{" +
                "id=" + id +
                ", fromDate=" + fromDate +
                ", workDescripton='" + workDescripton + '\'' +
                ", role=" + role +
                ", compannyName='" + compannyName + '\'' +
                ", toDate=" + toDate +
                ", candidate=" + candidate +
                '}';
    }
}
