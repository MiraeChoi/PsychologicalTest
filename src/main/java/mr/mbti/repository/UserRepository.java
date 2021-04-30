package mr.mbti.repository;

import mr.mbti.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserData, Long> {
    //select u from UserData u where u.username = ?
    List<UserData> findByUsername(String username);
}
