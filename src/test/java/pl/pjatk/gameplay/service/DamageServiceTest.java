package pl.pjatk.gameplay.service;

import org.junit.jupiter.api.Test;
import pl.pjatk.gameplay.model.Player;

import static org.assertj.core.api.Assertions.assertThat;

public class DamageServiceTest {

    private DamageService damageService = new DamageService();

    @Test
    void shouldHealPlayer() {
        //given
        Player player = new Player("my nickname", 90, 10);
        //when
        damageService.heal(player);
        //then
        assertThat(player.getHealth()).isEqualTo(100);
    }

    @Test
    void shouldPoisonPlayerAt5hp() {
        //given
        Player player = new Player("name", 5, 10);
        //when
        damageService.poisoned(player);
        //then
        assertThat(player.getHealth()).isEqualTo(5);
    }

    @Test
    void shouldPoisonPlayerAt10hp() {
        //given
        Player player = new Player("name", 10, 10);
        //when
        damageService.poisoned(player);
        //then
        assertThat(player.getHealth()).isEqualTo(5);
    }

    @Test
    void shouldTransferHealthFor100hp() {
        //given
        Player donator = new Player("name", 100, 10);
        Player receiver = new Player("name", 100, 10);
        //when
        damageService.transferHealth(donator, receiver);
        //then
        assertThat(receiver.getHealth() == 110);
        assertThat(donator.getHealth() == 90);
    }

    @Test
    void shouldTransferHealthFor80hp() {
        //given
        Player donator = new Player("name", 80, 10);
        Player receiver = new Player("name", 80, 10);
        //when
        damageService.transferHealth(donator, receiver);
        //then
        assertThat(receiver.getHealth() == 100);
        assertThat(donator.getHealth() == 80);
    }

    @Test
    void shouldSuperPotionHealFor50hpWhenPlayerHas60hp() {
        //given
        Player player = new Player("name", 60, 10);
        //when
        damageService.superPotion(player);
        //then
        assertThat(player.getHealth() == 100);
    }

    @Test
    void shouldSuperPotionHealFor50hpWhenPlayerHas40hp() {
        //given
        Player player = new Player("name", 40, 10);
        //when
        damageService.superPotion(player);
        //then
        assertThat(player.getHealth() == 90);
    }

    @Test
    void shouldIncreaseMaxHpTo120() {
        //given
        Player player = new Player("name", 100, 10);
        //when
        damageService.increaseMaxHP(player);
        //then
        assertThat(player.getHealth() == 120);
    }
}