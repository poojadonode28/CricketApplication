package com.cricket.cricket.Controlller;

import com.cricket.cricket.Entity.*;
import com.cricket.cricket.Repositories.InningInfoRepository;
import com.cricket.cricket.Repositories.MatchRepository;
import com.cricket.cricket.Repositories.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static com.cricket.cricket.Constants.Constants.*;


@RestController
public class MatchController {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private InningInfoRepository inningInfoRepository;

    @Autowired
    private TeamsRepository teamRepository;

    public static class Pair{
        int runs;
        int wickets;
        public  Pair(int runs,int wickets){
            this.runs=runs;
            this.wickets=wickets;
        }
    }

    public static class winningTeamDetails{
        int maxRuns;
        String winningTeam;
        public  winningTeamDetails(int maxRuns,String winningTeam){
            this.maxRuns=maxRuns;
            this.winningTeam=winningTeam;
        }
    }


    public static Pair scoreGenerator(int overs,List<ScoreInfo> scoreBoard) {
        int runScored = 0;
        int wicket = 0;

        for (int ball = 1; ball <= overs * NO_OF_BALLS; ball++) {

            int currentRun=randomScoreGenerator();

            if (wicket == MAX_WICKETS) {
                break;
            }
            if (currentRun == UPPER_BOUND - 1) {
                wicket += 1;

            } else {
                runScored += currentRun;
            }
            int currentOver=ball/NO_OF_BALLS;
            ScoreInfo s1 = new ScoreInfo(ball, currentOver, currentRun, runScored, wicket);
            scoreBoard.add(s1);


        }
        return new Pair(runScored, wicket);

    }

    @Override
    public String toString() {
        return "MatchController{}";
    }


    private static int randomScoreGenerator(){
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int currentRun = random.nextInt( LOWER_BOUND, UPPER_BOUND);
        return currentRun;
    }

    @PostMapping("/createMatch/{matchId}")
    public Match createMatch(@RequestBody Match match, @PathVariable int matchId){
        List<InningInfo> inningInfos=inningInfoRepository.findAll();
        List<Team> teams=teamRepository.findAll();
        match.setMatchId(matchId);
        match.setInningInfo(inningInfos);
        match.setTeams(teams);
        winningTeamDetails winningScore=winningTeamScore();
        match.setTotalRuns(winningScore.maxRuns);
        match.setWinningTeam(winningScore.winningTeam);
        return matchRepository.save(match);

    }

    @GetMapping("/getAllMatches")
    public List<Match> getMatch(){
        return matchRepository.findAll();
    }

    @GetMapping("/getAllMatches/{matchId}")
    public Optional<Match> getMatchById(@PathVariable int matchId){
        return matchRepository.findById(matchId);
    }

    public winningTeamDetails winningTeamScore(){
        List<InningInfo> info=inningInfoRepository.findAll();
        int runsScored=Integer.MIN_VALUE;
        String winningTeam=null;
        for(InningInfo inningInfo:info){
            if(inningInfo.getRuns()>runsScored){
                runsScored=Math.max(runsScored,inningInfo.getRuns());
                winningTeam=inningInfo.getBattingTeam();
            }
        }
        return new winningTeamDetails(runsScored,winningTeam);
    }

}
