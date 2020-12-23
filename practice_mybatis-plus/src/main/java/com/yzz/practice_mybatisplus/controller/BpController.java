package com.yzz.practice_mybatisplus.controller;

import com.yzz.practice_mybatisplus.dao.StuDao;
import com.yzz.practice_mybatisplus.entity.Stu;
import com.yzz.practice_mybatisplus.service.StuService;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
public class BpController {

	@Resource
	private StuDao stuDao;
	
	@Resource
	private StuService stuService;

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
	
	@ResponseBody
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public List<Stu> getAll(){
		return stuDao.getAll();
	}
	
	@ResponseBody
	@RequestMapping(value = "/getOne", method = RequestMethod.GET)
	public Stu getOne(@RequestParam(value = "id") int id){
		return stuService.getOne(id);
	}

	@ResponseBody
	@RequestMapping(value = "/insertOne", method = RequestMethod.POST)
	public Integer insertOne(@RequestBody @Valid Stu stu){
		return stuDao.insert(stu);
	}
}
