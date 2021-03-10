import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @ClassName test
 * @Author yky
 * @Date 2021/3/4
 * @Version 1.0
 */

public class test {
	
	@Test
	void EncodePassword(){
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		//$2a$10$EQN.9UH7b4ENTZ2uU4nQ7.0lPvI8sWD6iyrM8VZAhi9Dwn5U2Tbgu
		System.out.println(passwordEncoder.encode("123"));
	}
}
