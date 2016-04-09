package cn.crap.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class Html2Pdf {
    /**
     * Creates a PDF with the words "Hello World"
     * @param file
     * @throws Exception 
     */
    public static String createPdf(HttpServletRequest request,String interFaceId) throws Exception {
    	String destDir = Tools.getServicePath(request)+"resources/upload/pdf";
    	if(!new File(destDir).exists()){  
    		new File(destDir).mkdirs();  
	     } 
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(destDir+"/temp.pdf"));
        // step 3
        document.open();
        // step 4
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, HttpPostGet.GetString(Cache.getSetting(Const.SETTING_DOMAIN).getValue()+
        		"/interface/detail/pdf.do?id="+interFaceId), Charset.forName("UTF-8"));
        // step 5
        document.close();
        return destDir+"/temp.pdf";
    }
  
}

