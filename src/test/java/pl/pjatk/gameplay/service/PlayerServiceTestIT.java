package pl.pjatk.gameplay.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.pjatk.gameplay.model.Player;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PlayerServiceTestIT {

    @Autowired
    private PlayerService playerService;

    @BeforeEach
    void cleanUP(){
        playerService.deleteAll();
    }

    @Test
    void shouldNotFindAnyone(){
        List<Player> all = playerService.findAll();
        assertThat(all).isEmpty();
    }

    @Test
    void shouldNotFindAllPlayers(){
        playerService.save(new Player("nickname", 100, 10));
        List<Player> all = playerService.findAll();
        assertThat(all).isNotEmpty();
    }

    @Test
    void shouldSavePlayer(){
        Player player = playerService.save(new Player("nickname", 100, 10));
        assertThat(player.getId()).isPositive();
    }

    @Test
    void shouldFindById(){
        playerService.save(new Player("nickname", 100, 10));
        Optional<Player> all = playerService.findByID(1L);
        assertThat(all).isNotEmpty();
    }

    @Test
    void shouldThrowExceptionOnFindByID() {
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> playerService.findByID(10L));
    }

    @Test
    void shouldAttackPlayer() {
        Player attacker = playerService.save(new Player("nickname", 100, 10));
        Player defender = playerService.save(new Player("nickname", 100, 10));

        playerService.attackPlayer(attacker.getId(), defender.getId());

        assertThat(playerService.findByID(defender.getId()).get().getHealth()).isEqualTo(90);
    }

//    @Test
//    void shouldAttackPlayer(){
//        Player player1 = new Player(1L, "test 1", 1000, 50);
//        Player player2 = new Player(2L, "test 1", 1000, 50);
//
//
//    }

// findbyid
// attackplayer
// narprawic logiek
}

//    @Test
//    void shouldAttackPlayer() {
//        Player player1 = new Player(1L, "test 1", 1000, 50);
//        Player player2 = new Player(2L, "test 1", 1000, 50);
//
//        when(playerRepository.findById(1L)).thenReturn(Optional.of(player1));
//        when(playerRepository.findById(2L)).thenReturn(Optional.of(player2));
//        when(playerRepository.save(player2)).thenReturn(player2);
//        when(damageService.attackPlayer(any(), any())).thenCallRealMethod();
//
//        Player attack1 = playerService.attackPlayer(1L, 2L);
//        assertThat(attack1.getHealth()).isEqualTo(950);
//    }
