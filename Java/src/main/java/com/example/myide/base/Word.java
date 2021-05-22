package com.example.myide.base;

import com.example.myide.entity.WordState;

public class Word {
    String content;
    String token;
    int state = 0;

    public Word(String content){
        this.content = content;
    }

    public Word(String content,int state){
        this.content = content;
        this.state = state;
    }

    public void setToken(){
        this.token = WordState.values()[state].toString();
    }
}
