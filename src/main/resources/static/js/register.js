// Call the dataTables jQuery plugin
$(document).ready(function() {

});


async function  registerUser(){
    let user={
        name:document.getElementById("txtName").value,
        lastName:document.getElementById("txtLastName").value,
        email:document.getElementById("txtEmail").value,
        phone:document.getElementById("txtPhone").value,
        password:document.getElementById("txtPassword").value,
    };

    const request = await fetch('api/user', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body:JSON.stringify(user)
    });

    alert("Success Register")
    window.location.href="login.html"

}