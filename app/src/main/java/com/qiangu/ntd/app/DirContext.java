package com.qiangu.ntd.app;

import android.content.Context;
import com.qiangu.ntd.base.utils.FileUtils;
import java.io.File;

/**
 * 应用文件系统上下文， 负责应用中文件目录的初始化等工作.
 *
 * @author jianhao025@gmail.com
 * @data: 2016/07/27 08:59
 * @version: V1.0
 */
public class DirContext {

    private static DirContext sInstance = null;

    private String mCacheDir;

    public enum DirEnum {

        ROOT_dir("TempusWallet"), IMAGE("ImgCache"), CACHE("cache"), VOLLEY
                ("volley"), UPLOAD_IMAGE_TEMP("ImgUploadTemp"), DOWNLOAD
                ("download"), CRASH_LOGS("crashLogs"), LUBANCACHE
                ("LubanCache"),IMAGES_FROM_WEBVIEW("ImagesFromWebView");

        private String value;

        DirEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    private DirContext() {
        initDirContext();
    }

    public static DirContext getInstance() {
        if (sInstance == null) {
            sInstance = new DirContext();
        }
        return sInstance;
    }

    private void initDirContext() {

    }

    public void initCacheDir(Context context) {
        this.mCacheDir = FileUtils.getDiskCacheDir(context, "").getAbsolutePath();
    }

    public File getRootDir() {
        File file = new File(
                android.os.Environment.getExternalStorageDirectory(),
                DirEnum.ROOT_dir.getValue());

        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }

        return file;
    }

    public File getDir(DirEnum dirEnum) {
        File file = new File(getRootDir(), dirEnum.getValue());
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }
        return file;
    }
}