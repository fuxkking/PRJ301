<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login - Feane Style</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <div class="form-box">
            <h2>Welcome Back</h2>
            <p>Please login to continue</p>

            <form action="${pageContext.request.contextPath}/Login" method="post" class="form">
                <div class="input-group">
                    <input type="text" name="username" value="${username}" required>
                    <label>Username</label>
                </div>
                <div class="input-group">
                    <input type="password" name="password" value="${password}" required>
                    <label>Password</label>
                </div>

                <h3 class="error">${error}</h3>
                <button type="submit" class="btn">Login</button>
            </form>

            <form action="${pageContext.request.contextPath}/Register" method="get" class="form login-redirect">
                <p class="register-link">Don't have an account?</p>
                <button type="submit" name="btn" class="btn-outline">Register</button>
            </form>
        </div>
    </div>
</body>
</html>
