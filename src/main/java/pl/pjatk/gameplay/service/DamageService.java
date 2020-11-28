package pl.pjatk.gameplay.service;

import org.springframework.stereotype.Service;
import pl.pjatk.gameplay.model.Player;

@Service
public class DamageService {

    public Player attackPlayer(Player attacker, Player defender){
        defender.setHealth(defender.getHealth() - attacker.getAttack());
        return defender;
    }

    public Player heal(Player player) {
        if (player.getHealth() >= 100) {
            player.setHealth(player.getHealth());
        } else {
            player.setHealth(player.getHealth() + 10);
        }
        return player;
    }

 //-----------------------------------------------

    public Player poisoned(Player player) {
        if (player.getHealth() <= 5) {
            player.setHealth(player.getHealth());
        } else {
            player.setHealth(player.getHealth() - 5);
        }

        return player;
    }

    public Player transferHealth(Player donator, Player receiver) {

        if (receiver.getHealth() >= 90) {
            donator.setHealth(donator.getHealth() - 10);
            receiver.setHealth(receiver.getHealth() + 10);
        }

        donator.setHealth(donator.getHealth() - 20);
        receiver.setHealth(receiver.getHealth() + 20);

        return receiver;
    }

    public Player superPotion(Player player) {
        if (player.getHealth() > 50 && player.getHealth() <= 60) {
            player.setHealth(player.getHealth() + 40);
        } else {
            player.setHealth(player.getHealth() + 50);
        }
        return player;
    }

    public Player increaseMaxHP(Player player) {
        player.setHealth(120);
        return player;
    }
}
