## 代码注释生成功能目录
- [提升代码幸福感的小技巧之java中的@see与@link](https://www.jianshu.com/p/24f492810321)
- [{@link}与@see的简单使用以及区别--如何写好Java代码注释](https://blog.csdn.net/qq_27093465/article/details/59121608)
- 示例
  ```
  /**
   * 测试功能目录
   * @see com.GrpcUtil
   * {@link com.GrpcUtil}
   * 推荐使用@link，因为@link可以放在任何地方
   * @Author: yaozh
   * @Description:
   */
  public enum Menu {
      /**
       * TOKEN认证测试
       * {@link org.nuhara.demos.AuthGrpcHeaderTest#oneCallTest()}
       */
      AUTH_CLIENT_TEST
  }

  ```