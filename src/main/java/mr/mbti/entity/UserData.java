package mr.mbti.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserData {

    @Id @GeneratedValue
    private Long id;

    private String username;

    private int q01;
    private int q02;
    private int q03;
    private int q04;
    private int q05;
    private int q06;
    private int q07;
    private int q08;
    private int q09;
    private int q10;
    private int q11;
    private int q12;
    private int q13;
    private String result;

    public UserData(String username, int q01, int q02, int q03, int q04, int q05, int q06, int q07, int q08, int q09, int q10, int q11, int q12, int q13, String result) {
        this.username = username;
        this.q01 = q01;
        this.q02 = q02;
        this.q03 = q03;
        this.q04 = q04;
        this.q05 = q05;
        this.q06 = q06;
        this.q07 = q07;
        this.q08 = q08;
        this.q09 = q09;
        this.q10 = q10;
        this.q11 = q11;
        this.q12 = q12;
        this.q13 = q13;
        this.result = result;
    }
}

