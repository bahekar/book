<%@include file="header.jsp" %>   
  <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    
    <div class="content">
        <div class="header">
            <h1 class="page-title">Add RSS Feed</h1>
        </div>
        <ul class="breadcrumb">
            <li><a href="home">Home</a> <span class="divider">/</span></li>
            <li><a href="list_rss_feed">RSS Feed</a> <span class="divider">/</span></li>
            <li class="active">Add RSS Feed</li>
        </ul>
        <div class="container-fluid">
            <div class="row-fluid">           
                <br><br><br>
                <div class="well">
                     <form:form method="post" onsubmit="return Categorysave();" action="rss_feed_insert"  enctype="multipart/form-data">

                        <label>Category</label>
                        <select id="sub_category_id" name="sub_category_id">
                            <option value="">Select Category</option>
			</select>
                        <div id="sub_category_id_err" class="error-message-input">*Required</div>
                        <br><br>
                        <label>Description</label>
                        <textarea rows="5" style="width:80%;" placeholder="Description" id="description" name ="description" class="input-xlarge"></textarea><br>
                        <div id="description_err" class="error-message-input">*Required</div>
                        <br><b>OR</b><br>
                        <label>Choose Excel File</label>
                        <input type="file" id="excel" name="file[]" class="input-xlarge" style="border: 1px solid #ccc;">
                        <div id="excel_err" class="error-message-input">*Required</div>
                        <br><br>
                        <button id="savefeed" class="btn btn-primary btn-sign-in"><i class="icon-save"></i> Save</button>
                        </form:form>
                </div>
<script type="text/javascript">
    get_sub_category();
    function get_sub_category()
    {
        $.ajax({
            url: "getcategorylist",
            type: "GET",
            dataType: "json",
            async: false,
            contentType: "application/json",
            success: function (response)
            {
                response = response.response.propertyTypes;
                $('#sub_category_id').html('<option value="">Select Category</option>');
                $.each(response, function (idx, rec) {
                    $('<option/>', {
                        'value': rec.id,
                        'text': rec.name
                    }).appendTo('#sub_category_id');
                });
            }
        });
    }
   function Categorysave()  {
        var loginjson = {};
        sub_category_id = $("#sub_category_id").val();
        description = $("#description").val();
        excel = $("#excel").val();
        iserror = false;
        if (sub_category_id == '') {
            addclass('sub_category_id');
            iserror = true;
        } else {
            removeclass('sub_category_id');
        }
        if (description == '' && excel == '') {
            addclass('description');
            iserror = true;
        } else {
            removeclass('description');
        }
        if (iserror) {
            return false;
        }
        return true;
        $.ajax({
            url: "rss_feed_insert?sub_category_id=" + sub_category_id + "&description=" + description,
            type: "GET",
            dataType: "json",
            async: false,
            contentType: "application/json",
            success: function (data)
            {
                code = data.response.code;
                if (code == 0) {
                    swal({title: "Success", text: "RSS Feed Added Successfully", imageUrl: "resources/images/thumbs-up.jpg"}, function (isConfirm) {
                        if (isConfirm) {
                            window.location = 'list_rss_feed';
                        }
                    });
                } else if (code == 108) {
                    sweetAlert('Oops...', 'Invalid!', 'error');
                } else if (code == 109) {
                    sweetAlert('Oops...', 'Invalid!', 'error');
                } else {
                    sweetAlert('Oops...', 'Something went wrong!', 'error');
                }
            }
        });

    }
</script>
<%@include file="footer.jsp" %> 