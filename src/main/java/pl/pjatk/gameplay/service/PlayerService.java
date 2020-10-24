package pl.pjatk.gameplay.service;

import org.springframework.stereotype.Service;
import pl.pjatk.gameplay.model.Player;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {

    public List<Player> findAll() {
        List<Player> playerList = new ArrayList<>();
        Player player = new Player();
        Player player2 = new Player();
        player.setAttack(100);
        playerList.add(player);
        playerList.add(player2);
        return playerList;

    }
}
