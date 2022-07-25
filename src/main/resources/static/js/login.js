// Call the dataTables jQuery plugin
$(document).ready(function() {

});


async function  login(){
    let user={
        email:document.getElementById("txtEmail").value,
        password:document.getElementById("txtPassword").value,
    };
    const request = await fetch('auth/login', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body:JSON.stringify(user)
    });
    const response = await  request.text();
    console.log("Response login: " + JSON.stringify(response))
    if(response!="FAIL"){
        localStorage.token = response;
        localStorage.email = user.email;
        window.location.href="users.html"
    }else
        alert("Incorrect")

}