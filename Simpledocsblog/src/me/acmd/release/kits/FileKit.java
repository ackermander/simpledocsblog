package me.acmd.release.kits;

import com.google.gson.Gson;
import fr.opensagres.poi.xwpf.converter.core.ImageManager;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLConverter;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLOptions;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import me.acmd.release.kits.FileKit;
import me.acmd.release.kits.JsonKit;
import me.acmd.release.kits.PathKit;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class FileKit {
	private static HashMap<File, Long> files = new HashMap();

	private static XHTMLOptions opt;

	private static ImageManager im;

	public static Map<File, Long> scanNotConvert() {
		delConverted();
		File fold = new File(String.valueOf(PathKit.getPath()) + PathKit.getScanPath());
		System.out.println(fold);
		File[] fileList = fold.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				// TODO Auto-generated method stub
				if (pathname.getName().endsWith(".doc") || pathname.getName().endsWith(".docx")) {
					System.out.println(pathname.getName());
					return true;
				}
				else
					return false;
			}
		});
		HashMap<File, Long> fileMap = new HashMap<File, Long>();
		for (int i = 0; i < fileList.length; i++) {
			if (!files.containsKey(fileList[i]))
				fileMap.put(fileList[i], Long.valueOf(fileList[i].lastModified()));
		}
		return fileMap;
	}

	public static void initOption() {
		im = new ImageManager(new File(PathKit.getPath()), "media/");
		opt = XHTMLOptions.create().setImageManager(im);
	}

	private static XHTMLOptions getOption() {
		return opt;
	}

	public static void DOCX2HTM(Map<File, Long> fileMap) {
		InputStream in = null;
		
		
		for (Map.Entry<File, Long> set : fileMap.entrySet()) {
			File f = (File) set.getKey();
			try {
				in = new FileInputStream(f);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			if (in != null) {
				try {
					XWPFDocument doc = new XWPFDocument(in);
					opt = XHTMLOptions.create()
							.setImageManager(new ImageManager(new File(PathKit.getPath()), "media/" + f.getName()));
					OutputStream out = new FileOutputStream(new File(
							String.valueOf(PathKit.getPath()) + f.getName().substring(0, f.getName().lastIndexOf(".")))
							+ ".htm");
					XHTMLConverter.getInstance().convert(doc, out, opt);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		try {
			if (in != null)
				in.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * FILE 已扫描的文件\r Long 文件的创建时间
	 * 
	 * @return
	 */
	public static Map<File, Long> scanConverted() {
		Gson g = JsonKit.g;
		Map<File, Long> convertedFiles = new HashMap<File, Long>();
		File folder = new File(PathKit.getPath());
		File[] HTMFiles = folder.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				// TODO Auto-generated method stub
				if (pathname.getName().endsWith(".htm"))
					return true;
				else
					return false;
			}
		});
		byte b;
		int i;
		File[] arrayOfFile;
		for (File f : HTMFiles) {
			convertedFiles.put(f, Long.valueOf(f.lastModified()));
		}
		return convertedFiles;
	}

	public static void delConverted() {
		File folder = new File(PathKit.getPath());
		File[] HTMFiles = folder.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				// TODO Auto-generated method stub
				if (pathname.getName().endsWith(".htm"))
					return true;
				else
					return false;
			}
		});
		File[] fs = folder.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				// TODO Auto-generated method stub
				if (pathname.isDirectory() && !pathname.getName().endsWith("-INF"))
					return true;
				else
					return false;
			}
		});
		for (File f : HTMFiles) {
			f.delete();
		}

		for (File f: fs) {
			f.delete();
		}

	}
public static void main(String[] args) {
	scanNotConvert();
}

}
