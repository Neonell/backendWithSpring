package ch.neonell.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ch.neonell.dto.UserDTO;
import ch.neonell.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/getUser")
    public List<UserDTO> greeting() {
		return userService.getUsers();
    }
}
