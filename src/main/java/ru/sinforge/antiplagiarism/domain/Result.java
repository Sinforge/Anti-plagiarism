package ru.sinforge.antiplagiarism.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Integer id;


    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Getter
    @Setter
    private double res;

    @Getter
    @Setter
    private String date;

    public Result (double res, User user, String date) {
        this.res = res;
        this.user = user;
        this.date = date;
    }


    public Result() {
    }
}
