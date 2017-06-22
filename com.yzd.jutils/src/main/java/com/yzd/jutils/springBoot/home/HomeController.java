package com.yzd.jutils.springBoot.home;

/**
 * Created by zd.yao on 2017/6/22.
 */
public class HomeController {
    /***
     * @RestController
    public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
     @RequestMapping(value = {"", "/index"})
     public String Index(){
     logger.debug("home/index");
     logger.info("home/index");
     return "home/index";
     }
     }
     //--------------------------------------------------
     @RequestMapping(value = {"", "/index"})
     public String index(Model model) {
     model.addAttribute("shortcutMapFormList",shortcutMapFormList);
     return "home/index";
     }
     //--------------------------------------------------
     @GetMapping("openExe/{id}")
     public void openExe(@PathVariable("id")Integer id){
     Shortcut entity=shortcutMapper.getById(id);
     String filePath=entity.getFilePath()==null?"":entity.getFilePath();
     ExeUtil.openWindowsExe(filePath);
     }
     //--------------------------------------------------
     使用forward实现API接口转发
     @RequestMapping("call")
     @ResponseBody
     public void call(String api,String v,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     Preconditions.checkArgument(StringUtils.isNotBlank(api),"api is null");
     //api不等于openapi.call
     Preconditions.checkArgument(!"openapi.call".equals(api.toLowerCase()),"api=openapi.call");
     //替换API名称中的.号为/
     api=api.replace(".","/");
     request.getRequestDispatcher("/"+api).forward(request, response);
     }
     //--------------------------------------------------
     @RequestMapping(value="/redirect",method=RequestMethod.GET)
     public String testRedirect(RedirectAttributes attr){
     attr.addAttribute("a", "a");
     attr.addFlashAttribute("b", "b");
     return "redirect:/index.action";
     }
     -=============================
     response.sendRedirect(defaultRedirectURL);
     //--------------------------------------------------
     //-json request
     public LoginCallbackVM loginCallback(@RequestBody String body) throws IOException {
     if (StringUtils.isBlank(body)) {
     return new LoginCallbackVM("FAILURE", "request body is blank");
     }
     logger.info(body);
     LoginCallbackForm form = XmlUtil.deserialize(body, LoginCallbackForm.class);
     //LoginCallbackVM vm = new LoginCallbackVM("SUCCESS", "OK");
     //LoginCallbackVM vm = new LoginCallbackVM("FAILURE", "TradeError");
     return vm;
     }
     */
}
