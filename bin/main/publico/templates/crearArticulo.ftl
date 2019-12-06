<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <#if editable != "si">
        <link rel="icon" href="../assets/img/favicon.ico">
        <!-- Bootstrap core CSS -->
        <link href="assets/css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom styles for this template -->
        <link href="assets/css/mediumish.css" rel="stylesheet">
    </#if>
    <#if editable == "si">
        <link rel="icon" href="../assets/img/favicon.ico">
        <!-- Bootstrap core CSS -->
        <link href="../assets/css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom styles for this template -->
        <link href="../assets/css/mediumish.css" rel="stylesheet">
    </#if>
    <title>Mediumish</title>
    <!-- Fonts -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Righteous%7CMerriweather:300,300i,400,400i,700,700i" rel="stylesheet">
    <!-- Textarea -->
    <script src="https://cdn.tiny.cloud/1/no-api-key/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.11/css/select2.min.css" rel="stylesheet" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.11/js/select2.min.js"></script>
    <script>tinymce.init(
        {
            selector:'textarea#default',
            height: 350,
            menubar: false // removes the menubar
        });
    </script>
</head>
<body>

<!-- Begin Nav
================================================== -->
<#include "*/navBar.ftl">
<!-- End Nav
================================================== -->
<div class="container">
    <div class="row justify-content-center">
        <div class="col">
            <#if editable != "si">
                <form method="post" action="/postArticulo/">
                    <div class="row">
                        <div class="col form-group">
                            <label for="title">Title</label>
                            <input id="titulo" name="titulo" class="form-control" placeholder="Title" type="text">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <label for="default">Content</label>
                            <textarea id="default" name="cuerpo">Write your Post here!</textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <br/>
                        <label for="etiquetas">Tags</label>
                        <input name="etiquetas" class="form-control" placeholder="Enter your tags here, separated by commas..." type="text">
                    </div>
                    <br/>
                    <div class="row align-items-center">
                        <div class="col">
                            <button type="submit" class="btn btn-primary">Post</button>
                            <a href="/home" class="btn btn-danger">Cancel</a>
                        </div>
                    </div>
                </form>
            </#if>
            <#if editable == "si">
                <form method="post" action="/editarPost/${articulo.id}">
                    <div class="row">
                        <div class="col form-group">
                            <label for="title">Titulo</label>
                            <input id="titulo" name="titulo" class="form-control" value="${articulo.titulo}" type="text">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <label for="default">Content</label>
                            <textarea id="default" name="cuerpo">${articulo.cuerpo}</textarea>
                        </div>
                    </div>
                    <#--    ESTE TEMA AMASTE               -->
                    <label for="etiquetas">Etiquetas</label>
                    <input name="etiquetas" class="form-control" value="${etiquetas}" type="text">

                    <br/>
                    <div class="row align-items-center">
                        <div class="col">
                            <button type="submit" class="btn btn-primary">Post</button>
                            <a href="/home" class="btn btn-danger">Cancel</a>
                        </div>
                    </div>
                </form>
            </#if>

        </div>
    </div>
</div>
<!-- Begin Footerx
================================================== -->
<div class="container">
    <div class="footer">
        <p class="pull-left">
            Copyright &copy; 2019 Your Website Name
        </p>
        <p class="pull-right">
            Mediumish Theme by <a target="_blank" href="https://www.wowthemes.net">WowThemes.net</a>
        </p>
        <div class="clearfix">
        </div>
    </div>
</div>
<!-- End Footer
================================================== -->

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<#if editable != "si">
    <script src="assets/js/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/ie10-viewport-bug-workaround.js"></script>
    <script src="assets/js/mediumish.js"></script>
</#if>
<#if editable == "si">
    <script src="../assets/js/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
    <script src="../assets/js/bootstrap.min.js"></script>
    <script src="../assets/js/ie10-viewport-bug-workaround.js"></script>
    <script src="../assets/js/mediumish.js"></script>
</#if>
</body>
</html>
