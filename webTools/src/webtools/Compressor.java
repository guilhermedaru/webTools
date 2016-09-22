/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webtools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 *
 * @author gdaru
 */
public class Compressor {
    public int CompressCSS(String filePath,String fileName, String destination)
    {
        BufferedReader br = null;
        String text = "";
        
        try{
            //Reading file
            br = new BufferedReader(new FileReader(filePath));
            StringBuilder sb = new StringBuilder();
            String line;
            while((line=br.readLine())!= null)
            {
                sb.append(line);
            }
            text = sb.toString();
            //removing line breaks
            text = text.replace("\r", "");
            text = text.replace("\n", "");
            //removing white spaces
            text = text.replace(" ","");
            //removing tabs
            text = text.replace("\t","");
            //writing file
            fileName = fileName.replace(".css",".min.css");
            PrintWriter pw = new PrintWriter(destination+"/"+fileName);
            pw.write(text);
            pw.close();
            
        }
        catch(Exception e)
        {
            System.out.println(e.getStackTrace());
        }
        
        return 0;
    }
    public int CompressJS(String fileName, String destination)
    {
        
        return 0;
    }
}
