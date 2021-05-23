package com.example.myide.controller;

import com.example.myide.base.Word;
import com.example.myide.service.LexicalAnalysisService;
import com.example.myide.service.ParsingService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * 语法分析的controller
 */
@RestController
public class ParsingController {
    LexicalAnalysisService las = new LexicalAnalysisService();
    ParsingService ps = new ParsingService();

    @CrossOrigin //解决跨域问题
    @GetMapping(value = "api/Parsing/parse")  //映射get请求
    public String parse(@RequestParam("str") String str){
        las.initLex();
        ps.init();
        ArrayList<Word> words = las.scan(str);
        for(int i = 0; i < words.size();i++){
            words.get(i).setToken();
        }
        ps.setToken(words);
        try {
            ps.statement();
        }catch(Exception e){
            return e.getMessage();
        }
        return "success";
    }
}
