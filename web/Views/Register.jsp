<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Register - Feane Style</title>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    </head>
    <body>
        <div class="container">
            <div class="form-box">
                <h2>Create Account</h2>
                <p>Please fill the form to register</p>

                <form action="${pageContext.request.contextPath}/Register" method="post" class="form">
                    <div class="input-group">
                        <input type="text" name="username" required>
                        <label>Username</label>
                    </div>
                    <div class="input-group">
                        <input type="email" name="email" required>
                        <label>Email</label>
                    </div>
                    <div class="input-group">
                        <input type="text" name="phone" required>
                        <label>Phone</label>
                    </div>
                    <div class="input-group">
                        <input type="password" name="password" required>
                        <label>Password</label>
                    </div>
                    <div class="input-group">
                        <input type="password" name="confirmPassword" required>
                        <label>Confirm Password</label>
                    </div>

                    <h3 class="error">${error}</h3>
                    <button type="submit" class="btn">Register</button>
                </form>

                <form action="${pageContext.request.contextPath}/Login" method="get" class="form login-redirect">
                    <p class="register-link">Already have an account?</p>
                    <button type="submit" name="btn" class="btn-outline">Login</button>
                </form>
            </div>
        </div>
   
    </body>
</html>
