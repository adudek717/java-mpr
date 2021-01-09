package pl.pjatk.gameplay.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pjatk.gameplay.model.Player;
import pl.pjatk.gameplay.repository.PlayerRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

    @Mock
    private DamageService damageService;

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerService playerService;


    @Test
    void FindAll() {
        //given \/nadpisanie metody
        when(playerRepository.findAll()).thenReturn(List.of(new Player()));
        //when \/wywołanie metody
        List<Player> all = playerService.findAll();
        //then
        assertThat(all).hasSize(2);
    }

    @Test
    void findByID() {
        //given
        when(playerRepository.findById(1L)).thenReturn(Optional.of(new Player(1L,"Bob",100,10)));
        //when
        Optional<Player> player = playerService.findByID(11L);
        //then
        assertThat(player.get().getId()).isEqualTo(1L);
    }

    @Test
    void deleteById() {
        //given
        when(playerRepository.findById(anyLong())).thenReturn((Optional.empty()));
        //when
        playerService.deleteById(1L);
        playerService.deleteById(2L);
        playerService.deleteById(2L);
        //then
        //verify(playerRepository, times(1)).deleteById(1L);
        verify(playerRepository, times(3)).deleteById(anyLong());
    }

    @Test
    void save() {
        //given
        Player player = new Player(1L,"Bob",100,10);
        when(playerRepository.save(player)).thenReturn(new Player(1L,"Alex",100,10));
        //when
        Player save = playerService.save(player);
        //then
        assertThat(save.getNickname()).isEqualTo("Alex");
    }

//    @Test
//    void attackPlayer() {
//        //given
//        Player attacker = new Player(1L,"MeAttack",100,10);
//        Player defender = new Player(2L,"MeDefend",100,10);
//        Long attackerId = attacker.getId();
//        Long defenderId = defender.getId();
//
//        when(damageService.attackPlayer(attacker, defender)).thenReturn(defender);
//
//        when(playerRepository.findById(attackerId)).thenReturn(Optional.of(attacker));
//        when(playerRepository.findById(defenderId)).thenReturn(Optional.of(defender));
//
//        when(playerRepository.save(defender)).thenReturn(defender);
//        //when
//        Player defendingPlayer = playerService.attackPlayer(attackerId,defenderId);
//        //then
//        assertThat(defendingPlayer.getHealth()).isEqualTo(90);
//    }

    @Test
    void shouldAttackPlayer() {
        Player player1 = new Player(1L, "test 1", 1000, 50);
        Player player2 = new Player(2L, "test 1", 1000, 50);

        when(playerRepository.findById(1L)).thenReturn(Optional.of(player1));
        when(playerRepository.findById(2L)).thenReturn(Optional.of(player2));
        when(playerRepository.save(player2)).thenReturn(player2);
        when(damageService.attackPlayer(any(), any())).thenCallRealMethod();

        Player attack1 = playerService.attackPlayer(1L, 2L);
        assertThat(attack1.getHealth()).isEqualTo(950);
    }
    // powyższy test nie działa
}