<!DOCTYPE html>
<html lang="es">
<head>
    <link href="/css/bootstrap.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
    <title>${titulo}</title>

</head>
<body>
<br/>
<br/>
<br/>
<div class="container">
    <div class="row justify-content-center">
        <aside class="col-lg-9">
            <div class="card shadow-lg">
                <div class="card-header">
                    <div class="row">
                        <div class="col">
                            <h2 class="card-title mb-4 mt-1"><strong>Sign up</strong></h2>
                        </div>
                        <div class="col justify-content-end">
                            <a href="/login" class="float-right btn btn-outline-danger">Cancel</a>
                        </div>
                    </div>
                </div>
                <article class="card-body">
                    <form action="/hacerRegister/" method="post"  enctype="application/x-www-form-urlencoded">
                        <div class="row">
                            <div class="col form-group">
                                <label>Username</label>
                                <input name="username" class="form-control" placeholder="Username" type="text">
                            </div> <!-- form-group// -->
                            <div class="col form-group">
                                <label>Name</label>
                                <input name="name" class="form-control" placeholder="Name" type="text">
                            </div> <!-- form-group// -->
                        </div>
                        <div class="row">
                            <div class="col form-group">
                                <label> Password</label>
                                <input name="password" class="form-control" placeholder="******" type="password">
                            </div> <!-- form-group// -->
                            <div class="col form-group">
                                <label> Re-type Password</label>
                                <input name="repassword" class="form-control" placeholder="******" type="password">
                            </div> <!-- form-group// -->
                        </div>
                        <div class="row justify-content-center">
                            <div class="col-auto my-1">
                                <div class="custom-control custom-checkbox mr-sm-2">
                                    <input name="isadmin" type="checkbox" class="custom-control-input" id="isadmin">
                                    <label class="custom-control-label" for="isadmin">Administrador</label>
                                </div>
                            </div>
                            <div class="col-auto my-1">
                                <div class="custom-control custom-checkbox mr-sm-2">
                                    <input name="isauthor" type="checkbox" class="custom-control-input" id="isauthor">
                                    <label class="custom-control-label" for="isauthor">Autor</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                        </div> <!-- form-group// -->
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary btn-block"> Register </button>
                        </div> <!-- form-group// -->
                    </form>
                </article>
            </div> <!-- card.// -->

        </aside> <!-- col.// -->

    </div> <!-- row.// -->
</div>
</body>
</html>
