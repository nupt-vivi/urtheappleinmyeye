package com.oracle;

public class HrefOfPage  
{  
    /**  
     * ���ҳ��Դ�����г�����  
     */ 
    public static void getHrefOfContent(String content)  
    {  
        System.out.println("��ʼ");  
        String[] contents = content.split("<a href=\"");
        for (int i = 1; i < contents.length; i++)  
        {  
            int endHref = contents[i].indexOf("\"");

            String aHref = FunctionUtils.getHrefOfInOut(contents[i].substring(endHref));

            if (aHref != null)
            {
                String href = FunctionUtils.getHrefOfInOut(aHref);
 
                if (!UrlQueue.isContains(href)
                        && href.indexOf("/code/explore") != -1 
                        && !VisitedUrlQueue.isContains(href))
                {  
                    UrlQueue.addElem(href);  
                }  
            }  
        }  
 
        System.out.println(UrlQueue.size() + "--ץȡ����������");  
        System.out.println(VisitedUrlQueue.size() + "--�Ѵ����ҳ����");  
 
    }  
 
} 