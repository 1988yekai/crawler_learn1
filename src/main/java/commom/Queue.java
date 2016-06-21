package commom;

import java.util.LinkedList;

/**
 * 队列，保存将要访问的url
 * Created by Administrator on 2016/4/20.
 */
public class Queue {
    //使用链表实现队列
    private LinkedList queue = new LinkedList();

    /**
     * 入列
     * @param t
     */
    public void enQueue(Object t) {
        queue.addLast(t);
    }
    /**
     * 出列
     */
    public Object deQueue() {
        return queue.removeFirst();
    }

    /**
     * 判空
     * @return
     */
    public boolean isQueueEmpty() {
        return queue.isEmpty();
    }

    /**
     * 判包含
     * @param t
     * @return
     */
    public boolean isContains(Object t){
        return queue.contains(t);
    }

    /**
     * 清空
     */
    public void clearQueue(){
        queue.clear();
    }
}
