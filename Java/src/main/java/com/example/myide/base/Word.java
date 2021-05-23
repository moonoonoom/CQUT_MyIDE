package com.example.myide.base;

import com.example.myide.entity.WordState;

public class Word {

    String content;
    String token;
    String token_print;
    int state = 0;

    public Word(String content){
        this.content = content;
    }

    public Word(String content,int state){
        this.content = content;
        this.state = state;
    }

    public void setTokenPrint(){

        this.token = WordState.values()[state].toString();
        if(this.token.equals("KEY")||this.token.equals("OPERATOR")){
            this.token_print=this.token+"_"+this.content.toUpperCase();
        }
        else{
            this.token_print = this.token;
        }
    }
}
