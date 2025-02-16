async function loginUser(event) {
    event.preventDefault(); // Prevent page refresh

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    const response = await fetch("http://localhost:8080/api/v1/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username: email, password: password })
    });

    const result = await response.json();

    if (response.ok) {
        alert("Login successful!");
        // Store the token in localStorage (if needed)
        localStorage.setItem("authToken", result.token);
        window.location.href = "dashboard.html"; // Redirect to dashboard
    } else {
        alert("Login failed: " + result.message);
    }
}

function togglePasswordVisibility(inputId) {
    const passwordInput = document.getElementById(inputId);
    const toggleIcon = passwordInput.nextElementSibling; // Correct reference

    if (passwordInput.type === "password") {
        passwordInput.type = "text";
        toggleIcon.innerHTML = "&#x1F441;"; // Eye open
    } else {
        passwordInput.type = "password";
        toggleIcon.innerHTML = "&#x1F441;"; // Eye closed
    }

// Attach event listener to form
document.getElementById("login-form").addEventListener("submit", loginUser);
