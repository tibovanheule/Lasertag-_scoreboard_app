let focused = true;

//in focus dus true
window.onfocus = function () {
    focused = true;
};
//niet meer infocus dus false
window.onblur = function () {
    focused = false;
};

function putUrl(url, callback) {
    let request = window.ActiveXObject ?
        new ActiveXObject('Microsoft.XMLHTTP') :
        new XMLHttpRequest;

    request.onreadystatechange = function () {
        if (request.readyState === 4) {
            callback(request, request.status);
        }
    };

    request.open('GET', url, true);
    request.send(null);
}

function status() {
    if (focused) {
        putUrl('https://tibovanheule.space/lasershoot/status.php?time=' + (new Date).getTime(), function (data) {
            data = JSON.parse(data.response);
            let verdiepen = data.verdiepen;
            console.log(data);
            document.getElementById('status').innerHTML = "";
            Array.prototype.forEach.call(verdiepen,function (i) {
                let node = document.createElement("p");
                node.innerHTML = i.message;
                document.getElementById('status').appendChild(node);
                node = document.createElement("br");
                document.getElementById('status').appendChild(node);
            });
        });
    }


}

setInterval(function () {
    status();
}, 5000);