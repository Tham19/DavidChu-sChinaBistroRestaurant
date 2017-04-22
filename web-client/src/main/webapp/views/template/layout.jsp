<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<html>
<head>
    <title>Template</title>
    <!-- //for-mobile-apps -->
    <link href="/resources/css/bootstrap/bootstrap.css" rel="stylesheet" type="text/css"
          media="all"/>
    <link href="/resources/css/style.css" rel="stylesheet" type="text/css"
          media="all"/>
    <!-- js -->
    <link rel="stylesheet" href="/resources/css/flexslider.css"
          type="text/css" media="screen"/>
    <script src="/resources/js/jquery-1.11.1.min.js"></script>
    <!-- //js -->
    <link rel="stylesheet" href="/resources/css/chocolat.css" type="text/css"
          media="screen" charset="utf-8">
    <!-- for-gallery-rotation -->
    <script src="/resources/js/modernizr.custom.97074.js"></script>
    <!-- //for-gallery-rotation -->
    <link
            href='//fonts.googleapis.com/css?family=Cabin:400,400italic,500,500italic,600,600italic,700,700italic'
            rel='stylesheet' type='text/css'>
    <link href='//fonts.googleapis.com/css?family=Peralta' rel='stylesheet'
          type='text/css'>
    <!-- start-smoth-scrolling -->
    <script type="text/javascript" src="/resources/js/move-top.js"></script>
    <script type="text/javascript" src="/resources/js/easing.js"></script>
    <script type="text/javascript">
        jQuery(document).ready(function ($) {
            $(".scroll").click(function (event) {
                event.preventDefault();
                $('html,body').animate({
                    scrollTop: $(this.hash).offset().top
                }, 1000);
            });
        });
    </script>
    <!-- start-smoth-scrolling -->
</head>

<body ng-app="Application" ng-controller="Controller">
<table width="100%">
    <tr>
        <td colspan="2"><tiles:insertAttribute name="header"/></td>
    </tr>
    <tr>
        <td width="80%"><tiles:insertAttribute name="body"/></td>
    </tr>
    <tr>
        <td colspan="2"><tiles:insertAttribute name="footer"/></td>
    </tr>
</table>
</body>
</html>
