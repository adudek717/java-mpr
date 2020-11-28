package pl.pjatk.gameplay.service;

import org.springframework.stereotype.Service;
import pl.pjatk.gameplay.model.Player;
import pl.pjatk.gameplay.repository.PlayerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private PlayerRepository playerRepository;
    private DamageService damageService;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    public Optional<Player> findByID(Long playerId) {
        return playerRepository.findById(playerId);
    }

    public Player save(Player player) {
        return playerRepository.save(player);
    }

    public void delete(Player player) {
        playerRepository.delete(player);
    }

    public void deleteById(Long id) {
        playerRepository.deleteById(id);
    }

    public Player update(Player player) {
        Optional<Player> byId = playerRepository.findById(player.getId());
        if(byId.isEmpty()) {
            throw new RuntimeException();
        }
        else {
            return playerRepository.save(player);
        }
    }

    public Player attackPlayer(Long attackerId, Long defenderId){
        Player attacker = findByID(attackerId).get();
        Player defender = findByID(defenderId).get();

        defender = damageService.attackPlayer(attacker, defender);
        return update(defender);
    }
}

