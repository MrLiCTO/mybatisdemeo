package com.tztd.app.pojo;


import com.tztd.app.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonPojo {
    private Person person;

    private Long pageNum;
    private Long pageSize;
}
