package ch.neonell.controller;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import ch.neonell.Application;
import ch.neonell.dao.UserRepository;
import ch.neonell.model.User;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

/**
 * This is a test class for the user service
 * 
 * @author fnell
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class UserControllerTest {

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;

	private List<User> userList = new ArrayList<>();

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private UserRepository userRepository;

	/**
	 * init the tests datas
	 */
	@Before
	public void setup(){
		 this.mockMvc = webAppContextSetup(webApplicationContext).build();
		 //Error here, can be run with mvn test but the save doesnt work and make a 
		 //nullpointer exception on the id field from user...
		 this.userList.add(userRepository.save(new User("test","test@test.ch")));
		 System.out.println("user added");
		 this.userList.add(userRepository.save(new User("test2","test2@test2.ch")));
		 System.out.println("user added");
	}

	@Test
	public void readSingleUser() throws Exception {
		System.out.println("read single user with id: " + userList.get(0).getId());
		mockMvc.perform(get("/getUser/" + userList.get(0).getId())).andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.id", is(this.userList.get(0).getId().intValue())))
				.andExpect(jsonPath("$.name", is("test"))).andExpect(jsonPath("$.email", is("test@test.ch")));
	}

	@Test
	public void readUsers() throws Exception {
		System.out.println("read single user with id: " + userList.get(0).getId() + " and " + userList.get(1).getId());
		mockMvc.perform(get("/getUsers")).andExpect(status().isOk()).andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].id", is(this.userList.get(0).getId().intValue())))
				.andExpect(jsonPath("$[0].name", is("test"))).andExpect(jsonPath("$[0].email", is("test@test.ch")))
				.andExpect(jsonPath("$[1].id", is(this.userList.get(1).getId().intValue())))
				.andExpect(jsonPath("$[1].name", is("test2"))).andExpect(jsonPath("$[1].email", is("test2@test2.ch")));
	}
	//
	// @Test
	// public void createBookmark() throws Exception {
	// String bookmarkJson = json(new Bookmark(
	// this.account, "http://spring.io", "a bookmark to the best resource for
	// Spring news and information"));
	//
	// this.mockMvc.perform(post("/" + userName + "/bookmarks")
	// .contentType(contentType)
	// .content(bookmarkJson))
	// .andExpect(status().isCreated());
	// }
}
 