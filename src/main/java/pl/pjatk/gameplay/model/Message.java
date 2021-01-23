package pl.pjatk.gameplay.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    // IntelliJ nie widzi player_id bo nie przypisaliśmy bazy danych do intelliJ'a
    @JoinColumn(name = "player_id")
    @ManyToOne
    @JsonIgnore
    private Player player;

    public Message(String content, Player player) {
        this.content = content;
        this.player = player;
    }

    public Message() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
