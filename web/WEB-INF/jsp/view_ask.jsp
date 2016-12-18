<%@include file="header.jsp" %>   
<div class="content">
    <div class="header">
        <h1 class="page-title">View Ask Me</h1>
    </div>
    <ul class="breadcrumb">
        <li><a href="home">Dashboard</a> <span class="divider">/</span></li>
        <li class="active">View Ask Me</li>
    </ul>
    <div class="container-fluid">
        <div class="row-fluid">
            <br>
            <div id="result"></div>
<%@include file="footer.jsp" %> 
<script type="text/javascript">
    $(document).ready(function () {
        childserviceload();
    });
    function childserviceload() {
        id = getParameterByName("id");
             $.ajax({
            url: "getasklist?id=" + id,
            type: "GET",
            dataType: "json",
            async: false,
            contentType: "application/json",
            success: function (data)
            {
                code = data.rows;
                //console.log(code[0]);
                var str = '';
                $('#result').html('');
                var code2 = code[0];
                function myself(code2) {
                    if(code2){
                        console.log(code2.id);
                        str = '<ul class="rec"><li>'+code2.question+'</li><li>'+code2.answer+'</li>';
                        $('#result').append(str);
                    }else{
                        return 1;
                    }
                    myself(code2.cq);
                }
                myself(code2);
                str = '</ul>';
                $('#result').append(str);
                
                for(var x=1; x<=100; x++){
                    var y = x * 20 + "px";
                    $("ul.rec:nth-of-type("+x+")").css("margin-left", y);
                }
            }
        });
    }
    
</script>
</body>
</html>