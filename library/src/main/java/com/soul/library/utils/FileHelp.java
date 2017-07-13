package com.soul.library.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.text.format.Formatter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;

public class FileHelp {

    public static boolean createFolderIfNotExist(String strFolder) {

        File basefile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/rearview");
        if (!basefile.exists()) {
            boolean result = basefile.mkdir();
        }
        File file = new File(strFolder);

        if (!file.exists()) {
            return file.mkdir();
        }
        return true;
    }

    /**
     * 获取生成腾实电子所需要的IMEI的字符串
     *
     * @return
     */
    public static String getTsdzImei() {

        if (FileHelp.isSdCardMounted()) {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/EasyConnected/License.ini";
            try {
                File file = new File(path);
                String content = FileHelp.readDataFromFile(file);
                if (content.contains("\n")) {
                    content.replace("\n", "").trim();
                }
                if (content.contains("-")) {
                    String replace = content.replace("-", "");
                    return replace;
                } else {
                    return content;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
        return "";
    }


    public static boolean isSdCardMounted() {
        String storageState = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(storageState);
    }

    public static boolean copyFile(String fromFile, String toFile) {
        try {
            InputStream is = new FileInputStream(fromFile);
            OutputStream os = new FileOutputStream(toFile);
            byte bt[] = new byte[1024];
            int c;
            while ((c = is.read(bt)) > 0) {
                os.write(bt, 0, c);
            }
            is.close();
            os.close();

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * 从指定的文件中读取字符串
     *
     * @param file
     * @return
     */
    public static String readDataFromFile(File file) {

        BufferedReader br = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            InputStreamReader inputreader = new InputStreamReader(fis);
            br = new BufferedReader(inputreader);
            String line;
            StringBuffer content = new StringBuffer();
            //分行读取
            while ((line = br.readLine()) != null) {
                content.append(line + "\n");
            }

            return content.toString();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (br != null) {
                    br.close();
                }

                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "";
    }

    // 创建目录
    public static void createDirIfNotExist(String path) {
        if (StringUtils.isNotEmpty(path)) {
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
    }

    /**
     * 设置图片透明度
     */
    public static Bitmap getTransparentBitmap(Bitmap sourceImg, int number) {
        int[] argb = new int[sourceImg.getWidth() * sourceImg.getHeight()];
        sourceImg.getPixels(argb, 0, sourceImg.getWidth(), 0, 0, sourceImg
                .getWidth(), sourceImg.getHeight());// 获得图片的ARGB值
        number = number * 255 / 100;
        for (int i = 0; i < argb.length; i++) {
            argb[i] = (number << 24) | (argb[i] & 0x00FFFFFF);
        }
        sourceImg = Bitmap.createBitmap(argb, sourceImg.getWidth(), sourceImg
                .getHeight(), Bitmap.Config.ARGB_8888);
        return sourceImg;
    }

    /**
     * 获取指定文件夹、文件大小
     */
    public static long getFileSize(File f) {
        long size = 0L;
        File[] list = f.listFiles();
        if (list == null || list.length == 0) {
            return 0L;
        }
        for (File file : list) {
            if (file.isDirectory()) {
                size = size + getFileSize(file);
            } else {
                // 排除播放文件
                if (!file.getName().equalsIgnoreCase("cache.db") && !file.getName().equalsIgnoreCase("private_data.db")) {
                    size = size + file.length();
                }
            }
        }

        return size;
    }

    public static void DeleteAPKFile(File file) {
        if (file.isFile()) {
            file.delete();
            return;
        }
        if (file.isDirectory()) {
            File[] childFile = file.listFiles();
            if (childFile == null || childFile.length == 0) {
                return;
            }
            for (File f : childFile) {
                if (f.getName().contains(".apk") || f.getName().contains(".patch")) {
                    DeleteAPKFile(f);
                }

            }
        }
    }


    /**
     * create parent dir by file path
     *
     * @param filePath file path
     * @return true mean create parent dir succeed
     */
    public static final boolean createFileParentDir(String filePath) {
        File file = new File(filePath);
        if (file != null) {
            if (file.exists()) {
                return true;// parent dir exist
            } else {
                File parentFile = file.getParentFile();
                if (parentFile != null) {
                    if (parentFile.exists()) {
                        return true;// parent dir exist
                    } else {
                        return parentFile.mkdirs();// create parent dir
                    }
                }
            }
        }
        return false;
    }

    /**
     * get file suffix by file path
     *
     * @param filePath file path
     * @return file suffix,return null means failed
     */
    public static String getFileSuffix(String filePath) {
        if (!TextUtils.isEmpty(filePath)) {
            int start = filePath.lastIndexOf(".");
            if (start != -1) {
                return filePath.substring(start + 1);
            }
        }
        return null;
    }

    /**
     * whether the path is file path
     *
     * @param path file path
     * @return true means the path is file path
     */
    public static boolean isFilePath(String path) {
        if (TextUtils.isEmpty(path)) {
            return false;
        }
        return path.startsWith(File.separator);
    }

    /**
     * whether the file path can write
     *
     * @param path file path
     * @return true means can write to
     */
    public static final boolean canWrite(String path) {
        // if sdcard,needs the permission:  android.permission.WRITE_EXTERNAL_STORAGE
        if (isSDCardPath(path)) {
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                // TODO write bytes for test
                return true;
            }
        } else {
            // TODO write bytes for test
            return true;
        }
        return false;
    }

    /**
     * whether the file path is sdcard path
     *
     * @param path file path
     * @return true means the file path is sdcard path
     */
    public static final boolean isSDCardPath(String path) {
        if (TextUtils.isEmpty(path)) {
            return false;
        }
        String sdRootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        return path.startsWith(sdRootPath);
    }

    /**
     * get available space(free space can use) by fileDirPath
     *
     * @param fileDirPath file dir path
     * @return available space,-1 means failed
     */
    public static long getAvailableSpace(String fileDirPath) {
        try {
            File file = new File(fileDirPath);
            if (!file.exists()) {
                file.mkdirs();// create to make sure it is not error below
            }
            final StatFs stats = new StatFs(fileDirPath);
            long result = (long) stats.getBlockSize() * (long) stats.getAvailableBlocks();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * copy a file to another one
     *
     * @param fromFile
     * @param toFile
     * @param forceOverwrite
     * @return
     * @throws IOException
     */
    public static boolean copyFile(File fromFile, File toFile, boolean forceOverwrite) {

        if (fromFile == null || !fromFile.exists() || toFile == null) {
            return false;
        }

        if (toFile.exists() && !forceOverwrite) {
            return false;
        }

        try {
            InputStream fosFrom = new FileInputStream(fromFile);
            OutputStream fosTo = new FileOutputStream(toFile);
            byte bytes[] = new byte[1024];

            int writeSize = 0;
            int startIndex = 0;
            int readCount = 0;

            while ((readCount = fosFrom.read(bytes)) != -1) {
                fosTo.write(bytes, startIndex, readCount);
                writeSize += (readCount - startIndex);
            }
            fosFrom.close();
            fosTo.close();

            return writeSize > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * whether the file exist
     *
     * @param filePath the check file path
     * @return true means exist
     */
    public static boolean isFileExist(String filePath) {

        if (!isFilePath(filePath)) {
            return false;
        }

        File file = new File(filePath);
        return file != null && file.exists() && file.isFile();

    }

    /**
     * 从SD卡中删除文件
     */
    public static void deleteFile(File file) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            if (file.exists()) {
                if (file.isFile()) {
                    file.delete();
                }
                // 如果它是一个目录
                else if (file.isDirectory()) {
                    // 声明目录下所有的文件 files[];
                    File files[] = file.listFiles();
                    for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
                        deleteFile(files[i]); // 把每个文件 用这个方法进行迭代
                    }
                }
                file.delete();
            }
        }
    }

    /**
     * @param msg      写入的消息
     * @param parent   父目录
     * @param filename 文件名
     * @param isAppend 是否是追加
     * @return 是否保存成功
     */
    public static boolean saveFile(String msg, String parent, String filename, boolean isAppend) {
        if (TextUtils.isEmpty(msg)) {
            return false;
        }
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File dir = new File(parent);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            try {
                FileWriter fw = new FileWriter(dir + File.separator + filename, isAppend);
                PrintWriter pw = new PrintWriter(fw, true);
                pw.println(msg);
                return true;
            } catch (IOException e) {
                //                Log.e(TAG, "saveFile: 发生异常");
                e.printStackTrace();
                return false;
            }
        } else {
            //            Log.e(TAG, "saveFile: 没有内存卡");
            return false;
        }
    }

    /**
     * 获得sd卡剩余容量，即可用大小
     *
     * @return
     */
    public static String getSDAvailableSizeFormat(Context context) {
        return Formatter.formatFileSize(context, getSDAvailableSize(context));
    }

    /**
     * 获得sd卡剩余容量，即可用大小
     *
     * @param context
     * @return
     */
    public static long getSDAvailableSize(Context context) {
        if (isSdCardMounted()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long availableBlocks = stat.getAvailableBlocks();

            return blockSize * availableBlocks;
        }
        return 0;
    }

    public static long getSDAvailableSize() {
        if (isSdCardMounted()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long availableBlocks = stat.getAvailableBlocks();

            return blockSize * availableBlocks;
        }
        return 0;
    }


    // 转换文件大小
    public static String formatSize(long size) {
        DecimalFormat df = new DecimalFormat("#0.00");
        String fileSizeString = "";
        if (size < 1024) {
            fileSizeString = df.format((double) size) + "B";
        } else if (size < 1048576) {
            fileSizeString = df.format((double) size / 1024) + "KB";
        } else if (size < 1073741824) {
            fileSizeString = df.format((double) size / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) size / 1073741824) + "GB";
        }
        return fileSizeString;
    }
}
