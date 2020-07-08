package com.hu.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hu.eduservice.entity.EduTeacher;
import com.hu.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hu
 * @since 2020-07-08
 */
@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    // 1 查询所有人员数据
    @GetMapping("findAll")  //http://localhost:8001/eduservice/edu-teacher/findAll
    public List<EduTeacher> findAll(){
        // 插入数据
        EduTeacher eduTeacher = new EduTeacher();
        eduTeacher.setIntro("清华毕业");
        eduTeacher.setCareer("java");
        eduTeacher.setLevel(1);
        eduTeacher.setName("jack");
        eduTeacher.setSort(1);
        eduTeacherService.save(eduTeacher);

        // 调用service查询
        List<EduTeacher> results = eduTeacherService.list(null);
        System.out.println(results);
        return results;
    }

    // 2 逻辑删除方法     这是delete请求  因此无法用浏览器进行测试（用swagger测试）
    // http://localhost:8001/eduservice/edu-teacher/id
    @DeleteMapping("{id}")      // 需要通过路径传递id值           @PathVariable 从路径中获取值
    public boolean removePeople(@PathVariable String id){
        // 逻辑删除
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", id);
//        eduTeacherService.removeById(id);
        boolean result = eduTeacherService.remove(wrapper);
        return result;
    }
}

