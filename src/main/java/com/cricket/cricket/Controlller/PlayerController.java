package com.cricket.cricket.Controlller;


import com.cricket.cricket.Entity.Player;
import com.cricket.cricket.Entity.Team;
import com.cricket.cricket.Repositories.PlayerRepository;
import com.cricket.cricket.Repositories.TeamsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamsRepository teamRepository;

    @Autowired
    private ModelMapper modelMapper;



    @PostMapping("/addPlayer")
    public Player addPlayer(@RequestBody Player player) {

        return playerRepository.save(player);
    }

    @GetMapping("/getPlayer/{playerId}")
    public Optional<Player> getPlayer(@PathVariable int playerId) {

        return playerRepository.findById(playerId);
    }
    @GetMapping("/getPlayers")
    public List<Player> getPlayers() {

        return playerRepository.findAll();
    }
    @DeleteMapping("/deletePlayer/{playerId}")
    public void deletePlayer(@PathVariable int playerId) {
        playerRepository.deleteById(playerId);
    }

}

