package commom;

import java.util.HashSet;
import java.util.Set;

/**
 * 队列，保存已访问过的url
 * Created by Administrator on 2016/4/20.
 */
public class LinkQueue {
    //已访问过的url
    private static Set visitedUrl = new HashSet();
    //待访问的url
    private static Queue unVisitedUrl = new Queue();

    public static Queue getUnVisitedUrl() {
        return unVisitedUrl;
    }
    //添加到访问过的URL中
    public static  void addVisitedUrl(String url){
        visitedUrl.add(url);
    }
    //移除访问过的url
    public static void removeVisitedUrl(String url) {
        visitedUrl.remove(url);
    }
    //未访问的URL出列
    public static Object unVisitedUrlDeQueue() {
        return unVisitedUrl.deQueue();
    }
    //保证每个URL只能被访问一次
    public static void addUnvisitedUrl(String url){
        if(url != null && !"".equals(url.trim())
                && !visitedUrl.contains(url)
                && !unVisitedUrl.isContains(url))
            unVisitedUrl.enQueue(url);
    }
    //已访问的URL数目
    public static int getVisitedUrlNum() {
        return visitedUrl.size();
    }
    //判断未访问的URL是否为空
    public static boolean unVisitedUrlIsEmpty(){
        return unVisitedUrl.isQueueEmpty();
    }

}
