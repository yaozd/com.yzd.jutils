# traefik
- [官网](https://docs.traefik.io/) -https://docs.traefik.io/
- [install-traefik](https://www.qikqiak.com/traefik-book/getting-started/install-traefik/#_1)
- [https://github.com/containous/traefik/releases](https://github.com/containous/traefik/releases)

## Benchmarks
- [https://docs.traefik.cn/benchmarks](https://docs.traefik.cn/benchmarks)
- [https://docs.traefik.io/v1.4/benchmarks/](https://docs.traefik.io/v1.4/benchmarks/)-官网测试

## Routers
- [Routers](https://docs.traefik.io/routing/routers/)

## 全局配置文件
- [https://docs.traefik.cn/toml#configuration-backends](https://docs.traefik.cn/toml#configuration-backends)
- [概念](https://docs.traefik.cn/basics)

## 示例
- [Examples](https://docs.traefik.cn/user-guide/examples)
- traefik-grpc代理测试
```
# traefik.yaml

entryPoints:
  web:
    address: ":10000"

providers:
  file:
    directory: /home/traefik/config

api: {}
-------------------------------------
# backend.yaml

http:
  routers:
    yaozhendong:
      service: yaozhendong-service-grpc
      rule: PathPrefix(`/grpc`)

  services:
    yaozhendong-service-grpc:
      loadBalancer:
        servers:
        - url: h2c://172.20.60.45:30009

-------------------------------------
# 启动
./traefik --configfile traefik.yaml

```