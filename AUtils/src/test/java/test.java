import com.yzz.hub.utils.SerializeUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @ClassName test
 * @Author yky
 * @Date 2021/3/10
 * @Version 1.0
 */
public class test {
	
	@DisplayName("测试序列化utils")
	@Test
	void init(){
		SerializeUtils.serizlize("123");
	}
}
