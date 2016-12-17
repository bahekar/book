<%@include file="header.jsp" %>   
<div class="content">
    <div class="header">
        <h1 class="page-title">List Ask Question</h1>
    </div>
    <ul class="breadcrumb">
        <li><a href="home">Dashboard</a> <span class="divider">/</span></li>
        <li class="active">List Ask Question</li>
    </ul>
    <div class="container-fluid">
        <div class="row-fluid">
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
            url: 'ask_list',
            onLoadSuccess: checkchildserviceload
        });
    }
    function checkchildserviceload() {
        length = $('#tt').datagrid('getRows').length;
        htmocontent = '<div class="well"><table class="table"><thead><tr>';
        htmocontent = htmocontent + '<th>#</th>';
        htmocontent = htmocontent + '<th>Mobile</th>';
        htmocontent = htmocontent + '<th>Question</th>';
        htmocontent = htmocontent + '<th>Answer</th>';
        htmocontent = htmocontent + '<th>Edit</th>';
        htmocontent = htmocontent + '<th>View</th>';
        htmocontent = htmocontent + '</tr></thead><tbody>';
        for (i = 0; i < length; i++) {
            var selectedRow = $('#tt').datagrid('getRows')[i];
            htmocontent = htmocontent + '<tr>';
            htmocontent = htmocontent + '<td>' + selectedRow.id + '</td>';
            htmocontent = htmocontent + '<td>' + selectedRow.mobile + '</td>';
            htmocontent = htmocontent + '<td>' + selectedRow.question + '</td>';
            htmocontent = htmocontent + '<td>' + selectedRow.answer + '</td>';
            htmocontent = htmocontent + '<td><a href="#" onclick="editcontent(' + selectedRow.id + ')" >Edit</a></td>';
            htmocontent = htmocontent + '<td><a href="#" onclick="viewcontent(' + selectedRow.id  +')">View</a></td>';
            htmocontent = htmocontent + '</tr>';
        }
        htmocontent = htmocontent + '</tbody></table></div>';
        $("#ctl00_cph_divWorkAreaContent").html(htmocontent);
    }
    function editcontent(id){
        window.location="edit_ask?id="+id;
    }
    function viewcontent(id){
        window.location="view_ask?id="+id;
    }
</script>
</body>
</html>