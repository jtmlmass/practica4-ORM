<nav class="navbar navbar-toggleable-md navbar-light bg-white fixed-top mediumnavigation">
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="container">
        <!-- Begin Logo -->
        <a class="navbar-brand" href="/home">
            <img src="assets/img/logo.png" alt="logo">
        </a>
        <!-- End Logo -->
        <div class="collapse navbar-collapse" id="navbarsExampleDefault">
            <!-- Begin Menu -->
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="/home">Stories <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/misPosts">My Posts</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/author">My Profile</a>
                </li>
            </ul>
            <!-- End Menu -->
            <!-- Begin Search -->
<#--            <form class="form-inline my-2 my-lg-0">-->
<#--                <input class="form-control mr-sm-2" type="text" placeholder="Search">-->
<#--                <span class="search-icon"><svg class="svgIcon-use" width="25" height="25" viewbox="0 0 25 25"><path d="M20.067 18.933l-4.157-4.157a6 6 0 1 0-.884.884l4.157 4.157a.624.624 0 1 0 .884-.884zM6.5 11c0-2.62 2.13-4.75 4.75-4.75S16 8.38 16 11s-2.13 4.75-4.75 4.75S6.5 13.62 6.5 11z"></path></svg></span>-->
<#--            </form>-->
            <!-- End Search -->
            <!-- User info -->
            <div class="btn-group">
                <#if usuario == "">
                    <a href="/login" class="float-right btn btn-outline-light">Sign in/Sign up</a>
                </#if>
                <#if usuario != "">
                    <div class="dropdown">
                        <a class="btn btn-secondary dropdown-toggle" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            ${usuario.username}
                        </a>
                        <div class="dropdown-menu">
                            <#if usuario.author || usuario.administrator>
                                <h6 class="dropdown-header">Authors</h6>
                                <a class="dropdown-item btn" href="#">Tags</a>
                                <div class="dropdown-divider"></div>
                            </#if>
                            <#if usuario.administrator>
                                <h6 class="dropdown-header">Administrators</h6>
                                <a class="dropdown-item btn" href="#">Users</a>
                                <div class="dropdown-divider"></div>
                            </#if>
                            <a class="dropdown-item btn" href="/hacerLogout">Log out</a>
                        </div>
                    </div>
                    <div class="btn-group">
                        <#if usuario.author || usuario.administrator>
                            <div class="btn-group">
                                <a class="topbtn btn-primary" href="/crearArticulo"><i class="fa fa-plus"></i></a>
                            </div>
                        </#if>
                    </div>
                </#if>
            </div>
        </div>
    </div>
</nav>