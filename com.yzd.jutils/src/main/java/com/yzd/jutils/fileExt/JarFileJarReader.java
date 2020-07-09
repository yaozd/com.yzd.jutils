package com.yzd.jutils.fileExt;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Properties;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/**
 * java：读取jar包中配置文件的几种方式
 * https://blog.csdn.net/ywg_1994/article/details/104440114
 * java之String, inputStream与Reader转换
 */
public class JarFileJarReader {
    public static void main(String[] args) throws IOException, ConfigurationException {
        String jarPath = "E:\\ProgramData\\com.yzd.prometheus.demo\\com.yzd.prometheus.demo\\target\\com.yzd.prometheus.demo.jar";
        //"Spring-Boot-Classes" -> BOOT-INF/classes/
        getManiFest(jarPath);
        //spring boot 2.x打包后增加的路径：BOOT-INF/classes
        String content = readFromJar(jarPath, "BOOT-INF/classes/git.properties");
        System.out.println(content);
        readPropertiesFromJar(jarPath,"BOOT-INF/classes/git.properties");
        //java之String, inputStream与Reader转换
        StringReader sReader = new StringReader(content);
        //IOUtils.toString(sReader);
        //读取properties配置文件
        Properties properties=new Properties();
        properties.load(sReader);
        String property = properties.getProperty("git.commit.id.abbrev");
        System.out.println(property);
        //java之String, inputStream与Reader转换
        //1、String 转成 InputStream
        InputStream inputStrem = new ByteArrayInputStream(content.getBytes("utf-8"));
        PropertiesConfiguration propertiesConfiguration = new PropertiesConfiguration();
        propertiesConfiguration.load(inputStrem);
        //
        IOUtils.toString(inputStrem,"utf-8");

    }

    public static String readFromJar(String jarPath, String file) throws IOException {
        JarFile jarFile = null;
        try {
            jarFile = new JarFile(jarPath);
            JarEntry jarEntry = jarFile.getJarEntry(file);
            InputStream input = jarFile.getInputStream(jarEntry);
            return IOUtils.toString(input, "UTF-8");
        } catch (IOException e) {
            throw e;
        } finally {
            IOUtils.closeQuietly(jarFile);
        }
    }

    /**
     * read Properties
     * @param jarPath
     * @param file
     * @throws IOException
     * @throws ConfigurationException
     */
    public static void readPropertiesFromJar(String jarPath, String file) throws IOException, ConfigurationException {
        JarFile jarFile = null;
        try {
            jarFile = new JarFile(jarPath);
            JarEntry jarEntry = jarFile.getJarEntry(file);
            InputStream input = jarFile.getInputStream(jarEntry);
            PropertiesConfiguration propertiesConfiguration = new PropertiesConfiguration();
            propertiesConfiguration.load(input);
            String value = propertiesConfiguration.getString("git.commit.id.abbrev");
            System.out.println(value);
        } catch (IOException e) {
            throw e;
        } catch (ConfigurationException e) {
            throw e;
        } finally {
            IOUtils.closeQuietly(jarFile);
        }
    }

    public static void getManiFest(String jarPath) throws IOException {
        JarFile jarFile = null;
        try {
            jarFile = new JarFile(jarPath);
            Manifest manifest = jarFile.getManifest();
            if (manifest != null) {
                //获取Class-Path
                String classPaths = (String) manifest.getMainAttributes().get(new Attributes.Name("Spring-Boot-Classes"));
                if (classPaths != null && !classPaths.isEmpty()) {
                    String[] classPathArray = classPaths.split(" ");

                }
                //获取JDK版本
                String jdkVersion = (String) manifest.getMainAttributes().get(new Attributes.Name("Build-Jdk"));

                //还可以获取其它内容，比如Main-Class等等
            }

        } catch (IOException e) {
            throw e;
        } finally {
            IOUtils.closeQuietly(jarFile);
        }
    }
}
