# mybatis-plus的使用

## 主键自增

mysql一定要配置主键自增才可以，这时候输入的id就无效了。

```java
@TableId( type = IdType.AUTO)
	private Long id;
```

可以配置主键增加的策略

```yml
mybatis-plus:
  mapper-locations: /mapper/*.xml
  type-aliases-package: com.yzz.practice_mybatisplus.entity
  global-config:
    id-type: 0
    field-strategy: 1
    db-column-underline: true
    refresh-mapper: true
    capital-mode: true
  #    配置自增主键的策略
    key-generator: com.baimidou.mybatisplus.incrementer.OracleKeyGenerator
```



## 自动填充功能

entity

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stu implements Serializable {

	@TableId(type = IdType.ID_WORKER)
	private int id;
	
	@Size(max = 10, message = "姓名长度不能超过10")
	private String name;
	
	@Min(value = 5, message = "不能小于5岁")
	@Max(value = 25, message = "不能超过25岁")
	private int age;
	
	@NotNull(message = "id不能为空")
	private String gender;

	@TableField(value = "insert_time", fill = FieldFill.INSERT)
	private Date insertTime;
	//一般udpate也需要配置insert
	@TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;
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
        //注意这里的class类型需要和entity里的属性类型保持一致
        //同时注意是update还是insert
        //在这里的属性，注解必须要有insert，哪怕是insert_update也可以
		this.strictInsertFill(metaObject, "insertTime", Date.class, new Date());
		this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
		this.strictInsertFill(metaObject, "version", Integer.class, 1);
		log.info("end autofill insertTime...");
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		log.info("start autofill updateTime...");
		this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
		log.info("end autofill updateTime...");
	}
}
```



```java
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
	@Override
	public void insertFill(MetaObject metaObject) {
		log.info("start autofill insertTime...");
		this.strictInsertFill(metaObject, "insertTime", Date.class, new Date());
		this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
		log.info("end autofill insertTime...");
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		log.info("start autofill updateTime...");
		this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
		log.info("end autofill updateTime...");
	}
}
```

### 注意事项

- 使用BaseMapper中的方法可以填充数据，但是使用自定义的Mapper.xml中写的语句却不生效。后来发现填充数据是在执行完自定义的sql之后填充的数据，所以在写sql语句的时候，需要自动填充的字段不可以使用非空判
- handler中的数据类型需要和实体类中的保持一致



## 乐观锁的功能

和官网不一样，注意是需要有一个interceptor

```java
Configuration
public class Mybatis_plusConfig {
	
	public OptimisticLockerInnerInterceptor OptimisticLockerInnerInterceptor(){
		return new OptimisticLockerInnerInterceptor();
	}
	
	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor(){
		MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
		mybatisPlusInterceptor.addInnerInterceptor(this.OptimisticLockerInnerInterceptor());
		return mybatisPlusInterceptor;
	}
}
```

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stu implements Serializable {
	
	private int id;
	
	@Size(max = 10, message = "姓名长度不能超过10")
	private String name;
	
	@Min(value = 5, message = "不能小于5岁")
	@Max(value = 85, message = "不能超过25岁")
	private int age;
	
	@NotNull(message = "id不能为空")
	private String gender;

	@TableField(value = "insert_time", fill = FieldFill.INSERT)
	private Date insertTime;
	
	@TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;
	
    //在实体类增加一个version属性，表里也需要配置，注意不支持string类型
	@Version
	@TableField(value = "version", fill = FieldFill.INSERT)
	private Integer version;
}
```

最无语的是，必须要先查出来再去更新才可以类似这样，不可以直接用updateById（controller封装的bean）

```java
@Override
	public Integer updateAfterSelect(int id, Stu stu) {
		Stu stuOld = stuDao.selectById(id);
		stuOld.setAge(stu.getAge());
		stuOld.setName(stu.getName());
		stuOld.setGender(stu.getGender());
		stuOld.setId(stu.getId());
		return stuDao.updateById(stuOld);
	}
```



## 性能分析插件



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



# pagehelper的使用

pom，这个是springboot开箱即用的，如果是单独的pagehelper还需要注入

```xml
		   <!-- pagehelper begin -->
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper-spring-boot-starter</artifactId>
    <version>1.3.0</version>
</dependency>
            <!-- pagehelper end -->
```

注入类似这种

```java
@Configuration
public class mybatisConfiguration {
@Bean
public PageHelperpageHelper(){
    System.out.println("MybatisConfiguration.pageHelper()");
     PageHelper pageHelper =new PageHelper();
     Properties properties =new Properties();
     properties.setProperty("offsetAsPageNum","true");
     properties.setProperty("rowBoundsWithCount","true");
     properties.setProperty("reasonable","true");
     pageHelper.setProperties(properties);
     return pageHelper;
 }
}
```

yml

```yml
pagehelper:
  # dialect: ①
  # 分页插件会自动检测当前的数据库链接，自动选择合适的分页方式（可以不设置）
  helper-dialect: mysql
  # 上面数据库设置后，下面的设置为true不会改变上面的结果（默认为true）
  auto-dialect: true
  page-size-zero: false # ②
  reasonable: true # ③
  # 默认值为 false，该参数对使用 RowBounds 作为分页参数时有效。（一般用不着）
  offset-as-page-num: false
  # 默认值为 false，RowBounds是否进行count查询（一般用不着）
  row-bounds-with-count: false
  #params: ④
  #support-methods-arguments: 和params配合使用，具体可以看下面的讲解
  # 默认值为 false。设置为 true 时，允许在运行时根据多数据源自动识别对应方言的分页
  auto-runtime-dialect: false # ⑤
  # 与auto-runtime-dialect配合使用
  close-conn: true
  # 用于控制默认不带 count 查询的方法中，是否执行 count 查询，这里设置为true后，total会为-1
  default-count: false
  #dialect-alias: ⑥
```

具体使用，划重点，而且使用分页的sql语句，不能在句尾加 ; 号，注意！！！

```java
@Override
	public ResultDTO selectBatch(int pageCur, int pageSize) {
		PageHelper.startPage(pageCur, pageSize);
//		List<Stu> listStu = stuDao.selectList(new QueryWrapper<>());
		List<Stu> listStu = stuDao.getAll();
		PageInfo<Stu> result = new PageInfo<>(listStu);
		long total = result.getTotal();
		int pages = result.getPages();
		PageData pageData = new PageData();
		pageData.put("total",total);
		pageData.put("pages", pages);
		pageData.put("currentPage", result.getPageNum());
		pageData.put("list", listStu);
		
		return new ResultDTO(true, 200, "查询成功", pageData);
	}
```

