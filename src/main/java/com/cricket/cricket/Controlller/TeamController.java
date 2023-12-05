package com.cricket.cricket.Controlller;

import com.cricket.cricket.Entity.Inning;
import com.cricket.cricket.Entity.Player;
import com.cricket.cricket.Entity.Team;
import com.cricket.cricket.Entity.Type;
import com.cricket.cricket.Repositories.PlayerRepository;
import com.cricket.cricket.Repositories.TeamsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class TeamController {

    @Autowired
    private TeamsRepository teamsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PlayerRepository playerRepository;


    @GetMapping("/getAllTeams")
    public List<Team> getAllTeams(){
        return teamsRepository.findAll();

    }

    @GetMapping("/getTeam/{teamId}")
    public Optional<Team> getTeam(@PathVariable int teamId){
        return teamsRepository.findById(teamId);
    }

    @PostMapping("/addTeam/{teamId}")
    public Team addTeamPlayers(@RequestBody Team team,@PathVariable int teamId){
        team.setTeamId(teamId);
        List<Player> players=playerRepository.findAll();
        List<Player> teamA=new ArrayList<>();
        List<Player> teamB=new ArrayList<>();
        List<Player> teamAExtras=new ArrayList<>();
        List<Player> teamBExtras=new ArrayList<>();


        for(Player p: players){
            if(p.getTeamId()==1 && p.getPlayerType()==Type.EXTRAS){
                teamAExtras.add(p);
            }
            else if(p.getTeamId()==2 && p.getPlayerType()==Type.EXTRAS){
                teamBExtras.add(p);
            }

            else if(p.getTeamId() == 1) {
                teamA.add(p);
            }
            else{
                teamB.add(p);
            }
        }
        /*if(teamId==1 && teamAExtras.get(0).getPlayerType()==Type.EXTRAS){
            team.setExtras(teamAExtras);
        }
        else if(teamId==2 && players.get(0).getPlayerType()==Type.EXTRAS){
            team.setExtras(teamBExtras);
        }*/
        if(teamId==1){
            team.setPlayers(teamA);
            team.setExtras(teamAExtras);
        }
        else if(teamId==2){
            team.setPlayers(teamB);
            team.setExtras(teamBExtras);
        }


        /*if(teamId==1){
            team.setPlayers(teamA);
        }
        else{
            team.setPlayers(teamB);
        }*/



        return teamsRepository.save(team);
    }

    public Inning inning(Inning type){
        if(type==Inning.FIRST_INNING)
        {
            return Inning.FIRST_INNING;
        }
         else if(type==Inning.SECOND_INNING)  {
             return Inning.SECOND_INNING;
        }

         return null;
    }

    /*public List<Player> addPlayers(List<Player> player){
        Team team1=this.modelMapper.map(player,Team.class);
        Team team2=this.modelMapper.map(player,Team.class);
        if(team1.getTeamName()=="INDIA" && team1.getTeamId()==1 && player.getPlayerType()==Type.EXTRAS){
            team1.getExtras().add(player);
        }
        else if(team1.getTeamName()=="AUSTRALIA" && team1.getTeamId()==2 && player.getPlayerType()==Type.EXTRAS){
            team2.getExtras().add(player);
        }

        else if(team1.getTeamId()==1){
            team1.getPlayers().add(player);
        }
        else{
            team2.getPlayers().add(player);
        }*/


    }

