package clone;

import java.io.*;

/**
 * 类功能说明: 克隆效率
 * 类修改者	创建日期2020/10/31
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 * @description 说明：
 **/
public class CloneEfficiency  {

    public static void main(String[] args) {
        // 测试浅拷贝
        StudentClone studentClone = new StudentClone();
        studentClone.setNumber(1);
        long start = System.currentTimeMillis();
        studentClone.clone();
        long end = System.currentTimeMillis();
        System.out.println("重新clone" + end + "- " +start + "-" + (end - start));

        try {

        StudentReflect studentReflect = new StudentReflect();
        studentReflect.setNumber(1);
        long start1 = System.currentTimeMillis();
        studentReflect.copy(studentReflect);
        long end1 = System.currentTimeMillis();
        System.out.println("反射" + (end1 - start1));

        StudentSerializable studentSerializable = new StudentSerializable();
        studentSerializable.setNumber(2);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream streamWriter = new ObjectOutputStream(byteArrayOutputStream);
        streamWriter.writeObject(studentSerializable);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        long start2 = System.currentTimeMillis();
         objectInputStream.readObject();
        long end2 = System.currentTimeMillis();
        System.out.println("转换流" + (end2 - start2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
