

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="<%=request.getContextPath()%>/assets/css/accountDetail.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/assets/css/changePass.css" rel="stylesheet">
<div class="user-container">
    <div class="menu">
        <ul>
            <li><div><img class="menu-img" src="<%=request.getContextPath()%>/${initParam.imgPath}${userLogin.getAvatar()}"/></div>
                <div style="margin-left: 24px;"><h3>${userLogin.getUserName()}</h3>
                    <a href=""><i class="fas fa-pen"></i>Sửa hồ sơ</a>
                </div>
            <li class="spacer"></li>

            <li class="menu-li"><a href="<%=request.getContextPath()%>/myaccount/accountDetail.jsp" class="menu-item"><i class="far fa-user-circle"></i> Tài khoản của tôi</a></li>
            <li class="menu-li"><a href="<%=request.getContextPath()%>/myaccount/accountDetail.jsp" class="menu-item depend"> Hồ sơ</a></li>
            <li class="menu-li"><a href="<%=request.getContextPath()%>/myaccount/ChangePassword.jsp" class="menu-item depend"> Đổi mật khẩu</a></li>
            <li class="menu-li"><a href="<%=request.getContextPath()%>/myaccount/checkAccount.jsp" class="menu-item active"><i class="fas fa-user-check"></i> Xác minh thuộc khu cách ly</a></li>
            <li class="menu-li"><a href="<%=request.getContextPath()%>/Don/view-list" class="menu-item"><i class="far fa-check-circle"></i> Đơn của tôi</a></li>
        </ul>
    </div>
    <div class="menu-detail">
        <h3 class="heading">Đổi mật khẩu</h3>
        <p class="desc">Để bảo mật tài khoản, vui lòng không chia sẻ mật khẩu cho người khác</p>
        <div class="spacer"></div>
        <div class="menu-list">
            <form action="ChangePassword" method="POST" id="form-change-pass" class="form-change-pass">
                <div class="form-group">
                    <label for="current_pass" class="form-label">Mật khẩu hiện tại</label>
                    <input id="current_pass" class="form-control" name="current_pass" type="password" >
                    <span class="form-message"></span>
                </div>
                <div class="form-group">
                    <label for="new_pass" class="form-label">Mật khẩu mới</label>
                    <input id="new_pass" class="form-control" name="new_pass" type="password" >
                    <span class="form-message"></span>
                </div>
                <div class="form-group">
                    <label for="new2_pass" class="form-label">Xác nhận mật khẩu</label>
                    <input id="new2_pass" class="form-control" name="new2_pass" type="password" >
                    <span class="form-message"></span>
                </div>
                <button class="form-submit" type="submit">Xác nhận</button>
            </form>
        </div>
    </div>
</div>
                    
<script src="<%=request.getContextPath()%>/assets/js/validator.js"></script>
<script>
    Validator({
        form: "#form-change-pass",
        errorSelector: '.form-message',
        rules: [
            Validator.minLength("#current_pass", 6),
            Validator.minLength("#new_pass", 6),
            Validator.minLength("#new2_pass", 6),
        ]
    });
</script>