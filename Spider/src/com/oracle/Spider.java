package com.oracle;

import java.sql.SQLException;  

import com.oracle.UrlDataHanding;  
import com.oracle.UrlQueue;  
 
public class Spider 
{  
  public static void main(String[] args) throws SQLException
  {  
      String url0 ="http://www.oschina.net/code/explore/achartengine/client/AndroidManifest.xml";  
      String url1 ="http://www.oschina.net/code/explore";
      String url2 ="http://www.oschina.net/code/explore/achartengine";
      String url3 ="http://www.oschina.net/code/explore/achartengine/client";
      
      UrlQueue.addElem(url0);
      UrlQueue.addElem(url1);
      UrlQueue.addElem(url2);
      UrlQueue.addElem(url3);
        
      UrlDataHanding[] url_Handings = new UrlDataHanding[10];
        
          for(int i = 0 ; i < 10 ; i++)  
          {  
              url_Handings[i] = new UrlDataHanding();  
              new Thread(url_Handings[i]).start();  
          }  
 
  }  
} 