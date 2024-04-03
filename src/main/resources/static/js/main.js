// connect - odpalenie po odpaleniu strony
// sendmessage - wysyła wiadomość na serwer
// shwoMessage - pokazuje wiadomość na stronie

var socket = new SockJS('/ws');
var stompClient = Stomp.over(socket);

stompClient.connect({}, function (frame) {
    console.log('Connect:' + frame);
    stompClient.subscribe('/topic/ws', function (message) {
        showMessage(JSON.parse(message.body));
    })
});


function sendMessage1() {
    var messageInput = document.getElementById('messageInputUser1');
    var nick = document.getElementsByName('nick')[0];
    var idConversation = document.getElementsByName('idConversation')[0];
    var messageDto = {
        message: messageInput.value,
        nick: nick.value,
        idConversation: idConversation.value,

    }

    stompClient.send("/app/sendMessage", {}, JSON.stringify(messageDto)); messageInput.value = '';

}

function sendMessage2() {
    var messageInput = document.getElementById('messageInputUser2');
    var nick = document.getElementsByName('nick2')[0];
    var idConversation = document.getElementsByName('idConversation2')[0];
    var messageDto = {
        message: messageInput.value,
        nick: nick.value,
        idConversation: idConversation.value,
    }


    stompClient.send("/app/sendMessage", {}, JSON.stringify(messageDto)); messageInput.value = '';

}

document.getElementById('messageForm1').addEventListener('submit', function (event) {
    event.preventDefault();
    sendMessage1();
});

document.getElementById('messageForm2').addEventListener('submit', function (event) {
    event.preventDefault();
    sendMessage2();
});


