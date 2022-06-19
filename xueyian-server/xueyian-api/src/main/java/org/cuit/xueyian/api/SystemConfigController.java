package org.cuit.xueyian.api;

import org.cuit.xueyian.model.Menu;
import org.cuit.xueyian.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/system/config")
public class SystemConfigController {

    @Autowired
    MenuService menuService;


    @GetMapping("/menu")
    public List<Menu> getMenuByHrId() {
        return menuService.getMenuByHrId();
    }
}
