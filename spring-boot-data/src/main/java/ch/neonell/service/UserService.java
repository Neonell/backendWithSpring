package ch.neonell.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.neonell.dao.UserRepository;
import ch.neonell.dto.UserDTO;
import ch.neonell.model.User;

/**
 * This class is used for the communication between the Rest controller and the user repository
 * 
 * We can directly call the user repository from the rest controller
 * But if you need to had some more logic to it it's good to use a service inbetween so you don't
 * have to make your controller too complex.
 * 
 * Here I used this service class in order to make the conversion between dto and entity
 * and also to demonstrate how to define a service (which is actually an application component)
 * 
 * @author fnell
 *
 */
@Service("userService")
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public void addUsers(UserDTO user) {

	}

	public List<UserDTO> getUsers() {
		List<UserDTO> userList = new ArrayList<>();
		for (User user : userRepository.findAll()) {
			UserDTO userDTO = new UserDTO();
			userDTO.setId(user.getId());
			userDTO.setName(user.getName());
			userDTO.setEmail(user.getEmail());
			userDTO.setDate(user.getDate());
			userList.add(userDTO);
		}
		return userList;
	}

	public UserDTO addUser(UserDTO userDTO) {
		User result = userRepository.save(new User(userDTO.getName(), userDTO.getEmail()));
		if (result != null) {
			UserDTO addedUser = new UserDTO();
			addedUser.setId(result.getId());
			addedUser.setName(result.getName());
			addedUser.setEmail(result.getEmail());
			addedUser.setDate(result.getDate());
			return userDTO;
		} else {
			return null;
		}
	}

	public UserDTO getUser(long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			UserDTO userDTO = new UserDTO();
			userDTO.setId(user.get().getId());
			userDTO.setName(user.get().getName());
			userDTO.setEmail(user.get().getEmail());
			userDTO.setDate(user.get().getDate());
			return userDTO;
		} else {
			return null;
		}
	}

}
