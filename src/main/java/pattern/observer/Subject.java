package pattern.observer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 类功能说明:  主体目标
 * 类修改者	创建日期2021/1/31
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 * @description 说明：
 **/
public class Subject {

    static List<Observer> observers = new ArrayList<>();

    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        this.notifyAllObserver();
    }

    public void add(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObserver(){
        for (Observer observer : observers) {
            observer.update();
        }
    }


    public static void main(String[] args) {

        Subject subject = new Subject();
        new ObserverTwo(subject);
        new ObserverOne(subject);
        subject.setState(1);
        subject.setState(2);

        LocalDate parse = LocalDate.parse("2020-11-31", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(parse);
    }


}
