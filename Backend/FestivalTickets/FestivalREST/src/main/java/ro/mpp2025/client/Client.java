package ro.mpp2025.client;

import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;
import ro.mpp2025.Domain.Spectacol;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

public class Client
{
    public static final String URL="http://localhost:8080/festival/spectacole";
    private RestClient restTemplate = RestClient.create(URL);
    private  <T> T execute(Callable<T> callable)
    {
        try
        {
            return callable.call();
        }
        catch (HttpServerErrorException e)
        {
            System.out.println("Error from server "+e);
        }
        catch (ResourceAccessException e)
        {
            e.printStackTrace();}
        catch (Exception e)
        {
            e.printStackTrace();}
        return null;
    }
    public Spectacol[] getAllSpectacole()
    {
        return execute(() -> restTemplate.get().uri(URL).retrieve().body(Spectacol[].class));
    }
    public Spectacol getSpectacol(String id)
    {
        return execute(() -> restTemplate.get().uri(String.format("%s/%s", URL, id)).retrieve().body(Spectacol.class));
    }
    public Spectacol addSpectacol(Spectacol spectacol)
    {
        return execute(() -> restTemplate.post().uri(URL).body(spectacol).contentType(MediaType.APPLICATION_JSON).body(spectacol).retrieve().body(Spectacol.class));
    }
    public Spectacol updateSpectacol(Spectacol spectacol)
    {
        if (spectacol.getId() == null) {
            throw new IllegalArgumentException("ID-ul spectacolului nu poate fi null.");
        }
        return execute(() -> {
            restTemplate.put().uri(String.format("%s/%s", URL, spectacol.getId())).body(spectacol).contentType(MediaType.APPLICATION_JSON).retrieve().body(Spectacol.class);
            return spectacol;
        });
    }
    public void deleteSpectacol(String id)
    {
        execute(() -> {
            restTemplate.delete().uri(String.format("%s/%s", URL, id)).retrieve().toBodilessEntity();
            return null;
        });
    }
}