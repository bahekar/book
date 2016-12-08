<%@include file="header.jsp" %>   
      <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    
    <div class="content">
        <div class="header">
            <h1 class="page-title">Add Photo</h1>
        </div>
        <ul class="breadcrumb">
            <li><a href="home">Home</a> <span class="divider">/</span></li>
            <li>Add Photo</li>
        </ul>
        <div class="container-fluid">
            <div class="row-fluid">
		<br><br><br>
                <form:form method="post" onsubmit="return Categorysave();" action="addphoto" enctype="multipart/form-data">
                    <input type="hidden" name="type" value="1">
                    <div class="well">
									
					<div class="row-fluid">
                                            <div class="span6">
						<div class="span4">Image*</div>
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
                excel = $("#excel").val();
                
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
            }
                    
</script>