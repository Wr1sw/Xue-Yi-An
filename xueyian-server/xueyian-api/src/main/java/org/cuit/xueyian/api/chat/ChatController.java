package org.cuit.xueyian.api.chat;

import org.cuit.xueyian.model.Hr;
import org.cuit.xueyian.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wr1sw
 * @version 1.0.0
 * @description
 */
@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    HrService hrService;


    @GetMapping("/hrs")
    public List<Hr> getAllHr() {
        return hrService.getAllHrExcludeCurHr();
    }
}
