$('#logId').submit(function (event){

    event.preventDefault();// evita mandar submit
    var username = $('#username').val();
    var pass = $('#password').val();
    var user = {
        "username": username,
        "password": pass
    };
    
    alert(JSON.stringify(user));    
    $.ajax({
        type: 'ajax',
        method: 'post',
        url:'http://localhost:8085/efvServ/session/loggin',
        data: JSON.stringify(user),
        contentType: 'application/json; charset=utf-8',
        success: (User) => {     
            alert(JSON.stringify(User))       
           window.location="http://localhost:8085/efvServ/site/dashboard"; 
        },
        error: (e) => {
            
            console.log(JSON.stringify(e));
            console.log(e);
            alert(e);
           

        }
    });

});