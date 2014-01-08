package com.oracle;

import java.io.BufferedWriter;  
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.OutputStreamWriter;  
import java.util.regex.Matcher;  
import java.util.regex.Pattern;  
 
public class FunctionUtils  
{  
 
    /**
     * ƥ�䳬���ӵ�������ʽ  
     */ 
    private static String pat = "http://www\\.oschina\\.net/code/explore/.*/\\w+\\.[a-zA-Z]+";  
    private static Pattern pattern = Pattern.compile(pat);

    private static BufferedWriter writer = null;

    /**
     * �����������  
     */
    public static int depth = 0;
 
    /**
     * ��"/"���ָ�URL,��ó����ӵ�Ԫ��  
     *   
     * @param url  
     * @return  
     */
    public static String[] divUrl(String url)  
    {  
        return url.split("/");  
    }  
 
    /**  
     * �ж��Ƿ񴴽��ļ�  
     *   
     * @param url  
     * @return  
     */ 
    public static boolean isCreateFile(String url)  
    {  
        Matcher matcher = pattern.matcher(url);  
 
        return matcher.matches();  
    }  
 
    /**  
     * ������Ӧ�ļ�  
     *   
     * @param content  
     * @param urlPath  
     */ 
    public static void createFile(String content, String urlPath)  
    {  
        /* �ָ�url */
        String[] elems = divUrl(urlPath);
        StringBuffer path = new StringBuffer();
 
        File file = null;
        for (int i = 1; i < elems.length; i++)
        {  
            if (i != elems.length - 1)
            {  
 
                path.append(elems[i]);  
                path.append(File.separator);  
                file = new File("D:" + File.separator + path.toString());  
 
            }  
 
            if (i == elems.length - 1)
            {  
                Pattern pattern = Pattern.compile("\\w+\\.[a-zA-Z]+");  
                Matcher matcher = pattern.matcher(elems[i]);  
                if ((matcher.matches()))  
                {  
                    if (!file.exists())  
                    {  
                        file.mkdirs();  
                    }
                    String[] fileName = elems[i].split("\\.");  
                    file = new File("D:" + File.separator + path.toString()  
                            + File.separator + fileName[0] + ".txt");
                    try
                    {  
                        file.createNewFile();  
                        writer = new BufferedWriter(new OutputStreamWriter(  
                                new FileOutputStream(file)));  
                        writer.write(content);  
                        writer.flush();  
                        writer.close();
                        System.out.println("�����ļ��ɹ�");
                    } catch (IOException e)
                    {  
                        e.printStackTrace();  
                    }  
 
                }  
            }  
 
        }  
    }  
 
    /**  
     * ��ȡҳ��ĳ����Ӳ�����ת��Ϊ��ʽ��A��ǩ  
     *   
     * @param href  
     * @return  
     */ 
    public static String getHrefOfInOut(String href)  
    {  
        /* ���ⲿ��������ת��Ϊ���������Ӹ�ʽ */ 
        String resultHref = null;  
 
        /* �ж��Ƿ�Ϊ�ⲿ���� */ 
        if (href.startsWith("http://"))  
        {  
            resultHref = href;
        } else 
        {  
            /* ������ڲ�����,�򲹳����������ӵ�ַ,�����ĸ�ʽ���Բ�����,�磺a href="#" */ 
            if (href.startsWith("/"))  
            {  
                resultHref = "http://www.oschina.net" + href;  
            }  
        }  
 
        return resultHref;  
    }  
 
    /**  
     * ��ȡ��ҳ��ҳԴ�ļ���Ŀ������  
     *   
     * @param content  
     * @return  
     */ 
    public static String getGoalContent(String content)  
    {  
        int sign = content.indexOf("<pre class=\"");  
        String signContent = content.substring(sign);  
 
        int start = signContent.indexOf(">");  
        int end = signContent.indexOf("</pre>");  
 
        return signContent.substring(start + 1, end);  
    }  
 
    /**  
     * �����ҳԴ�ļ����Ƿ���Ŀ���ļ�  
     * @param content  
     * @return  
     */ 
    public static int isHasGoalContent(String content)  
    {  
        return content.indexOf("<pre class=\"");  
    }  
 
} 