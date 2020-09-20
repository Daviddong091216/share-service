window.addEventListener("load",function(){

    let takeoffButton = document.getElementById("takeoff");
    let flightStatus = document.getElementById("flightStatus");
    let spaceShuttleHeight =document.getElementById('spaceShuttleHeight');
    let shuttleBackGround = document.getElementById("shuttleBackground");
    let landButton = document.getElementById("landing");
    let abortMission = document.getElementById("missionAbort");
    let upButton = document.getElementById("up");
    let downButton = document.getElementById("down");
    let rightButton = document.getElementById("right");
    let leftButton = document.getElementById("left");
    let rocket = document.getElementById("rocket");
    // rocket.style.top = "0px";
    // rocket.style.left = "250px";
    rocket.style.position = "absolute";


    takeoffButton.addEventListener("click", function(){
        let confirmStatus = window.confirm("Confirm that the shuttle is ready for takeoff.");
        if(confirmStatus == true) {
            flightStatus.innerHTML = "Shuttle inflight";
            spaceShuttleHeight.innerHTML = Number(spaceShuttleHeight.innerHTML) + Number(10000);
            shuttleBackGround.style.background = "blue";
            rocket.style.bottom = "120px";
        };
     });

    landButton.addEventListener("click", function(){
        window.alert("The shuttle is landing. Landing gear engaged.");
        flightStatus.innerHTML ="The shuttle has landed.";
        spaceShuttleHeight.innerHTML = 0;
        shuttleBackGround.style.background = "green";
        rocket.style.bottom = "0";
    });


    abortMission.addEventListener("click", function(){
        let confirmAbort = window.confirm("Confirm that you want to abort the mission." );
        if(confirmAbort == true) {
            flightStatus.innerHTML = "Mission abort";
            spaceShuttleHeight.innerHTML = 0;
        };
    });

    upButton.addEventListener("click", function(){
        spaceShuttleHeight.innerHTML =Number(spaceShuttleHeight.innerHTML) + Number(10000);
        rocket.style.bottom = parseInt(rocket.style.bottom) + 10 + "px";

    });

    downButton.addEventListener("click", function(){
        spaceShuttleHeight.innerHTML =Number(spaceShuttleHeight.innerHTML) - Number(10000);
        rocket.style.bottom = parseInt(rocket.style.bottom) - 10 + "px";

    });

    rightButton.addEventListener("click", function(){
        rocket.style.left = parseInt(rocket.style.left) + 10 + "px";
    });

    leftButton.addEventListener("click", function(){
        rocket.style.left = parseInt(rocket.style.left) - 10 + "px";
    });


});
