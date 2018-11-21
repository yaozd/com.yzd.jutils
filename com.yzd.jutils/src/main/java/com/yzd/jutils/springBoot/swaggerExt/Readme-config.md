
### SwaggerConfig

```
@Configuration
@ConditionalOnProperty(prefix = "swagger",value = {"enable"},havingValue = "true")
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

    @Value("${swagger.enable}")
    private boolean swaggerShow;

    /***
     * http://localhost:8890/swagger-ui.html
     * @return
     */
    @Bean
    public Docket buildDocket() {
        //rest full 的API要与controller的命令空间分开。分开以后才不会影响到swagger文档的正常显示
        return new Docket(DocumentationType.SWAGGER_2)
                //是否开启
                .enable(swaggerShow)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yzd.web.api.controllerApi"))
                .paths(PathSelectors.any())
                .build().globalOperationParameters(getGlobalParamInHeader());
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot 中使用 Swagger2构建 RESTfulAPI 文档")
                .description("更多 Spring Boot 相关文章 http://localhost:8870")
                .termsOfServiceUrl("http://localhost:8870")
                .version("V1.0")
                .build();
    }

    /***
     * 全局head变量
     * @return
     */
    private List<Parameter> getGlobalParamInHeader(){
        //在header 中添加一个默认值
        ParameterBuilder tokenParam = new ParameterBuilder();
        tokenParam.name("Authorization")
                .defaultValue("")
                .description("Token令牌")
                .modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        List<Parameter> pars = new ArrayList<Parameter>();
        pars.add(tokenParam.build());
        return pars;
    }


}
```

### WebMvcConfig

```
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * SpringMvc_@RequestMapping设置Router Url大小写不敏感
     * http://www.cnblogs.com/gossip/p/5441358.html
     * 如何取消 /index.*映射
     * http://www.oschina.net/question/190714_116949
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        AntPathMatcher matcher = new AntPathMatcher();
        matcher.setCaseSensitive(false);
        configurer.setPathMatcher(matcher);
        configurer.setUseSuffixPatternMatch(false);
    }

    @Value("${swagger.enable}")
    private boolean swaggerShow;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //在生产环境中禁打开/swagger-ui.html
        if(!swaggerShow){
            registry.addResourceHandler("/swagger-ui.html").addResourceLocations(
                    "classpath:/noswagger");
        }
    }

}
```
### application.properties

```
#--------------------------------------#
#是否显示swagger-ui.html ,正常情况生产环境都会禁用swagger-ui.html
swagger.enable=true
#--------------------------------------#
```
