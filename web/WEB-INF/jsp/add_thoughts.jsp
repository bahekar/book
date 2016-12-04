<%@include file="header.jsp" %>   
      <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    
    <div class="content">
        <div class="header">
            <h1 class="page-title">Add Thoughts</h1>
        </div>
        <ul class="breadcrumb">
            <li><a href="home">Home</a> <span class="divider">/</span></li>
            <li>Add Thoughts</li>
        </ul>
        <div class="container-fluid">
            <div class="row-fluid">
				<br><br><br>
                                        <form:form method="post" onsubmit="return Categorysave();" action="addcontent"  enctype="multipart/form-data">

				<div class="well">
										
					<div class="row-fluid">
						<div class="span6">
							<div class="span4">Title*</div>
                                                        <input type="text" id="title" name="title" placeholder="Title" class="input-xlarge">
						</div>
						<div class="span6">
						</div>
					</div>
					
					<div class="row-fluid">
                                                <div class="span6">
							<div class="span4">Upload File*</div>
                                         <input type="file" id="excel" name="file[]" class="input-xlarge" style="border: 1px solid #ccc;">
                     			</div>
						<div class="span6">
						</div>
					</div>
                                        <button id="addcontent" class="btn btn-primary btn-sign-in"><i class="icon-save"></i> Save</button>
					
				</div>
                              </form:form>
<%@include file="footer.jsp" %> 
<script type="text/javascript">
          function Categorysave()  {
                var contentnamejson = {};
                iserror = false;
                title = $("#title").val();
                excel = $("#excel").val();
                
                if (title == '') {
                    addclass('title');
                    iserror = true;
                } else {
                    removeclass('title');
                }
                if (excel == '') {
                    addclass('excel');
                    iserror = true;
                } else {
                    removeclass('excel');
                }
                if (iserror) {
                    return false;
                }else{
                     return true;
                }
                
                contentnamejson['title'] = book_name_english;
                contentnamejson['excel'] = book_name_hindi;
                contentnamejson['book_name_urdu'] = book_name_urdu;
                contentnamejson['category_id'] = category_id;
                contentnamejson['sub_category_id'] = sub_category_id;
                contentnamejson['published_date'] = published_date;
                contentnamejson['author_name'] = author_name;
                
                       var content_value = JSON.stringify(contentnamejson);
                        $.ajax({
                            url: "addcontent",
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
                                    swal({title: "Success", text: "Book added Successfully", imageUrl: "resources/images/thumbs-up.jpg"}, function (isConfirm) {
                                        if (isConfirm) {
                                            //alert(isConfirm);
                                            window.location = 'list_book';
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
                        }
                    
</script>