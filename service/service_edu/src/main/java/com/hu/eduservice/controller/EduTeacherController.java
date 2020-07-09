package com.hu.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hu.eduservice.entity.EduTeacher;
import com.hu.eduservice.entity.QueryPeople;
import com.hu.eduservice.service.EduTeacherService;
import com.hu.message.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器     接口
 * </p>
 *
 * @author hu
 * @since 2020-07-08
 */
@Api(tags = "人员信息控制器")
@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    // 1 查询所有人员数据
    @ApiOperation(value = "查询所有的接口")
    @GetMapping("findAll")  //http://localhost:8001/eduservice/edu-teacher/findAll
    public ResponseMessage findAll(){
        // 插入数据
//        EduTeacher eduTeacher = new EduTeacher();
//        eduTeacher.setIntro("清华毕业");
//        eduTeacher.setCareer("java");
//        eduTeacher.setLevel(1);
//        eduTeacher.setName("lee");
//        eduTeacher.setSort(1);
//        eduTeacherService.save(eduTeacher);

        // 调用service查询
        List<EduTeacher> results = eduTeacherService.list(null);
        HashMap<String, Object> map = new HashMap<>();
        map.put("items", results);

        if (results != null){
            ResponseMessage message = ResponseMessage.ok().data(map);
            return message;
        }else{
            ResponseMessage message = ResponseMessage.error().data(null);
            return message;
        }
    }

    // 2 逻辑删除方法     这是delete请求  因此无法用浏览器进行测试（用swagger测试）
    // http://localhost:8001/eduservice/edu-teacher/id
    @ApiOperation(value = "根据ID删除的接口")
    @DeleteMapping("{id}")      // 需要通过路径传递id值           @PathVariable 从路径中获取值
    public ResponseMessage removePeople(@ApiParam(name = "id",required = true,value = "人员id") @PathVariable String id){
        // 逻辑删除
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", id);
//        eduTeacherService.removeById(id);
        boolean result = eduTeacherService.remove(wrapper);

        if (result){
            ResponseMessage ok = ResponseMessage.ok().message("删除成功");
            return ok;
        }else{
            ResponseMessage error = ResponseMessage.error().message("删除失败");
            return error;
        }
    }

    // 获取当前页的数据
    @ApiOperation(value = "分页查询接口")
    @GetMapping("pagePeople/{current}/{limit}")
    public ResponseMessage findByPage(@PathVariable long current,@PathVariable long limit){
        Page<EduTeacher> page = new Page<>(current, limit);
        eduTeacherService.page(page, null);
        long total = page.getTotal();       // 总页数
        List<EduTeacher> records = page.getRecords();// 数据
        return ResponseMessage.ok().data("total",total).data("items",records);
    }

    // 重要功能
    // 条件查询分页功能    多条件组合查询    利用对象获取条件@RequestBody  必须用Post请求获取
    @ApiOperation(value = "多条件组合查询带分页功能的接口")
    @PostMapping("conditionPage/{current}/{limit}")
    public ResponseMessage findByCondition(@PathVariable long current, @PathVariable long limit,@RequestBody(required = false) QueryPeople condition){
        Page<EduTeacher> page = new Page<>(current, limit);
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        // 多条件组合  不为空则拼接
        String name = condition.getName();
        String start_time = condition.getStart_time();
        String end_time = condition.getEnd_time();
        if (!StringUtils.isEmpty(name)){
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(start_time)){
            wrapper.ge("create_time", start_time);
        }
        if (!StringUtils.isEmpty(end_time)){
            wrapper.le("create_time", end_time);
        }

        eduTeacherService.pageMaps(page, wrapper);
        long total = page.getTotal();       // 总页数
        List<EduTeacher> records = page.getRecords();// 数据
        return ResponseMessage.ok().data("total",total).data("items",records);
    }
    //  添加
    // 通过对象获取数据
    @ApiOperation(value="新增人员的接口")
    @PostMapping("addPeople")
    public ResponseMessage addPerson(@RequestBody EduTeacher people){
        boolean res = eduTeacherService.save(people);
        if (res){
            return ResponseMessage.ok().message("添加成功").data("items", people);
        }else{
            return ResponseMessage.error().message("添加失败").data("items", people);
        }
    }

    // 根据id查询 接口
    @ApiOperation(value = "根据id查询的接口")
    @GetMapping("getPeople/{id}")
    public ResponseMessage findById(@PathVariable String id){
        // 先查询
        EduTeacher target = eduTeacherService.getById(id);
        return ResponseMessage.ok().message("查询成功").data("items", target);
    }

    // 修改  接口
    @ApiOperation(value = "修改 接口")
    @PostMapping("updatePeople")
    public ResponseMessage update(@RequestBody EduTeacher target){
        boolean res = eduTeacherService.updateById(target);
        if (res){
            return ResponseMessage.ok().message("修改成功").data("items", target);
        }
        return ResponseMessage.error().message("修改失败");
    }
}

