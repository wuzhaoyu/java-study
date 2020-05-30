package com.wzy.Enum;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/3/26
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 **/
public class Life1 {


    private LifeTimes time;

    public enum LifeTimes{
        INIT(0){
            @Override
            public boolean isInit(){
                return true;
            };
        },
        MIDDEL(1){
            @Override
            public boolean isMiddel(){
                return true;
            };
        },
        END(2){
            @Override
            public boolean isEnd(){
                return true;
            };
        };

        private int status;

        public boolean isInit(){
            return false;
        };
        public boolean isMiddel(){
            return false;
        };
        public boolean isEnd(){
            return false;
        };

        public int getStatus() {
            return status;
        }

         LifeTimes(int status){
            this.status = status;
        }
    }



    public LifeTimes getTimes() {
        return time;
    }

    public void setTimes(LifeTimes time) {
        this.time = time;
    }

    public int currentTimes(){
        return this.time.getStatus();
    }
}
