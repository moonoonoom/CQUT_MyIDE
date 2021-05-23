package com.example.myide.controller;

import com.example.myide.base.Word;
import com.example.myide.service.LexicalAnalysisService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.google.gson.Gson;
import com.sun.org.apache.xpath.internal.objects.XString;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 词法分析的controller
 */
@RestController
public class LexicalAnalysisController {

    LexicalAnalysisService lexAna = new LexicalAnalysisService();


    @CrossOrigin //解决跨域问题
    @GetMapping(value = "api/LexicalAnalysis/Scan")  //映射get请求
    public String Scan(@RequestParam("str") String str) throws JsonProcessingException {
        List<Word> words = lexAna.scan(str);
        for(int i = 0; i < words.size();i++){
            words.get(i).setTokenPrint();
        }
        Gson gson = new Gson();
        String json = gson.toJson(words);
        lexAna.initLex();
        return json;
        //return "2";
    }

//    @CrossOrigin //解决跨域问题
//    @GetMapping(value = "api/test")  //映射get请求
//    public String test(){
//        return "111";
//    }


}
