package org.cuit.xueyian.api.system;

import org.cuit.xueyian.model.JObLevel;
import org.cuit.xueyian.model.RespBean;
import org.cuit.xueyian.model.Train;
import org.cuit.xueyian.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/system/cfg/train")
public class TrainBasicController {

    @Autowired
    TrainService trainService;

    @GetMapping("/")
    public List<Train> getAllTrain(){
        return trainService.getAllTrain();
    }

    @PostMapping("/")
    public RespBean addJobLevel(@RequestBody Train train) {
        if (trainService.addTrain(train) == 1) {
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @PutMapping("/")
    public RespBean updateJobLevelById(@RequestBody Train train) {
        if (trainService.updateTrain(train) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteJobLevelById(@PathVariable Integer id) {
        if (trainService.deleteTrain(id) == 1) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
}
