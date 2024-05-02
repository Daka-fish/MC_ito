package net.tv.twitch.chrono_fish.ito;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Deck {
    int count;
    List<Card> deck = new ArrayList<>();
    List<Card> field = new ArrayList<>();
    HashMap<Player, Card> map = new HashMap<>();
}
