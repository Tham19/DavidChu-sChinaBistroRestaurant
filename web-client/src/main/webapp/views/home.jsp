<%@taglib prefix="sec"
          uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Kids School a Education School Category Flat Bootstrap
        Responsive Website Template | Gallery :: w3layouts</title>
    <!-- for-mobile-apps -->
    <script type="application/x-javascript">

        addEventListener("load", function () {
            setTimeout(hideURLbar, 0);
        }, false);
        function hideURLbar() {
            window.scrollTo(0, 1);
        }

    </script>

</head>

<body>

<%--try--%>
<sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
    <!-- For login user -->
    <c:url value="/logout" var="logoutUrl"/>
    <form action="${logoutUrl}" method="post" id="logoutForm">
        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
    </form>
    <script>
        function formSubmit() {
            document.getElementById("logoutForm").submit();
        }
    </script>

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <h2>
            User : ${pageContext.request.userPrincipal.name} | <a
                href="javascript:formSubmit()"> Logout</a>
        </h2>
    </c:if>

</sec:authorize>
<%--try--%>

<!-- body -->
<div class="body-content">
    <div class="container">
        <div class="body-content1">
            <!-- header -->
            <div class="logo-search">
                <div class="logo">
                    <h1>
                        <a href="index.html">k<span class="color">i</span><span
                                class="color1">d</span><span class="color2">s</span> <span>school</span>
                            <i>Good Start To Your Child</i></a>
                    </h1>
                </div>
                <div class="search">
                    <form>
                        <input type="text" value="Search Here..."
                               onfocus="this.value = '';"
                               onblur="if (this.value == '') {this.value = 'Search Here...';}"
                               required=""> <input type="submit" value=" ">
                    </form>
                </div>
                <div class="clearfix"></div>
            </div>
            <!-- //header -->
            <!-- nav -->
            <div class="navigation">
                <nav class="navbar navbar-default">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed"
                                data-toggle="collapse"
                                data-target="#bs-example-navbar-collapse-1">
                            <span class="sr-only">Toggle navigation</span> <span
                                class="icon-bar"></span> <span class="icon-bar"></span> <span
                                class="icon-bar"></span>
                        </button>
                    </div>

                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse nav-wil"
                         id="bs-example-navbar-collapse-1">
                        <nav class="stroke">
                            <ul class="nav navbar-nav">
                                <li><a href="home" class="hvr-rectangle-out">Home</a></li>
                                <li><a href="services" class="hvr-rectangle-out">Services</a></li>
                                <li><a href="events.html" class="hvr-rectangle-out">News
                                    & Events</a></li>
                                <li><a href="short-codes.html" class="hvr-rectangle-out">Short
                                    Codes</a></li>
                                <li class="active"><a href="gallery.html">Gallery</a></li>
                                <li><a href="mail.html" class="hvr-rectangle-out">Mail
                                    Us</a></li>
                            </ul>
                        </nav>
                    </div>
                    <!-- /.navbar-collapse -->
                </nav>
            </div>
            <!-- //nav -->
            <!-- banner -->
            <div class="banner">
                <div class="banner-grids">
                    <div class="banner-left">
                        <div class="banner-left1">
                            <div class="banner-left1-grid">
                                <img src="../resources/images/1.jpg" alt=" " class="img-responsive"/>
                                <div class="banner-info">
                                    <a class="read-more" href="single.html"><i></i></a>
                                    <h3>
                                        <a href="single.html"> Kids Playing </a>
                                    </h3>
                                    <div class="event-meta">
                                        <h4>Description</h4>
                                        <p>Sed ut perspiciatis unde omnis iste natus error sit
                                            voluptatem</p>
                                    </div>
                                </div>
                            </div>
                            <div class="banner-left1-grid">
                                <img src="../resources/images/4.jpg" alt=" " class="img-responsive"/>
                                <div class="banner-info">
                                    <a class="read-more" href="single.html"><i></i></a>
                                    <h3>
                                        <a href="single.html"> Kids Playing </a>
                                    </h3>
                                    <div class="event-meta">
                                        <h4>Description</h4>
                                        <p>Sed ut perspiciatis unde omnis iste natus error sit
                                            voluptatem</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="banner-left2">
                            <div class="banner-left1-grid">
                                <img src="../resources/images/3.jpg" alt=" " class="img-responsive"/>
                                <div class="banner-info">
                                    <a class="read-more" href="single.html"><i></i></a>
                                    <h3>
                                        <a href="single.html"> Kids Playing </a>
                                    </h3>
                                    <div class="event-meta">
                                        <h4>Description</h4>
                                        <p>Sed ut perspiciatis unde omnis iste natus error sit
                                            voluptatem</p>
                                    </div>
                                </div>
                            </div>
                            <div class="banner-left1-grid">
                                <img src="../resources/images/2.jpg" alt=" " class="img-responsive"/>
                                <div class="banner-info">
                                    <a class="read-more" href="single.html"><i></i></a>
                                    <h3>
                                        <a href="single.html"> Kids studying </a>
                                    </h3>
                                    <div class="event-meta">
                                        <h4>Description</h4>
                                        <p>Sed ut perspiciatis unde omnis iste natus error sit
                                            voluptatem</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                    <div class="banner-right">
                        <section class="slider">
                            <div class="flexslider">
                                <ul class="slides">
                                    <li>
                                        <div class="services-grid-right-grid1"></div>
                                    </li>
                                    <li>
                                        <div class="services-grid-right-grid2"></div>
                                    </li>
                                    <li>
                                        <div class="services-grid-right-grid3"></div>
                                    </li>
                                </ul>
                            </div>
                        </section>
                        <!--FlexSlider-->
                        <script defer src="../resources/js/jquery.flexslider.js"></script>
                        <script type="text/javascript">
                            $(window).load(function () {
                                $('.flexslider').flexslider({
                                    animation: "slide",
                                    start: function (slider) {
                                        $('body').removeClass('loading');
                                    }
                                });
                            });
                        </script>
                        <!--End-slider-script-->
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
            <!-- //banner -->
            <!-- gallery -->
            <div class="gallery">
                <h2>Gallery</h2>
                <div class="gallery-grids">
                    <section>
                        <ul id="da-thumbs" class="da-thumbs">
                            <li><a href="../resources/images/8.jpg" rel="title"
                                   class="b-link-stripe b-animate-go  thickbox"> <img
                                    src="../resources/images/8.jpg" alt=""/>
                                <div>
                                    <h5>Kids School</h5>
                                    <span>non suscipit leo fringilla non suscipit leo
												fringilla molestie</span>
                                </div>
                            </a></li>
                            <li><a href="../resources/images/9.jpg" rel="title"
                                   class="b-link-stripe b-animate-go  thickbox"> <img
                                    src="../resources/images/9.jpg" alt=""/>
                                <div>
                                    <h5>Kids School</h5>
                                    <span>non suscipit leo fringilla non suscipit leo
												fringilla molestie</span>
                                </div>
                            </a></li>
                            <li><a href="../resources/images/10.jpg" rel="title"
                                   class="b-link-stripe b-animate-go  thickbox"> <img
                                    src="../resources/images/10.jpg" alt=""/>
                                <div>
                                    <h5>Kids School</h5>
                                    <span>non suscipit leo fringilla non suscipit leo
												fringilla molestie</span>
                                </div>
                            </a></li>
                            <li><a href="../resources/images/11.jpg" rel="title"
                                   class="b-link-stripe b-animate-go  thickbox"> <img
                                    src="../resources/images/11.jpg" alt=""/>
                                <div>
                                    <h5>Kids School</h5>
                                    <span>non suscipit leo fringilla non suscipit leo
												fringilla molestie</span>
                                </div>
                            </a></li>
                            <li><a href="../resources/images/12.jpg" rel="title"
                                   class="b-link-stripe b-animate-go  thickbox"> <img
                                    src="../resources/images/12.jpg" alt=""/>
                                <div>
                                    <h5>Kids School</h5>
                                    <span>non suscipit leo fringilla non suscipit leo
												fringilla molestie</span>
                                </div>
                            </a></li>
                            <li><a href="../resources/images/8.jpg" rel="title"
                                   class="b-link-stripe b-animate-go  thickbox"> <img
                                    src="../resources/images/8.jpg" alt=""/>
                                <div>
                                    <h5>Kids School</h5>
                                    <span>non suscipit leo fringilla non suscipit leo
												fringilla molestie</span>
                                </div>
                            </a></li>
                            <li><a href="../resources/images/9.jpg" rel="title"
                                   class="b-link-stripe b-animate-go  thickbox"> <img
                                    src="../resources/images/9.jpg" alt=""/>
                                <div>
                                    <h5>Kids School</h5>
                                    <span>non suscipit leo fringilla non suscipit leo
												fringilla molestie</span>
                                </div>
                            </a></li>
                            <li><a href="../resources/images/10.jpg" rel="title"
                                   class="b-link-stripe b-animate-go  thickbox"> <img
                                    src="../resources/images/10.jpg" alt=""/>
                                <div>
                                    <h5>Kids School</h5>
                                    <span>non suscipit leo fringilla non suscipit leo
												fringilla molestie</span>
                                </div>
                            </a></li>
                            <li><a href="../resources/images/11.jpg" rel="title"
                                   class="b-link-stripe b-animate-go  thickbox"> <img
                                    src="../resources/images/11.jpg" alt=""/>
                                <div>
                                    <h5>Kids School</h5>
                                    <span>non suscipit leo fringilla non suscipit leo
												fringilla molestie</span>
                                </div>
                            </a></li>
                            <div class="clearfix"></div>
                        </ul>
                    </section>
                </div>
                <!-- pop-up-script -->
                <script src="../resources/js/jquery.chocolat.js"></script>
                <!--light-box-files -->
                <script type="text/javascript" charset="utf-8">
                    $(function () {
                        $('.gallery a').Chocolat();
                    });
                </script>
                <!-- //pop-up-script -->
                <script type="text/javascript" src="../resources/js/jquery.hoverdir.js"></script>
                <script type="text/javascript">
                    $(function () {

                        $(' #da-thumbs > li ').each(function () {
                            $(this).hoverdir();
                        });

                    });
                </script>
            </div>
            <!-- //gallery -->
        </div>
    </div>
