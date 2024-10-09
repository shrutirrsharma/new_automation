package com.example.automation.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.example.automation.Model.LocationModel;
import com.example.automation.Repository.LocationRepositoryImpl;
import com.example.automation.Repository.iLocationRepos;
@Service
public class LocationService implements iLocationRepos {
	
	
	  @Autowired(required=false) 
	  LocationRepositoryImpl locationRepository;
	  iLocationRepos ilocationRepository;
	   
	 
	@Override
	public int fetchAllLocation() { 
		return ilocationRepository.fetchAllLocation(); 
		//return null;   
	}
	
	@Override
	public List<LocationModel> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<LocationModel> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<LocationModel> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends LocationModel> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public <S extends LocationModel> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends LocationModel> List<S> saveAllAndFlush(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void deleteAllInBatch(Iterable<LocationModel> entities) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public LocationModel getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public LocationModel getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public LocationModel getReferenceById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends LocationModel> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends LocationModel> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Page<LocationModel> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends LocationModel> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Optional<LocationModel> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(LocationModel entity) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteAll(Iterable<? extends LocationModel> entities) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public <S extends LocationModel> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends LocationModel> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends LocationModel> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public <S extends LocationModel> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public <S extends LocationModel, R> R findBy(Example<S> example,
			Function<FetchableFluentQuery<S>, R> queryFunction) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
