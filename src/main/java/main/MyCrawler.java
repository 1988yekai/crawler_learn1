package main;

import commom.DownLoadFile;
import commom.HtmlParserTool;
import commom.LinkFilter;
import commom.LinkQueue;

import java.util.Set;

/**
 * Created by Administrator on 2016/4/21.
 */
public class MyCrawler {
    /**
     * 使用种子初始化 url 队列
     * @param seeds 种子url
     */
    private void initCrawlerWithSeeds(String[] seeds){
        for (int i = 0; i < seeds.length; i++)
            LinkQueue.addUnvisitedUrl(seeds[i]);
    }

    /**
     * 抓取过程
     * @param seeds
     */
    public void crawling( String[] seeds) throws Exception {
        LinkFilter filter = new LinkFilter() {
            public boolean accept(String url) {
                if (url.startsWith("http://www.baidu.com") ||url.startsWith("https://www.sogou.com"))
                    return true;
                else
                    return false;
            }
        };
        //初始化url队列
        initCrawlerWithSeeds(seeds);
        //循环条件：待抓取的链接不为空且 不多于1000
        while(!LinkQueue.unVisitedUrlIsEmpty() && LinkQueue.getVisitedUrlNum() <= 100){
            //队列头 url 出列
            String visitUrl = (String) LinkQueue.unVisitedUrlDeQueue();
            if(visitUrl == null && "".equals(visitUrl))
                continue;
            DownLoadFile downLoadFile = new DownLoadFile();
            //下载网页
            String filePath = downLoadFile.downloadFile(visitUrl);
            //放入已访问url中
            LinkQueue.addVisitedUrl(visitUrl);
            System.out.println(filePath);
            if(filePath != null && !"".equals(filePath)) {
                //提取出下载网页中的url
                Set<String> links = HtmlParserTool.extracLinks(visitUrl, filter);
                for (String link : links)
                    LinkQueue.addUnvisitedUrl(link);
            }
        }

    }
    public static void main(String[] args){
        MyCrawler crawler = new MyCrawler();
        try {
            crawler.crawling(new String[] {"http://www.baidu.com"});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
