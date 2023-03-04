<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link href="<%=request.getContextPath()%>/assets/css/form.css" rel="stylesheet">

<div class="form-container">
    <form action="RegisterServlet" method="POST" id="form-dk" class="form">
        <h3 class="heading">Đăng ký</h3>
        <p class="desc">Đăng ký ngay nào bạn ơi</p>

        <div class="spacer"></div>

        <div class="form-group">
            <label for="username" class="form-label">Tên đăng nhập</label>
            <input id="username" class="form-control" name="username" type="text">
            <span class="form-message"></span>
        </div>

        <div class="form-group">
            <label for="password" class="form-label">Mật khẩu</label>
            <input id="password" class="form-control" name="password" type="password">
            <span class="form-message"></span>
        </div>
        
        <div class="form-group">
            <label for="fullname" class="form-label">Họ và tên chủ tài khoản</label>
            <input id="fullname" class="form-control" name="fullname" type="text">
            <span class="form-message"></span>
        </div>
        
         <div class="form-group">
            <label for="patientId" class="form-label">Mã bệnh nhân</label>
            <input id="patientId" class="form-control" name="patientId" type="text">
            <span class="form-message"></span>
        </div>
        
        
        <div class="form-group">
            <label for="phone" class="form-label">Số điện thoại của bạn</label>
            <input id="phone" class="form-control" name="phone" type="text">
            <span class="form-message"></span>
        </div>
        
        <div class="form-group">
            <label for="email" class="form-label">Email của bạn</label>
            <input id="email" class="form-control" name="email" type="text">
            <span class="form-message"></span>
        </div>

        <button class="form-submit">Đăng ký</button>
    </form>
</div>

<script src="<%=request.getContextPath()%>/assets/js/validator.js"></script>
<script>
    Validator({
        form: "#form-dk",
        errorSelector: '.form-message',
        rules: [
            Validator.isRequired("#username"),
            Validator.minLength("#password", 6),
            Validator.isPhone("#phone"),
            Validator.isRequired("#fullname"),
            Validator.isRequired("#makhucachly"),
        ]
    });
</script>
