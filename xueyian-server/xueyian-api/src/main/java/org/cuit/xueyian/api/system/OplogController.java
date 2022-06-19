package org.cuit.xueyian.api.system;


import org.cuit.xueyian.model.OpLog;
import org.cuit.xueyian.model.Position;
import org.cuit.xueyian.model.RespBean;
import org.cuit.xueyian.service.OplogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Oplog")
public class OplogController {

    @Autowired
    OplogService oplogService;

    @GetMapping("/")
    public List<OpLog> getAllOplog(){
        return oplogService.getAllOplog();
    }

    @DeleteMapping("/{id}")
    public RespBean deleteOplogById(@PathVariable Integer id) {
        if (oplogService.deleteOplogById(id) == 1) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @DeleteMapping("/")
    public RespBean deleteOplogByIds(Integer[] ids) {
        if (oplogService.deleteOplogByIds(ids) == ids.length) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
}
