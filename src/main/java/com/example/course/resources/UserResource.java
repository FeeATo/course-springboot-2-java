package com.example.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.course.entities.User;
import com.example.course.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {

	@Autowired
	private UserService service;
	
	//tipo que retorna respostas de requisições web
	@GetMapping //Get pq retorna requisições http GET
	public ResponseEntity<List<User>> findAll(){
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj){ //requestBody pq é uma variável que vai ser passada no corpo da requisição
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);//.created retorna o código 201 do HTTP e precisa de um URI como parâmetro 
	}
	
	@DeleteMapping(value="/{id}") //void pq n retorna nd quando deleta
	public ResponseEntity<Void> delete(@PathVariable Long id){ //path variable pq é uma variável que vai ser passada junto da url
		service.delete(id);
		return ResponseEntity.noContent().build(); //noContent retorna uma resposta vazia, com o código 204
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj){
		obj =  service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
}
