<%@include file="header.jsp" %>   
      <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    
    <div class="content">
        <div class="header">
            <h1 class="page-title">Edit Sham-e-Ferozan</h1>
        </div>
        <ul class="breadcrumb">
            <li><a href="home">Home</a> <span class="divider">/</span></li>
            <li>Edit Sham-e-Ferozan</li>
        </ul>
        <div class="container-fluid">
            <div class="row-fluid">
                <br><br><br>
                <form:form method="post" onsubmit="return Categorysave();" action="editcontenttype" enctype="multipart/form-data">
                    <input type="hidden" id="cid" name="cid" >
                    <input type="hidden" id="ctid" name="ctid" >
                    <input type="hidden" id="type" name="type" value="5" >
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
                                                    <a id="link" target="_blank">File Link</a>
						</div>
					</div>
                                        <button id="addcontent" class="btn btn-primary btn-sign-in"><i class="icon-save"></i> Save</button>
					
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
        $("#ctid").val(obj.ctid);
        $("#title").val(obj.title);
        $("#link").attr("href", obj.image);
    }
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
                
                if (iserror) {
                    return false;
                }else{
                     return true;
                }
            }
                    
</script>