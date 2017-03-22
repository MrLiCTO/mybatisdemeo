package com.tztd.app.SqlProvider;

import com.tztd.app.model.Person;
import com.tztd.app.pojo.PersonPojo;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/22.
 */
public class PersonSqlProvider {
    private static final String colunms = "id,name,sex,age";

    public String findNewListByPojo(PersonPojo personPojo) {
        SQL sql = new SQL();
        String[] sql_c = sql_con(personPojo);
        if (sql_c != null && sql_c.length > 0) {
            sql = sql.SELECT(colunms).FROM("person").WHERE(sql_c);
        }
        return sql.toString();
    }

    private String[] sql_con(PersonPojo personPojo) {
        List<String> list = new ArrayList<>();
        String[] strs=null;
        if (personPojo != null) {
            Person person = personPojo.getPerson();
            if (person != null) {
                if (!StringUtils.isEmpty(person.getName())) {
                    String name = person.getName();
                    if (name.endsWith("_like")) {
                        name = name.split("_like")[0];
                        list.add("name like '%" + name + "%'");
                    } else {
                        list.add("name =" + name);
                    }
                }
            }
        }
        if (list.size()>0){
            strs=new String[list.size()];
            for (int i=0;i<list.size();i++) {
                strs[i]=list.get(i);
            }
        }
        return strs;
    }
    /*public static void main(String[] args) {
        SQL sql_insert=new SQL();
        SQL sql_delete=new SQL();
        SQL sql_update=new SQL();
        SQL sql_select=new SQL();
        sql_select = sql_select.SELECT("列名").FROM("表A").RIGHT_OUTER_JOIN("表B").LEFT_OUTER_JOIN("表C").WHERE("条件").GROUP_BY("列名").HAVING("条件").ORDER_BY("列名 DESC");
        sql_delete=sql_delete.DELETE_FROM("表A").WHERE("条件");
        sql_update=sql_update.UPDATE("表A").SET("列名=值").AND().SET("列名=值","列名=值").WHERE("条件");
        sql_insert=sql_insert.INSERT_INTO("表A").INTO_COLUMNS("列名","列名").INTO_VALUES("值","值");
        System.out.println(sql_select.toString());
        System.out.println(sql_delete.toString());
        System.out.println(sql_update.toString());
        System.out.println(sql_insert.toString());
    }*/
}
