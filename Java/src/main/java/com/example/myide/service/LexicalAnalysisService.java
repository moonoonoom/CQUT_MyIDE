package com.example.myide.service;

import com.example.myide.entity.KeyWords;
import com.example.myide.entity.WordState;
import org.springframework.stereotype.Service;
import com.example.myide.base.Word;
import org.springframework.util.StringUtils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;



//词法分析
@Service
public class LexicalAnalysisService {

    Map<String,String> dic = new HashMap<String,String>();
    String tokenPath="src/txt/token.txt";
    int begin = 0;
    KeyWords kw = new KeyWords();

    //初始化词法分析器
    public void initLex(){
        this.begin = 0;
    }
    

    public void getDic(){
        Scanner scanner = new Scanner(tokenPath);
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String dics[] = line.split(":");
            dic.put(dics[0],dics[1]);
        }
    }

    public ArrayList<Word> scan(String s){
        char[] chars = s.toCharArray();
        ArrayList<Word> words = new ArrayList<Word>();
        while(begin<chars.length){//没有读到末尾
            char flagc = chars[begin];//取出判断的起始字符
            if(Character.isDigit(flagc)){ //如果起始字符是数字
                words.add(beginFromDigit(chars));//进入数字开始的状态转换标识符
            }else if(Character.isAlphabetic(flagc)){//如果起始字符是字母
                words.add(beginFromAlp(chars));
            }else if(kw.symbolList.contains(flagc)){//如果是合法的符号
                words.add(beginFromSymbol(chars));
            }
            else if(isEnd(flagc)){//如果是结束字符
                begin++;//读下一个字符
            }
        }
        return words;
    }

    /**
     * 从字符开始的符号转换
     * @param chars
     * @return
     */
    private Word beginFromSymbol(char[] chars){
        char c;
        int state = WordState.START.ordinal();//初始状态为开始状态
        String wordStr = chars[begin]+"";
        int index = begin;
        while(state!=WordState.ERROR.ordinal()&&index<chars.length){
            c = chars[index];
            if(c == ';'){
                if(state==WordState.START.ordinal()){//如果当前状态为开始状态
                    state = WordState.DELIMITER.ordinal();//转为界符状态
                    break;
                }else if(state==WordState.OPERATOR.ordinal()){//如果已经是运算符状态
                    state = WordState.ERROR.ordinal();//报错
                }
            }else if (c == '+'){
                if(state==WordState.START.ordinal()){
                    state = WordState.OPERATOR.ordinal();
                    index++;
                    continue;
                }else if (state == WordState.OPERATOR.ordinal()){
                    if(chars[index-1]=='+'&&index-begin==1){
                        state = WordState.DOPERATOR.ordinal();
                        index++;
                        continue;
                    }else{
                        state = WordState.ERROR.ordinal();//报错
                    }
                }else{
                    state = WordState.ERROR.ordinal();//报错
                }
            } else if (c == '-'){
                if(state==WordState.START.ordinal()){
                    state = WordState.OPERATOR.ordinal();
                    index++;
                    continue;
                }else if (state == WordState.OPERATOR.ordinal()){
                    if(chars[index-1]=='-'&&index-begin==1){
                        state = WordState.DOPERATOR.ordinal();
                        index++;
                        continue;
                    }else{
                        state = WordState.ERROR.ordinal();//报错
                    }
                }else{
                    state = WordState.ERROR.ordinal();//报错
                }
            }else if (c == '*'){
                if(state==WordState.START.ordinal()){
                    state = WordState.OPERATOR.ordinal();
                    index++;
                    continue;
                }else if (state == WordState.OPERATOR.ordinal()){
                    if(chars[index-1]=='*'&&index-begin==1){
                        state = WordState.DOPERATOR.ordinal();
                        index++;
                        continue;
                    }else{
                        state = WordState.ERROR.ordinal();//报错
                    }
                }else{
                    state = WordState.ERROR.ordinal();//报错
                }
            }else if (c == '/'){
                if(state==WordState.START.ordinal()){
                    state = WordState.OPERATOR.ordinal();
                    index++;
                    continue;
                }else if (state == WordState.OPERATOR.ordinal()){
                    state = WordState.ERROR.ordinal();
                }
            }else if (c == '='){
                if(state==WordState.START.ordinal()){
                    state = WordState.OPERATOR.ordinal();
                    index++;
                    continue;
                }else if (state == WordState.OPERATOR.ordinal()){
                    if((chars[index-1]=='='||chars[index-1]=='+'||chars[index-1]=='-'
                            ||chars[index-1]=='*'||chars[index-1]=='/'||chars[index-1]=='>'
                            ||chars[index-1]=='<'
                    )&&index-begin==1){
                        state = WordState.DOPERATOR.ordinal();
                        index++;
                        continue;
                    }else{
                        state = WordState.ERROR.ordinal();//报错
                    }
                }else{
                    state = WordState.ERROR.ordinal();//报错
                }
            }else if (c == '>'){
                if(state==WordState.START.ordinal()){
                    state = WordState.OPERATOR.ordinal();
                    index++;
                    continue;
                }else if (state == WordState.OPERATOR.ordinal()){
                    if(chars[index-1]=='>'&&index-begin==1){
                        state = WordState.DOPERATOR.ordinal();
                        index++;
                        continue;
                    }else{
                        state = WordState.ERROR.ordinal();//报错
                    }
                }else{
                    state = WordState.ERROR.ordinal();//报错
                }
            }else if (c == '<'){
                if(state==WordState.START.ordinal()){
                    state = WordState.OPERATOR.ordinal();
                    index++;
                    continue;
                }else if (state == WordState.OPERATOR.ordinal()){
                    if(chars[index-1]=='>'&&index-begin==1){
                        state = WordState.DOPERATOR.ordinal();
                        index++;
                        continue;
                    }else{
                        state = WordState.ERROR.ordinal();//报错
                    }
                }else{
                    state = WordState.ERROR.ordinal();//报错
                }
            }
            else if(isEnd(c)||c==';'||(state == WordState.OPERATOR.ordinal()&&(Character.isAlphabetic(c)||Character.isDigit(c)))){ //遇到结束字符
                break;
            }
        }
        if(state != WordState.ERROR.ordinal()) {//不是错误状态说明就是正确状态
            if(index == begin){
                wordStr=new String(chars,begin,1);
                begin++;
            }else{
                wordStr=new String(chars,begin,index-begin);
                begin = index;
            }
        }else{//报错状态
            wordStr=new String(chars,begin,index-begin+1);//把错误字符也包括进去
            begin = index+1;
        }

        return new Word(wordStr,state);
    }

    /**
     * 从字母开始的状态转换
     * @param chars
     * @return
     */
    private Word beginFromAlp(char[] chars){
        int index = begin+1;//下一个字符
        int state = WordState.CHAR.ordinal();//初始状态为字符状态
        char tempc;
        String wordStr="";
        while(state!=WordState.ERROR.ordinal()&&index<chars.length){ //只要状态不是结束或者报错
            tempc = chars[index];
            if(Character.isAlphabetic(tempc)){//如果是字符
                //变成变量状态
                state = WordState.VAR.ordinal();
                index++; //继续判断下一个字符
            } else if(isEnd(tempc)||tempc==';'){ //遇到结束字符
                break;
            } else if(tempc == '.'){//遇到小数点
                state = WordState.ERROR.ordinal();//把状态改为报错状态
            }
        }
        if(state != WordState.ERROR.ordinal()){//不是错误状态说明就是正确状态
            wordStr=new String(chars,begin,index-begin);
            if(kw.list.contains(wordStr.toLowerCase())){//如果这个变量是关键字,ps不区分大小写
                state = WordState.KEY.ordinal();//变为关键字状态
            }
            begin = index;
        }else{//报错状态
            wordStr=new String(chars,begin,index-begin+1);//把错误字符也包括进去
            begin = index + 1 ;
        }
        return new Word(wordStr,state);
    }

    /**
     * 从数字开始的状态转换
     * @param chars 字符数组
     * @return 得到的单词
     */
    private Word beginFromDigit(char[] chars){
        int index = begin+1;//下一个字符
        int state = WordState.INT.ordinal();//初始状态为整数状态
        char tempc;
        String wordStr="";
        //判断下一个字符的状态
        while(state!=WordState.ERROR.ordinal()&&index<chars.length){ //只要状态不是结束或者报错
            tempc = chars[index];
            if(Character.isDigit(tempc)){//如果是数字
                //保持当前状态
                //state = State.INT.ordinal();
                index++; //继续判断下一个数字
            } else if(isEnd(tempc)||tempc==';'){ //遇到结束字符
                break;
            } else if(tempc == '.'){
                if(state == WordState.FLOAT.ordinal()){//如果此时已经是浮点数状态
                    state = WordState.ERROR.ordinal();//把状态改为报错状态
                }else{
                    state = WordState.FLOAT.ordinal();//转为浮点数状态
                    index++;//继续判断下一数字
                }
            }
        }
        if(state != WordState.ERROR.ordinal()){//不是错误状态说明就是正确状态
            wordStr=new String(chars,begin,index-begin);
            begin = index;
        }else{//报错状态
            wordStr=new String(chars,begin,index-begin+1);//把错误字符也包括进去
            begin = index + 1;
        }
        return new Word(wordStr,state);
    }

    /**
     * 这些特殊字符都被判断为结束字符
     * @param c 需判断的字符
     * @return 判断结果
     */
    private boolean isEnd(char c){
        return c == '\n' || c == '\t' || c == '\r' || c == ' ';
    }



}
