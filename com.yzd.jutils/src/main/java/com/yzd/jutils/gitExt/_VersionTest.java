package com.yzd.jutils.gitExt;

/**
 * @Author: yaozh
 * @Description:
 */
public class _VersionTest {
    /**
     * ```
     * 关键配置：
     * <resources>
     *     <resource>
     *         <directory>src/main/resources</directory>
     *         <filtering>true</filtering>
     *     </resource>
     * </resources>
     * ```
     * @param args
     */
    public static void main(String[] args) {
        String major_version = Version.RUNTIME_VERSION.MAJOR_VERSION;
        System.out.println(major_version);
    }
}
