package de.dhbw.turniermanager.domain.repository;

import java.util.List;

import de.dhbw.turniermanager.domain.fighter.Fighter;

public interface FighterRepository {
    void save(Fighter fighter);
    List<Fighter> findAll();
}