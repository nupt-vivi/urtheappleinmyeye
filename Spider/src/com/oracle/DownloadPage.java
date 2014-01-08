package com.oracle;

import java.io.IOException;  
import org.apache.http.HttpEntity;  
import org.apache.http.HttpResponse;  
import org.apache.http.client.ClientProtocolException;  
import org.apache.http.client.HttpClient;  
import org.apache.http.client.methods.HttpGet;  
import org.apache.http.impl.client.DefaultHttpClient;  
import org.apache.http.util.EntityUtils;  
  
public class DownloadPage  
{  
  
    /**  
     * ����URLץȡ��ҳ����  
     *   
     * @param url  
     * @return  
     */ 
    public static String getContentFormUrl(String url)  
    {  
        /* ʵ����һ��HttpClient�ͻ��� */ 
        HttpClient client = new DefaultHttpClient();
        HttpGet getHttp = new HttpGet(url);
  
        String content = null;

        HttpResponse response;
        try 
        {  
            /*�����Ϣ����*/ 
            response = client.execute(getHttp);  
            HttpEntity entity = response.getEntity();  
  
            VisitedUrlQueue.addElem(url);  
  
            if (entity != null)  
            {  
                /* ת��Ϊ�ı���Ϣ */ 
                content = EntityUtils.toString(entity);  
  
                /* �ж��Ƿ����������ҳԴ���뵽���ص����� */ 
                if (FunctionUtils.isCreateFile(url)  
                        && FunctionUtils.isHasGoalContent(content) != -1)  
                {  
                    FunctionUtils.createFile(FunctionUtils  
                            .getGoalContent(content), url);  
                }  
            }  
  
        } catch (ClientProtocolException e)  
        {  
            e.printStackTrace();  
        } catch (IOException e)  
        {  
            e.printStackTrace();  
        } finally 
        {  
            client.getConnectionManager().shutdown();  
        }  
          
        return content;  
    }  
  
} 