package pl.pjatk.gameplay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.pjatk.gameplay.model.Player;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query("SELECT p from Player p where p.nickname = :nickname")
    Optional<Player> getSomePlayerByName(String nickname);

    @Query("SELECT p from Player p where p.health = :health")
    Optional<Player> getSomePlayerByHealth(int health);

    @Query("SELECT p from Player p where p.attack = :attack")
    Optional<Player> getSomePlayerByAttack(int attack);

    Optional<Player> findByNickname(String nick);

    Optional<Player> findByHealth(int health);

    Optional<Player> findByHealthAndNickname(int health, int nickname);

    //Optional<Player> findByHealthAndNicknameOOrderByAttack(int health, int nickname);

    // To są querymethods
}
