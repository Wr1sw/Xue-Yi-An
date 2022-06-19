package org.cuit.xueyian.api.system;

import org.cuit.xueyian.model.JObLevel;
import org.cuit.xueyian.model.RespBean;
import org.cuit.xueyian.model.Role;
import org.cuit.xueyian.service.JobLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/cfg/joblevel")
public class JobLevelController {

    @Autowired
    JobLevelService joblevelservice;

    @GetMapping("/")
    public List<JObLevel> getAllJobLevels() {
        return joblevelservice.getAllJobLevels();
    }

    @PostMapping("/")
    public RespBean addJobLevel(@RequestBody JObLevel jobLevel) {
        if (joblevelservice.addJobLevel(jobLevel) == 1) {
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @PutMapping("/")
    public RespBean updateJobLevelById(@RequestBody JObLevel jobLevel) {
        if (joblevelservice.updateJobLevelById(jobLevel) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteJobLevelById(@PathVariable Integer id) {
        JObLevel jObLevel = new JObLevel();
        jObLevel.setId(id);
        joblevelservice.deleteJobLevel(jObLevel);
        if (jObLevel.getResult() == -1) {
            return RespBean.error("该职位仍在使用，删除失败");
        } else if (jObLevel.getResult() == 1) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @DeleteMapping("/")
    public RespBean deleteJobLevelsByIds(Integer[] ids) {
        if (joblevelservice.deleteJobLevelsByIds(ids) == ids.length) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

   //获取职称名称与ID组合
    @GetMapping("/getJobLevelName")
    public RespBean getDepartmentID_Name(){
        return RespBean.ok(joblevelservice.getID_Name());
    }

   //根据ID获取职称名称
    @GetMapping("/getJobLevelNameById")
    public RespBean getDepartmentNameById(@RequestParam("jobLevelId") Integer jobLevelId){
        return RespBean.ok(joblevelservice.selectNameByPrimaryKey(jobLevelId));
    }
}
