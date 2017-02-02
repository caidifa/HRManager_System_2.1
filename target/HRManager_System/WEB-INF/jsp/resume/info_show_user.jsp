<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人简历</title>
    <%String path = request.getContextPath();%>
</head>
<body>
<%--左上角标题显示--%>
<div class="am-cf am-padding am-padding-bottom-0">
    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">个人简历</strong> /
        <small>Personal Resume</small>
    </div>
</div>
<hr/>

<%--内容 开始--%>
<div class="am-g">
    <div class="am-u-sm-12 am-u-md-4 am-u-md-push-8"></div>
    <div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4">
        <%--简历内容--%>
        <form id="resumeChangeForm" method="post" class="am-form am-form-horizontal">
            <div class="am-form-group">
                <label class="am-u-sm-3 am-form-label">电子邮件</label>
                <div class="am-u-sm-5">
                    <input type="email" name="email" value="${sessionScope.user.email}" disabled>
                </div>
                <div class="am-u-sm-4"></div>
            </div>

            <div class="am-form-group">
                <label class="am-u-sm-3 am-form-label">手机号</label>
                <div class="am-u-sm-5">
                    <input type="number" name="phone" value="${sessionScope.user.phone}" disabled>
                </div>
                <div class="am-u-sm-4"></div>
            </div>
            <div class="am-form-group">
                <label class="am-u-sm-3 am-form-label">真实姓名</label>
                <div class="am-u-sm-5">
                    <input type="text" name="realName" value="${sessionScope.user.resume.realName}" disabled>
                </div>
                <div class="am-u-sm-4"></div>
            </div>
            <div class="am-form-group">
                <label class="am-u-sm-3 am-form-label">身份证号</label>
                <div class="am-u-sm-6">
                    <input type="number" name="idCard" value="${sessionScope.user.resume.idCard}" disabled>
                </div>
                <div class="am-u-sm-3"></div>
            </div>
            <div class="am-form-group">
                <label class="am-u-sm-3 am-form-label">性别</label>
                <div class="am-u-sm-2">
                    <input type="text" name="sex" value="${sessionScope.user.resume.sex}" disabled>
                </div>
                <div class="am-u-sm-7"></div>
            </div>
            <div class="am-form-group">
                <label class="am-u-sm-3 am-form-label">出生年月</label>
                <div class="am-u-sm-5 am-form-icon">
                    <input type="date" name="birthday" value="${sessionScope.user.resume.birthday}" disabled>
                </div>
                <div class="am-u-sm-4"></div>
            </div>
            <div class="am-form-group">
                <label class="am-u-sm-3 am-form-label">住址</label>
                <div class="am-u-sm-9">
                    <input type="text" name="address" value="${sessionScope.user.resume.address}" disabled>
                </div>
            </div>
            <div class="am-form-group">
                <label class="am-u-sm-3 am-form-label">婚姻状况</label>
                <div class="am-u-sm-2">
                    <input type="text" name="married" value="${sessionScope.user.resume.married}" disabled>
                </div>
                <div class="am-u-sm-7"></div>
            </div>
            <div class="am-form-group">
                <label class="am-u-sm-3 am-form-label">爱好</label>
                <div class="am-u-sm-9">
                    <input type="text" name="hobby" value="${sessionScope.user.resume.hobby}" disabled>
                </div>
            </div>
            <div class="am-form-group">
                <label class="am-u-sm-3 am-form-label">专业</label>
                <div class="am-u-sm-9">
                    <input type="text" name="major" value="${sessionScope.user.resume.major}" disabled>
                </div>
            </div>
            <div class="am-form-group">
                <label class="am-u-sm-3 am-form-label">教育程度</label>
                <div class="am-u-sm-3">
                    <input type="text" name="education" value="${sessionScope.user.resume.education}" disabled>
                </div>
                <div class="am-u-sm-6"></div>
            </div>
            <div class="am-form-group">
                <label class="am-u-sm-3 am-form-label">工作经验</label>
                <div class="am-u-sm-3">
                    <input type="text" name="experience" value="${sessionScope.user.resume.experience}" disabled>
                </div>
                <div class="am-u-sm-6"></div>
            </div>
            <div class="am-form-group">
                <label class="am-u-sm-3 am-form-label">自我介绍</label>
                <div class="am-u-sm-9">
                    <textarea class="text" rows="5" name="selfIntroduce"
                              disabled>${sessionScope.user.resume.selfIntroduce}</textarea>
                </div>
            </div>
        </form>
    </div>
</div>
<%--内容 结束--%>

</body>
</html>
