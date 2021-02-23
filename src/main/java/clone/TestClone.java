package clone;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import java.io.*;
import java.util.Hashtable;
import java.util.TreeMap;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 类功能说明:
 * 类修改者	创建日期2021/1/23
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 * @description 说明：
 **/
public class TestClone {

    public static void streamCopy() throws IOException, ClassNotFoundException {

        StudentReflect source = new StudentReflect();
        source.setName("吴兆玉");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(source);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

        StudentReflect target = (StudentReflect)objectInputStream.readObject();
        System.out.println(target.getName());

//        ConcurrentHashMap
        Hashtable hashtable = new Hashtable();
        TreeMap treeMap = new TreeMap();
        Vector vector =new Vector();

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        streamCopy();
    }



}
