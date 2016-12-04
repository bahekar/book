<%@include file="header.jsp" %>   
      <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    
    <div class="content">
        <div class="header">
            <h1 class="page-title">Add Book</h1>
        </div>
        <ul class="breadcrumb">
            <li><a href="home">Home</a> <span class="divider">/</span></li>
            <li>Add Book</li>
        </ul>
        <div class="container-fluid">
            <div class="row-fluid">
				<br><br><br>
                                        <form:form method="post" onsubmit="return Categorysave();" action="addcontent"  enctype="multipart/form-data">

				<div class="well">
										
					<div class="row-fluid">
						<div class="span6">
							<div class="span4">Book Title*</div>
                                                        <input type="text" id="book_title" name="book_title" placeholder="Book Title" class="input-xlarge">
						</div>
						<div class="span6">
							<div class="span4">Published Date*</div>
                                                        <input type="text" id="published_date" autocomplete="off" name="published_date" placeholder="Published Date" class="input-xlarge">
						</div>
					</div>
					
					<div class="row-fluid">
                                                <div class="span6">
							<div class="span4">Author Name*</div>
                                                        <input type="text" id="author_name" name="author_name" placeholder="Author Name" class="input-xlarge">
						</div>
						<div class="span6">
							<div class="span4">Book Type*</div>
                                                        <select id="book_type" name="book_type">
                                                            <option value="">Select Book Type</option>
                                                            <option value="1">English</option>
                                                            <option value="2">Hindi</option>
                                                            <option value="3">Urdu</option>
                                                            <option value="4">Arabic</option>
							</select>
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
                book_title = $("#book_title").val();
                book_type = $("#book_type").val();
                published_date = $("#published_date").val();
                author_name = $("#author_name").val();
                
                if (book_title == '') {
                    addclass('book_title');
                    iserror = true;
                } else {
                    removeclass('book_title');
                }
                if (book_type == '') {
                    addclass('book_type');
                    iserror = true;
                } else {
                    removeclass('book_type');
                }
                if (published_date == '') {
                    addclass('published_date');
                    iserror = true;
                } else {
                    removeclass('published_date');
                }
                if (author_name == '') {
                    addclass('author_name');
                    iserror = true;
                } else {
                    removeclass('author_name');
                }
                if (iserror) {
                    return false;
                }else{
                     return true;
                }
                contentnamejson['book_title'] = book_title;
                contentnamejson['book_type'] = book_type;
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