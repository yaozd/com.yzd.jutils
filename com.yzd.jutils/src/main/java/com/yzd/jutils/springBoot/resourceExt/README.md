```
@RequestMapping(value = "/json/{filename}", method = RequestMethod.GET)
    public String getJsonData(@PathVariable String filename) {
        String jsonPath = "mock/json/"+filename+".json";
        return readJson(jsonPath);
    }
    /**
     * 读取json格式文件
     * @param jsonPath
     * @return
     */
    private String readJson(String jsonPath) {
        String json = "";
        InputStream stream=null;
        try {
            stream = MockController.class.getClassLoader().getResourceAsStream(jsonPath);
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
```