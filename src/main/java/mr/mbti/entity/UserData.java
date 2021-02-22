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

    //나중에 12번까지 늘리자
    private int q1;
    private int q2;
    private int q3;

    private String result;

    public UserData(String username, int q1, int q2, int q3) {
        this.username = username;
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
    }
}

