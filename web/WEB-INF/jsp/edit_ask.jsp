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
                <form:form method="post" onsubmit="return Categorysave();" action="editask" enctype="multipart/form-data">
                    <input type="hidden" id="cid" name="cid" >
                    <div class="well">
										
					<div class="row-fluid">
						<div class="span6">
							<div class="span4">Question*</div>
                                                        <input type="text" id="question" name="title" readonly="true" class="input-xlarge">
						</div>
						<div class="span6">
						</div>
					</div>
					
					<div class="row-fluid">
                                                <div class="span6">
							<div class="span4">Answer*</div>
                                            <input type="text" id="answer" name="link" placeholder="Answer" class="input-xlarge">
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
        $("#question").val(obj.question);
        $("#answer").val(obj.answer);
    }
          function Categorysave()  {
                var contentnamejson = {};
                iserror = false;
                question = $("#question").val();
                answer = $("#answer").val();

                if (answer == '') {
                    addclass('answer');
                    iserror = true;
                } else {
                    removeclass('answer');
                }
                
                if (iserror) {
                    return false;
                }else{
                     return true;
                }
            }
                    
</script>