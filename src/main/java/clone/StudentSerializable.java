package clone;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 类功能说明: 序列化方式拷贝流
 * 类修改者	创建日期2020/10/30
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 * @description 说明：
 **/
public class StudentSerializable implements Serializable {

    private static final long serialVersionUID = -3564386270244211766L;
    private int number;

    private List<String> list = new ArrayList<>();

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public static void main(String[] args) throws IOException {
        StudentSerializable studentSerializable = new StudentSerializable();
        studentSerializable.setNumber(2);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream streamWriter = new ObjectOutputStream(byteArrayOutputStream);
        streamWriter.writeObject(studentSerializable);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

        try {
            StudentSerializable clone =  (StudentSerializable)objectInputStream.readObject();
            List<String> list = studentSerializable.getList();
            list.add("sdsd");
            clone.getNumber();
            System.out.println(clone.getList().size());
            System.out.println(clone.getNumber());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