</div>
<!-- //body -->
<!-- footer -->
<div class="footer">
    <div class="container">
        <h3>Sign up for our newsletter</h3>
        <p class="para">Lorem ipsum dolor sit amet, consectetur
            adipiscing elit. Praesent vitae eros eget tellus tristique bibendum.
            Donec rutrum sed sem quis venenatis.</p>
        <div class="footer-contact">
            <form>
                <input type="text" placeholder="Enter your email to update"
                       required=" "> <input type="submit" value="">
            </form>
        </div>
        <div class="footer-grids">
            <div class="col-md-3 footer-grid">
                <p>
                    HALOVIETNAM LTD 66, Dang Van ngu, Dong Da Hanoi, USA. <a
                        href="mailto:info@example.com">contact@example.com</a>
                <p>Phone : +844 35149182</p>
            </div>
            <div class="col-md-3 footer-grid">
                <ul>
                    <li><a href="mail.html">Contact</a></li>
                    <li><a href="gallery.html">Gallery</a></li>
                    <li><a href="short-codes.html">Short Codes</a></li>
                </ul>
            </div>
            <div class="col-md-3 footer-grid">
                <ul>
                    <li><a href="events.html">News & Events</a></li>
                    <li><a href="services.html">Services</a></li>
                    <li><a href="index.html">Home</a></li>
                </ul>
            </div>
            <div class="col-md-3 footer-grid">
                <div class="footer-grid-left">
                    <a href="single.html"><img src="../resources/images/10.jpg"
                                               alt=" " class="img-responsve"/></a>
                </div>
                <div class="footer-grid-left">
                    <a href="single.html"><img src="../resources/images/12.jpg"
                                               alt=" " class="img-responsve"/></a>
                </div>
                <div class="footer-grid-left">
                    <a href="single.html"><img src="../resources/images/11.jpg"
                                               alt=" " class="img-responsve"/></a>
                </div>
                <div class="footer-grid-left">
                    <a href="single.html"><img src="../resources/images/10.jpg"
                                               alt=" " class="img-responsve"/></a>
                </div>
                <div class="footer-grid-left">
                    <a href="single.html"><img src="../resources/images/8.jpg" alt=" "
                                               class="img-responsve"/></a>
                </div>
                <div class="footer-grid-left">
                    <a href="single.html"><img src="../resources/images/9.jpg" alt=" "
                                               class="img-responsve"/></a>
                </div>
                <div class="clearfix"></div>
            </div>
            <div class="clearfix"></div>
        </div>
        <div class="footer-copy">
            <p>
                &copy 2016 Kids School. All rights reserved | Design by <a
                    href="http://w3layouts.com">W3layouts.</a>
            </p>
            <ul>
                <li><a href="#" class="twitter"><span type="button"
                                                      class="btn btn-default" data-toggle="tooltip"
                                                      data-placement="bottom" title="Follow Us On Twitter"></span></a>
                </li>
                <li><a href="#" class="p"><span type="button"
                                                class="btn btn-default" data-toggle="tooltip"
                                                data-placement="bottom" title="Follow Us On Pinterest"></span></a></li>
                <li><a href="#" class="facebook" id="facebook"><span
                        type="button" class="btn btn-default" data-toggle="tooltip"
                        data-placement="bottom" title="Follow Us On Facebook"></span></a></li>
                <li><a href="#" class="dribble"><span type="button"
                                                      class="btn btn-default" data-toggle="tooltip"
                                                      data-placement="bottom" title="Follow Us On Dribbble"></span></a>
                </li>
                <li><a href="#" class="rss"><span type="button"
                                                  class="btn btn-default" data-toggle="tooltip"
                                                  data-placement="bottom" title="Follow Us On RSS"></span></a></li>
                <div class="clearfix"></div>
            </ul>
            <script>
                $(function () {
                    $('[data-toggle="tooltip"]').tooltip()
                })
            </script>
        </div>
    </div>
</div>
<!-- //footer -->
<!-- here stars scrolling icon -->
<script type="text/javascript">
    $(document).ready(function () {
        /*
         var defaults = {
         containerID: 'toTop', // fading element id
         containerHoverID: 'toTopHover', // fading element hover id
         scrollSpeed: 1200,
         easingType: 'linear'
         };
         */

        $().UItoTop({
            easingType: 'easeOutQuart'
        });

    });
</script>
<!-- //here ends scrolling icon -->
<!-- for bootstrap working -->
<script src="../resources/js/bootstrap.js"></script>
<!-- //for bootstrap working -->
</body>
</html>