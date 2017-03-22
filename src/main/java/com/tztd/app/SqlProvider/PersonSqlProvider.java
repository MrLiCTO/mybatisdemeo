package com.tztd.app.SqlProvider;

import org.apache.ibatis.jdbc.SQL;

/**
 * Created by Administrator on 2017/3/22.
 */
public class PersonSqlProvider {
    private static final String colunms="id,name,sex,age";
    public String findNewListByPojo(){
        SQL sql = new SQL();
        sql= sql.SELECT(colunms).FROM("person").WHERE("name like '${person.name}'");
        return sql.toString();
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
