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
import java.util.regex.Pattern;

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
            return 1;
        }
        
        return 0;
    }
    public int CompressJS(String filePath,String fileName, String destination)
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
                // if line starts with "//" its a comment, we don't need to add it to the string.
                if(!line.startsWith("//"))
                {
                    sb.append(line);
                }   
            }
            text = sb.toString();
            
            //remove comments between /* and */
            char[] cs = text.toCharArray();
            StringBuilder sb2 = new StringBuilder();
            boolean skip = false;
            boolean inString = false;
            for(int i=0; i< cs.length; i++){
                char c = cs[i];
		int j = i + 1;	
		if(skip == false && (c == '\'' || c == '"')){
			inString = !inString;
		}	
		if(skip == false && c == '/' && cs[j] == '*' && inString == false){
			i = i + 2;
			skip = true;
		}
		if(skip == true && c == '*' && cs[j] == '/'){
			i = i + 2;
			if(i < cs.length){
				c = cs[i];
				skip = false;		
				j = i + 1;
				if(skip == false && c == '/' && cs[j] == '*'){
					i = i + 2;
					skip = true;
				}
			}
		}
		if(skip == false){
			sb2.append(c);
		}
	    }
            //System.out.println(sb2.toString());
            text = sb2.toString();
            
            //removing line breaks
            text = text.replace("\r", "");
            text = text.replace("\n", "");
            //removing many consecutives white spaces to only 1
            text = text.replace("  "," ").replace("   ", " ").replace("    ", " ").replace("     ", " ").replace("      ", " ").replace("       ", " ");
            text = text.replace(" ","");
            //removing tabs
            text = text.replace("\t","");
            //writing file
            fileName = fileName.replace(".js",".min.js");
            System.out.println(fileName);
            PrintWriter pw = new PrintWriter(destination+"/"+fileName);
            pw.write(text);
            pw.close();
            
        }
        catch(Exception e)
        {
            System.out.println(e.getStackTrace());
            return 1;
        }
        
        return 0;
    }
}
