package com.springboot.demo;

import org.apache.commons.lang.StringEscapeUtils;
import org.junit.Test;

public class tests {
    @Test
    public void test01(){
        new Thread(() ->
        {
            //使用lambda方式启动个新线程
            System.out.print("使用lambda方式启动个新线程");

        }
        ).start();

    }
    @Test
    public void test02(){
        System.out.println("测试方法");
    }
    @Test
    public void test03(){

        String str1 = "{\"resourceId\":\"dfead70e4ec5c11e43514000ced0cdcaf\",\"properties\":{\"process_id\":\"process4\",\"name\":\"\",\"documentation\":\"\",\"processformtemplate\":\"\"}}";
        String tmp = StringEscapeUtils.unescapeJavaScript(str1);
        System.out.println("tmp:" + tmp);

    }
}
