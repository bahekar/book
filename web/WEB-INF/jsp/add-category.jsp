<%@include file="header.jsp" %>   
    <div class="content">
        <div class="header">
            <h1 class="page-title">Add Category</h1>
        </div>
        <ul class="breadcrumb">
            <li><a href="home">Home</a> <span class="divider">/</span></li>
            <li><a href="category">Category</a> <span class="divider">/</span></li>
            <li class="active">Add Category</li>
        </ul>
        <div class="container-fluid">
            <div class="row-fluid">           
                <br><br><br>
                <div class="well">
                        <label>Add Category</label>
                        <input type="text" placeholder="category" id="category" name ="category" class="input-xlarge"><br>
                        <div id="description_err" class="error-message-input">*Required</div>
                        <button id="categorysave" class="btn btn-primary btn-sign-in"><i class="icon-save"></i> Save</button>
                </div>
<script type="text/javascript">
    $("#categorysave").click(function () {
        var loginjson = {};
        category = $("#category").val();
        iserror = false;
        if (category == '') {
            addclass('category');
            iserror = true;
        } else {
            removeclass('category');
        }
        if (iserror) {
            return false;
        }
        $.ajax({
            url: "categorysave?category=" + category,
            type: "GET",
            dataType: "json",
            async: false,
            contentType: "application/json",
            success: function (data)
            {
                code = data.response.code;
                if (code == 0) {
                    swal({title: "Success", text: "Category Created Successfully", imageUrl: "resources/images/thumbs-up.jpg"}, function (isConfirm) {
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
            }
        });

    });
</script>
<%@include file="footer.jsp" %> 