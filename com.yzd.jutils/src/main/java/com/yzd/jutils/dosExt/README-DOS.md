
###　快速打开当前文件夹的dos命令
> 下面的文件保存为do.reg
```
Windows Registry Editor Version 5.00

[HKEY_CLASSES_ROOT\Directory\shell\CmdPrompt]
@="进入DOS命令行"

[HKEY_CLASSES_ROOT\Directory\shell\CmdPrompt\Command]
@="cmd.exe /k cd %l"
```
- 使用方法：右击，选择“CmdPrompt”或“进入DOS命令行”