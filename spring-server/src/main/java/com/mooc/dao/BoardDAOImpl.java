package com.mooc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.mooc.entity.Member;

public class BoardDAOImpl {
	@PersistenceContext
	private EntityManager em;
}
