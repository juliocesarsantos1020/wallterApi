package br.com.jcsistemas.wallter.controller;

import br.com.jcsistemas.wallter.dto.UserDto;
import br.com.jcsistemas.wallter.entity.User;
import br.com.jcsistemas.wallter.service.api.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {

	public static final long ID = 1L;
	private static final String EMAIL = "email@teste.com.br";
	private static final String NAME = "User Test";
	private static final String PASSWORD = "123456";
	private static final String URL = "/user";
	private ObjectMapper mapper;

	@Before
	public void setUp() {
		this.mapper = new ObjectMapper();
	}

	@MockBean
	private UserService service;
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testSave() throws Exception {
		BDDMockito.given(service.save(Mockito.any(User.class))).willReturn(getMockUser());

		mockMvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayLoad(ID, EMAIL, NAME, PASSWORD))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.data.id").value(ID))
			.andExpect(jsonPath("$.data.email").value(EMAIL))
			.andExpect(jsonPath("$.data.name").value(NAME))
			.andExpect(jsonPath("$.data.password").value(PASSWORD));
	}

	@Test
	public void testSaveInvaldUser() throws JsonProcessingException, Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayLoad(ID, "email", NAME, PASSWORD))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.errors[0]").value("Email inválido"));
	}

	private User getMockUser() {
		return User.builder()
			.id(ID)
			.email(EMAIL)
			.name(NAME)
			.password(PASSWORD)
			.build();
	}

	private String getJsonPayLoad(Long id, String email, String name, String password) throws JsonProcessingException {
		return mapper.writeValueAsString(UserDto.builder().id(id).email(email).name(name).password(password).build());
	}
}
