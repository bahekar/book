<%@include file="header.jsp" %>   

<div class="content">
    <div class="header">
        <h1 class="page-title">List Sub-Category</h1>
    </div>
    <ul class="breadcrumb">
        <li><a href="home">Dashboard</a> <span class="divider">/</span></li>
        <li class="active">List Sub-Category</li>
    </ul>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="btn-toolbar">
                <a href="add_sub_category" class="btn btn-primary btn-sign-in"><i class="icon-plus"></i>Add Sub-Category</a>
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
            url: 'sub_category_list',
            onLoadSuccess: checkchildserviceload
        });
    }
    function checkchildserviceload() {
        length = $('#tt').datagrid('getRows').length;
        htmocontent = '<div class="well"><table class="table"><thead><tr><th>#</th><th>Sub-Category</th></tr></thead><tbody>';
        for (i = 0; i < length; i++) {
            var selectedRow = $('#tt').datagrid('getRows')[i];
            htmocontent = htmocontent + '<tr><td>' + selectedRow.id + '</td><td>' + selectedRow.sub_category + '</td><td><a href="edit_sub_category?id='+selectedRow.id+'">Edit Sub Category</a></td><td><a href="#" onclick="delete_sub_category(' + selectedRow.id  +')">Delete Sub Category</a></td></tr>';
        }
        htmocontent = htmocontent + '</tbody></table></div>';
        $("#ctl00_cph_divWorkAreaContent").html(htmocontent);
    }
    function delete_sub_category(id) {
                swal({title: "Are you sure?", text: "You want to delete this Sub-Category!", type: "warning", showCancelButton: true, confirmButtonColor: "#DD6B55", confirmButtonText: "Yes!", closeOnConfirm: false}, function () {
                    $.ajax({
                        url: "delete_sub_category?id=" + id,
                        type: "GET",
                        dataType: "json",
                        contentType: "application/json",
                        success: function (data)
                        {                        
                            code = data.response.code;
                            if (code == 0) {
                                swal({title: "Success", text: "Sub-Category Deleted Successfully", imageUrl: "resources/images/thumbs-up.jpg"}, function (isConfirm) {
                                    if (isConfirm) {
                                        window.location = 'sub_category';
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