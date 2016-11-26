<%@include file="header.jsp" %>   
    <div class="content">
        <div class="header">
            <h1 class="page-title">Edit Category</h1>
        </div>
        <ul class="breadcrumb">
            <li><a href="home">Home</a> <span class="divider">/</span></li>
            <li><a href="category">Category</a> <span class="divider">/</span></li>
            <li class="active">Edit Category</li>
        </ul>
        <div class="container-fluid">
            <div class="row-fluid">
            <br><br><br>
            <div class="well">
                    <label>Edit Category</label>
                    <input type="text" placeholder="category" id="category" name ="category" value="${category_name}" class="input-xlarge"><br>
                    <div id="description_err" class="error-message-input">*Required</div>
                    <button id="categorysave" class="btn btn-primary btn-sign-in"><i class="icon-save"></i> Save</button>
            </div>
<script type="text/javascript">
    $("#categorysave").click(function () {
        var loginjson = {};
        category = $("#category").val()       
        iserror = false;
        if (category == '') {
            addclass('category');
            iserror = true;
        } else {
            removeclass('category');
        }
        id = getParameterByName("id");
        if (iserror) {
            return false;
        }
        $.ajax({
            url: "categoryupdate?category=" + category+ "&id=" + id,
            type: "GET",
            dataType: "json",
             async: true,
            contentType: "application/json",
            success: function (data)
            {
                code = data.response.code;
                if (code == 0) {
                    swal({title: "Success", text: "Category Updated Successfully", imageUrl: "resources/images/thumbs-up.jpg"}, function (isConfirm) {
                        if (isConfirm) {
                            window.location = 'category';
                        }
                    });
                } else if (code == 108) {
                    sweetAlert('Oops...', 'Invalid Category!', 'error');
                } else if (code == 109) {
                    sweetAlert('Oops...', 'Invalid Category!', 'error');
                } else {
                    sweetAlert('Oops...', 'Something went wrong!', 'error');
                }
            }, error: function (request, status, error) {
                sweetAlert("Update Failed...", "Category Failed", "error");
            }
        });

    });
    $("[rel=tooltip]").tooltip();
    $(function () {
        $('.demo-cancel-click').click(function () {
            return false;
        });
    });
</script>
<%@include file="footer.jsp" %> 