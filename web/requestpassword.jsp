<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.3/dist/css/bootstrap.min.css">
        <link rel="stylesheet"
              href="https://unpkg.com/bs-brain@2.0.4/components/password-resets/password-reset-5/assets/css/password-reset-5.css">
    </head>

    <body>
        <!-- Password Reset 5 - Bootstrap Brain Component -->
        <section class="p-3 p-md-4 p-xl-5">
            <div class="container">
                <div class="card border-light-subtle shadow-sm">
                    <div class="row g-0">
                        <div class="col-12 col-md-6 text-bg-primary">
                            <div class="d-flex align-items-center justify-content-center h-100">
                                <div class="col-10 col-xl-8 py-3">
                                    <hr class="border-primary-subtle mb-4">
                                    <h2 class="h1 mb-4">Apartment Management System</h2>
                                    <p class="lead m-0">Thon 3, Binh Yen, Thach That, Ha Noi</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-12 col-md-6">
                            <div class="card-body p-3 p-md-4 p-xl-5">
                                <div class="row">
                                    <div class="col-12">
                                        <div class="mb-5">
                                            <h2 class="h3">Password Reset</h2>
                                            <h3 class="fs-6 fw-normal text-secondary m-0">Provide username of your account to recover your password.</h3>
                                        </div>
                                    </div>
                                </div>
                                <form action="request-password" method="post">
                                    <div class="row gy-3 gy-md-4 overflow-hidden">
                                        <div class="col-12">
                                            <label for="username" class="form-label">Username <span
                                                    class="text-danger">*</span></label>
                                            <input type="username" class="form-control" name="username" id="username"
                                                   placeholder="" required>
                                        </div>
                                        <div class="col-12">
                                            <div class="d-grid">
                                                <button class="btn bsb-btn-xl btn-primary" type="submit">Request
                                                    Password</button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <div class="row">
                                    <div class="col-12">
                                        <hr class="mt-5 mb-4 border-secondary-subtle">
                                        <div class="d-flex gap-2 gap-md-4 flex-column flex-md-row justify-content-md-end">
                                            <a href="login.html" class="link-secondary text-decoration-none">Login</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</section>
</body>

</html>