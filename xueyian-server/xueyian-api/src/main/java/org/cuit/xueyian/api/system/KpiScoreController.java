package org.cuit.xueyian.api.system;

import org.cuit.xueyian.model.JObLevel;
import org.cuit.xueyian.model.KpiScore;
import org.cuit.xueyian.model.RespBean;
import org.cuit.xueyian.service.KpiScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/cfg/kpiscore")
public class KpiScoreController {

    @Autowired
    KpiScoreService kpiScoreService;

    @GetMapping("/")
    public List<KpiScore> getAllKpiScore(){
        return kpiScoreService.getAllKpiScore();
    }

    @PostMapping("/")
    public RespBean addKpiScore(@RequestBody KpiScore kpiScore){
        if (kpiScoreService.addKpiScore(kpiScore) == 1){
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @PutMapping("/")
    public RespBean updateKpiScore(@RequestBody KpiScore kpiScore){
        if (kpiScoreService.updateKpiScore(kpiScore) == 1){
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteKpiScoreById(@PathVariable Integer id){
        if (kpiScoreService.deleteKpiScoreById(id) == 1){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @DeleteMapping("/")
    public RespBean deleteKpiScoreByIds(Integer[] ids){
        if (kpiScoreService.deleteKpiScoreByIds(ids) == ids.length){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
