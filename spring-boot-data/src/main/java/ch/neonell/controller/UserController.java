package ch.neonell.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.neonell.dto.UserDTO;
import ch.neonell.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/getUsers")
    public List<UserDTO> getUsers() {
		return userService.getUsers();
    }
}
