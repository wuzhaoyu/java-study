package com.wzy.Enum;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/3/26
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 **/
public class TestCLass {

    public static void main(String[] args) {
        Life life = new Life();
        life.setTimes(Life.LifeTimes.END);

//        System.out.println(life.getTimes() == Life.LifeTimes.END);
//        System.out.println(life.getTimes() .equals( Life.LifeTimes.END));


        Life1 life1 = new Life1();
        life1.setTimes(Life1.LifeTimes.INIT);

        System.out.println(life1.currentTimes());
        System.out.println(Life1.LifeTimes.END.getStatus());

    }
}
