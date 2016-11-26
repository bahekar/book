<%@include file="header.jsp" %>   
    <div class="content">
        <div class="header">
            <h1 class="page-title">Edit Sub-Category</h1>
        </div>
        <ul class="breadcrumb">
            <li><a href="home">Home</a> <span class="divider">/</span></li>
            <li><a href="sub_category">Sub-Category</a> <span class="divider">/</span></li>
            <li class="active">Edit Sub-Category</li>
        </ul>
        <div class="container-fluid">
            <div class="row-fluid">
            <br><br><br>
            <div class="well">
                    <label>Category</label>
                        <select id="category_id" name="category_id">
                            <option value="">Select Category</option>
			</select>
                    <div id="category_id_err" class="error-message-input">*Required</div>
                    <br><br>
                    <label>Sub-Category</label>
                    <input type="text" placeholder="Sub-Category" id="sub_category" name ="sub_category" value="${sub_category_name}" class="input-xlarge"><br>
                    <div id="sub_category_err" class="error-message-input">*Required</div>
                    <br>
                    <button id="categorysave" class="btn btn-primary btn-sign-in"><i class="icon-save"></i> Save</button>
            </div>
<script type="text/javascript">
    category_id();
    function category_id() 
    {
        $.ajax({
            url: "getcategory",
            type: "GET",
            dataType: "json",
            async: false,
            contentType: "application/json",
            success: function (response)
            {
                response = response.response.propertyTypes;
                $.each(response, function (idx, rec) {
                    $('<option/>', {
                        'value': rec.id,
                        'text': rec.name
                    }).appendTo('#category_id');
                });
            }
        });
        var category_id = '${category_id}';
        $('#category_id').val(category_id);
    }
    $("#categorysave").click(function () {
        var loginjson = {};
        category_id = $("#category_id").val();
        sub_category = $("#sub_category").val()       
        iserror = false;
        if (category_id == '') {
            addclass('category_id');
            iserror = true;
        } else {
            removeclass('category_id');
        }
        if (sub_category == '') {
            addclass('sub_category');
            iserror = true;
        } else {
            removeclass('sub_category');
        }
        id = getParameterByName("id");
        if (iserror) {
            return false;
        }
        $.ajax({
            url: "sub_category_update?sub_category=" + sub_category + "&id=" + id + "&category_id=" + category_id,
            type: "GET",
            dataType: "json",
             async: true,
            contentType: "application/json",
            success: function (data)
            {
                code = data.response.code;
                if (code == 0) {
                    swal({title: "Success", text: "Sub-Category Updated Successfully", imageUrl: "resources/images/thumbs-up.jpg"}, function (isConfirm) {
                        if (isConfirm) {
                            window.location = 'sub_category';
                        }
                    });
                } else if (code == 108) {
                    sweetAlert('Oops...', 'Invalid Sub-Category!', 'error');
                } else if (code == 109) {
                    sweetAlert('Oops...', 'Invalid Sub-Category!', 'error');
                } else {
                    sweetAlert('Oops...', 'Something went wrong!', 'error');
                }
            }, error: function (request, status, error) {
                sweetAlert("Update Failed...", "Sub-Category Failed", "error");
            }
        });

    });
</script>
<%@include file="footer.jsp" %> 