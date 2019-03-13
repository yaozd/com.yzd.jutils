### VUE问题总结
1. VUE浏览器兼容性问题：IE浏览器不支持template标签
```
VUE浏览器兼容性问题IE与Chrome
IE浏览器不支持template
 <template v-for="item in loaners"> 要调整为<tr role="row" class="odd"  v-for="item in loaners">
  <template v-for="item in loaners">
    <tr role="row" class="odd">
        <td>{{item.nickName}}</td>
        <td>{{item.trueName}}</td>
        <td>{{item.phone}}</td>
        <td>{{item.registerTime}}</td>
        <!--<td>5</td>-->
        <!--<td>10000.00</td>-->
        <td>
            <a href="javascript:void(0);" class="btn btn-default btn-xs" role="button" @click.prevent="addPlanBtn(item.id)">添加借款</a>
        </td>
    </tr>
</template>
--调整后--
<tr role="row" class="odd"  v-for="item in loaners">
    <td>{{item.nickName}}</td>
    <td>{{item.trueName}}</td>
    <td>{{item.phone}}</td>
    <td>{{item.registerTime}}</td>
    <!--<td>5</td>-->
    <!--<td>10000.00</td>-->
    <td>
        <a href="javascript:void(0);" class="btn btn-default btn-xs" role="button" @click.prevent="addPlanBtn(item.id)">添加借款</a>
    </td>
</tr>
```
2. [v-if与v-show的区别](https://segmentfault.com/q/1010000017751489/a-1020000017751636)
```
v-if:元素是不存在的。
v-show：元素是真实存在单不显示。
--------
使用的是v-if的时候，aaa这个div是不存在的，这就导致bbb这个div就变成了第一个，最终first-child对bbb这个div生效
如果是用v-show，则aaa这个div只是隐藏，实际是存在的，所以first-child是对aaa这个div生效，所以看不到
```