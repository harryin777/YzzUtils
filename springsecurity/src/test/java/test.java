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
		System.out.println(passwordEncoder.encode("123"));;
	}
}
