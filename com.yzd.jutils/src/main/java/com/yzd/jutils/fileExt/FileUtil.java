package com.yzd.jutils.fileExt;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.List;

/**
 * Created by zd.yao on 2017/6/13.
 */
@Slf4j
public class FileUtil {

    /**
     * 读取当前应用的根目录文件
     * @param path
     * @param encoding
     * @return
     */
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
     * 读取根目录文件
     * 读取json格式文件
     * @param jsonPath
     * @return
     */
    public static String readJson(String jsonPath) {
        String json = "";
        InputStream stream=null;
        try {
            stream = FileUtil.class.getClassLoader().getResourceAsStream(jsonPath);
            if(stream==null){
                return "FILE NOT FOUND!";
            }
            json = IOUtils.toString(stream,"utf-8");
        } catch (IOException e) {
            log.error("Exception:", e);
        } finally {
            IOUtils.closeQuietly(stream);
        }
        return json;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        String insurerXmlStr = FileUtil.read("/project.properties", "utf-8");
    }
}
