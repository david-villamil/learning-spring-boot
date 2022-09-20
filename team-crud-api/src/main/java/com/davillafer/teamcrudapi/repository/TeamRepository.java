package com.davillafer.teamcrudapi.repository;

import com.davillafer.teamcrudapi.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    /**
     * Obtains teams containing the name used.
     * @param name, to find.
     * @return List of teams containing the name.
     */
    List<Team> findByNameContaining(String name);
}
