window.addEventListener("load", function() {
    fetch("https://handlers.education.launchcode.org/static/weather.json").then(function(response) {
        response.json().then( function(json) {
            const div = document.getElementById("weather-conditions");
                  // Add HTML that includes the JSON data
                div.innerHTML = `
                    <ul>
                        <li>Temp : ${json.temp}</li>
                        <li>Wind Speed : ${json.windSpeed}</li>
                        <li>Status : ${json.status}</li>
                        <li>Chance of Precip : ${json.chanceOfPrecipitation}</li>
                     </ul>
                `;
            });
        });
    });