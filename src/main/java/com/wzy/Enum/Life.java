package com.wzy.Enum;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/3/26
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 **/
public class Life {

    private LifeTimes time;

    public enum LifeTimes{
        INIT,
        MIDDEL,
        END
    }



    public LifeTimes getTimes() {
        return time;
    }

    public void setTimes(LifeTimes time) {
        this.time = time;
    }
}
