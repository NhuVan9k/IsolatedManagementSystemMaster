<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container mt-3">
  <h2>Filterable Table</h2>
  <p>Nhập một cái gì đó vào trường nhập liệu để tìm kiếm tên, họ hoặc email trong bảng:</p>  
  <input class="form-control" id="myInput" type="text" placeholder="Search..">
  <br>
  <table class="table table-bordered">
   <thead>
      <tr>
        <th>Tên</th>
        <th>Họ</th>
        <th>Email</th>
      </tr>
    </thead>
    <tbody id="myTable">
      <tr>
        <td>Tiệp</td>
        <td>Hoàng</td>
        <td>ht@ht.vn</td>
      </tr>
      <tr>
        <td>Hòa</td>
        <td>NGuyễn</td>
        <td>nguyenhoa@gmail.com</td>
      </tr>
      <tr>
        <td>Tân</td>
        <td>Đặng</td>
        <td>tancute@gmail.com</td>
      </tr>
      <tr>
        <td>Thanh</td>
        <td>Hoàng</td>
        <td>hoangthanh@gmail.com</td>
      </tr>
    </tbody>
  </table>  
  <p>Nhập một cái gì đó vào trường nhập liệu để tìm kiếm tên, họ hoặc email trong bảng:</p>
</div>
<script>
$(document).ready(function(){
  $("#myInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#myTable tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});
</script>
</body>
</html>