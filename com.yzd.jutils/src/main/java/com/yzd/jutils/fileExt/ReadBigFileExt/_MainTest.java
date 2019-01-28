package com.yzd.jutils.fileExt.ReadBigFileExt;

import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
public class _MainTest {
    /**
     * 测试-大文件读取（PS：1亿条数）
     */
    @Test
    public void bufferedRandomAccessFileReadLine() {
        File file = new File("E:\\tmp-001\\sqlResult_2069262.csv");
        String encoding = "gbk";
        int num = 5000;
        Preconditions.checkArgument(file.exists(), file.getAbsolutePath() + ",file is not found!");
        Stopwatch stopwatch = Stopwatch.createStarted();
        long total = readLine(file, encoding, num);
        stopwatch.stop();
        log.info("记录总数：" + total);
        log.info("记录读取花费的时间(毫秒)：" + String.valueOf(stopwatch.elapsed(TimeUnit.MILLISECONDS)));
    }

    /**
     * 读取文件
     *
     * @param file
     * @param encoding
     * @param num
     * @return
     */
    private long readLine(File file, String encoding, int num) {
        long pos = 0L;
        long total = 0L;
        while (true) {
            Map<String, Object> res = FileUtilForBigFile.bufferedRandomAccessFileReadLine(file, encoding, pos, num);
            // 如果返回结果为空结束循环
            if (res.isEmpty()) {
                break;
            }
            List<String> pins = (List<String>) res.get("pins");
            if (pins.isEmpty()) {
                break;
            }
            //记录总数
            total += pins.size();
            //实际的业务逻辑处理
            doWork(pins);
            //
            if (pins.size() < num) {
                break;
            }
            pos = (Long) res.get("pos");
        }
        return total;
    }

    /**
     * 实际的业务逻辑处理
     *
     * @param pins
     */
    private void doWork(List<String> pins) {
        File file = new File("E:\\tmp-001\\tmp_yzd_2069262.csv");
        String encoding = "utf-8";
        //读取后再写入
        FileUtilForBigFile.writer(file, encoding, pins);
    }
}
