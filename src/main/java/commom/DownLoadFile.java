package commom;


import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;

/**
 *
 * Created by Administrator on 2016/4/20.
 */
public class DownLoadFile {
    /**
     * 根据URL 和网页类型生成 要保存的文件名，去除URL中的非文件名字符
     */
    public String getFileNameByUrl(String url, String contentType) {
        // remove http:\\
        url = url.substring(7);
        // if file is html
        if (contentType.indexOf("html") != -1) {
            url = url.replaceAll("[\\?:/*|<>\"]", "_") + ".html";
            return url;
        } else
        //file not html
        return url.replaceAll("[\\?:/*|<>\"]", "_") + "." +
                contentType.substring(contentType.lastIndexOf("/") + 1);
    }

    /**
     * 保存网页到本地
     */
    public void saveToLocal(InputStream is, String filePath) throws IOException{
        File file = new File(filePath);
//        System.out.println(file.getAbsolutePath());
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file.getAbsoluteFile()));
        int byteRead = 0;
        while ((byteRead = is.read()) != -1){
            out.write(byteRead);
        }
        out.flush();
        out.close();
        is.close();
    }

    //下载URL指定的网页
    public String downloadFile(String url) throws IOException{
        String filePath = null;

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);
        CloseableHttpResponse response = httpclient.execute(httpget);
        try {
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
                System.err.println("method failed " + response.getStatusLine());
                return null;
            }
            HttpEntity entity = response.getEntity();
            InputStream is = entity.getContent();
            filePath = "temp/" + getFileNameByUrl(url,entity.getContentType().getValue());

            saveToLocal(is,filePath);
        } finally {
            response.close();
        }
        return filePath;
    }

}