package com.util.documentutil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 * 操作Word文档的工具类
 *	参照官方网站http://poi.apache.org/
 */
public class WordUtil {      
	public static void main(String[] args) throws IOException{
		System.out.println(WordUtil.getSectionNum("F:/test.doc"));
		WordUtil.readDoc("F:/test.doc");
		WordUtil.writeDoc("F:/test1.doc");
	}
	
	/**
	 * 读WORD文档
	 * @param filepath
	 */
	public static void readDoc(String filepath){
		 FileInputStream in=null;
		 WordExtractor extractor=null;
		try {
			in = new FileInputStream(filepath);
			extractor = new WordExtractor(in);
			String [] strArray = extractor.getParagraphText();
			for (int i = 0; i < strArray.length; i++) {
				//按WORD文档的段落读取文件
				String duanluo=strArray[i];
				System.out.println(duanluo);
			}
			//全文
			String quanwen =  extractor.getText();
			System.out.println(quanwen);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 获取WORD文档的段落数
	 * @return
	 */
	public static int getSectionNum(String filename){
		 HWPFDocument doc=null;
		 int numP=0;
			try {
				doc = new HWPFDocument(new FileInputStream(filename));
				 Range range = doc.getRange();
				 numP = range.numParagraphs();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		return numP;
	}
	
	public static void writeDoc(String filename){
		XWPFDocument doc=new XWPFDocument();
		XWPFParagraph paragraph=doc.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.CENTER);
		paragraph.setBorderBottom(Borders.DOUBLE);
		paragraph.setBorderLeft(Borders.DOUBLE);
		paragraph.setBorderRight(Borders.DOUBLE);
		paragraph.setBorderTop(Borders.DOUBLE);
		paragraph.setBorderBetween(Borders.SINGLE);
		paragraph.setVerticalAlignment(TextAlignment.TOP);
		XWPFRun r1=paragraph.createRun();
		r1.setBold(true);
		r1.setText("hi  kaka pirlo rooney 你好吗世界");
		r1.setFontFamily("楷体");
		r1.setUnderline(UnderlinePatterns.DOT_DOT_DASH);
		r1.setTextPosition(100);
		FileOutputStream output=null;
		try {
			output=new FileOutputStream(filename);
			doc.write(output);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
