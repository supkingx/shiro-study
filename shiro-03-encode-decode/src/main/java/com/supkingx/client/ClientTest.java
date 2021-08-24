package com.supkingx.client;

import com.supkingx.tools.EncodesUtil;
import org.junit.Test;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/8/24
 */
public class ClientTest {

    @Test
    public void testHex(){
        String val = "hello";
        String flag = EncodesUtil.encodeHex(val.getBytes());
        String result = new String(EncodesUtil.decodeHex(flag));
        System.out.println(result);
    }

    @Test
    public void testBase64(){
        String val = "hello";
        String flag = EncodesUtil.encodeBase64(val.getBytes());
        String result = new String(EncodesUtil.decodeBase64(flag));
        System.out.println(result);
    }
}
