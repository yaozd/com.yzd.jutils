package com.yzd.jutils.fileExt;

import com.google.common.collect.Lists;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.List;

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
    public static List<String> readLine(File file, String encoding, int index, int num) {
        List<String> pins = Lists.newArrayList();
        LineNumberReader reader = null;
        try {
            reader = new LineNumberReader(new InputStreamReader(new FileInputStream(file), encoding));
            int lines = 0;
            while (true) {
                String pin = reader.readLine();
                if (StringUtils.isBlank(pin)) {
                    break;
                }
                if (lines >= index) {
                    if (StringUtils.isNotBlank(pin)) {
                        pins.add(pin);
                    }
                }
                if (num == pins.size()) {
                    break;
                }
                lines++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(reader);
        }
        return pins;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        String insurerXmlStr = FileUtil.read("/project.properties", "utf-8");
    }
}
