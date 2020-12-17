package proxy.cglib;

import net.sf.cglib.beans.ImmutableBean;

/**
 * 类功能说明:  不可修改代理对象，只能修改原始对象
 * 类修改者	创建日期2020/12/17
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 * @description 说明：
 **/
public class ImmutableSampleBean {
    private String value;

    public ImmutableSampleBean() {
    }

    public ImmutableSampleBean(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static void main(String[] args) {
        ImmutableSampleBean sampleBean = new ImmutableSampleBean("aa");
        ImmutableSampleBean o = (ImmutableSampleBean) ImmutableBean.create(sampleBean);
        System.out.println(sampleBean.getValue());
        sampleBean.setValue("333");
        System.out.println(sampleBean.getValue());
        o.setValue("222");

    }
}
