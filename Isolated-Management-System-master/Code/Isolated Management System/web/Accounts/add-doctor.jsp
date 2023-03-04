
<%@page import="utils.Utils"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/assets/css/list/add.css">
<div class="col-10 content">
    <div class="content-label">
        <span class="content-label__menu">Bảng điều khiển</span>
        <span class="content-label__info"><i class="fas fa-home"></i> > Hệ thống > Quản lý tài khoản > Thêm nhân sự</span>
        <div class="spacer2"></div>
    </div>
    <div class="form-container form-container_add">
        <div>
            <form action="AddDoctor" method="GET" id="form-xn" class="form">
                <div class="form-group">
                    <label for="ho_ten" class="form-label">Họ tên nhân viên:</label>
                    <input id="ho_ten" class="form-control" name="name" type="text" required>
                    <span class="form-message"></span>
                </div>

                <div class="form-group">
                    <label for="ten_dang_nhap" class="form-label">Tên đăng nhập:</label>
                    <input id="ten_dang_nhap" class="form-control" name="username" type="text" required>
                    <span class="form-message"></span>
                </div>
               
                <div class="form-group">
                    <label for="mat_khau" class="form-label">Mật khẩu</label>
                    <input id="mat_khau" class="form-control" name="password" type="password" required>
                    <span class="form-message"></span>
                </div>
                <div class="form-group">
                    <label for="mat_khau" class="form-label">Nhập Lại Mật khẩu</label>
                    <input id="mat_khau" class="form-control" name="repass" type="password" required>
                    <span class="form-message"></span>
                </div>
                <div class="form-group">
                    <label for="so_dien_thoai" class="form-label">Số điện thoại:</label>
                    <input id="so_dien_thoai" class="form-control" name="phone" type="text" >
                    <span class="form-message"></span>
                </div>
                <div class="form-group">
                    <label for="so_dien_thoai" class="form-label">Địa chỉ </label>
                    <input id="so_dien_thoai" class="form-control" name="address" type="text" >
                    <span class="form-message"></span>
                </div>
                 <div class="form-group">
                    <label for="so_dien_thoai" class="form-label">Email </label>
                    <input id="so_dien_thoai" class="form-control" name="email" type="text" >
                    <span class="form-message"></span>
                </div>
               
                <button type = "submit" class="form-submit"><i class="far fa-save"></i><span>Lưu</span></button>
            </form>
        </div>
        <div><img class="form-img" name ="file" id="output" src="<%=request.getContextPath()%>/${initParam.imgPath}${userLogin.getAvatar()}"/>
            <input name="file" type="file" accept="image/*" onchange="loadFile(event)" id="getFile" style="display:none" >
            <button style="margin-top: 4px; display:block;width: max-content; height:30px;" onclick="document.getElementById('getFile').click()">Chọn ảnh</button>
            <script>
                var loadFile = function (event) {
                    var reader = new FileReader();
                    reader.onload = function () {
                        var output = document.getElementById('output');
                        output.src = reader.result;
                    };
                    reader.readAsDataURL(event.target.files[0]);
                };
            </script>
        </div>
    </div>
</div>

