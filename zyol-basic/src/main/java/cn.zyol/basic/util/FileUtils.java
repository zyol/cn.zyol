package cn.zyol.basic.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class FileUtils {

    public static byte[] getBytesFromFile(File f) {
        if (f == null) {
            return null;
        }
        try {
            FileInputStream stream = new FileInputStream(f);
            ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = stream.read(b)) != -1) {
                out.write(b, 0, n);
            }
            stream.close();
            out.close();
            return out.toByteArray();
        } catch (IOException e) {
        }
        return null;
    }

    public static File getFileFromBytes(byte[] b, String outputFile) {
        BufferedOutputStream stream = null;
        File file = null;
        try {
            file = new File(outputFile);
            FileOutputStream fstream = new FileOutputStream(file);
            stream = new BufferedOutputStream(fstream);
            stream.write(b);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return file;
    }

    /**
     * 删除文件和文件夹
     * 
     * @param path
     */
    public static void deleteFile(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            File[] ff = file.listFiles();
            for (int i = 0; i < ff.length; i++) {
                deleteFile(ff[i].getPath());
            }
        }
        file.delete();
    }

    /**
     * 删除指定文件夹下的所有文件不删文件夹
     * 
     * @param path
     * @return
     */
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
                flag = true;
            }
        }
        return flag;
    }
    
    /**
     * 判断文件是否是图片
     * @param inputStream
     * @return
     */
    public static boolean isImage(InputStream inputStream){
        if (null == inputStream) {  
            return false;  
        }  
        Image img = null;  
        try {  
            img = ImageIO.read(inputStream);  
            if (img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0) {  
                return false;  
            }  
            return true;  
        } catch (Exception e) {  
            return false;  
        } finally {  
            img = null;  
        } 
    }
}
