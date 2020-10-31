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
public class Address implements Cloneable {
    private String add;

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    @Override
    protected Address clone() {
        Address address = new Address();
        try {
            address = (Address) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return address;

    }
}
