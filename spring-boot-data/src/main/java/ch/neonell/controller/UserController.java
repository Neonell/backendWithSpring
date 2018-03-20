package ch.neonell.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ch.neonell.dto.UserDTO;
import ch.neonell.service.UserService;

/**
 * Rest Controller for the user request
 * 
 * 
 * @author fnell
 *
 */
@RestController
public class UserController {

	// we use a service layer in order to communicate with the repository, but
	// we could also have directly wired the repository himself
	@Autowired
	private UserService userService;

	@GetMapping("/getUsers")
	public List<UserDTO> getUsers() {
		return userService.getUsers();
	}

	@GetMapping("/getUser/{userId}")
	public UserDTO getUser(@PathVariable long userId) {
		return userService.getUser(userId);
	}

	@PostMapping
	public ResponseEntity<?> addUser(@RequestBody UserDTO user) {

		UserDTO userDTO = userService.addUser(user);

		if (userDTO != null) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/getUser/{id}")
					.buildAndExpand(userDTO.getId()).toUri();

			return ResponseEntity.created(location).build();
		} else {
			return ResponseEntity.noContent().build();
		}
	}
}
