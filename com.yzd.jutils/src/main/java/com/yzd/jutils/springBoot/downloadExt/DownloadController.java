package com.yzd.jutils.springBoot.downloadExt;

//World、Excel利用流下载
//http://www.cnblogs.com/jimmy-muyuan/p/5715946.html
public class DownloadController {
    /* *//**
     * 下载excel
     * @param request
     * @param response
     * @param filePath
     * @param fileName
     *//*
    public static void download(HttpServletRequest request, HttpServletResponse response, String filePath, String fileName) {
        response.setContentType("application/vnd.ms-excel");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            String recommendedName = new String(fileName.getBytes(), "iso_8859_1");//设置文件名称的编码格式
            response.setHeader("Content-Disposition", "attachment; filename=" + recommendedName + ".xls");
            bis = new BufferedInputStream(new FileInputStream(filePath));
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (bis != null)
                    bis.close();
                if (bos != null)
                    bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    *//**
     * 下载word
     * @param request
     * @param response
     * @param filePath
     * @param fileName
     *//*
    public void download(HttpServletRequest request, HttpServletResponse response, String filePath, String fileName) {
        response.setContentType("application/x-msdownload;charset=UTF-8");

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("gbk"), "ISO8859-1"));
            bis = new BufferedInputStream(new FileInputStream(filePath));
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (bis != null)
                    bis.close();
                if (bos != null)
                    bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/
}
