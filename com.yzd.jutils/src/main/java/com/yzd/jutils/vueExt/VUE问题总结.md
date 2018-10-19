### VUE问题总结
1.VUE浏览器兼容性问题：IE浏览器不支持template标签
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