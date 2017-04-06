package com.tztd.app;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.tztd.app.mapperOne.PersonCustomOneMapper;
import com.tztd.app.mapperOne.PersonOneMapper;
import com.tztd.app.mapperThree.PersonThreeMapper;
import com.tztd.app.mapperTwo.PersonTwoMapper;
import com.tztd.app.model.Person;
import com.tztd.app.pojo.PersonPojo;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.TransactionManager;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class MybatisDemeoApplicationTests {
    @Autowired
    private PersonCustomOneMapper personCustomMapper;

    @Autowired
    private PersonOneMapper personOneMapper;
    @Autowired
    private PersonTwoMapper personTwoMapper;
    @Autowired
    private PersonThreeMapper personThreeMapper;

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
        PersonPojo personPojo = new PersonPojo();
        Person person = new Person();
        person.setName("可以_like");
        personPojo.setPerson(person);
        personPojo.setPageNum(10L);
        personPojo.setPageSize(10L);
        //PageHelper.startPage(1,10);
        //List<Person> list = personCustomMapper.findList("%可以%");
        //List<Person> list = personCustomMapper.selectAllByName(personPojo);
        List<Person> list = personCustomMapper.findNewListByPojo(personPojo);
        //List<Person> list = personMapper.selectByExample(new PersonExample());
        PageInfo<Person> pageInfo = new PageInfo<>(list, 12);
        List<Person> persons = pageInfo.getList();
        long total = pageInfo.getTotal();
        int pages = pageInfo.getPages();
        int pageNum = pageInfo.getPageNum();
        int nextPage = pageInfo.getNextPage();
        int navigatePages = pageInfo.getNavigatePages();
        log.info(JSON.toJSONString(pageInfo));
        System.out.println(JSON.toJSONString(pageInfo));
    }
    @Autowired
    private TransactionManager transactionManager;//直接注入也可以
    @Test//测试多数据源分布式事务
    @Transactional(rollbackFor = Exception.class)
    public void testJTA() throws Exception{
        /*transactionManager.begin();
            //多数据源操作
        transactionManager.rollback();

        transactionManager.commit();*/

        Person person1 = new Person();
        person1.setName("可以_like");
        person1.setAge(10);
        person1.setSex("女");
        person1.setId("uuu4");
        personOneMapper.insert(person1);

        Person person2 = new Person();
        person2.setName("可以_like");
        person2.setAge(10);
        person2.setSex("女");
        person2.setId("uuu5");
        personTwoMapper.insert(person2);

        Person person3 = new Person();
        person3.setName("可以_like");
        person3.setAge(10);
        person3.setSex("女");
        person3.setId("uuu6");
        personThreeMapper.insert(person3);

        int i=1/0;
    }

}
