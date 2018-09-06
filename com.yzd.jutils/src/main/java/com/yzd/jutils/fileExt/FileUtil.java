package com.yzd.jutils.fileExt;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zd.yao on 2017/6/13.
 */
public class FileUtil {

    public static String read(String path, String encoding) {
        InputStream inputStream = FileUtil.class.getClass().getResourceAsStream(path);
        try {
            return IOUtils.toString(inputStream, encoding);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
        return null;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        String insurerXmlStr = FileUtil.read("/project.properties", "utf-8");
    }
}
