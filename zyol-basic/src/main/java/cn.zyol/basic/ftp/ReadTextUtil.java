package cn.zyol.basic.ftp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ReadTextUtil {

    /**
     * 读取txt文件的内容
     * 
     * @param file 想要读取的文件对象
     * @return 返回文件内容
     */
    public static String txt2String(File file) {
        String result = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            while ((s = br.readLine()) != null) {
                result = result + "\n" + s;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除单个文件
     * 
     * @param sPath 被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    /**
     * 获取指定目录下所有文件名称
     * 
     * @param path
     * @return
     */
    public static List<String> getFile(String path) {
        List<String> fileList = new ArrayList<String>();
        File file = new File(path);
        File[] array = file.listFiles();
        for (int i = 0; i < array.length; i++) {
            if (array[i].isFile()) {
                String fileName = array[i].getName();
                // System.out.println("*****" + array[i].getPath());
                if (fileName.endsWith("txt")) {
                    fileList.add(array[i].getName());
                }
            } else if (array[i].isDirectory()) {
                getFile(array[i].getPath());
            }
        }
        return fileList;
    }

    /**
     * 文件重命名
     * 
     * @param path 文件目录
     * @param oldname 原来的文件名
     * @param newname 新文件名
     */
    public static void renameFile(String path, String oldname, String newname) {
        if (!oldname.equals(newname)) {
            File oldfile = new File(path + "/" + oldname);
            File newfile = new File(path + "/" + newname);
            if (!oldfile.exists()) {
                return;
            }
            oldfile.renameTo(newfile);
        }
    }

    public static void main(String[] args) {
        // String filePath = "D:\\datatest\\000001OF.txt";
        // File file = new File(filePath);
        // System.out.println(txt2String(file));
        String filePath = "D:\\datatest\\";
        List<String> fileList = getFile("D:\\datatest");
        // System.out.println("deleteFile====" + deleteFile(filePath));
        for (String fileName : fileList) {
            System.out.println("===== fileName ============" + fileName);
            File file = new File(filePath + fileName);
            System.out.println(txt2String(file));
            renameFile(filePath, fileName, fileName + ".tmp");
        }
    }
}
