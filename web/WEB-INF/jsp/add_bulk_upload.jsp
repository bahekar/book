<%@include file="header.jsp" %>   
    
    <div class="content">
        <div class="header">
            <h1 class="page-title">Add Bulk Upload</h1>
        </div>
        <ul class="breadcrumb">
            <li><a href="home">Home</a> <span class="divider">/</span></li>
            <li><a href="">Add Bulk Upload</a> <span class="divider">/</span></li>
        </ul>
        <div class="container-fluid">
            <div class="row-fluid">
				<br><br><br>
				<div class="well">
                                    
					
					<div class="row-fluid">
						<div class="span6">
							<div class="span4">Content Type</div>
                                                        <select id="content_type_id" name="content_type_id">
								<option value="">Select Content Type</option>
								<option value="1">Text Feed</option>
								
							</select>
						</div>
						<div class="span6">
							
						</div>
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="span4">Name*</div>
							<input type="text" id="contentname" placeholder="Name" name="contentname" class="input-xlarge">
						</div>
						<div class="span6">
							<div class="span4">Code*</div>
                                                        <input type="text" id="code" name="code" placeholder="Code" class="input-xlarge">
						</div>
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="span4">Category</div>
                                                        <select id="category_id" name="category_id" onchange="get_sub_category(this.value)">
                                                            <option value="">Select Category</option>
							</select>
						</div>
						<div class="span6">
							<div class="span4">Sub Category</div>
                                                        <select id="sub_category_id" name="sub_category_id">
                                                            
							</select>
						</div>
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="span4">Provider Code*</div>
                                                        <input type="text" id="provider_code" name="provider_code" placeholder="Provider Code" class="input-xlarge">
						</div>
						<div class="span6">
							<div class="span4">Search Keywords*</div>
							<input type="text" id="keyword" name="keyword" placeholder="Provider Code" class="input-xlarge">
						</div>
					</div>
					
					<div class="row-fluid">
						<div class="span6">
							<div class="span4">Release Date</div>
                                                        <input type="text" id="release_date" autocomplete="off" name="release_date" placeholder="Release Date" class="input-xlarge">
						</div>
						<div class="span6">
							<div class="span4">Expiry Date</div>
                                                        <input type="text" id="expiry_date" autocomplete="off" name="expiry_date" placeholder="Expiry Date" class="input-xlarge">
						</div>
					</div>
					
					<div class="row-fluid">&nbsp;</div>
					<div class="row-fluid">
						<div class="span11">
							Content<br>
                                                        <textarea id="content" name="content" style="width:100%;" rows="5"></textarea>
						</div>
					</div>
                                        <button id="addcontent" class="btn btn-primary btn-sign-in"><i class="icon-save"></i> Save</button>
					
				</div>
                
<%@include file="footer.jsp" %> 
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
//                  alert(rec.propertyTypes.toString());
                    $('<option/>', {
                        'value': rec.id,
                        'text': rec.name
                    }).appendTo('#category_id');
                });
            }
        });
    }
    function get_sub_category(category_id)
    {
        $.ajax({
            url: "get_sub_category?id="+category_id,
            type: "GET",
            dataType: "json",
            async: false,
            contentType: "application/json",
            success: function (response)
            {
                response = response.response.propertyTypes;
                $('#sub_category_id').html('<option value="">Select Sub Category</option>');
                $.each(response, function (idx, rec) {
                    $('<option/>', {
                        'value': rec.id,
                        'text': rec.name
                    }).appendTo('#sub_category_id');
                });
            }
        });
    }
            $("#addcontent").click(function () {

                var contentnamejson = {};
                iserror = false;
                contentname = $("#contentname").val();
                content_type_id = $("#content_type_id").val();
                code = $("#code").val();
                category_id = $("#category_id").val();
                sub_category_id = $("#sub_category_id").val();
                provider_code = $("#provider_code").val();
                keyword = $("#keyword").val();
                release_date = $("#release_date").val();
                expiry_date = $("#expiry_date").val();
                content_text = $("#content").val();
                
                if (contentname == '') {
                    addclass('contentname');
                    iserror = true;
                } else {
                    removeclass('contentname');
                }
                if (content_type_id == '') {
                    addclass('content_type_id');
                    iserror = true;
                } else {
                    removeclass('content_type_id');
                }
                if (code == '') {
                    addclass('code');
                    iserror = true;
                } else {
                    removeclass('code');
                }
                if (category_id == '') {
                    addclass('category_id');
                    iserror = true;
                } else {
                    removeclass('category_id');
                }
                if (sub_category_id == '') {
                    addclass('sub_category_id');
                    iserror = true;
                } else {
                    removeclass('sub_category_id');
                }
                if (provider_code == '') {
                    addclass('provider_code');
                    iserror = true;
                } else {
                    removeclass('provider_code');
                }
                if (keyword == '') {
                    addclass('keyword');
                    iserror = true;
                } else {
                    removeclass('keyword');
                }
                if (release_date == '') {
                    addclass('release_date');
                    iserror = true;
                } else {
                    removeclass('release_date');
                }
                if (expiry_date == '') {
                    addclass('expiry_date');
                    iserror = true;
                } else {
                    removeclass('expiry_date');
                }
                if (content_text == '') {
                    addclass('content');
                    iserror = true;
                } else {
                    removeclass('content');
                }
                if (iserror) {
                    return false;
                }
                
                contentnamejson['contentname'] = contentname;
                contentnamejson['content_type_id'] = content_type_id;
                contentnamejson['code'] = code;
                contentnamejson['category_id'] = category_id;
                contentnamejson['sub_category_id'] = sub_category_id;
                contentnamejson['provider_code'] = provider_code;
                contentnamejson['keyword'] = keyword;
                contentnamejson['release_date'] = release_date;
                contentnamejson['expiry_date'] = expiry_date;
                contentnamejson['content'] = content_text;
                
                       var content_value = JSON.stringify(contentnamejson);
                        $.ajax({
                            url: "add_bulk_content",
                            type: "POST",
                            dataType: "json",
                            data:content_value,
                            async: false,
                            contentType: "application/json",
                            success: function (data)
                            {
                                code = data.response.code;
                                //alert(code);
                                if (code == 0) {
                                    swal({title: "Success", text: "Bulk Content added Successfully", imageUrl: "resources/images/thumbs-up.jpg"}, function (isConfirm) {
                                        if (isConfirm) {
                                            //alert(isConfirm);
                                            window.location = 'list_bulk_upload';
                                        }
                                    });
                                } else if (code == 108) {
                                    sweetAlert('Oops...', 'Invalid UserId!', 'error');

                                } else if (code == 109) {
                                    sweetAlert('Oops...', 'Invalid Password!', 'error');

                                } else {
                                    sweetAlert('Oops...', 'Something went wrong!', 'error');
                                }
                            }
                        });

                    });
                    
</script>