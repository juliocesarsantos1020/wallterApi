package br.com.jcsistemas.wallter.controller;

import br.com.jcsistemas.wallter.controller.response.Response;
import br.com.jcsistemas.wallter.dto.UserDto;
import br.com.jcsistemas.wallter.entity.User;
import br.com.jcsistemas.wallter.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping
	public ResponseEntity<Response<UserDto>> create(@Valid @RequestBody UserDto dto, BindingResult result)  {
		Response<UserDto> response = new Response<UserDto>();
		if(result.hasErrors()) {
			String erro = result.getAllErrors().get(0).getDefaultMessage();
			result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		User user = service.save(this.convertDtoToEntity(dto));
		response.setData(this.convertEntityToDto(user));
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
 	}

 	private User convertDtoToEntity(UserDto dto) {
		return User.builder().id(dto.getId()).email(dto.getEmail()).name(dto.getName()).password(dto.getPassword()).build();
	}

	private UserDto convertEntityToDto(User u) {
		return UserDto.builder().id(u.getId()).email(u.getEmail()).name(u.getName()).password(u.getPassword()).build();
	}
}
