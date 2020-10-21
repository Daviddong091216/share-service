package org.launchcode.shareservice.models.WebAPI;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DadJokeService {

    public DadJoke GetDadJoke() throws IOException {
        /*Fetch a random dad joke
        GET https://icanhazdadjoke.com/ fetch a random dad joke.
        Fetching a random joke as JSON:
        $ curl -H "Accept: application/json" https://icanhazdadjoke.com/
        {
            "id": "R7UfaahVfFd",
                "joke": "My dog used to chase people on a bike a lot. It got so bad I had to take his bike away.",
                "status": 200
        }*/


        URL url = new URL("https://icanhazdadjoke.com");
//        The Java HttpURLConnection class is http specific URLConnection.
//        It works for HTTP protocol only.
//        By the help of HttpURLConnection class, you can information of any HTTP URL such as header information, status code, response code etc.
//        The java. ... HttpURLConnection is subclass of URLConnection class.
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
//        API response format
//        All API endpoints follow their respective browser URLs, but we adjust the response formatting to be more suited for an API based on the provided HTTP Accept header.
//        Accepted Accept headers:
//        text/html - HTML response (default response format)
//        application/json - JSON response
//        text/plain - Plain text response
        connection.setRequestProperty("Accept", "application/json");

//        The Java.io.BufferedReader class reads text from a character-input stream,
//        buffering characters so as to provide for the efficient reading of characters, arrays, and lines.
//        Following are the important points about BufferedReader − The buffer size may be specified, or the default size may be used.
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;

//        StringBuffer is a peer class of String that provides much of the functionality of strings.
//        String represents fixed-length, immutable character sequences while StringBuffer represents growable and writable character sequences.
//        StringBuffer may have characters and substrings inserted in the middle or appended to the end.
        StringBuffer content = new StringBuffer();



        while((inputLine = reader.readLine()) != null){
            content.append(inputLine);
        }


        reader.close();
        connection.disconnect();

        String dadJokeJson = content.toString();

//        Gson is a Java library that can be used to convert Java Objects into their JSON representation.
//        It can also be used to convert a JSON string to an equivalent Java object. ...
//        Gson can work with arbitrary Java objects including pre-existing objects that you do not have source-code of.
        Gson gson = new Gson();
        DadJoke dadJoke = gson.fromJson(dadJokeJson, DadJoke.class);

        return dadJoke;
    }

    public DadJokeSearch SearchDadJokes(String searchString,int limit,int page) throws IOException{
        /*Search for dad jokes
        GET https://icanhazdadjoke.com/search - search for dad jokes.

        This endpoint accepts the following optional query string parameters:

        page - which page of results to fetch (default: 1)
        limit - number of results to return per page (default: 20) (max: 30)
        term - search term to use (default: list all jokes)
        Search through jokes with a search term:

        $ curl -H "Accept: application/json" "https://icanhazdadjoke.com/search?term=hipster"
*/
        URL url = new URL("https://icanhazdadjoke.com/search?term="+searchString+"&limit="+limit+"&page="+page);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");


        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();

        while((inputLine = reader.readLine()) != null){
            content.append(inputLine);
        }

        String dadJokeJson = content.toString();


        reader.close();
        connection.disconnect();

        Gson gson = new Gson();
        DadJokeSearch searchResults = gson.fromJson(dadJokeJson, DadJokeSearch.class);

        return searchResults;
    }
}
