package com.example.myide.service;

import com.example.myide.base.Word;

import java.util.ArrayList;
import java.util.List;


public class ParsingService {

    List<Word> tokens = new ArrayList<>();
    int index = 0;
    Word w;

    public void init(){
        this.index=0;
    }

    public void setToken(ArrayList<Word> words){
        this.tokens = words;
    }

    public void expression() throws Exception{
        this.factor();
        this.operator();
        this.factor();
    }

    public void factor() throws Exception {
        if(index<tokens.size()){
            w = tokens.get(index++);
            String token = w.getToken();
            if(token.equals("INT")||token.equals("VAR")){
                return;
            }
        }
        throw new Exception("缺失变量");

    }

    public void operator() throws Exception {
        if(index<tokens.size()){
            w = tokens.get(index++);
            String token = w.getToken();
            token = token.split("_")[0];
            if(token.equals("OPERATOR")){
                return;
            }
        }
        throw new Exception("缺失运算符");

    }

}
