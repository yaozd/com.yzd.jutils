(function () {
    //页面权限资源公共对象
    var permission = {
        data: [],
    };
    window.permission = permission;
})();
//使用ajax同步请求的方式，保证脚本执行的顺序
$.ajax({
    url:"/script/perms-ajax",
    async:false,
    beforeSend: function (XMLHttpRequest) {
        //通过token来验证用户的身份
        XMLHttpRequest.setRequestHeader("token", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxOD.....");
    },
    success: function (data) {
        permission.data=data;
        console.log(data);
    },error:function(error){
        console.log(error);
    },complete: function(XMLHttpRequest, status) {
        //请求完成后最终执行参数
        if (XMLHttpRequest.status == 403) {
            window.location.href = "/account/login";
        }
        if(XMLHttpRequest.status == 200){
            return;
        }
        alert("[perms-ajax]:系统异常，请稍后再试");
        return;
    }
});