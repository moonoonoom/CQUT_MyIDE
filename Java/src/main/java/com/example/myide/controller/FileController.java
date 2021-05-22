package com.example.myide.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

/**
 * 文件处理Controller
 */
@RestController
public class FileController {

    @CrossOrigin //解决跨域问题
    @PostMapping(value = "api/File/fileContent")  //映射post请求
    //@ResponseBody //将controller的方法返回的对象通过适当的转换器转换为指定的格式之后，写入到response对象的body区，通常用来返回JSON数据或者是XML数据，
    //需要注意的呢，在使用此注解之后不会再走试图处理器，而是直接将数据写入到输入流中，他的效果等同于通过response对象输出指定格式的数据
    //RequestBody用来接收前端传递给后端的json字符串中的数据
    public String getFileContent(@RequestBody String str){
        return "11";
    }


}
