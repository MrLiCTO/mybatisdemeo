package com.tztd.app;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.tztd.app.mapper.PersonCustomMapper;
import com.tztd.app.mapper.PersonMapper;
import com.tztd.app.model.Person;
import com.tztd.app.pojo.PersonPojo;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class MybatisDemeoApplicationTests {
	@Autowired
	private PersonCustomMapper personCustomMapper;

	@Autowired
	private PersonMapper personMapper;

	@Test
	public void contextLoads() {
		/*for (int i = 1; i < 100; i++) {
			Person person=new Person();
			person.setName("可以"+i+"可以");
			person.setId(UUID.randomUUID().toString().replace("-",""));
			person.setAge(i);
			person.setSex("男");
			personMapper.insert(person);
		}*/
		PersonPojo personPojo=new PersonPojo();
		Person person=new Person();
		person.setName("可以");
		personPojo.setPerson(person);
		personPojo.setPageNum(10L);
		personPojo.setPageSize(10L);
		//PageHelper.startPage(1,10);
		//List<Person> list = personCustomMapper.findList("%可以%");
		//List<Person> list = personCustomMapper.selectAllByName(personPojo);
		List<Person> list = personCustomMapper.findListByPojo(personPojo);
		//List<Person> list = personMapper.selectByExample(new PersonExample());
		PageInfo<Person> pageInfo = new PageInfo<>(list,12);
		List<Person> persons = pageInfo.getList();
		long total = pageInfo.getTotal();
		int pages = pageInfo.getPages();
		int pageNum = pageInfo.getPageNum();
		int nextPage = pageInfo.getNextPage();
		int navigatePages = pageInfo.getNavigatePages();
		log.info(JSON.toJSONString(pageInfo));
		System.out.println(JSON.toJSONString(pageInfo));
	}

}
