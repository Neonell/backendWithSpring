package ch.neonell.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.neonell.dao.UserRepository;
import ch.neonell.dto.UserDTO;
import ch.neonell.model.User;

@Service("userService")
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public void addUsers(UserDTO user) {

	}

	public List<UserDTO> getUsers() {
		List<UserDTO> userList = new ArrayList<>();
		for(User user : userRepository.findAll()){
			UserDTO userDTO = new UserDTO();
			userDTO.setId(user.getId());
			userDTO.setName(user.getName());
			userDTO.setEmail(user.getEmail());
			userDTO.setDate(user.getDate());
			userList.add(userDTO);
		}
		return userList;
	}


}
