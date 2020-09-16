window.addEventListener("load", function() {
    let list = document.getElementById("lane-list");
        list.addEventListener("mouseover", function (event) {
            let element = event.target;
                element.innerHTML += ">";
                console.log("target", element);
        });
});