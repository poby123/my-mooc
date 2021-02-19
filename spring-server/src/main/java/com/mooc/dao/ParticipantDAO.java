package com.mooc.dao;

import org.springframework.data.repository.CrudRepository;

import com.mooc.entity.Participant;
import com.mooc.entity.ParticipantId;

public interface ParticipantDAO extends CrudRepository<Participant, ParticipantId>{

}
