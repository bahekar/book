<%@include file="header.jsp" %>   
      <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    
    <div class="content">
        <div class="header">
            <h1 class="page-title">Add Video</h1>
        </div>
        <ul class="breadcrumb">
            <li><a href="home">Home</a> <span class="divider">/</span></li>
            <li>Add Video</li>
        </ul>
        <div class="container-fluid">
            <div class="row-fluid">
                <br><br><br>
                <form:form method="post" onsubmit="return Categorysave();" action="addaudiovideotype" enctype="multipart/form-data">
                    <input type="hidden" id="type" name="type" value="4">
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
							<div class="span4">Link*</div>
                                            <input type="text" id="link" name="link" placeholder="Link" class="input-xlarge">
                     			</div>
						<div class="span6">
						</div>
					</div>
                                        <div class="row-fluid">
                                               
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
                link = $("#link").val();
                book_type = $("#book_type").val();
                if (title == '') {
                    addclass('title');
                    iserror = true;
                } else {
                    removeclass('title');
                }
                if (link == '') {
                    addclass('link');
                    iserror = true;
                } else {
                    removeclass('link');
                }
                if (book_type == '') {
                    addclass('book_type');
                    iserror = true;
                } else {
                    removeclass('book_type');
                }
                if (iserror) {
                    return false;
                }else{
                     return true;
                }
            }
                    
</script>