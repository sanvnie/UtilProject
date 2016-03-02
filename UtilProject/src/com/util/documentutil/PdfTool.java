package com.util.documentutil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.corba.se.impl.io.FVDCodeBaseImpl;

/**
 * 操作PDF的工具类
 *详见：http://rensanning.iteye.com/blog/1538689
 */
public class PdfTool {
	
	/**
	 * 生成一个PDF文件
	 * @param filename 文件名（含路径）
	 * @throws DocumentException 
	 * @throws FileNotFoundException 
	 */
	public static void createPDF(String filename) throws FileNotFoundException, DocumentException{
		Document doc=new Document();
		PdfWriter.getInstance(doc, new FileOutputStream(filename));
		doc.open();
		doc.add(new Paragraph("Hello World"));
		doc.close();
	}
	
	/**
	 * 生成带样式的PDF文件（大小，背景色，文档属性等）
	 * @param filename
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 */
	public static void setPdfStyle(String filename) throws FileNotFoundException, DocumentException{
		//页面大小  
		Rectangle rect = new Rectangle(PageSize.B5.rotate());  
		//页面背景色  
		rect.setBackgroundColor(BaseColor.ORANGE);  
		Document doc = new Document(rect);   
		PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(filename));  
		//PDF版本(默认1.4)  
		writer.setPdfVersion(PdfWriter.PDF_VERSION_1_2);   
		//文档属性  
		doc.addTitle("Title@sample");  
		doc.addAuthor("Author@rensanning");  
		doc.addSubject("Subject@iText sample");  
		doc.addKeywords("Keywords@iText");  
		doc.addCreator("Creator@iText");  	  
		//页边空白  
		doc.setMargins(10, 20, 30, 40);  

		doc.open();  
		doc.add(new Paragraph("Hello World"));  
		doc.close();
	}
	
	/**
	 * 往一个PDF中添加一页
	 * @param filename PDF文件名
	 * @throws DocumentException 
	 * @throws FileNotFoundException 
	 */
	public static void addNewPage(String filename) throws FileNotFoundException, DocumentException{
		Document doc=new Document();
		PdfWriter writer=PdfWriter.getInstance(doc, new FileOutputStream(filename));
		doc.open();
		doc.newPage();
		writer.setPageEmpty(false);
		doc.newPage();
		doc.add(new Paragraph("第二页"));
		doc.close();
		
	}
	
	public static void main(String[] args) {
		String filename="d:\\aa.pdf";
		try {
			PdfTool.addNewPage(filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
