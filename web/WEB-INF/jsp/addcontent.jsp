<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Admin</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="stylesheet" type="text/css" href="resources/lib/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="resources/stylesheets/theme.css">
    <link rel="stylesheet" href="resources/lib/font-awesome/css/font-awesome.css">
    <script src="resources/lib/jquery-1.7.2.min.js" type="text/javascript"></script>
    <!-- Demo page code -->
    <style type="text/css">
        #line-chart {
            height:300px;
            width:800px;
            margin: 0px auto;
            margin-top: 1em;
        }
        .brand { font-family: georgia, serif; }
        .brand .first {
            color: #ccc;
            font-style: italic;
        }
        .brand .second {
            color: #fff;
            font-weight: bold;
        }
    </style>
    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="../assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
	
  </head>
  <!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
  <!--[if IE 7 ]> <body class="ie ie7 "> <![endif]-->
  <!--[if IE 8 ]> <body class="ie ie8 "> <![endif]-->
  <!--[if IE 9 ]> <body class="ie ie9 "> <![endif]-->
  <!--[if (gt IE 9)|!(IE)]><!--> 
  <body class=""> 
  <!--<![endif]-->
    <div class="navbar">
        <div class="navbar-inner">
                <ul class="nav pull-right">
                    <li id="fat-menu" class="dropdown">
                        <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="icon-user"></i> Admin
                            <i class="icon-caret-down"></i>
                        </a>

                        <ul class="dropdown-menu">
                            <li><a tabindex="-1" href="#">My Account</a></li>
                            <li class="divider"></li>
                            <li><a tabindex="-1" class="visible-phone" href="#">Settings</a></li>
                            <li class="divider visible-phone"></li>
                            <li><a tabindex="-1" href="sign-in.html">Logout</a></li>
                        </ul>
                    </li>
                    
                </ul>
               <a class="brand" href="app-report.html"><span class="logo-font">Meenubook</span></a>
        </div>
    </div>
    
    <div class="sidebar-nav">
        <a href="#dashboard-menu" class="nav-header" data-toggle="collapse">Dashboard</a>
        <ul id="dashboard-menu" class="nav nav-list collapse in">
            <li><a href="app-report.html">App Report</a></li>
            <li ><a href="credit-report.html">Credit Report</a></li>
            <li ><a href="coupon-report.html">Coupon Report</a></li>
            <li ><a href="restaurant-call-report.html">Restaurant call Report</a></li>
            <!--<li ><a href="calendar.html">Calendar</a></li>-->
            
        </ul>
        <a href="#error-menu" class="nav-header" data-toggle="collapse">Restaurant <i class="icon-chevron-up"></i></a>
        <ul id="error-menu" class="nav nav-list">
            <li><a href="neighborhood.html">Neighborhood </a></li>
            <li ><a href="restaurant.html">Restaurant</a></li>
        </ul>
    </div>
    
    <div class="content">
        <div class="header">
            <h1 class="page-title">Add Content</h1>
        </div>
        <ul class="breadcrumb">
            <li><a href="home">Home</a> <span class="divider">/</span></li>
            <li><a href="">Add Content</a> <span class="divider">/</span></li>
        </ul>
        <div class="container-fluid">
            <div class="row-fluid">
				<br><br><br>
				<div class="well">
					<form id="tab">
					<div class="row-fluid">
						<div class="span6">
							<div class="span4">Content Name</div>
							<input type="text" id="contentname" placeholder="Content Name" class="input-xlarge">
						</div>
						<div class="span6">
							<div class="span4">Search Keywords</div>
							<input type="text" placeholder="Search Keywords" class="input-xlarge">
						</div>
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="span4">Content Type</div>
							<select>
								<option value="">Select Content Type</option>
								<option>Week</option>
								<option>Month</option>
							</select>
						</div>
						<div class="span6">
							<div class="span4">Push Type</div>
							<select>
								<option value="">Select Push Type</option>
								<option>Week</option>
								<option>Month</option>
							</select>
						</div>
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="span4">Category</div>
							<select>
								<option value="">Select Category</option>
								<option>Week</option>
								<option>Month</option>
							</select>
						</div>
						<div class="span6">
							<div class="span4">Sub Category</div>
							<select>
								<option value="">Select Sub Category</option>
								<option>Week</option>
								<option>Month</option>
							</select>
						</div>
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="span4">Description</div>
							<input type="text" placeholder="Description" class="input-xlarge">
						</div>
						<div class="span6">
							<div class="span4">Select CLA No.</div>
							<select>
								<option value="">Select CLA No.</option>
								<option>Week</option>
								<option>Month</option>
							</select>
						</div>
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="span4">Delivery Type</div>
							<select>
								<option value="">Select Delivery Type</option>
								<option>Week</option>
								<option>Month</option>
							</select>
							<br><i>*Note: Service wise counter will be maintained</i>
						</div>
						<div class="span6">
							<div class="span4">Updated By</div>
							<input type="text" placeholder="Updated By" class="input-xlarge">
						</div>
					</div>
					<div class="row-fluid">&nbsp;</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="span4">Title Display</div>
							<label><input type="checkbox"> Enabled</label>
						</div>
						<div class="span6">
							<div class="span4">Detailed Text</div>
							<label><input type="checkbox"> Enabled</label>
						</div>
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="span4">Expiry Date</div>
							<input type="text" placeholder="Expiry Date" id="datepicker" class="input-xlarge">
						</div>
						<div class="span6">
							<div class="span4">Max Characters</div>
							<input type="text" placeholder="Max Characters" class="input-xlarge">
						</div>
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="span4">Message Type</div>
							<select>
								<option>Text Message</option>
								<option>WAP Link</option>
							</select>
						</div>
						<div class="span6">
							<div class="span4">Status</div>
							<select>
								<option>Active</option>
								<option>In-active</option>
							</select>
						</div>
					</div>
					<button id="addcontent" class="btn btn-primary btn-sign-in"><i class="icon-save"></i> Save</button>
					</form>
				</div>
                <footer>
                    <hr>
                    <p>&nbsp;</p>
                </footer>
            </div>
        </div>
    </div>
    <script src="lib/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
    </script>
	<script type="text/javascript">
            $(function () {
                $('#datepicker').datepicker();
            });
        </script>
<script type="text/javascript">

            $("#addcontent").click(function () {

                var contentnamejson = {};
                iserror = false;
                contentname = $("#contentname").val();
    
                contentnamejson['contentname'] = contentname;
                       var content = JSON.stringify(contentnamejson);
                        $.ajax({
                            url: "addcontent",
                            type: "POST",
                            dataType: "json",
                            data:content,
                            async: true,
                            contentType: "application/json",
                            success: function (data)
                            {

                                code = data.response.code;
                                if (code == 0) {
                                    swal({title: "Success", text: "Content added Successfully", imageUrl: "resources/images/thumbs-up.jpg"}, function (isConfirm) {
                                        if (isConfirm) {
                                          //  res_id = getParameterByName("res_id");
                                          //  window.location = "addmenu?res_id=" + res_id;
                                        }
                                    });


                                } else if (code == 108) {
                                    sweetAlert('Oops...', 'Invalid UserId!', 'error');

                                } else if (code == 109) {
                                    sweetAlert('Oops...', 'Invalid Password!', 'error');

                                } else {
                                    sweetAlert('Oops...', 'Something went wrong!', 'error');
                                }
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

  </body>
</html>


