package com.util.documentutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


/**
 * Zip压缩解压工具类
 * 
 */
public class ZipUtil {
	
 
	/**
	 * 解压缩
	 * @param zipFileName 压缩文件
	 * @param outputDirectory  解压缩输出目录
	 * @throws Exception
	 */
	public void unzip(String zipFileName, String outputDirectory) throws Exception {
		ZipInputStream in = new ZipInputStream(new FileInputStream(zipFileName));
		ZipEntry z;
		while ((z = in.getNextEntry()) != null) {
			System.out.println("正在解压缩 " + z.getName());
			if (z.isDirectory()) {
				String name = z.getName();
				name = name.substring(0, name.length() - 1);
				File folder=new File(outputDirectory);
				if(!folder.exists()){
					folder.mkdirs();
				}
				File f = new File(outputDirectory + File.separator + name);
				f.mkdir();
				System.out.println("正在创建目录 " + outputDirectory + File.separator + name);
			} else {
				File f = new File(outputDirectory + File.separator + z.getName());
				f.createNewFile();
				FileOutputStream out = new FileOutputStream(f);
				int b;
				while ((b = in.read()) != -1)
					out.write(b);
				out.close();
			}
		}
		in.close();
	    System.out.println("解压缩完成");
	}

	/**
	 * 压缩
	 * @param zipFileName 压缩输出文件路径
	 * @param inputDirectory 待压缩的输入文件路径
	 * @throws Exception
	 */
	public void zip(String zipFileName, String inputDirectory) throws Exception {
		zip(zipFileName, new File(inputDirectory));
	}

	public void zip(String zipFileName, File inputFile) throws Exception {
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
		zip(out, inputFile, "");
        System.out.println("压缩完成");
		out.close();
	}

	public void zip(ZipOutputStream out, File f, String base) throws Exception {
		System.out.println("正在压缩  " + f.getName());
		if (f.isDirectory()) {
			File[] fl = f.listFiles();
			out.putNextEntry(new ZipEntry(base + "/"));
			base = base.length() == 0 ? "" : base + "/";
			for (int i = 0; i < fl.length; i++) {
				zip(out, fl[i], base + fl[i].getName());
			}
		} else {
			out.putNextEntry(new ZipEntry(base));
			FileInputStream in = new FileInputStream(f);
			int b;
			while ((b = in.read()) != -1)
				out.write(b);
			in.close();
		}
	}

	public static void main(String[] args) {
		try {
			ZipUtil t = new ZipUtil();
			//t.zip("c:\\01.zip", "c:\\01");
			t.unzip("c:\\01.zip", "c:\\01");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
