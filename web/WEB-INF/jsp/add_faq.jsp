<%@include file="header.jsp" %>  
<style>
.input-xlarge{width: 500px;}
</style>
    <div class="content">
        <div class="header">
            <h1 class="page-title">Add FAQ</h1>
        </div>
        <ul class="breadcrumb">
            <li><a href="home">Home</a> <span class="divider">/</span></li>
            <li><a href="list_faq">FAQ</a> <span class="divider">/</span></li>
            <li class="active">Add FAQ</li>
        </ul>
        <div class="container-fluid">
            <div class="row-fluid">           
                <br><br><br>
                <div class="well">
                        <label>Question</label>
                        <input type="text" placeholder="Question" id="question" name="question" class="input-xlarge"><br>
                        <div id="question_err" class="error-message-input">*Required</div>
                        <br>
                        <label>Answer</label>
                        <textarea placeholder="Answer" id="answer" name="answer" class="input-xlarge"></textarea><br>
                        <div id="answer_err" class="error-message-input">*Required</div>
                        <button id="categorysave" class="btn btn-primary btn-sign-in"><i class="icon-save"></i> Save</button>
                </div>
<script type="text/javascript">
    $("#categorysave").click(function () {
        var loginjson = {};
        question = $("#question").val();
        answer = $("#answer").val();
        iserror = false;
        if (question == '') {
            addclass('question');
            iserror = true;
        } else {
            removeclass('question');
        }
        if (answer == '') {
            addclass('answer');
            iserror = true;
        } else {
            removeclass('answer');
        }
        if (iserror) {
            return false;
        }
        $.ajax({
            url: "faqsave?question=" + question + "&answer=" + answer,
            type: "GET",
            dataType: "json",
            async: false,
            contentType: "application/json",
            success: function (data)
            {
                code = data.response.code;
                if (code == 0) {
                    swal({title: "Success", text: "FAQ Added Successfully", imageUrl: "resources/images/thumbs-up.jpg"}, function (isConfirm) {
                        if (isConfirm) {
                            window.location = 'list_faq';
                        }
                    });
                } else if (code == 108) {
                    sweetAlert('Oops...', 'Invalid FAQ!', 'error');
                } else if (code == 109) {
                    sweetAlert('Oops...', 'Invalid FAQ!', 'error');
                } else {
                    sweetAlert('Oops...', 'Something went wrong!', 'error');
                }
            }
        });

    });
</script>
<%@include file="footer.jsp" %> 