package com.example.myide.entity;

import java.util.ArrayList;
import java.util.List;

public class KeyWords {
    public List<String> list = new ArrayList<>();
    public List<String> booleanList = new ArrayList<>();
    public List<Character> symbolList = new ArrayList<>();
    public KeyWords(){
        list.add("int");
        list.add("float");
        list.add("char");
        list.add("break");
        list.add("const");
        list.add("return");
        list.add("void");
        list.add("continue");
        list.add("do");
        list.add("while");
        list.add("if");
        list.add("else");
        list.add("for");
        booleanList.add("true");
        booleanList.add("false");
        symbolList.add('+');
        symbolList.add('-');
        symbolList.add('*');
        symbolList.add('/');
        symbolList.add('=');
        symbolList.add(';');
        symbolList.add('>');
        symbolList.add('<');
    }
}
