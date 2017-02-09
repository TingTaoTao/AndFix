package com.t.andfix;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import com.alipay.euler.andfix.patch.PatchManager;

import java.io.File;
import java.io.IOException;

/**
 * Created by jiatao on 2017/2/9.
 */

public class MainApplication  extends Application{
    private static final String TAG = "euler";
    private static final String APATCH_PATH = "/out.apatch";
    private static final String DIR = "apatch";//补丁文件夹

    //patch manager
    private PatchManager mPatchManager;

    @Override
    public void onCreate() {
        super.onCreate();
        // initialize 初始化patch管理
        mPatchManager = new PatchManager(this);
        // 初始化patch版本
        mPatchManager.init("1.0");
        Log.d(TAG, "inited.");

        // load patch 加载已经添加到PatchManager中的patch
        mPatchManager.loadPatch();

        // add patch at runtime
        try {
            String patchFileString = Environment.getExternalStorageDirectory().getAbsolutePath() + APATCH_PATH;
            mPatchManager.addPatch(patchFileString);
            Log.d(TAG, "apatch:" + patchFileString + " added.");
            // 这里我加了个方法，复制加载补丁成功后，删除sdcard的补丁，避免每次进入程序都重新加载一次
            File f = new File(this.getFilesDir(), DIR + APATCH_PATH);
            if (f.exists()) {
                boolean result = new File(patchFileString).delete();
                if (!result)
                    Log.e(TAG, patchFileString + " delete fail");
            }
        }catch (IOException e){
            Log.e(TAG, "",e);
        }

    }
}
