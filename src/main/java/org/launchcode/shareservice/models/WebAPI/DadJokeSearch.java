package org.launchcode.shareservice.models.WebAPI;

public class DadJokeSearch {
    public int current_page;
    public int limit;
    public int next_page;
    public int previous_page;
    public DadJoke[] results;
    public String search_Term;
    public int status;
    public int total_jokes;
    public int total_pages;
}
