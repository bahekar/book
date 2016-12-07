<%@include file="header.jsp" %>   
      <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    
    <div class="content">
        <div class="header">
            <h1 class="page-title">Edit Audio</h1>
        </div>
        <ul class="breadcrumb">
            <li><a href="home">Home</a> <span class="divider">/</span></li>
            <li>Edit Magazine</li>
        </ul>
        <div class="container-fluid">
            <div class="row-fluid">
                <br><br><br>
                <form:form method="post" onsubmit="return Categorysave();" action="editaudiovideo" enctype="multipart/form-data">
                    <input type="hidden" id="cid" name="cid" >
                    <input type="hidden" id="type" name="type" value="3" >
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
        $("#cid").val(obj.id);
        $("#title").val(obj.title);
        $("#link").val(obj.link);
    }
          function Categorysave()  {
                var contentnamejson = {};
                iserror = false;
                title = $("#title").val();
                link = $("#link").val();
                
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
                
                if (iserror) {
                    return false;
                }else{
                     return true;
                }
            }
                    
</script>