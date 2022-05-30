package com.example.beamo.controller.restaurant.menu;

import com.example.beamo.repository.restaurants.menu.Menu;
import com.example.beamo.repository.restaurants.menu.MenuRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api/menu" , produces = "application/json")
public class MenuController {
    @Autowired
    MenuRepository menuRepository;

    @ApiOperation(value = "메뉴 수량 선택 화면")
    @GetMapping("/{m_seq}")
    public ResponseEntity getMenuByM_seq(@PathVariable("m_seq") Long seq) {
        Optional<Menu> byId = menuRepository.findById(seq);
        return ResponseEntity.ok(byId);
    }
}
