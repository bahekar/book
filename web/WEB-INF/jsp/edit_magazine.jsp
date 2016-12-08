<%@include file="header.jsp" %>   
      <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <div class="content">
        <div class="header">
            <h1 class="page-title">Edit Magazine</h1>
        </div>
        <ul class="breadcrumb">
            <li><a href="home">Home</a> <span class="divider">/</span></li>
            <li>Edit Magazine </li>
        </ul>
        <div class="container-fluid">
            <div class="row-fluid">
		<br><br><br>
		<form:form method="post" onsubmit="return Categorysave();" action="edit_content"  enctype="multipart/form-data">
                    <input type="hidden" id="book_id" name="book_id">
                    <input type="hidden" name="type" value="3">
                    <div class="well">
					<div class="row-fluid">
						<div class="span6">
							<div class="span4">Title*</div>
                                                        <input type="text" id="book_title" name="book_title" placeholder="Title" class="input-xlarge">
						</div>
						<div class="span6">
							<div class="span4">Date*</div>
                                                        <input type="text" id="published_date" autocomplete="off" name="published_date" placeholder="Date" class="input-xlarge">
						</div>
					</div>
					
					<div class="row-fluid">
                                                <div class="span6">
							<div class="span4">Author Name*</div>
                                                        <input type="text" id="author_name" name="author_name" placeholder="Author Name" class="input-xlarge">
						</div>
						<div class="span6">
							<div class="span4">Language Type*</div>
                                                        <select id="book_type" name="book_type">
                                                            <option value="">Select Language Type</option>
                                                            <option value="1">English</option>
                                                            <option value="2">Hindi</option>
                                                            <option value="3">Urdu</option>
                                                            <option value="4">Arabic</option>
							</select>
						</div>
					</div>
                                    					
					<div class="row-fluid">
                                            <div class="span6">
						<div class="span4">Cover Image*</div>
                                                <input type="file" id="excel" name="file[]" class="input-xlarge" style="border: 1px solid #ccc;">
                                            </div>
                                            <div class="span6">
						<a id="link" target="_blank">Cover Image Link</a>
                                            </div>
					</div>
                                        <div class="row-fluid">
                                            <div class="span6">
						<div class="span4">Content File*</div>
                                                <input type="file" id="content_file" name="content_file[]" class="input-xlarge" style="border: 1px solid #ccc;">
                                            </div>
                                            <div class="span6">
                                                <a id="link2" target="_blank">Content Link</a>
                                            </div>
					</div>
                                        <button id="addcontent" class="btn btn-primary btn-sign-in"><i class="icon-save"></i> Update</button>
					
                    </div>
                </form:form>
<%@include file="footer.jsp" %> 
<script type="text/javascript">
    $(document).ready(function () {
        json =${restdet};
        loadresjson(json); 
    });
    function loadresjson(obj) 
    {
        $("#book_title").val(obj.book_title);
        $("#book_type").val(obj.book_type);
        $("#published_date").val(obj.published_date);
        $("#author_name").val(obj.author_name);
     
        $("#link").attr("href", obj.file_path);
        $("#link2").attr("href", obj.book_url);
    }
            function Categorysave(){
                var contentnamejson = {};
                iserror = false;
                book_title = $("#book_title").val();
                book_type = $("#book_type").val();
                published_date = $("#published_date").val();
                author_name = $("#author_name").val();
                excel = $("#excel").val();
                content_file = $("#content_file").val();
                
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
                    id = getParameterByName("id");
                    $("#book_id").val(id);
                    return true;
                }
            }
</script>