package ch.neonell.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import ch.neonell.Application;
import ch.neonell.dao.UserRepository;
import ch.neonell.dto.UserDTO;
import ch.neonell.model.User;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

/**
 * This is a test class for the user service
 * 
 * We use an h2 database for the tests who is created everytime we rerun the
 * tests
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

	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	private List<User> userList = new ArrayList<>();

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {

		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().orElse(null);

		assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
	}

	/**
	 * init the tests datas
	 */
	@Before
	public void setup() {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
		// Error here, can be run with mvn test but the save doesnt work and
		// make a
		// nullpointer exception on the id field from user...
		this.userList.add(userRepository.save(new User("test", "test@test.ch")));
		System.out.println("user added");
		this.userList.add(userRepository.save(new User("test2", "test2@test2.ch")));
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
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].id", is(this.userList.get(0).getId().intValue())))
				.andExpect(jsonPath("$[0].name", is("test"))).andExpect(jsonPath("$[0].email", is("test@test.ch")))
				.andExpect(jsonPath("$[1].id", is(this.userList.get(1).getId().intValue())))
				.andExpect(jsonPath("$[1].name", is("test2"))).andExpect(jsonPath("$[1].email", is("test2@test2.ch")));
	}

	@Test
	public void createUser() throws Exception {
		UserDTO user = new UserDTO();
		user.setName("test3");
		user.setEmail("test3@test3.ch");
		Calendar cal = Calendar.getInstance();
		user.setDate(cal.getTime());
		String userJson = json(user);

		this.mockMvc.perform(post("/addUser").contentType(contentType).content(userJson))
				.andExpect(status().isCreated());
	}

	protected String json(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();

		this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);

		return mockHttpOutputMessage.getBodyAsString();
	}
}
