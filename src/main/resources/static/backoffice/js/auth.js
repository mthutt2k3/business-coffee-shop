function login() {
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;

    fetch("/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: `username=${username}&password=${password}`
    })
        .then(res => {
            if (!res.ok) {
                throw new Error("Login failed");
            }
            return res.text();
        })
        .then(token => {
            localStorage.setItem("jwtToken", token);
            window.location.href = "/dashboard";
        })
        .catch(error => {
            alert("Login failed. Please check your credentials.");
        });
}
