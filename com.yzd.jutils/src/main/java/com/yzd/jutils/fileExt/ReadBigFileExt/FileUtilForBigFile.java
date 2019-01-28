package com.yzd.jutils.fileExt.ReadBigFileExt;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;

@Slf4j
public class FileUtilForBigFile {
    private FileUtilForBigFile() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 通过BufferedRandomAccessFile读取文件,推荐
     *
     * @param file     源文件
     * @param encoding 文件编码
     * @param pos      偏移量
     * @param num      读取量
     * @return pins文件内容，pos当前偏移量
     */
    public static Map<String, Object> bufferedRandomAccessFileReadLine(File file, String encoding, long pos, int num) {
        Map<String, Object> res = Maps.newHashMap();
        List<String> pins = Lists.newArrayList();
        res.put("pins", pins);
        BufferedRandomAccessFile reader = null;
        try {
            reader = new BufferedRandomAccessFile(file, "r");
            reader.seek(pos);
            for (int i = 0; i < num; i++) {
                String pin = reader.readLine();
                if (StringUtils.isBlank(pin)) {
                    break;
                }
                pins.add(new String(pin.getBytes("8859_1"), encoding));
            }
            res.put("pos", reader.getFilePointer());
        } catch (Exception e) {
            log.error("Exception:", e);
        } finally {
            IOUtils.closeQuietly(reader);
        }
        return res;
    }

    /**
     * 写入文件
     * 在文件的尾部做追加
     * @param file
     * @param encoding
     * @param pins
     */
    public static void writer(File file,String encoding,List<String>pins) {
        OutputStreamWriter out = null;
        try {
            out = new OutputStreamWriter(new FileOutputStream(file, true), encoding);
            for(String item:pins){
                out.write(item+ "\n");
            }
        } catch (Exception e) {
            log.error("Exception:", e);
        } finally {
            IOUtils.closeQuietly(out);
        }
    }
}
