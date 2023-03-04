
<%@page import="utils.Utils"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/assets/css/list/add.css">
<div class="col-10 content">
    <div class="content-label">
        <span class="content-label__menu">FeedBack</span>
        <!--<span class="content-label__info"><i class="fas fa-home"></i>FeedBack</span>-->
        <div class="spacer2"></div>
    </div>
    <div class="form-container form-container_add">
        <div>
            <form action="FeedBackServlet" method="POST" id="form-xn" class="form">
                <div class="form-group">
                    <div class="form-group">
                      <label for="exampleFormControlTextarea1">Report</label>
                      <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="report"></textarea>
                    </div>
                    <span class="form-message"></span>
                </div>
                 <span class="content-label__info"><i class="fas fa-home"></i></span>
                <button type = "submit" class="form-submit"><i class="far fa-save"></i><span>Save</span></button>
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
