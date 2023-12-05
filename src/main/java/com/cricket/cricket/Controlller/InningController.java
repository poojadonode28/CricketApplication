package com.cricket.cricket.Controlller;

import ch.qos.logback.core.joran.sanity.Pair;
import com.cricket.cricket.Entity.Inning;
import com.cricket.cricket.Entity.InningInfo;
import com.cricket.cricket.Entity.ScoreInfo;
import com.cricket.cricket.Entity.Team;
import com.cricket.cricket.Repositories.InningInfoRepository;
import com.cricket.cricket.Repositories.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class InningController {

    @Autowired
    private InningInfoRepository inningInfoRepository;

    @Autowired
    private TeamsRepository teamsRepository;

    @GetMapping("/getInningInfo")
    public List<InningInfo> getInningInfo(){

        return inningInfoRepository.findAll();
    }

    @PostMapping("/addInningInfo/{overs}/{teamId}")
    public InningInfo addInningInfo(@RequestBody InningInfo inningInfo,@PathVariable int overs,@PathVariable int teamId){

        List<ScoreInfo> scoreboard=new ArrayList<>();
        inningInfo.setOvers(overs);
        List<Team> team=teamsRepository.findAll();
        List<String> teamName=new ArrayList<>();
        for(Team t:team){
           teamName.add(t.getTeamName());
        }
        for(Team t1:team) {
            if (teamId==2 && t1.getTeamId()==2) {
                inningInfo.setBattingTeam(t1.getTeamName());
                for (Team t2 : team) {
                    if (t2.getTeamId() == 1) {
                        inningInfo.setFieldingTeam(t2.getTeamName());
                    }
                }
                inningInfo.setInning(Inning.SECOND_INNING);

            }

            else if(teamId==1 && t1.getTeamId()==1){
                inningInfo.setBattingTeam(t1.getTeamName());
                for (Team t2 : team) {
                    if (t2.getTeamId() == 2) {
                        inningInfo.setFieldingTeam(t2.getTeamName());
                    }
                    inningInfo.setInning(Inning.FIRST_INNING);
                }
            }
        }
        MatchController.Pair info1=MatchController.scoreGenerator(overs,scoreboard);
        inningInfo.setRuns(info1.runs);
        inningInfo.setWickets(info1.wickets);
        inningInfo.setTeamId(teamId);
        inningInfo.setScoreInfo(scoreboard);

        return inningInfoRepository.save(inningInfo);
    }
}
