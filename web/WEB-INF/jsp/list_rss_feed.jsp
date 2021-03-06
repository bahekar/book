﻿<%@include file="header.jsp" %>   
<div class="content">
    <div class="header">
        <h1 class="page-title">RSS Feed List</h1>
    </div>
    <ul class="breadcrumb">
        <li><a href="category">Dashboard</a> <span class="divider">/</span></li>
        <li class="active">RSS Feed List</li>
    </ul>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="btn-toolbar">
                <a href="add_rss_feed" class="btn btn-primary btn-sign-in"><i class="icon-plus"></i> Add RSS Feed</a>
                <select style="margin-left: 10px;" id="sub_category_id" name="sub_category_id">
                            <option value="">Select Category</option>
			</select>
            </div>
                                    
            <div id="ctl00_cph_divWorkAreaContent" style="overflow: hidden;" ></div>
            <div id="tt" style="width:auto;height:0!important"
                 title="DataGrid - CardView" 
                 showFooter="false" pagination="true" data-options="singleSelect:true,page:'',url:'mortagesettinglist',method:'get',pageList: [100,200,300],pageSize: 100,layout:['list']">
            </div>   
<%@include file="footer.jsp" %> 
<script type="text/javascript">
    $(document).ready(function () {
        childserviceload("");
    });
        get_sub_category();
    function get_sub_category()
    {
        $.ajax({
            url: "getcategorylist",
            type: "GET",
            dataType: "json",
            async: false,
            contentType: "application/json",
            success: function (response)
            {
                response = response.response.propertyTypes;
                $('#sub_category_id').html('<option value="">Select Category</option>');
                $.each(response, function (idx, rec) {
                    $('<option/>', {
                        'value': rec.id,
                        'text': rec.name
                    }).appendTo('#sub_category_id');
                });
            }
        });
    }
   $("#sub_category_id").change(function () {
        var catid = this.value;
        childserviceload(catid);
        
    });
    function childserviceload(catid) {
        $('#tt').datagrid({
            url: 'get_list_rss_feed?catid='+catid,
            onLoadSuccess: checkchildserviceload
        });
    }
    function checkchildserviceload() {
        length = $('#tt').datagrid('getRows').length;
        htmocontent = '<div class="well"><table class="table"><thead><tr>';
        htmocontent = htmocontent + '<th>ID</th>';
        htmocontent = htmocontent + '<th>Description</th>';
        htmocontent = htmocontent + '<th>Pub Date</th>';
        htmocontent = htmocontent + '<th>Status</th>';
        htmocontent = htmocontent + '<th>Category</th>';
        htmocontent = htmocontent + '<th>Edit</th>';
        htmocontent = htmocontent + '<th>Delete</th>';
        htmocontent = htmocontent + '</tr></thead><tbody>';
        for (i = 0; i < length; i++) {
            var selectedRow = $('#tt').datagrid('getRows')[i];
            htmocontent = htmocontent + '<tr>';
            htmocontent = htmocontent + '<td>' + selectedRow.id + '</td>';
            htmocontent = htmocontent + '<td>' + selectedRow.description + '</td>';
            htmocontent = htmocontent + '<td>' + selectedRow.createdOn + '</td>';
            htmocontent = htmocontent + '<td>True</td>';
            htmocontent = htmocontent + '<td>' + selectedRow.category + '</td>';
            htmocontent = htmocontent + '<td><a href="#" onclick="editcontent(' + selectedRow.id + ')" >Edit</a></td>';
            htmocontent = htmocontent + '<td><a href="#" onclick="deletecontent(' + selectedRow.id  +')">Delete</a></td>';
            htmocontent = htmocontent + '</tr>';
        }
        htmocontent = htmocontent + '</tbody></table></div>';
        $("#ctl00_cph_divWorkAreaContent").html(htmocontent);
    }
    function editcontent(id){
        window.location="edit_rss_content?id="+id;
    }
    function deletecontent(id) {
        swal({title: "Are you sure?", text: "You want to Delete this record!", type: "warning", showCancelButton: true, confirmButtonColor: "#DD6B55", confirmButtonText: "Yes!", closeOnConfirm: false}, function () {
            $.ajax({
                url: "delete_rss_feed?id=" + id,
                type: "GET",
                dataType: "json",
                contentType: "application/json",
                success: function (data)
                {
                    code = data.response.code;
                    if (code == 0) {
                        swal({title: "Success", text: "Record Deleted Successfully", imageUrl: "resources/images/thumbs-up.jpg"}, function (isConfirm) {
                            if (isConfirm) {
                                window.location = 'list_rss_feed';
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