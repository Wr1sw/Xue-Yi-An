package org.cuit.xueyian.api.system;

import org.cuit.xueyian.model.Menu;
import org.cuit.xueyian.model.RespBean;
import org.cuit.xueyian.model.Role;
import org.cuit.xueyian.service.MenuService;
import org.cuit.xueyian.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/system/cfg/permiss")
public class PermissController {
    @Autowired
    RoleService roleService;
    @Autowired
    MenuService menuService;
    @GetMapping("/")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }
    @GetMapping("/menus")
    public List<Menu> getAllMenus() {
        return menuService.getAllMenus();
    }

    @GetMapping("/mids/{rid}")
    public List<Integer> getMidsByRid(@PathVariable Integer rid) {
        return menuService.getMidsByRid(rid);
    }

    @OperationLog(operModel = "系统管理-基础信息管理-权限组管理", operDesc = "更新")
    @PutMapping("/")
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        if (menuService.updateMenuRole(rid, mids)) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @OperationLog(operModel = "系统管理-基础信息管理-权限组管理", operDesc = "添加角色")
    @PostMapping("/role")
    public RespBean addRole(@RequestBody Role role) {
        if (roleService.addRole(role) == 1) {
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @OperationLog(operModel = "系统管理-基础信息管理-权限组管理", operDesc = "删除角色")
    @DeleteMapping("/role/{rid}")
    public RespBean deleteRoleById(@PathVariable Integer rid) {
        Role role = new Role();
        role.setId(rid);
        roleService.deletePermissById(role);
        if (role.getResult() == -1) {
            return RespBean.error("该角色仍有权限，删除失败");
        } else if (role.getResult() == 1) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
