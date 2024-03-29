<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../assets/img/favicon.ico">
    <title>Mediumish - A Medium style template by WowThemes.net</title>
    <!-- Bootstrap core CSS -->
    <link href="../assets/css/bootstrap.min.css" rel="stylesheet">
    <!-- Fonts -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="../assets/css/mediumish.css" rel="stylesheet">
</head>
<body>

<!-- Begin Nav
================================================== -->
<#include "*/navBar.ftl">
<!-- End Nav
================================================== -->

<!-- Begin Top Author Page
================================================== -->
<div class="container justify-content-center">
    <div class="col-md-8">
        <div class="card text-center text-capitalize">
            <div class="card-header">
                <div class="row justify-content-center">
                    <h1>Información de Usuario</h1>
                </div>
            </div>
            <div class="card-body">
                <br/>
                <form>
                    <div class="form-group row justify-content-center">
                        <div class="form-inline">
                            <div class="col">
                                <label for="username">Username</label>
                            </div>
                            <div class="col">
                                <input id="username" name="username" type="text" value="${usuario.username}" class="form-control" disabled>
                            </div>
                        </div>
                    </div>
                    <div class="form-group row justify-content-center">
                        <div class="form-inline">
                            <div class="col">
                                <label for="nombre">Nombre</label>
                            </div>
                            <div class="col">
                                <input id="nombre" name="nombre" type="text" value="${usuario.nombre}" class="form-control" disabled>
                            </div>
                        </div>
                    </div>
                    <div class="form-group row justify-content-center">
                        <div class="col">
                            <div class="sociallinks">
                                <a  target="_blank" href="https://www.facebook.com/wowthemesnet/"><i class="fa fa-facebook"></i></a>
                                <span class="dot"></span>
                                <a target="_blank" href="https://plus.google.com/s/wowthemesnet/top">
                                    <i class="fa fa-google-plus"></i>
                                </a>
                            </div>
                        </div>
                    </div>
<#--                    <div class="row">-->
<#--                        <a target="_blank" href="https://twitter.com/wowthemesnet" class="btn follow">Follow</a>-->
<#--                    </div>-->
                </form>
            </div>
        </div>
    </div>
</div>
<!-- End Top Author Meta
================================================== -->

<#--<!-- Begin Author Posts-->
<#--================================================== &ndash;&gt;-->
<#--<div class="graybg authorpage">-->
<#--    <div class="container">-->
<#--        <div class="listrecent listrelated">-->
<#--        <#list articulos as art>-->
<#--            <!-- begin post &ndash;&gt;-->
<#--            <div class="authorpostbox">-->
<#--                <div class="card">-->
<#--                    <a href="author.html">-->
<#--                        <img class="img-fluid img-thumb" src="assets/img/demopic/8.jpg" alt="">-->
<#--                    </a>-->
<#--                    <div class="card-block">-->
<#--                        <h2 class="card-title"><a href="post.html">Life is worth living forever and ever</a></h2>-->
<#--                        <h4 class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</h4>-->
<#--                        <div class="metafooter">-->
<#--                            <div class="wrapfooter">-->
<#--									<span class="meta-footer-thumb">-->
<#--									<a href="author.html"><img class="author-thumb" src="https://www.gravatar.com/avatar/e56154546cf4be74e393c62d1ae9f9d4?s=250&amp;d=mm&amp;r=x" alt="Sal"></a>-->
<#--									</span>-->
<#--                                <span class="author-meta">-->
<#--									<span class="post-name"><a href="author.html">Sal</a></span><br/>-->
<#--									<span class="post-date">22 July 2017</span><span class="dot"></span><span class="post-read">6 min read</span>-->
<#--									</span>-->
<#--                                <span class="post-read-more"><a href="post.html" title="Read Story"><svg class="svgIcon-use" width="25" height="25" viewbox="0 0 25 25"><path d="M19 6c0-1.1-.9-2-2-2H8c-1.1 0-2 .9-2 2v14.66h.012c.01.103.045.204.12.285a.5.5 0 0 0 .706.03L12.5 16.85l5.662 4.126a.508.508 0 0 0 .708-.03.5.5 0 0 0 .118-.285H19V6zm-6.838 9.97L7 19.636V6c0-.55.45-1 1-1h9c.55 0 1 .45 1 1v13.637l-5.162-3.668a.49.49 0 0 0-.676 0z" fill-rule="evenodd"></path></svg></a></span>-->
<#--                            </div>-->
<#--                        </div>-->
<#--                    </div>-->
<#--                </div>-->
<#--            </div>-->
<#--            <!-- end post &ndash;&gt;-->
<#--        </#list>-->
<#--            <!-- begin post &ndash;&gt;-->
<#--            <div class="authorpostbox">-->
<#--                <div class="card">-->
<#--                    <a href="author.html">-->
<#--                        <img class="img-fluid img-thumb" src="assets/img/demopic/10.jpg" alt="">-->
<#--                    </a>-->
<#--                    <div class="card-block">-->
<#--                        <h2 class="card-title"><a href="post.html">Best European capitals to visit and the costs implied</a></h2>-->
<#--                        <h4 class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</h4>-->
<#--                        <div class="metafooter">-->
<#--                            <div class="wrapfooter">-->
<#--		              <span class="meta-footer-thumb">-->
<#--		              <a href="author.html"><img class="author-thumb" src="https://www.gravatar.com/avatar/e56154546cf4be74e393c62d1ae9f9d4?s=250&amp;d=mm&amp;r=x" alt="Sal"></a>-->
<#--		              </span>-->
<#--                                <span class="author-meta">-->
<#--		              <span class="post-name"><a href="author.html">Sal</a></span><br/>-->
<#--		              <span class="post-date">22 July 2017</span><span class="dot"></span><span class="post-read">6 min read</span>-->
<#--		              </span>-->
<#--                                <span class="post-read-more"><a href="post.html" title="Read Story"><svg class="svgIcon-use" width="25" height="25" viewbox="0 0 25 25"><path d="M19 6c0-1.1-.9-2-2-2H8c-1.1 0-2 .9-2 2v14.66h.012c.01.103.045.204.12.285a.5.5 0 0 0 .706.03L12.5 16.85l5.662 4.126a.508.508 0 0 0 .708-.03.5.5 0 0 0 .118-.285H19V6zm-6.838 9.97L7 19.636V6c0-.55.45-1 1-1h9c.55 0 1 .45 1 1v13.637l-5.162-3.668a.49.49 0 0 0-.676 0z" fill-rule="evenodd"></path></svg></a></span>-->
<#--                            </div>-->
<#--                        </div>-->
<#--                    </div>-->
<#--                </div>-->
<#--            </div>-->
<#--            <!-- end post &ndash;&gt;-->

