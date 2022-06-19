package org.cuit.xueyian.api.salary;

import org.cuit.xueyian.model.JsonResponse;
import org.cuit.xueyian.model.SalaryStandard;
import org.cuit.xueyian.model.SalaryStandardDetails;
import org.cuit.xueyian.service.SalaryStandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wr1sw
 * @version 1.0.0
 * @description
 */
@RestController
@RequestMapping("/salary/approval/")
public class SalaryApprovalApi {

    @Autowired
    private SalaryStandardService salaryStandardService;


    @GetMapping("/")
    public List<SalaryStandard> getAllApproval() {
        return salaryStandardService.getAllSalaryStanderNoApprove();
    }

    @PostMapping("/")
    public Integer doApproval(@RequestBody SalaryStandard standerDetailandStander) {
        System.out.println("salaryStandard = " + standerDetailandStander);
        return salaryStandardService.doApproval(standerDetailandStander);
    }

    @GetMapping("/standard-details")
    public JsonResponse<SalaryStandardDetails> getStandardDetailsByStanderId(String standardId) {
        return new JsonResponse<SalaryStandardDetails>(salaryStandardService.getStandardDetailsByStanderId(standardId));
    }
}
