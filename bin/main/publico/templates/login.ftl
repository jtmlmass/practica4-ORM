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
            <aside class="col-md-6">
                <div class="card shadow-lg">
                    <div class="card-header">
                        <div class="row">
                            <div class="col justify-content-md-start">
                                <h2 class="align-self-center"><strong>Sign in</strong></h2>
                            </div>
                            <div class="col justify-content-end">
                                <a href="/register" class="float-right btn btn-outline-primary">Sign up</a>
                            </div>
                        </div>
                    </div>
                    <article class="card-body">
                        <form action="/hacerLogin/" method="post"  enctype="application/x-www-form-urlencoded">
                            <div class="row">
                                <div class="col form-group">
                                    <label>Username</label>
                                    <input name="username" class="form-control" placeholder="Username" type="text">
                                </div> <!-- form-group// -->
                            </div>
                            <div class="row">
                                <div class="col form-group">
                                    <label> Password</label>
                                    <input name="password" class="form-control" placeholder="******" type="password">
                                </div> <!-- form-group// -->
                            </div>
                            <div class="row justify-content-center">
                                <div class="col-auto my-1">
                                    <div class="custom-control custom-checkbox mr-sm-2">
                                        <input name="recordar" type="checkbox" class="custom-control-input" id="recordar">
                                        <label class="custom-control-label" for="recordar">Recordarme</label>
                                    </div>
                                    <br/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col form-group">
                                    <button type="submit" class="btn btn-primary btn-block"> Login  </button>
                                </div> <!-- form-group// -->
                            </div>
                        </form>
                    </article>
                </div> <!-- card.// -->

            </aside> <!-- col.// -->

        </div> <!-- row.// -->
    </div>
</body>
</html>
