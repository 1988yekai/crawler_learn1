package commom;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/4/20.
 */
public class DownLoadFileTest {

    @org.junit.Test
    public void testGetFileNameByUrl() throws Exception {

    }

    @org.junit.Test
    public void testSaveToLocal() throws Exception {
        String url = "https://www.baidu.com/index.html";
        url = url.substring(7);
        url = url.replaceAll("[\\?/:*|<>\"]", "_") + ".html";
        System.out.println(url);
    }

    @org.junit.Test
    public void testDownloadFile() throws Exception {
        DownLoadFile d = new DownLoadFile();
        d.downloadFile("https://www.sogou.com");
    }

    @Test
    public void httpclientTest() throws Exception{
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("http://www.baidu.com");
        CloseableHttpResponse response = httpclient.execute(httpget);
        try {

            HttpEntity entity = response.getEntity();
            InputStream is = entity.getContent();
            FileOutputStream fos = new FileOutputStream("temp/1.html");
            int len = -1;
            while ((len=is.read()) != -1){
                fos.write(len);
            }
            fos.flush();
            fos.close();
            is.close();
        } finally {
            response.close();
        }
    }
}