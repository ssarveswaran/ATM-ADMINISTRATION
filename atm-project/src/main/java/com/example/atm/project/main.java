package com.example.atm.project;

import ch.qos.logback.core.net.SyslogOutputStream;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.Date;
import java.util.Scanner;

public class main
{

    public static void main(String[] args)
    {

        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the name");
        String username=sc.nextLine();
        System.out.println("Enter the password");
        String password=sc.nextLine();

        int flag=0;
        while (true) {
            System.out.println("1. Get All User");
            System.out.println("2. Get User By Id");
            System.out.println("3. Create New User");
            System.out.println("4. Update Already Existing User");
            System.out.println("5. Delete User By Id");
            System.out.println("6. Get Money");
              System.out.println("7. Adding Money");
           System.out.println("8. Report View");
              System.out.println("9. END");
            Byte value=sc.nextByte();
            switch (value) {
                case 1:
                    try {

                        String url1 = "http://localhost:9292/Atm/v1.0/User/get";
                        String response = sendGetRequest(url1, username, password);
                        System.out.println(response);
                    } catch (IOException | InterruptedException e) {
                        System.out.println("Exception occurred: " + e.getMessage());
                    }

                    break;
                case 2:

                    try {

                        System.out.println("Enter User Id: ");
                        String url2 = "http://localhost:9292/Atm/v1.0/User/"+sc.next();
                        String response = sendGetRequest(url2, username, password);
                        System.out.println(response);
                    } catch (IOException | InterruptedException e) {
                        System.out.println("Exception occurred: " + e.getMessage());
                    }
                    break;
                case 3:
                    String url3 = "http://localhost:9292/Atm/v1.0/User/add";
                    System.out.println("Enter New User");
                    System.out.println("Enter Name: ");
                    String uname=sc.next();
                    System.out.println("Enter password: ");
                    String pword=sc.next();
                    System.out.println("Enter the role");
                    String role=sc.next().toUpperCase();
                    String requestBody3 = "{\"name\": \"" + uname + "\", \"password\": \"" + pword + "\", \"role\": \"" + role + "\"}";

                    try {

                        String response = sendPostRequest(url3, requestBody3, username, password);
                        System.out.println(response);
                    } catch (IOException | InterruptedException e) {
                        System.out.println("Exception occurred: " + e.getMessage());
                    }
                    break;
                case 4:

                    System.out.println("Enter  Id: ");
                    String url4 = "http://localhost:9292/Atm/v1.0/User/update/"+sc.nextInt();
                    System.out.println("Enter Name: ");
                    String n=sc.next();
                    System.out.println("Enter password: ");
                    String p=sc.next();
                    System.out.println("Enter the role");
                    String r=sc.next().toUpperCase();
                    String requestBody4 = "{\"name\": \"" + n + "\", \"password\": \"" + p + "\", \"role\": \"" + r + "\"}";
                    try {
                        String response = sendPutRequest(url4, requestBody4, username, password);
                        System.out.println(response);
                    } catch (IOException | InterruptedException e) {
                        System.out.println("Exception occurred: " + e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("Enter Id: ");
                    String url5 = "http://localhost:9292/Atm/v1.0/User/delete/"+sc.nextInt();

                    try {

                        String response = sendDeleteRequest(url5, username, password);
                        System.out.println(response);
                    } catch (IOException | InterruptedException e) {
                        System.out.println("Exception occurred: " + e.getMessage());
                    }
                    System.out.println("Deleted Successfully");
                    break;
                case 6:
                    String url6 = "http://localhost:9292/Atm/v1.0/Custodian/get";

                    try {
                        String response = sendGetRequest(url6, username, password);
                        System.out.println(response);
                    } catch (IOException | InterruptedException e) {
                        System.out.println("Exception occurred: " + e.getMessage());
                    }
                    break;
                case 7:
                    String url7 = "http://localhost:9292/Atm/v1.0/Custodian/add";
                    System.out.println("Enter the custodian name:");
                    String name= sc.next();
                    System.out.println("Enter the Five Hundred:");
                    int five= sc.nextInt();
                    System.out.println("Enter the two Hundred:");
                    int two= sc.nextInt();
                    System.out.println("Enter the Hundred:");
                    int hun= sc.nextInt();
                    Date d= new Date();
                    String requestBody7 = "{\"userName\": \"" + name + "\", \"fiveHundred\": " + five + ", \"twoHundred\": " + two + ", \"hundred\": " + hun + ", \"Date\": \"" + String.valueOf(d) + "\"}";


                    try {
                        String response = sendPostRequest(url7, requestBody7, username, password);
                        System.out.println(response);
                    } catch (IOException | InterruptedException e) {
                        System.out.println("Exception occurred: " + e.getMessage());
                    }
                    break;
                case 8:
                    String url8 = "http://localhost:9292/Atm/v1.0/Report/get";


                    try {
                        String response = sendGetRequest(url8, username, password);
                        System.out.println(response);
                    } catch (IOException | InterruptedException e) {
                        System.out.println("Exception occurred: " + e.getMessage());
                    }
                    break;
                case 9:
                    flag=1;
                    break;
                    default:
                    break;

            }
            if(flag==1)
                break;
        }
    }

private static String sendGetRequest(String urlString, String username, String password) throws IOException, InterruptedException {
    HttpClient httpClient = HttpClient.newBuilder().build();

    String credentials = username + ":" + password;
    String authHeaderValue = "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());

    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(urlString))
            .header("Authorization", authHeaderValue)
            .GET()
            .build();

    HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

    return httpResponse.body();
}

private static String sendPostRequest(String urlString, String requestBody, String username, String password) throws IOException, InterruptedException {
    HttpClient httpClient = HttpClient.newBuilder().build();

    String credentials = username + ":" + password;
    String authHeaderValue = "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());

    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(urlString))
            .header("Authorization", authHeaderValue)
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .build();

    HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

    return httpResponse.body();
}

private static String sendPutRequest(String urlString, String requestBody, String username, String password) throws IOException, InterruptedException {
    HttpClient httpClient = HttpClient.newBuilder().build();

    String credentials = username + ":" + password;
    String authHeaderValue = "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());

    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(urlString))
            .header("Authorization", authHeaderValue)
            .header("Content-Type", "application/json")
            .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
            .build();

    HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

    return httpResponse.body();
}

private static String sendDeleteRequest(String urlString, String username, String password) throws IOException, InterruptedException {
    HttpClient httpClient = HttpClient.newBuilder().build();

    String credentials = username + ":" + password;
    String authHeaderValue = "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());

    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(urlString))
            .header("Authorization", authHeaderValue)
            .DELETE()
            .build();

    HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

    return httpResponse.body();
}}