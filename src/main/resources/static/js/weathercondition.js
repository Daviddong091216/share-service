//JSON (JavaScript Object Notation) is a lightweight,
//text-based, language-independent data exchange format that is easy for humans and machines to read and write.
//JSON can represent two structured types: objects and arrays. An object is an unordered collection of zero or more name/value pairs.
//Very first thing to mention, JSON is not an API but a data format webservices and programs use to communicate to each other.
//Webservices can be of many forms but most popular are REST and SOAP. Webservices give you a way to interact with remote machines and communicate with them.

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