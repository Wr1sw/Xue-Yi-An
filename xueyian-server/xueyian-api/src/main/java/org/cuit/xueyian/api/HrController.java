package org.cuit.xueyian.api;

import org.cuit.xueyian.model.Hr;
import org.cuit.xueyian.model.Position;
import org.cuit.xueyian.model.RespBean;
import org.cuit.xueyian.model.Role;
import org.cuit.xueyian.service.HrService;
import org.cuit.xueyian.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/system/hr/")
public class HrController {
    @Autowired
    HrService hrService;
    @Autowired
    RoleService roleService;
    @GetMapping("/")
    public List<Hr> getAllHrs(String keywords) {
        return hrService.getAllHrs(keywords);
    }

    @PostMapping("/")
    public RespBean addHr(@RequestBody Hr hr){
//        hr.isEnabled();
        if (hrService.addHr(hr) == 1 ){
            return RespBean.ok("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @PutMapping("/")
    public RespBean updateHr(@RequestBody Hr hr) {
        if (hrService.updateHr(hr) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }
    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @PutMapping("/role")
    public RespBean updateHrRole(Integer hrid, Integer[] rids) {
        if (hrService.updateHrRole(hrid, rids)) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteHrById(@PathVariable Integer id) {
        Hr hr = new Hr();
        hr.setId(id);
        hrService.deleteHr(hr);
        if (hr.getResult() == -1) {
            return RespBean.error("该管理员仍有权限，删除失败");
        } else if (hr.getResult() == 1) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败!");
    }

    @GetMapping("/{id}")
    public RespBean selectByPrimaryKey(@PathVariable Integer id){
        return RespBean.ok(hrService.selectById(id));
    }


    @PostMapping("getImgUrl")
    public RespBean getImgUrl(MultipartFile file) throws Exception {
        Object object = hrService.getImgUrl(file);
        return RespBean.ok(object);
    }
}
