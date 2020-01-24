package br.com.jcsistemas.wallter.repository;

import br.com.jcsistemas.wallter.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryTest {

	private static final String NAME = "Teste";
	private static final String EMAIL = "teste@teste.com.br";
	private static final String PASSWORD = "123456";

	@Autowired
	private UserRepository repository;

	private User user;

	@Before
	public void setUp(){
		this.user = User.builder().name(NAME).email(EMAIL).password(PASSWORD).build();
	}

	@After
	public void tearDown() {
		repository.deleteAll();
	}

	@Test
	public void testSave() {
		User response = repository.save(user);
		assertNotNull(response);
	}

	@Test
	public void testFindByEmail() {
		repository.save(user);
		Optional<User> response = repository.findByEmail(EMAIL);

		assertTrue(response.isPresent());
		assertEquals(response.get().getEmail(), EMAIL);
	}
}
