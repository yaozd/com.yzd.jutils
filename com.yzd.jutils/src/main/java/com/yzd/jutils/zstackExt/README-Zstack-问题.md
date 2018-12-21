### Zstack存储节点与计算节点


### -严重误操作-Zstack布置的主机一定不要修改主机名！！！

### [ZStack常见问题](https://my.oschina.net/readerror/blog/832179)
```
###Q1.目前重启不会自动恢复zstack node，用户需要做三件事： zstack-ctl start_node
zstack-ctl start_ui
进入UI界面 找到VirtualRouter点击action->start

###Q2.換IP了該怎麼辦? 到服務器下

zstack-ctl status    
就知道zstack.properties在哪個路徑
搜索zstack.properties里所有的老IP，换成新IP
修改完毕后，重启zstack 管理节点。

zstack-ctl stop_node
zstack-ctl start_node
###Q3.如何更改UI的admin密碼? zstack-cli LogInByAccount accountName=admin password=password #如果password已经更改，请用改过的密码替换password zstacl-cli UpdateAccount uuid=36c27e8ff05c4780bf6d2fa65700f22e password=NEW_PASSWORD

###Q4.手動啟動ZStack管理節點 zstack-ctl start_node

###Q5.手動啟動ZStack管理節點，延長為300秒 zstack-ctl start_node --timeout 300

###Q6.手動ZStack UI管理界面 zstack-ctl start_ui
```