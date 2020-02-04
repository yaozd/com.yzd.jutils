package com.yzd.jutils.commonsLangExt;

import com.google.common.collect.Lists;
import com.yzd.jutils.fileExt.FileUtil;
import com.yzd.jutils.httpExt.HttpUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

/***
 * 女0181024-1630
 */
public class TestFileUtilsDemo {
    private static final String ENCODING = "GBK";
    private static final int NUM = 50000;
    //private static File file = new File(ClassLoader.getSystemResource("").getPath() + File.separator + "test.txt");
    private static File file = new File("E:\\tmp-001\\sqlResult_2069262.csv");

    /***
     * 读取sqlResult_2069262.csv里的数据，然后判断认证文件是否存在有效
     * @throws IOException
     */
    @Test
    public void readLine() throws IOException {
        int index = 0;
        List<String> existFileList = Lists.newArrayList();
        //创建临时文件目录
        File exportFile = File.createTempFile("ExistFileExport", ".csv");
        int count = 0;
        while (true) {
            List<String> pins = FileUtil.readLine(file, ENCODING, index, NUM);
            for (String item : pins) {
                count++;
                String[] arr = StringUtils.split(item, ",");
                if (arr.length != 10) {
                    continue;
                }
                String url = arr[3];
                //
                url = StringUtils.remove(url, "\"");
                //以http字符开始
                if (!StringUtils.startsWith(url, "http")) {
                    continue;
                }
                boolean isExistFile = HttpUtil.sendGetForCheckFileIsExist(url);
                if (isExistFile) {
                    existFileList.add(item);
                }
            }
            if (CollectionUtils.isNotEmpty(pins)) {
                if (pins.size() < NUM) {
                    break;
                }
            } else {
                break;
            }
            index += NUM;
        }
        //按行写入文件
        FileUtils.writeLines(exportFile, existFileList, true);
    }
}
