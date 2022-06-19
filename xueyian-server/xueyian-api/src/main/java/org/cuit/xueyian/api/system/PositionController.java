package org.cuit.xueyian.api.system;

import org.cuit.xueyian.model.Position;
import org.cuit.xueyian.model.RespBean;
import org.cuit.xueyian.service.PostionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/cfg/position")
public class PositionController {

    @Autowired
    PostionService postionService;

    @GetMapping("/")
    public List<Position> getAllPositions() {
        return postionService.getAllPositions();
    }

    @OperationLog(operModel = "系统管理-基础信息管理-职位管理", operDesc = "添加职位")
    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position) {
        if (postionService.addPosition(position) == 1) {
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @OperationLog(operModel = "系统管理-基础信息管理-职位管理", operDesc = "更新职位")
    @PutMapping("/")
    public RespBean updatePositions(@RequestBody Position position) {
        if (postionService.updatePositions(position) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @OperationLog(operModel = "系统管理-基础信息管理-职位管理", operDesc = "删除职位")
    @DeleteMapping("/{id}")
    public RespBean deletePositionById(@PathVariable Integer id) {
        Position position = new Position();
        position.setId(id);
        postionService.deletePosition(position);
        if (position.getResult() == -1) {
            return RespBean.error("该职称仍在使用，删除失败");
        } else if (position.getResult() == 1) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @OperationLog(operModel = "系统管理-基础信息管理-职位管理", operDesc = "删除多个职位")
    @DeleteMapping("/")
    public RespBean deletePositionsByIds(Integer[] ids) {
        if (postionService.deletePositionsByIds(ids) == ids.length) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    @GetMapping("/getPosName")
    public RespBean getPositionID_Name(){
        return RespBean.ok(postionService.getID_Name());
    }

    @GetMapping("/getPosNameById")
    public RespBean getDepartmentNameById(@RequestParam("positionId") Integer positionId){
        return RespBean.ok(postionService.selectNameByPrimaryKey(positionId));
    }
}
