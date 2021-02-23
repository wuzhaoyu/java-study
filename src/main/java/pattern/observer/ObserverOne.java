package pattern.observer;

/**
 * 类功能说明:
 * 类修改者	创建日期2021/1/31
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 * @description 说明：
 **/
public class ObserverOne extends Observer {

    public ObserverOne(Subject subject){
        this.subject = subject;
        subject.add(this);
    }
    @Override
    public void update() {
        System.out.println( "ObserverOne: "
                + Integer.toOctalString( subject.getState() ) );
    }
}
