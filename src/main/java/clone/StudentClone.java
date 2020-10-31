package clone;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/10/30
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 * @description 说明：
 **/
public class StudentClone implements Cloneable {


    private int number;

    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    protected StudentClone clone() {
        StudentClone studentClone = new StudentClone();
        try {
            studentClone = (StudentClone) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        if(address!=null){
            studentClone.setAddress(address.clone());
        }
        return studentClone;
    }

    public static void main(String[] args) {

        // 测试浅拷贝
        StudentClone studentClone = new StudentClone();
        studentClone.setNumber(1);
        long start = System.currentTimeMillis();

        StudentClone studentClone1 = studentClone.clone();
        long end = System.currentTimeMillis();
        System.out.println( end - start );

        System.out.println(studentClone.getNumber());
        System.out.println(studentClone1.getNumber());
        studentClone.setNumber(2);
        System.out.println(studentClone.getNumber());
        System.out.println(studentClone1.getNumber());


        // 测试浅拷贝
        StudentClone studentClone2 = new StudentClone();
        Address address1 = new Address();
        address1.setAdd("拷贝");
        studentClone2.setNumber(3);
        studentClone2.setAddress(address1);

        System.out.println(studentClone2.getAddress().getAdd());

        StudentClone studentClone3 = studentClone2.clone();
        System.out.println(studentClone3.getAddress().getAdd());
        address1.setAdd("深拷贝");
        System.out.println(studentClone2.getAddress().getAdd());
        System.out.println(studentClone3.getAddress().getAdd());

    }
}
