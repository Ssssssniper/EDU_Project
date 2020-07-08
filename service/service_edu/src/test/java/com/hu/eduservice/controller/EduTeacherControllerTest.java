package com.hu.eduservice.controller;

import com.hu.eduservice.EduApplication;
import com.hu.eduservice.entity.EduTeacher;
import com.hu.eduservice.service.EduTeacherService;
import com.mybatisplus.codegenerate.EduApplicationTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes= EduApplication.class)
public class EduTeacherControllerTest {

    @Autowired
    private EduTeacherService eduTeacherService;

    @Test
    public void insert(){
        EduTeacher eduTeacher = new EduTeacher();
        eduTeacher.setIntro("清华毕业");
        eduTeacher.setCareer("java");
        eduTeacher.setLevel(1);
        eduTeacher.setName("jack");
        eduTeacher.setSort(1);
        eduTeacherService.save(eduTeacher);
    }
}

