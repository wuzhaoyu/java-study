package clone;

import java.lang.reflect.Field;

/**
 * 类功能说明: 反射的方式
 * 类修改者	创建日期2020/10/30
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 * @description 说明：
 **/
public class StudentReflect {

    private int number;


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public StudentReflect copy(StudentReflect source) throws IllegalAccessException, InstantiationException {
        Class<? extends StudentReflect> aClass = source.getClass();
            StudentReflect target = aClass.newInstance();
            for (Field field : aClass.getDeclaredFields()) {
                Object o = field.get(source);
                field.set(target,o);
            }
            return target;
    }

    public static void main(String[] args) {
        StudentReflect studentReflect = new StudentReflect();
        studentReflect.setNumber(1);
        try {
            StudentReflect copy = studentReflect.copy(studentReflect);
            System.out.println(copy.getNumber());
            studentReflect.setNumber(2);
            copy.setNumber(2);
            System.out.println(copy.getNumber());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }


    }
}