<#--            <!-- begin post &ndash;&gt;-->
<#--            <div class="authorpostbox">-->
<#--                <div class="card">-->
<#--                    <a href="author.html">-->
<#--                        <img class="img-fluid img-thumb" src="assets/img/demopic/9.jpg" alt="">-->
<#--                    </a>-->
<#--                    <div class="card-block">-->
<#--                        <h2 class="card-title"><a href="post.html">10 Things you should learn before visiting</a></h2>-->
<#--                        <h4 class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</h4>-->
<#--                        <div class="metafooter">-->
<#--                            <div class="wrapfooter">-->
<#--		              <span class="meta-footer-thumb">-->
<#--		              <a href="author.html"><img class="author-thumb" src="https://www.gravatar.com/avatar/e56154546cf4be74e393c62d1ae9f9d4?s=250&amp;d=mm&amp;r=x" alt="Sal"></a>-->
<#--		              </span>-->
<#--                                <span class="author-meta">-->
<#--		              <span class="post-name"><a href="author.html">Sal</a></span><br/>-->
<#--		              <span class="post-date">22 July 2017</span><span class="dot"></span><span class="post-read">6 min read</span>-->
<#--		              </span>-->
<#--                                <span class="post-read-more"><a href="post.html" title="Read Story"><svg class="svgIcon-use" width="25" height="25" viewbox="0 0 25 25"><path d="M19 6c0-1.1-.9-2-2-2H8c-1.1 0-2 .9-2 2v14.66h.012c.01.103.045.204.12.285a.5.5 0 0 0 .706.03L12.5 16.85l5.662 4.126a.508.508 0 0 0 .708-.03.5.5 0 0 0 .118-.285H19V6zm-6.838 9.97L7 19.636V6c0-.55.45-1 1-1h9c.55 0 1 .45 1 1v13.637l-5.162-3.668a.49.49 0 0 0-.676 0z" fill-rule="evenodd"></path></svg></a></span>-->
<#--                            </div>-->
<#--                        </div>-->
<#--                    </div>-->
<#--                </div>-->
<#--            </div>-->
<#--            <!-- end post &ndash;&gt;-->

<#--        </div>-->
<#--    </div>-->
<#--</div>-->
<#--<!-- End Author Posts-->
<#--================================================== &ndash;&gt;-->

<!-- Begin Twitter Timeline
================================================== -->

<!-- End Twitter Timeline
================================================== -->

<!-- Begin Footer
================================================== -->
<div class="container">
    <div class="footer">
        <p class="pull-left">
            Copyright &copy; 2017 Your Website Name
        </p>
        <p class="pull-right">
            Mediumish Theme by <a target="_blank" href="https://www.wowthemes.net">WowThemes.net</a>
        </p>
        <div class="clearfix"></div>
    </div>
</div>
<!-- End Footer
================================================== -->

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="../assets/js/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
<script src="../assets/js/bootstrap.min.js"></script>
<script src="../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
