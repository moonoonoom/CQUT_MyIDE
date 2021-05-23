package com.example.myide.entity;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public enum WordState{

    START,END,ERROR,INT,FLOAT,CHAR,VAR,KEY,OPERATOR,SYMBOL,DELIMITER,DOPERATOR

}

//public class WordState {
//    //单词对应的状态
//    public enum ws{START,END,ERROR,INT,FLOAT,CHAR,VAR,KEY}
//    public List<String> keyWords = new ArrayList<String>();
//
//    public WordState(){
//        keyWords.add("int");
//        keyWords.add("float");
//
//    }
//}
