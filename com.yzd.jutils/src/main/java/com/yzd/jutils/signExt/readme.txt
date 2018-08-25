restfull api 接口签名方案的思考
如何同时支付原生调用与H5调用
==
接口签名要包括：?sign=xxx&&timestamp=xxxx
==
如何在非Controller中获取Request和Response:
但当上传文件时则不在是ServletRequestAttributes对象，request对象实际为MultipartHttpServletRequest
所以对上传文件接的的签名做特殊处理就可以了。
ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
HttpServletRequest request = attributes.getRequest();
String sign = request.getParameter("sign");
String timestamp = request.getParameter("timestamp");
==
不要试图用一个签名的AOP去解决所有类型的controller，
如何controller的参数是list或是file文件类型。可以写多个AOP
==
(AOP实现)
对于需要签名验证方法通过AOP进行拦截
1.因为签名主要是针对原生的调用。
对于特殊的需要，如登录,则直接写两个方法原生与H5分开。H5可能需要验证码而原生可能不需要验证码
eg:密码找回，用户登录，
2.同一个接口支持原生与H5可以通过header里面的标识做区分，是否需要签名验证
AOP实现参考()：
com.yooeee.front.aspect.SignValidateAspect（此类有比较详细的代码）
    @Pointcut("@annotation(com.yooeee.front.annotation.SignValidate)")
	public void doPerform() {}
    /**
     * ASCII 码从小到大排序（字典序）
     * @param paramMap
     * @return
     */
    public static Map<String,Object> sortByASCII(Map<String,Object> paramMap){
        if (paramMap == null || paramMap.isEmpty()) {
                return new HashMap();
         }
        SortedMap<String,Object> sort=new TreeMap<String,Object>(paramMap);
        return sort;
    }
	/**
     * 使用 Map按key进行排序
     * @param map
     * @return
     */
    public static Map<String, Object> sortMapByKey(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, Object> sortMap = new TreeMap<String, Object>(new MapKeyComparator());
        sortMap.putAll(map);
        return sortMap;
    }
参考：
app内嵌入了一个h5页面，因为有些接口需要登录状态才能调用，那么如何才能把app的登录状态传给h5页面
https://q.cnblogs.com/q/DetailPage/102734/
@吴瑞祥: 已经实现了 App调用h5的时候把cookie存在请求头里，h5在调
==
Spring Boot中使用AOP统一处理Web请求日志
https://www.cnblogs.com/winner-0715/p/6822886.html
==
如何在非Controller中获取Request和Response:SpringMVC之RequestContextHolder分析
https://blog.csdn.net/wenxingchen/article/details/78667947
==
Spring MVC的RequestContextHolder使用误区
https://www.cnblogs.com/softidea/p/6125087.html
==
使用HttpServletRequestWrapper重写Request请求参数
https://www.cnblogs.com/cach/p/8052108.html

