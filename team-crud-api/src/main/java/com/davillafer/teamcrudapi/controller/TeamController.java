package com.davillafer.teamcrudapi.controller;

import com.davillafer.teamcrudapi.model.Team;
import com.davillafer.teamcrudapi.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
// annotation used to define a controller and to indicate that the return value of the methods should be bound to the web response body.
@RequestMapping("/api") // declares that all Apis’ url in the controller will start with /api
public class TeamController {

    final TeamRepository teamRepository;

    @Autowired
    public TeamController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @GetMapping("/v1/teams")
    public ResponseEntity<List<Team>> getAllTeams(@RequestParam(required = false) String name) {
        try {
            List<Team> teams = new ArrayList<>();

            if (name == null)
                teams.addAll(teamRepository.findAll());
            else
                teams.addAll(teamRepository.findByNameContaining(name));

            if (teams.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(teams, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/v1/teams/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable("id") long id) {
        Optional<Team> teamData = teamRepository.findById(id);

        return teamData.map(team -> new ResponseEntity<>(team, HttpStatus.OK)).orElseGet(
                () -> new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @PostMapping("/v1/teams")
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        try {
            Team _team = teamRepository.save(new Team(team.getName(), team.getSeason(), team.getCategory()));
            return new ResponseEntity<>(_team, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/v1/teams/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable("id") long id, @RequestBody Team team) {
        Optional<Team> teamData = teamRepository.findById(id);

        if (teamData.isPresent()) {
            Team _team = teamData.get();
            _team.setName(team.getName());
            _team.setSeason(team.getSeason());
            _team.setCategory(team.getCategory());
            return new ResponseEntity<>(teamRepository.save(_team), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/v1/teams/{id}")
    public ResponseEntity<HttpStatus> deleteTeam(@PathVariable("id") long id) {
        try {
            teamRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/v1/teams")
    public ResponseEntity<HttpStatus> deleteAllTeams() {
        try {
            teamRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
