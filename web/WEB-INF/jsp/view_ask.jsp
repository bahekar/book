<%@include file="header.jsp" %>   
<div class="content">
    <div class="header">
        <h1 class="page-title">List Audio</h1>
    </div>
    <ul class="breadcrumb">
        <li><a href="home">Dashboard</a> <span class="divider">/</span></li>
        <li class="active">List Audio</li>
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
    $(document).ready(function () {
        childserviceload();
    });
    function childserviceload() {
        id = getParameterByName("id");
        
        $.get('getasklist', {id:id}, function(data){
            console.log(data);
        });
    }
    
</script>
</body>
</html>