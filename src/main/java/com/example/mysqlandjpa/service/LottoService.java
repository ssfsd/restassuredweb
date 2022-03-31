package com.example.mysqlandjpa.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.mysqlandjpa.entity.Lotto;
import com.example.mysqlandjpa.entity.Winners;
import com.example.mysqlandjpa.repository.LottoReposiroty;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Log
@Service
public class LottoService {
    @Autowired
    private LottoReposiroty lottoReposiroty;

    public void save(Lotto lotto){
        lottoReposiroty.save(lotto);
    }

    public String searchList()
    {
        List<Lotto> listLotto=new ArrayList<Lotto>();
        lottoReposiroty.findAll().forEach(lotto2->listLotto.add(lotto2));
        if(listLotto.size()==0)
        {
            return null;
        }
        JSONArray jsonArrayOutter=new JSONArray();;
        JSONObject lottoOuter=null;
        JSONObject lottoInner=null;
        for(int i=0;i<listLotto.size();i++){
            lottoOuter=new JSONObject();
            lottoInner=new JSONObject();
            int id=listLotto.get(i).getLottoId();
            JSONArray winnerNumberS=JSONObject.parseArray(listLotto.get(i).getWinningNumbers());
            String winnersS=listLotto.get(i).getWinners();
            lottoInner.put("lottId",id);
            lottoInner.put("winning-numbers",winnerNumberS);
            JSONArray jsonArray;
            JSONObject winnersO=new JSONObject();
            if(!(winnersS==null||"".equals(winnersS))){
                jsonArray=JSONObject.parseArray(winnersS);
                winnersO.put("winners",jsonArray);
                lottoInner.put("winners",winnersO);
            }
            else {
                winnersO.put("winners","");
                lottoInner.put("winners",winnersO);
            }
            lottoOuter.put("lotto",lottoInner);
            jsonArrayOutter.add(lottoOuter);
        }
        return JSONObject.toJSONString(jsonArrayOutter, SerializerFeature.WriteMapNullValue);
    }
}
