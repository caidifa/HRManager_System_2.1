<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人简历修改</title>
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
                    <input type="text" name="realName" value="${sessionScope.user.resume.realName}">
                </div>
                <div class="am-u-sm-4"></div>
            </div>
            <div class="am-form-group">
                <label class="am-u-sm-3 am-form-label">身份证号</label>
                <div class="am-u-sm-6">
                    <input type="number" name="idCard" value="${sessionScope.user.resume.idCard}">
                </div>
                <div class="am-u-sm-3"></div>
            </div>
            <div class="am-form-group">
                <label class="am-u-sm-3 am-form-label">性别</label>
                <div class="am-u-sm-2">
                    <select data-am-selected="{btnSize: 'sm'}" name="sex">
                        <c:if test="${sessionScope.user.resume.sex eq '男'}">
                            <option value="男">男</option>
                            <option value="女">女</option>
                        </c:if>
                        <c:if test="${sessionScope.user.resume.sex eq '女'}">
                            <option value="女">女</option>
                            <option value="男">男</option>
                        </c:if>
                    </select>
                </div>
                <div class="am-u-sm-7"></div>
            </div>
            <div class="am-form-group">
                <label class="am-u-sm-3 am-form-label">出生年月</label>
                <div class="am-u-sm-5 am-form-icon">
                    <input type="date" name="birthday" value="${sessionScope.user.resume.birthday}">
                </div>
                <div class="am-u-sm-4"></div>
            </div>
            <div class="am-form-group">
                <label class="am-u-sm-3 am-form-label">住址</label>
                <div class="am-u-sm-9">
                    <input type="text" name="address" value="${sessionScope.user.resume.address}">
                </div>
            </div>
            <div class="am-form-group">
                <label class="am-u-sm-3 am-form-label">婚姻状况</label>
                <div class="am-u-sm-2">
                    <select data-am-selected="{btnSize: 'sm'}" name="married">
                        <c:if test="${sessionScope.user.resume.married eq '未婚'}">
                            <option value="未婚">未婚</option>
                            <option value="已婚">已婚</option>
                        </c:if>
                        <c:if test="${sessionScope.user.resume.married eq '已婚'}">
                            <option value="已婚">已婚</option>
                            <option value="未婚">未婚</option>
                        </c:if>
                    </select>
                </div>
                <div class="am-u-sm-7"></div>
            </div>
            <div class="am-form-group">
                <label class="am-u-sm-3 am-form-label">爱好</label>
                <div class="am-u-sm-9">
                    <input type="text" name="hobby" value="${sessionScope.user.resume.hobby}">
                </div>
            </div>
            <div class="am-form-group">
                <label class="am-u-sm-3 am-form-label">专业</label>
                <div class="am-u-sm-9">
                    <input type="text" name="major" value="${sessionScope.user.resume.major}">
                </div>
            </div>
            <div class="am-form-group">
                <label class="am-u-sm-3 am-form-label">教育程度</label>
                <div class="am-u-sm-3">
                    <select data-am-selected="{btnSize: 'sm'}" name="education">
                        <option value="${sessionScope.user.resume.education}">${sessionScope.user.resume.education}</option>
                        <option value="高中">高中</option>
                        <option value="专科">专科</option>
                        <option value="本科">本科</option>
                        <option value="研究生">研究生</option>
                    </select>
                </div>
                <div class="am-u-sm-6"></div>
            </div>
            <div class="am-form-group">
                <label class="am-u-sm-3 am-form-label">工作经验</label>
                <div class="am-u-sm-3">
                    <select data-am-selected="{btnSize: 'sm'}" name="experience">
                        <option value="${sessionScope.user.resume.experience}">${sessionScope.user.resume.experience}</option>
                        <option value="1年">1年</option>
                        <option value="2年">2年</option>
                        <option value="3年">3年</option>
                        <option value="3年以上">3年以上</option>
                        <option value="应届生">应届生</option>
                    </select>
                </div>
                <div class="am-u-sm-6"></div>
            </div>
            <div class="am-form-group">
                <label class="am-u-sm-3 am-form-label">自我介绍</label>
                <div class="am-u-sm-9">
                    <textarea class="text" rows="5" name="selfIntroduce">${sessionScope.user.resume.selfIntroduce}</textarea>
                </div>
            </div>
            <div class="am-form-group">
                <div class="am-u-sm-9 am-u-sm-push-3">
                    <a href="javascript:editResume()" class="am-btn am-btn-success">确认修改</a>
                </div>
            </div>
        </form>
    </div>
</div>
<%--内容 结束--%>

<%--js代码 ajax--%>
<script type="text/javascript">
    //保存简历
    function editResume() {
        $.ajax({
            url: '/resume/editResume.do?id=${sessionScope.user.resume.id}',
            data: $('#resumeChangeForm').serialize(),
            type: "post",
            success: function (result) {
                if (result == 'ok') {
                    $('#messageContent').html('修改成功');
                } else {
                    $('#messageContent').html(result);
                }
                $('#messageTips').modal();
            }
        });
    }
</script>
</body>
</html>
