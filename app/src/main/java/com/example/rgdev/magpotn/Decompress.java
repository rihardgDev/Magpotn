package com.example.rgdev.magpotn;


import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.File;

public class Decompress {
    private String _zipFile;
    private String _location;
    private String _status = "Success!";

    public Decompress(String zipFile, String location) {
        _zipFile = zipFile;
        _location = location;

        _dirChecker("");
    }

    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        return dir.delete();
    }

    public boolean unzipFile() {

        String source = _zipFile;
        String destination = _location;

        try {
            ZipFile zipFile = new ZipFile(source);


            zipFile.extractAll(destination);
            return true;
        } catch (ZipException e) {
            e.printStackTrace();
            _status = e.toString();
            return false;
        }
    }

    public String getStatus() {
        return _status;
    }

    private void _dirChecker(String dir) {


        File f = new File(_location + dir);
        deleteDir(f);

        if (!f.isDirectory()) {
            f.mkdirs();
        }
    }

}
