package com.example.myide.service;

import com.example.myide.base.Word;
import com.example.myide.entity.KeyWords;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ParsingService {

    KeyWords kw = new KeyWords();
    List<Word> tokens = new ArrayList<>();
    int index = 0;
    Word w;
    List<String> vars = new ArrayList<>();
    Boolean flagS = false; //是否完成一条语句

    public void init(){
        this.index=0;
    }

    public void setToken(ArrayList<Word> words){
        this.tokens = words;
    }

    public void statement() throws Exception {
        while(true){
            if(index>=tokens.size()){
                return;
            }else if(index < tokens.size()-1){
                w = tokens.get(index);
                if(kw.typeList.contains(w.getContent().toLowerCase())){//第一个字符是类型关键字

                    assign();

                }
                if(w.getContent().equals(";")){
                    index++;
                }
            }else if(w.getContent().equals(";")){
                index++;
            }
        }

    }



    /**
     * 赋值语句
     */
    public void assign() throws Exception {

            index++;
            this.varName();
            w = this.tokens.get(index);
            if(index<this.tokens.size() && w.getContent().equals("=")){//进入赋值语句
                try{
                    index++;
                    expression();
                }catch (Exception e){
                    throw new Exception("赋值语句有误"+e.getMessage());
                }
            }else if(index<this.tokens.size() && w.getContent().equals(";")){//进入初始化语句

                return;
            }else{
                throw new Exception("语句缺失");
            }

    }

    public void varName() throws Exception {
        if(index < this.tokens.size()){
            w = this.tokens.get(index++);
            if(w.getToken().equals("VAR")){
                return;
            }else{
                throw new Exception("语法错误");
            }
        }else{
            throw new Exception("语句缺失");
        }

    }



    /**
     * a op b 形式的表达式
     * @throws Exception
     */
    public void expression() throws Exception{
        this.factor();
        this.operator();
        if(index<tokens.size()&&tokens.get(index).getContent().equals(";")){
            return;
        }else if(index==tokens.size()&&tokens.get(index-1).getContent().equals(";")){
            return;
        }
        else{
            expression();
        }
//        this.factor();
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
                if(index>=tokens.size()||tokens.get(index).getContent().equals(";")){
                    throw new Exception("缺失变量");
                }else{
                    return;
                }
            }else if(w.getContent().equals(";")){
                if(index<tokens.size()){
                    index--;
                }
                return;
            }
        }
        throw new Exception("缺失运算符");

    }

}
