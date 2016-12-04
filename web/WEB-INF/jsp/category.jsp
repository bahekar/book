<%@include file="header.jsp" %>   
<div class="content">
    <div class="header">
        <h1 class="page-title">List Category</h1>
    </div>
    <ul class="breadcrumb">
        <li><a href="home">Dashboard</a> <span class="divider">/</span></li>
        <li class="active">List Category</li>
    </ul>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="btn-toolbar">
                <a href="add-category" class="btn btn-primary btn-sign-in"><i class="icon-plus"></i>Add Category</a>
            </div>
            <div id="ctl00_cph_divWorkAreaContent" style="overflow: hidden;" ></div>
            <div id="tt" style="width:auto;height:0!important"
                 title="DataGrid - CardView" 
                 showFooter="false" pagination="true" data-options="singleSelect:true,page:'',url:'categorylist',method:'get',pageList: [100,200,300],pageSize: 100,layout:['list']">
            </div>   
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        childserviceload();
    });
    function childserviceload() {
        $('#tt').datagrid({
            url: 'categorylist',
            onLoadSuccess: checkchildserviceload
        });
    }
    function checkchildserviceload() {
        length = $('#tt').datagrid('getRows').length;
        htmocontent = '<div class="well"><table class="table"><thead><tr><th>#</th><th>Category</th></tr></thead><tbody>';
        for (i = 0; i < length; i++) {
            var selectedRow = $('#tt').datagrid('getRows')[i];
            htmocontent = htmocontent + '<tr><td>' + selectedRow.id + '</td><td>' + selectedRow.Category + '</td><td><a href="editCategory?id='+selectedRow.id+'">Edit Category</a></td><td><a href="#" onclick="deleteneigh(' + selectedRow.id  +')">Delete Category</a></td></tr>';
        }
        htmocontent = htmocontent + '</tbody></table></div>';
        $("#ctl00_cph_divWorkAreaContent").html(htmocontent);
    }
    function deleteneigh(id) {
                swal({title: "Are you sure?", text: "You want to delete this Category!", type: "warning", showCancelButton: true, confirmButtonColor: "#DD6B55", confirmButtonText: "Yes!", closeOnConfirm: false}, function () {
                    $.ajax({
                        url: "delete_category?id=" + id,
                        type: "GET",
                        dataType: "json",
                        contentType: "application/json",
                        success: function (data)
                        {                        
                            code = data.response.code;
                            //alert(code);
                            if (code == 0) {
                                swal({title: "Success", text: "Category Deleted Successfully", imageUrl: "resources/images/thumbs-up.jpg"}, function (isConfirm) {
                                    if (isConfirm) {
                                        window.location = 'category';
                                    }
                                });
                            } else {
                                sweetAlert("Deletion Failed...", "", "error");
                            }
                        }
                    });
                });
    }
</script>
<%@include file="footer.jsp" %> 