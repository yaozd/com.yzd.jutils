## git
- [Spring Boot项目获取Git版本信息](https://blog.csdn.net/t5721654/article/details/89149151)
```
https://github.com/yaozd/com.yzd.prometheus.demo
@Component
public class GitInfo {
    @Autowired(required = false)
    private GitProperties git;
    @Autowired
    MetricsService metricsService;
    public String getShortCommitId(){
        if(git==null){
            return "unknown";
        }
        return git.getShortCommitId();
    }
    @PostConstruct
    void init(){
        //添加git代码的提交id到prometheus,这样就可以知道当前运行的代码的版本
        metricsService.gitGauge(Tags.of("short_commit_id",getShortCommitId()));
    }
}
```