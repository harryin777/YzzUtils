package com.yzz.practice_mybatisplus.controller;

import com.yzz.hub.vo.ResultVO;
import com.yzz.practice_mybatisplus.dao.StuDao;
import com.yzz.practice_mybatisplus.entity.Stu;
import com.yzz.practice_mybatisplus.service.StuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Api(tags = "Mybatis-plus服务")
@Slf4j
@Controller
public class BpController {

	@Resource
	private StuDao stuDao;
	
	@Resource
	private StuService stuService;

	@ApiOperation("表单提交设置stu，校验参数")
	@ResponseBody
	@RequestMapping(value = "/setStu", method = RequestMethod.POST)
	public String setStu(@Valid Stu stu, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return bindingResult.toString();
		}
		return String.valueOf(stuDao.updateById(stu));
	}

	@ApiOperation("json格式提交设置stu，校验参数")
	@ResponseBody
	@RequestMapping(value = "/setStu2", method = RequestMethod.POST)
	public String setStu2(@Valid @RequestBody  Stu stu, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return bindingResult.toString();
		}
		return String.valueOf(stuDao.updateById(stu));
	}

	@ApiOperation("获取全部stu")
	@ResponseBody
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public List<Stu> getAll(){
		return stuDao.getAll();
	}

	@ApiOperation("单个查询，校验参数")
	@ResponseBody
	@RequestMapping(value = "/getOne", method = RequestMethod.GET)
	public Stu getOne(@RequestParam(value = "id") int id){
		return stuService.getOne(id);
	}

	@ApiOperation("插入，校验参数")
	@ResponseBody
	@RequestMapping(value = "/insertOne", method = RequestMethod.POST)
	public Integer insertOne(@RequestBody @Valid Stu stu){

//		return stuDao.insert(stu);
		return stuService.insertStu(stu);
	}

	@ApiOperation("更新，校验参数")
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Integer update(@RequestBody @Valid Stu stu){
		return stuDao.updateById(stu);
	}

//	@ApiOperation("更新，乐观锁，校验参数")
//	@ResponseBody
//	@RequestMapping(value = "/supdate", method = RequestMethod.POST)
//	public Integer supdate(@RequestBody @Valid Stu stu){
//		return stuService.updateAfterSelect(stu.getId(), stu);
//	}

	@ApiOperation("分页查询")
	@ResponseBody
	@RequestMapping(value = "/selectBatch", method = RequestMethod.GET)
	public ResultVO selectBatch(@RequestParam int pageCur, @RequestParam int pageSize){
		return stuService.selectBatch(pageCur, pageSize);
	}
}
