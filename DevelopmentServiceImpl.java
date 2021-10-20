package com.iiht.training.ratings.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.training.ratings.dto.DevelopmentDto;
import com.iiht.training.ratings.entity.Development;
import com.iiht.training.ratings.exceptions.DevelopmentNotFoundException;
import com.iiht.training.ratings.repository.DevelopmentRepository;
import com.iiht.training.ratings.service.DevelopmentService;

@Service
public class DevelopmentServiceImpl implements DevelopmentService {

	@Autowired
	private DevelopmentRepository repository;

	@Override
	public DevelopmentDto createDevelopment(DevelopmentDto developmentDto) {
		Development development = new Development();
		BeanUtils.copyProperties(developmentDto, development);
		repository.save(development);
		return developmentDto;
	}

	@Override
	public DevelopmentDto updateDevelopment(DevelopmentDto developmentDto) {
		Development development = new Development();
		BeanUtils.copyProperties(developmentDto, development);
		repository.save(development);
		return developmentDto;
	}

	@Override
	public boolean deleteDevelopment(Long developmentId) {
		Development development = new Development();
		DevelopmentDto developmentById = getDevelopmentById(developmentId);
		BeanUtils.copyProperties(developmentById , development);
		repository.delete(development);
		return true ;
	}

	@Override
	public DevelopmentDto getDevelopmentById(Long developmentId) {
		Optional<Development> development = repository.findById(developmentId);
		if (!development.isPresent()) {
			DevelopmentDto developmentDto = new DevelopmentDto();
			BeanUtils.copyProperties(development.get(), developmentDto);
			return developmentDto;
		} else {
			throw new DevelopmentNotFoundException("Political Leader with id " + developmentId + " not found");
		}

	}

	@Override
	public List<DevelopmentDto> getAllDevelopments() {
		List<Development> developments = repository.findAll();
		List<DevelopmentDto> developmentDtos = new ArrayList<>();
		for (Development leader : developments) {
			DevelopmentDto developmentDto = new DevelopmentDto();
			BeanUtils.copyProperties(leader, developmentDto);
			
		}
		return developmentDtos;
	}

	@Override
	public List<DevelopmentDto> getAllDevelopmentsByLeaderId(Long politicalLeaderId) {
		List<Development> developments = repository.findByPoliticalLeaderId(politicalLeaderId);
		List<DevelopmentDto> developmentDtos = new ArrayList<>();
		for (Development leader : developments) {
			DevelopmentDto developmentDto = new DevelopmentDto();
			BeanUtils.copyProperties(leader, developmentDto);
			developmentDtos.add(developmentDto);
		}
		return developmentDtos;
	}

}
