package com.xbwl.demo.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * <p> 类说明  序列化与反序列化工具</p>
 * @author lib
 * Date: 2015年4月15日
 * @version 1.01
 *
 */
public class SerializeUtil {
	
	public static byte[] getByte(String str){
		return str.getBytes();
	}
	public static String toString(byte[] bytes){
		return new String(bytes);
	}
	
    /**
     * serialize 方法
     * <p>方法说明</p>
     * @param object
     * @return
     */
    public static byte[] serialize(Object object) {
    	ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            // 序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * unserialize 方法
     * <p>方法说明</p>
     * @param bytes
     * @return
     */
    public static Object unserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            // 反序列化
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {

        }
        return null;
    }
}