<!DOCTYPE html>
<html lang="en">
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ page  isELIgnored="false" %> 
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
  <head>
    <meta charset="utf-8">
    <title>Admin</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" type="text/css" href="resources/lib/bootstrap/css/bootstrap.css">
    
    <link rel="stylesheet" type="text/css" href="resources/stylesheets/theme.css">
    <link rel="stylesheet" href="resources/lib/font-awesome/css/font-awesome.css">
    
    <script src="resources/lib/jquery-1.7.2.min.js" type="text/javascript"></script>
    
        <link rel="stylesheet" type="text/css" href="resources/css/easyui.css">
        <script type="text/javascript" src="resources/js/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="resources/js/common.js"></script>
        <script src="resources/js/sweetalert-dev.js"></script>
        <link rel="stylesheet" type="text/css" href="resources/stylesheets/sweetalert.css">
    <!-- Demo page code -->
    <style type="text/css">
        #line-chart {height:300px; width:800px; margin: 0px auto; margin-top: 1em;}
        .brand { font-family: georgia, serif; }
        .brand .first { color: #ccc; font-style: italic; }
        .brand .second {color: #fff; font-weight: bold; }
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
                            <li><a tabindex="-1" href="/bookmanagement">Logout</a></li>
                        </ul>
                    </li>
                    
                </ul>
               <a class="brand" href="home"><span class="logo-font">Book Admin</span></a>
        </div>
    </div>
    
    <div class="sidebar-nav">
        <a href="home" class="nav-header" data-toggle="collapse">Dashboard</a>
        <ul id="dashboard-menu" class="nav nav-list collapse in">
            <li><a href="category">Category Listing</a></li>
            <li><a href="sub_category">Sub-Category Listing</a></li>
            <li><a href="list_book">Book Listing</a></li>
            <li><a href="list_faq">FAQ Listing</a></li>
            <li><a href="list_magazine">Magazine Listing</a></li>
            <li><a href="list_thoughts">Thoughts Listing</a></li>
            <li><a href="list_audio">Audio Listing</a></li>
            <li><a href="list_video">Video Listing</a></li>
            <li><a href="list_ferozan">Sham-E-Ferozan</a></li>
        </ul>
    </div>
  <script type="text/javascript">
            function getParameterByName(name) {
                //alert("deh");
                name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
                var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
                        results = regex.exec(location.search);
                return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
            }
            $.ajaxSetup({async: false});
        </script>