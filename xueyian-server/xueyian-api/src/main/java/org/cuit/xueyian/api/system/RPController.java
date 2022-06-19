package org.cuit.xueyian.api.system;

import org.cuit.xueyian.model.RP;
import org.cuit.xueyian.model.RespBean;
import org.cuit.xueyian.service.RPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/cfg/rp")
public class RPController {

    @Autowired
    RPService rpService;

    @GetMapping("/")
    public List<RP> getAllRP(){
        return rpService.getAllRP();
    }

    @OperationLog(operModel = "系统管理-基础信息管理-奖惩管理", operDesc = "添加奖惩规则")
    @PostMapping("/")
    public RespBean addRP(@RequestBody RP rp){
//        if (rp.getResult() >= 0){
//            rp.setEcType(1);
//        }else if (rp.getResult() < 0){
//            rp.setEcType(0);
//        }
        if(rpService.addRP(rp) == 1){
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @OperationLog(operModel = "系统管理-基础信息管理-奖惩管理", operDesc = "更新奖惩规则")
    @PutMapping("/")
    public RespBean updateRP(@RequestBody RP rp){
        if(rpService.updateRP(rp) == 1){
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @OperationLog(operModel = "系统管理-基础信息管理-奖惩管理", operDesc = "删除奖惩规则")
    @DeleteMapping("/{id}")
    public RespBean deleteRP(@PathVariable Integer id){
        if(rpService.deleteRPById(id) == 1){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @DeleteMapping("/")
    public RespBean deleteRPByIds(Integer[] ids){
        if(rpService.deleteRPByIds(ids) == ids.length){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
