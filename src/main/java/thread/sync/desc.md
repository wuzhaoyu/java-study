- 对象头
  - 普通对象
  - 数组对象 存储对象的头
  - Mark Word (对象获取锁会将地址指向monitor,锁状态为重量级锁)
 
- Monitor锁
  - WaitSet
  - EntrySet
  - Own

- 轻量级锁 （无竞争）
  - 对象与线程关联（lock record 地址 00  cas 对象头 01 ; Object Reference 指向加锁对象）
  - 发生竞争 同一对象发生锁重入（lock record 地址为null;Object Reference 指向加锁对象 ）,并添加一条Lock Record
  非同对象 进入锁膨胀
  - 解锁 轻量级锁解锁，发生锁重入将lock record地址为nul 的所记录删除；不为null 通过cas将对象头交换，并删除所记录。
  
 - 锁膨胀
   - 对同一个对象发生竞争
   - 申请monitor锁，对象头的轻量级锁地址，会被更换为monitor地址，并且阻塞线程进入entrySet队列
 
 - 自旋优化
   - 避免阻塞，阻塞会导致线程的上下文切换，性能受损
   - 重量级锁竞争，未持有锁不会立马进入阻塞队列，可以不断自旋重试
   - 自旋重试 占用CPU 所以适合多核

 - 偏向锁
  - 轻量级锁重入，每一次都需要cas比较lock record 与对象头。 
  - 将mark word地址替换为线程ID
