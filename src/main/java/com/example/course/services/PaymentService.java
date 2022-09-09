package com.example.course.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.course.entities.Payment;
import com.example.course.entities.Payment;
import com.example.course.repositories.PaymentRepository;
import com.example.course.services.exceptions.DatabaseException;
import com.example.course.services.exceptions.ResourceNotFoundException;

//precisa registrar essa classe no Spring pra q o autowired dela funcione
//@Component
@Service
public class PaymentService {
	@Autowired
	private PaymentRepository repository;

	public List<Payment> findAll() {
		return repository.findAll();
	}

	public Payment findById(Long id) {
		Optional<Payment> obj = repository.findById(id);
		return obj.get();
	}
	
	
	
}
