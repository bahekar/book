<%@include file="header.jsp" %>   
      <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <div class="content">
        <div class="header">
            <h1 class="page-title">Edit Book</h1>
        </div>
        <ul class="breadcrumb">
            <li><a href="home">Home</a> <span class="divider">/</span></li>
            <li>Edit Book </li>
        </ul>
        <div class="container-fluid">
            <div class="row-fluid">
				<br><br><br>
				<div class="well">
					     <form:form method="post" onsubmit="return Categorysave();" action="edit_content"  enctype="multipart/form-data">

					<div class="row-fluid">
						<div class="span6">
							<div class="span4">Category*</div>
                                                        <select id="category_id" name="category_id" onchange="get_sub_category(this.value)">
                                                            <option value="">Select Category</option>
							</select>
						</div>
						<div class="span6">
							<div class="span4">Sub Category*</div>
                                                        <select id="sub_category_id" name="sub_category_id">
                                                            
							</select>
						</div>
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="span4">Book Name English*</div>
                                                        <input type="text" id="book_name_english" name="book_name_english" placeholder="Book Name English Code" class="input-xlarge">
						</div>
						<div class="span6">
							<div class="span4">Book Name Hindi*</div>
							<input type="text" id="book_name_hindi" name="book_name_hindi" placeholder="Book Name Hindi" class="input-xlarge">
						</div>
					</div>
					
					<div class="row-fluid">
						<div class="span6">
							<div class="span4">Book Name Urdu*</div>
                                                        <input type="text" id="book_name_urdu" name="book_name_urdu" placeholder="Book Name Urdu" class="input-xlarge">
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
						</div>
					</div>
                                    					<div class="row-fluid">
                                                <div class="span6">
							<div class="span4">Upload File*</div>
                                         <input type="file" id="excel" name="file[]" class="input-xlarge" style="border: 1px solid #ccc;">
                     			</div>
						<div class="span6">
                                                    <a id="link" target="_blank">File Link</a>
						</div>
					</div>
                                                 <input type="hidden" id="book_id" name="book_id">
			    
                                        <button id="addcontent" class="btn btn-primary btn-sign-in"><i class="icon-save"></i> Save</button>
					
				</div>
                  </form:form>
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
    $(document).ready(function () {
        json =${restdet};
        loadresjson(json); 
    });
    function loadresjson(obj) 
    {
        $("#book_name_english").val(obj.book_name_english);
        $("#book_name_hindi").val(obj.book_name_hindi);
        $("#book_name_urdu").val(obj.book_name_urdu);
        $("#category_id").val(obj.category_id);
        get_sub_category(obj.category_id);
        $("#sub_category_id").val(obj.sub_category_id);
        $("#published_date").val(obj.published_date);
        $("#author_name").val(obj.author_name);
     
        $("#link").attr("href", obj.file_path);
        //$("#link").html(obj.file_path);
    }
           function Categorysave(){

                var contentnamejson = {};
                iserror = false;
                book_name_english = $("#book_name_english").val();
                book_name_hindi = $("#book_name_hindi").val();
                book_name_urdu = $("#book_name_urdu").val();
                category_id = $("#category_id").val();
                sub_category_id = $("#sub_category_id").val();
                published_date = $("#published_date").val();
                author_name = $("#author_name").val();
                
                if (book_name_english == '') {
                    addclass('book_name_english');
                    iserror = true;
                } else {
                    removeclass('book_name_english');
                }
                if (book_name_hindi == '') {
                    addclass('book_name_hindi');
                    iserror = true;
                } else {
                    removeclass('book_name_hindi');
                }
                if (book_name_urdu == '') {
                    addclass('book_name_urdu');
                    iserror = true;
                } else {
                    removeclass('book_name_urdu');
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
                }else{book_id
                    id = getParameterByName("id");
                       $("#book_id").val(id);
                     return true;
                }
                contentnamejson['book_name_english'] = book_name_english;
                contentnamejson['book_name_hindi'] = book_name_hindi;
                contentnamejson['book_name_urdu'] = book_name_urdu;
                contentnamejson['category_id'] = category_id;
                contentnamejson['sub_category_id'] = sub_category_id;
                contentnamejson['published_date'] = published_date;
                contentnamejson['author_name'] = author_name;
                contentnamejson['id'] = getParameterByName("id");
                
                //id = getParameterByName("id");
                //alert(id);
                       var content = JSON.stringify(contentnamejson);
                       //alert(content);
                        $.ajax({
                            url: "edit_content",
                            type: "POST",
                            dataType: "json",
                            data:content,
                            async: false,
                            contentType: "application/json",
                            success: function (data)
                            {                      
                                code = data.response.code;
                                if (code == 0) {
                                    swal({title: "Success", text: "Book updated Successfully", imageUrl: "resources/images/thumbs-up.jpg"}, function (isConfirm) {
                                        if (isConfirm) {
                                            window.location = "list_book";
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