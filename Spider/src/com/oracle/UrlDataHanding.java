package com.oracle;

public class UrlDataHanding implements Runnable  
{  
    /**  
     * ���ض�Ӧҳ�沢������ҳ���Ӧ��URL����δ���ʶ����С�
     * @param url
     */
    public void dataHanding(String url)
    {
        HrefOfPage.getHrefOfContent(DownloadPage.getContentFormUrl(url));
    }
          
    public void run()  
    {  
        while(!UrlQueue.isEmpty())  
        {  
           dataHanding(UrlQueue.outElem());  
        }  
    }  
} 