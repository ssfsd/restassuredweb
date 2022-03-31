package com.example.mysqlandjpa.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.mysqlandjpa.entity.Lotto;
import com.example.mysqlandjpa.entity.Winners;
import com.example.mysqlandjpa.service.LottoService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
@Log
@RestController
public class LottoController {
    @Autowired
    LottoService lottoService;
    @PostMapping("/lotto")
    public void saveOneLotte(@RequestBody String lotto)
    {
        Map map= JSONObject.parseObject(lotto,Map.class);
        Iterator iterator=map.entrySet().iterator();
        Lotto lotto1 =null;
        while(iterator.hasNext()){
            Map.Entry entry=(Map.Entry) iterator.next();
            lotto1=JSONObject.parseObject(entry.getValue().toString(),Lotto.class);
            Map map2=JSONObject.parseObject(entry.getValue().toString(),Map.class);
            lotto1.setWinners(map2.get("winners").toString());
        }
        lottoService.save(lotto1);
    }
    @GetMapping("/getList")
    public String getLotto()
    {
        return lottoService.searchList();
    }
}
