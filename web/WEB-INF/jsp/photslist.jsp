<%@include file="header.jsp" %>   
<div class="content">
    <div class="header">
        <h1 class="page-title">List Photos</h1>
    </div>
    <ul class="breadcrumb">
        <li><a href="home">Dashboard</a> <span class="divider">/</span></li>
        <li class="active">List Photos</li>
    </ul>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="btn-toolbar">
                <a href="add_photo" class="btn btn-primary btn-sign-in"><i class="icon-plus"></i> Add Photos</a>
            </div>
            <div id="ctl00_cph_divWorkAreaContent" style="overflow: hidden;" ></div>
            <div id="tt" style="width:auto;height:0!important"
                 title="DataGrid - CardView" 
                 showFooter="false" pagination="true" data-options="singleSelect:true,page:'',url:'photolist',method:'get',pageList: [100,200,300],pageSize: 100,layout:['list']">
            </div>   
<%@include file="footer.jsp" %> 
<script type="text/javascript">
    function serach() {
        childserviceload();
    }
    $(document).ready(function () {
        childserviceload();
    });

    function childserviceload() {
        $('#tt').datagrid({
            url: 'photolist',
            onLoadSuccess: checkchildserviceload
        });
    }
    function checkchildserviceload() {
        length = $('#tt').datagrid('getRows').length;
        htmocontent = '<div class="well"><table class="table"><thead><tr>';
        htmocontent = htmocontent + '<th>#</th>';
        htmocontent = htmocontent + '<th>Image</th>';
        htmocontent = htmocontent + '<th>Delete</th>';
        htmocontent = htmocontent + '</tr></thead><tbody>';
        for (i = 0; i < length; i++) {
            var selectedRow = $('#tt').datagrid('getRows')[i];
            htmocontent = htmocontent + '<tr>';
            htmocontent = htmocontent + '<td>' + selectedRow.id + '</td>';
            htmocontent = htmocontent + '<td><a href="' + selectedRow.image + '" target="_blank" ><img src="'+selectedRow.image+'" width="30" height="30"></a></td>';
            htmocontent = htmocontent + '<td><a href="#" onclick="deletecontent(' + selectedRow.id  +', ' + selectedRow.ctid + ')">Delete</a></td>';
            htmocontent = htmocontent + '</tr>';
        }
        htmocontent = htmocontent + '</tbody></table></div>';
        $("#ctl00_cph_divWorkAreaContent").html(htmocontent);
    }
    function editcontent(id, ctid){
        window.location="edit_magazine?id="+id+"&ctid="+ctid;
    }
    function deletecontent(id, ctid) {
        swal({title: "Are you sure?", text: "You want to delete this Photo!", type: "warning", showCancelButton: true, confirmButtonColor: "#DD6B55", confirmButtonText: "Yes!", closeOnConfirm: false}, function () {
            $.ajax({
                url: "delete_photo?id=" + id,
                type: "GET",
                dataType: "json",
                contentType: "application/json",
                success: function (data)
                {
                    code = data.response.code;
                    if (code == 0) {
                        swal({title: "Success", text: "Photo Deleted Successfully", imageUrl: "resources/images/thumbs-up.jpg"}, function (isConfirm) {
                            if (isConfirm) {
                                window.location = 'photslist';
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
</body>
</html>