### SourceTree
- [windows 下 sourceTree 乱码问题](https://blog.csdn.net/u011526234/article/details/72667620)

已测试，可通过-byArvin
```
发现不好使, 又查了一下, 有的说在git bash 中执行以下命令, 发现问题解决
git config --global core.quotepath off
git config --global --unset i18n.logoutputencoding
git config --global --unset i18n.commitencoding
```
- [sourcetree跳过注册的方法](https://www.cnblogs.com/lucio110/p/8192792.html)

已测试，可通过-byArvin
```
%LocalAppData%\Atlassian\SourceTree\ 接下来你应该在当前文件夹下创建一个json文件，文件名为accounts.json
[
  {
    "$id": "1",
    "$type": "SourceTree.Api.Host.Identity.Model.IdentityAccount, SourceTree.Api.Host.Identity",
    "Authenticate": true,
    "HostInstance": {
      "$id": "2",
      "$type": "SourceTree.Host.Atlassianaccount.AtlassianAccountInstance, SourceTree.Host.AtlassianAccount",
      "Host": {
        "$id": "3",
        "$type": "SourceTree.Host.Atlassianaccount.AtlassianAccountHost, SourceTree.Host.AtlassianAccount",
        "Id": "atlassian account"
      },
      "BaseUrl": "https://id.atlassian.com/"
    },
    "Credentials": {
      "$id": "4",
      "$type": "SourceTree.Model.BasicAuthCredentials, SourceTree.Api.Account",
      "Username": "",
      "Email": null
    },
    "IsDefault": false
  }
]
```