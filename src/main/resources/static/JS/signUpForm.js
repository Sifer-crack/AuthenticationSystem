async function registerUser(event) {
    event.preventDefault();

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirmPassword").value;

    if (password !== confirmPassword) {
        alert("Passwords do not match!");
        return;
    }

    const response = await fetch("http://localhost:8080/api/v1/auth/sign-up", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username: email, password: password })
    });

    const result = await response.json();

    if (response.ok) {
        alert("Account created successfully!");
        window.location.href = "login.html"; // Redirect to login page
    } else {
        alert("Sign-up failed: " + result.message);
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
document.getElementById("signup-form").addEventListener("submit", registerUser);
