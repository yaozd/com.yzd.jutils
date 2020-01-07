## mysql-连接池
- 如果使用事务，事务一直不提交，就会占用数据库连接。
    - 解决方法：程序中不使用事务
```
有一个问题，就是我们要使用排他锁来进行分布式锁的lock，那么一个排他锁长时间不提交，就会占用数据库连接。
一旦类似的连接变得多了，就可能把数据库连接池撑爆。
```

## Mysql 连接数,最大并发数设置
- [Mysql 连接数,最大并发数设置](https://www.cnblogs.com/phpper/p/9570792.html)
- [如果所有的客户端都公用一个mysql 连接会有问题吗？](https://segmentfault.com/q/1010000019708056)

## 让你的DBCP连接池连接不超时
- [让你的DBCP连接池连接不超时](https://blog.csdn.net/hunger_wang/article/details/55224726)
- [DBCP连接池TestOnBorrow的坑](https://blog.csdn.net/wangyangzhizhou/article/details/52209336)
- eg:HLL--byMultidatasource动态数据源
```
    public static DruidDataSource buildDatasource(DatasourceModel dataSourceModel, ZkProperties properties) {
        DruidDataSource ds = new DruidDataSource();
        if (!dataSourceModel.validateDatasourceModel()) {
            log.error("dataSourceModel is not valid:{}", dataSourceModel);
            throw new MultiDatasourceException("Datasource config has empty value! Please check again.");
        }

        // 取设置的默认值
        ds.setDriverClassName(DRIVER_CLASS_NAME);
        ds.setUsername(dataSourceModel.getUsername());
        String url = makeDsUrl(dataSourceModel, properties);
        log.info("build datasource ! url:{}", url);
        ds.setUrl(url);
        ds.setPassword(dataSourceModel.getPwd());
        ds.setInitialSize(
                dataSourceModel.getInitialSize() != null ? Integer.valueOf(dataSourceModel.getInitialSize()) : 5);
        ds.setMaxActive(dataSourceModel.getMaxActive() != null ? Integer.valueOf(dataSourceModel.getMaxActive()) : 80);
        ds.setMinIdle(dataSourceModel.getMinIdle() != null ? Integer.valueOf(dataSourceModel.getMinIdle()) : 10);
        // 配置从连接池获取连接等待超时的时间
        ds.setMaxWait(dataSourceModel.getMaxWait() != null ? Integer.valueOf(dataSourceModel.getMaxWait()) : 10000);
        ds.setValidationQuery(
                (dataSourceModel.getValidationQuery() != null && !"".equals(dataSourceModel.getValidationQuery()))
                        ? dataSourceModel.getValidationQuery() : "SELECT 1");
        ds.setValidationQueryTimeout(1000);
        // 配置一个连接在池中最大空闲时间，单位是毫秒
        ds.setMinEvictableIdleTimeMillis(60000);
        // 设置检测空闲最大时长
        // 配置间隔多久启动一次 DestroyThread，对连接池内的连接才进行一次检测，单位是毫秒。
        // 检测时:
        // 1.如果连接空闲并且超过minIdle以外的连接，如果空闲时间超过minEvictableIdleTimeMillis设置的值则直接物理关闭。
        // 2.在minIdle以内的不处理。
        ds.setTimeBetweenEvictionRunsMillis(60000);
        // 空闲时间超过上值时长,测试该连接是否可用
        // 设置从连接池获取连接时是否检查连接有效性，true 时，如果连接空闲时间超过 minEvictableIdleTimeMillis 进行检查，否则不检查; false时，不检查
        ds.setTestWhileIdle(true);
        // 长时间空闲连接回收
        ds.setRemoveAbandoned(true);
        // 设置超过多长时间未使用的连接强制关闭,秒
        ds.setRemoveAbandonedTimeout(150);
        // 关闭abanded连接时输出错误日志，这样出现连接泄露时可以通过错误日志定位忘记关闭连接的位置
        ds.setLogAbandoned(true);
        // 在取出连接时检查connection的可用性，可保证连接的绝对可用性，如不可用则换其他connection，但是较影响性能
        ds.setTestOnBorrow(true);
        // 设置往连接池归还连接时是否检查连接有效性，true 时，每次都检查；false 时，不检查
        ds.setTestOnReturn(false);
        // 打开后，增强timeBetweenEvictionRunsMillis的周期性连接检查，minIdle内的空闲连接，每次检查强制验证连接有效性.
        // 参考：https://github.com/alibaba/druid/wiki/KeepAlive_cn
//        ds.setKeepAlive(true);
        return ds;
    }
```
