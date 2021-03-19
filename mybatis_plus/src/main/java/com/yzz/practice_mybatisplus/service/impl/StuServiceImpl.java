package com.yzz.practice_mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzz.hub.vo.ResultVO;
import com.yzz.practice_mybatisplus.dao.StuDao;
import com.yzz.practice_mybatisplus.entity.Stu;
import com.yzz.practice_mybatisplus.service.StuService;
import com.yzz.hub.vo.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 注意这个地方继承了ServiceImpl，这个集成了一些常用方法，只是方便了controller调用service
 * 但是一般的业务逻辑都在service中进行，所以其实还是需要对ServiceImpl的结果进行再加工
 */
@Service
public class StuServiceImpl extends ServiceImpl<StuDao, Stu> implements StuService {
	
	@Resource
	private StuDao stuDao;
	
	@Override
	public List<Stu> getAll() {
		return stuDao.getAll();
	}
	
	@Override
	public Stu getOne(int id) {
		QueryWrapper<Stu> qw = new QueryWrapper<Stu>();
		qw.eq("id", id);
		return stuDao.selectOne(qw);
	}

	/**
	 * mybatis-plus的乐观锁
	 * 有点鸡肋，还得先查询才能更新
	 * @param id
	 * @param stu
	 * @return
	 */
	@Override
	public Integer updateAfterSelect(Long id, Stu stu) {
		Stu stuOld = stuDao.selectById(id);
		stuOld.setAge(stu.getAge());
		stuOld.setName(stu.getName());
		stuOld.setGender(stu.getGender());
		stuOld.setId(stu.getId());
		return stuDao.updateById(stuOld);
	}
	
	@Override
	public ResultVO selectBatch(int pageCur, int pageSize) {
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
		
		return new ResultVO(true, 200, "查询成功", pageData);
	}

	@Override
	public Integer insertStu(Stu stu) {
		return stuDao.insert(stu);
	}
}
