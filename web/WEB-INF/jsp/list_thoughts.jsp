<%@include file="header.jsp" %>   
<div class="content">
    <div class="header">
        <h1 class="page-title">List Thoughts</h1>
    </div>
    <ul class="breadcrumb">
        <li><a href="home">Dashboard</a> <span class="divider">/</span></li>
        <li class="active">List Thoughts</li>
    </ul>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="btn-toolbar">
                <a href="add_thoughts" class="btn btn-primary btn-sign-in"><i class="icon-plus"></i> Add Thoughts</a>
            </div>
            <div id="ctl00_cph_divWorkAreaContent" style="overflow: hidden;" ></div>
            <div id="tt" style="width:auto;height:0!important"
                 title="DataGrid - CardView" 
                 showFooter="false" pagination="true" data-options="singleSelect:true,page:'',url:'mortagesettinglist',method:'get',pageList: [100,200,300],pageSize: 100,layout:['list']">
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
            url: 'get_single_upload_list?type=4',
            onLoadSuccess: checkchildserviceload
        });
    }
    function checkchildserviceload() {
        length = $('#tt').datagrid('getRows').length;
        htmocontent = '<div class="well"><table class="table"><thead><tr>';
        htmocontent = htmocontent + '<th>#</th>';
        htmocontent = htmocontent + '<th>Title</th>';
        htmocontent = htmocontent + '<th>Author Name</th>';
        htmocontent = htmocontent + '<th>Date</th>';
        htmocontent = htmocontent + '<th>Cover Image</th>';
        htmocontent = htmocontent + '<th>Content File</th>';
        htmocontent = htmocontent + '<th>Edit</th>';
        htmocontent = htmocontent + '<th>Delete</th>';
        htmocontent = htmocontent + '</tr></thead><tbody>';
        for (i = 0; i < length; i++) {
            var selectedRow = $('#tt').datagrid('getRows')[i];
            htmocontent = htmocontent + '<tr>';
            htmocontent = htmocontent + '<td>' + selectedRow.id + '</td>';
            htmocontent = htmocontent + '<td>' + selectedRow.title + '</td>';
            htmocontent = htmocontent + '<td>' + selectedRow.author_name + '</td>';
            htmocontent = htmocontent + '<td>' + selectedRow.published_date + '</td>';
            htmocontent = htmocontent + '<td><a href="' + selectedRow.file_path + '" target="_blank" ><img src="resources/images/images.png" width="30" height="30"></a></td>';
            htmocontent = htmocontent + '<td><a href="' + selectedRow.book_url + '" target="_blank" ><img src="resources/images/images.png" width="30" height="30"></a></td>';
            htmocontent = htmocontent + '<td><a href="#" onclick="editcontent(' + selectedRow.id + ')" >Edit</a></td>';
            htmocontent = htmocontent + '<td><a href="#" onclick="deletecontent(' + selectedRow.id  +')">Delete</a></td>';
            htmocontent = htmocontent + '</tr>';
        }
        htmocontent = htmocontent + '</tbody></table></div>';
        $("#ctl00_cph_divWorkAreaContent").html(htmocontent);
    }
    function editcontent(id){
        window.location="edit_thoughts?id="+id;
    }
    function deletecontent(id) {
        swal({title: "Are you sure?", text: "You want to delete this thought!", type: "warning", showCancelButton: true, confirmButtonColor: "#DD6B55", confirmButtonText: "Yes!", closeOnConfirm: false}, function () {
            $.ajax({
                url: "delete_single_upload?id=" + id,
                type: "GET",
                dataType: "json",
                contentType: "application/json",
                success: function (data)
                {
                    code = data.response.code;
                    if (code == 0) {
                        swal({title: "Success", text: "Thoughts Deleted Successfully", imageUrl: "resources/images/thumbs-up.jpg"}, function (isConfirm) {
                            if (isConfirm) {
                                window.location = 'list_thoughts';
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