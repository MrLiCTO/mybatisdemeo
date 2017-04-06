package com.tztd.app.mapperTwo;


import com.tztd.app.SqlProvider.PersonSqlProvider;
import com.tztd.app.model.Person;
import com.tztd.app.pojo.PersonPojo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface PersonCustomTwoMapper {
    @Insert("INSERT INTO person(id,name,sex,age) VALUES(#{id},#{name},#{sex},#{age})")
    void save(Person person);

    @Select("select * from person where name like #{name}")
    List<Person> findList(String name);

    //@Select("select * from person where name like #{person.name}")
    @Select("select * from person where name like '%${person.name}%'")
    List<Person> findListByPojo(PersonPojo personPojo);

    @SelectProvider(type = PersonSqlProvider.class,method = "findNewListByPojo")
    List<Person> findNewListByPojo(PersonPojo personPojo);

    List<Person> selectAllByName(PersonPojo personPojo);
}
