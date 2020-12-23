# mybatis-plus的使用

## 自动填充功能

entity

```java
public class Stu implements Serializable {

	@TableId(type = IdType.ID_WORKER)
	@NotNull(message = "id不能为空")
	private Long id;
	
	@Size(max = 10, message = "姓名长度不能超过10")
	private String name;
	
	@Min(value = 5, message = "不能小于5岁")
	@Max(value = 25, message = "不能超过25岁")
	private int age;
	
	@NotNull(message = "id不能为空")
	private String gender;

    //注意以下这两个
	@TableField(fill = FieldFill.INSERT)
	private Date insert_time;

	@TableField(fill = FieldFill.UPDATE)
	private Date update_time;
}
```

config，配置一个handler

```java
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
	@Override
	public void insertFill(MetaObject metaObject) {
		log.info("start autofill insertTime...");
		this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
		log.info("end autofill insertTime...");
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		log.info("start autofill updateTime...");
		this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
		log.info("end autofill updateTime...");
	}
}
```



# @Valid的使用

pom如下

```xml
<dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-validation</artifactId>
                <version>2.3.6.RELEASE</version>
            </dependency>
```

controller，注意这个BindingResult的使用

```java
@Slf4j
@Controller
public class BpController {

	@Resource
	private StuDao stuDao;

	@ResponseBody
	@RequestMapping(value = "/setStu", method = RequestMethod.POST)
	public String setStu(@Valid Stu stu, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return bindingResult.toString();
		}
		return String.valueOf(stuDao.updateById(stu));
	}
	
	@ResponseBody
	@RequestMapping(value = "/setStu2", method = RequestMethod.POST)
	public String setStu2(@Valid @RequestBody  Stu stu, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return bindingResult.toString();
		}
		return String.valueOf(stuDao.updateById(stu));
	}
}
```

entity

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stu implements Serializable {

	@TableId(type = IdType.ID_WORKER)
	@NotNull(message = "id不能为空")
	private Long id;
	
	@Size(max = 10, message = "姓名长度不能超过10")
	private String name;
	
	@Min(value = 5, message = "不能小于5岁")
	@Max(value = 25, message = "不能超过25岁")
	private int age;
	
	@NotNull(message = "id不能为空")
	private String gender;
}
```



```java
@Null 限制只能为null
@NotNull 限制必须不为null
@AssertFalse 限制必须为false
@AssertTrue 限制必须为true
@DecimalMax(value) 限制必须为一个不大于指定值的数字
@DecimalMin(value) 限制必须为一个不小于指定值的数字
@Digits(integer,fraction) 限制必须为一个小数，且整数部分的位数不能超过integer，小数部分的位数不能超过fraction
@Future 限制必须是一个将来的日期
@Max(value) 限制必须为一个不大于指定值的数字
@Min(value) 限制必须为一个不小于指定值的数字
@Past 限制必须是一个过去的日期
@Pattern(value) 限制必须符合指定的正则表达式
@Size(max,min) 限制字符长度必须在min到max之间
@Past 验证注解的元素值（日期类型）比当前时间早
@NotEmpty 验证注解的元素值不为null且不为空（字符串长度不为0、集合大小不为0）
@NotBlank 验证注解的元素值不为空（不为null、去除首位空格后长度为0），不同于@NotEmpty，@NotBlank只应用于字符串且在比较时会去除字符串的空格
@Email 验证注解的元素值是Email，也可以通过正则表达式和flag指定自定义的email格式
需要注意每个注解对应的数据类型
```

