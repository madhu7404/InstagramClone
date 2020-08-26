package com.example.instagramclone.Utils;

import android.content.Context;
import android.os.Environment;
import android.os.storage.StorageVolume;

import java.io.File;

public class FilePaths {
    //"storage/emulated/0"
    public String ROOT_DIR = Environment.getExternalStorageDirectory().getPath();
    public String PICTURES = ROOT_DIR + "/pictures";
    public String CAMERA = ROOT_DIR + "/DCIM/camera";
}
