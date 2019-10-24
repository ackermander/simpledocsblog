package me.acmd.release.kits;

public class PathKit {
	  private static String path;
	  private static final String SCAN_PATH = "/WEB-INF/DOCS";
	  public static String getPath() { return path; }
	  public static void setPath(String path) { PathKit.path = path; }
	  public static String getScanPath() { return "/WEB-INF/DOCS"; }
}
