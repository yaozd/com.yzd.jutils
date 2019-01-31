#### Preconditions使用示例
```
PS:
Preconditions.checkArgument(!(expression),"errorMessage");
```
> 改造前
```
 //查询标的信息
 TbBidPlan bidPlan = productService.selectByBpId(form.getBpId());
 if (bidPlan.getBpLoginId() == currentUser.getId()) {
     return new JsonResultError("投资人不能是该标的借款人");
 }
 //投资人详情
 TbUserDetails outUser = userService.getUserDetailsInfo(currentUser.getId());
 if (outUser != null) {
     if (outUser.getUdThirdAccount() == null) {
         return new JsonResultError("您尚未开通第三方");
     }
 }
 //投资人账户
 TbAccountsFunds accountsFunds = userService.getAccountsFundsInfo(currentUser.getId());
 if (accountsFunds == null || accountsFunds.getAfBalance().compareTo(form.getInvestMoney()) < 0) {
     return new JsonResultError("账户余额不足");
 }

 //判断是否为新手标
 if (bidPlan.getBpType() == 2) {
     TbInvestInfo tbInvestInfo =InvestInfoForm.toEntity4InvestInfo(currentUser.getId());
     int count = investService.selectInvestBaseByLoginIdForPageCount(tbInvestInfo);
     Preconditions.checkArgument(!(count>0),"新手专享");
 }
 //校验
 ValidationResult resultValidation = ValidationUtil.validateEntity(form);
 if (resultValidation.isHasErrors()) {
     return new JsonResultError(resultValidation.getErrorMsg().toString());
 }
```
> 改造后
```
//查询标的信息
TbBidPlan bidPlan = productService.selectByBpId(form.getBpId());
Preconditions.checkArgument(!(bidPlan.getBpLoginId() == currentUser.getId()),"投资人不能是该标的借款人");
//投资人详情
TbUserDetails outUser = userService.getUserDetailsInfo(currentUser.getId());
Preconditions.checkArgument(!(outUser==null||outUser.getUdThirdAccount()==null),"您尚未开通第三方");
//投资人账户
TbAccountsFunds accountsFunds = userService.getAccountsFundsInfo(currentUser.getId());
Preconditions.checkArgument(!(accountsFunds==null||accountsFunds.getAfBalance().compareTo(form.getInvestMoney())<0),"账户余额不足");
//判断是否为新手标
if (bidPlan.getBpType() == 2) {
    TbInvestInfo tbInvestInfo =InvestInfoForm.toEntity4InvestInfo(currentUser.getId());
    int count = investService.selectInvestBaseByLoginIdForPageCount(tbInvestInfo);
    Preconditions.checkArgument(!(count>0),"新手专享");
}
//校验
ValidationResult resultValidation = ValidationUtil.validateEntity(form);
if (resultValidation.isHasErrors()) {
    return new JsonResultError(resultValidation.getErrorMsg().toString());
}
```