package com.yzd.jutils.Excel;

/**
 * Created by zd.yao on 2017/6/20.
 */
public class ExcelController {
    /*@RequestMapping("/download")
    public void download(HttpServletResponse response) throws Exception {
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=user.xls");
        Map<String,String> map1 = new LinkedHashMap<>();
        map1.put("a","姓名");
        map1.put("b","年龄");
        map1.put("c","性别");
        map1.put("d","出生日期");
        Collection<Object> dataset=new ArrayList<Object>();
        for(int i=1;i<10000;i++)
            dataset.add(new Model("王五", "34", "男",new Date()));
        ExcelUtil.exportExcel(map1, dataset, response.getOutputStream());

    }
    @RequestMapping("/download2")
    public void download2(HttpServletResponse response) throws Exception {
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=user.xls");
        Map<String,String> map1 = new LinkedHashMap<>();
        map1.put("id","编号");
        map1.put("name","姓名");
        map1.put("age","年龄");
        Collection<Object> dataset=new ArrayList<Object>();
        for(Integer i=1;i<10000;i++){
            dataset.add(new User(i.longValue(),"a",1));
        }
        ExcelUtil.exportExcel(map1, dataset, response.getOutputStream());

    }*/
}
