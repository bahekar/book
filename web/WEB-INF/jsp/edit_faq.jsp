<%@include file="header.jsp" %>   
    <div class="content">
        <div class="header">
            <h1 class="page-title">Edit FAQ</h1>
        </div>
        <ul class="breadcrumb">
            <li><a href="home">Home</a> <span class="divider">/</span></li>
            <li><a href="list_faq">FAQ</a> <span class="divider">/</span></li>
            <li class="active">Edit FAQ</li>
        </ul>
        <div class="container-fluid">
            <div class="row-fluid">
            <br><br><br>
            <div class="well">
                        <label>Question</label>
                        <input type="text" placeholder="Question" id="question" name="question" value="${question}" class="input-xlarge"><br>
                        <div id="question_err" class="error-message-input">*Required</div>
                        <br>
                        <label>Answer</label>
                        <textarea placeholder="Answer" id="answer" name="answer" class="input-xlarge">${answer}</textarea><br>
                        <div id="answer_err" class="error-message-input">*Required</div>
                        <button id="categorysave" class="btn btn-primary btn-sign-in"><i class="icon-save"></i> Update</button>
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
        id = getParameterByName("id");
        if (iserror) {
            return false;
        }
        $.ajax({
            url: "faqupdate?question=" + question + "&answer=" + answer + "&id=" + id,
            type: "GET",
            dataType: "json",
             async: true,
            contentType: "application/json",
            success: function (data)
            {
                code = data.response.code;
                if (code == 0) {
                    swal({title: "Success", text: "FAQ Updated Successfully", imageUrl: "resources/images/thumbs-up.jpg"}, function (isConfirm) {
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
            }, error: function (request, status, error) {
                sweetAlert("Update Failed...", "FAQ Failed", "error");
            }
        });

    });
    $("[rel=tooltip]").tooltip();
    $(function () {
        $('.demo-cancel-click').click(function () {
            return false;
        });
    });
</script>
<%@include file="footer.jsp" %> 