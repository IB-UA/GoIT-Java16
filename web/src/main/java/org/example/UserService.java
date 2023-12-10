package org.example;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private static final String USERS_URL = "https://jsonplaceholder.typicode.com/users";

    private final HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .build();

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public List<User> getUsers() {
        return users;
    }

    private final Type listType = new TypeToken<List<User>>() {}.getType();

    private List<User> users;

    public List<User> getALlUsers() {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(USERS_URL))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            users = gson.fromJson(response.body(), listType);
        } catch (IOException | InterruptedException ignored) {
            users = new ArrayList<>();
        }
        return users;
    }

    public String toJSON(User user) {
        return gson.toJson(user, User.class);
    }

    public User getUserById(int id) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(USERS_URL + "/" + id))
                .header("Content-Type", "application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("response.body by user name() = " + response.body());
            return gson.fromJson(response.body(), User.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getUsersByUsername(String username) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(USERS_URL + "?username=" + username))
                .header("Content-Type", "application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("response.body by user name() = " + response.body());
            return gson.fromJson(response.body(), listType);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public User saveUser(User user) {
        String body = gson.toJson(user, User.class);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(USERS_URL))
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString(body))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            user.setId(gson.fromJson(response.body(), User.class).getId());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public void deleteUser(User user) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(USERS_URL + "/" + user.getId()))
                .header("Content-Type", "application/json")
                .method("DELETE", HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("onDeleteStatusCode = " + response.statusCode());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public User updateUser(User user) {
        String body = gson.toJson(user, User.class);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(USERS_URL + "/" + user.getId()))
                .header("Content-Type", "application/json")
                .method("PUT", HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return gson.fromJson(response.body(), User.class);
    }

    public void saveToFileAllCommentsForLatestPostByUserId(int userId) {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(USERS_URL + "/" + userId + "/posts" ))
                .header("Content-Type", "application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Type type = new TypeToken<List<Post>>() {}.getType();
            List<Post> posts = gson.fromJson(response.body(), type);
            Post post = posts.stream().max(Comparator.comparing(Post::getId)).orElse(null);
            if (post != null) {
                HttpRequest postCommentsRequest = HttpRequest.newBuilder()
                        .uri(URI.create("https://jsonplaceholder.typicode.com/posts/"+ post.getId() +"/comments" ))
                        .header("Content-Type", "application/json")
                        .method("GET", HttpRequest.BodyPublishers.noBody())
                        .build();

                HttpResponse<String> commentsResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

                try(FileWriter fw = new FileWriter("user-"+ userId +"-post-" + post.getId() + "-comments.json")) {
                    fw.write(commentsResponse.body());
                } catch (IOException ignored) {

                }

            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Todo> getUncompletedTodosByUserId(int userId) {
        Type type = new TypeToken<List<Todo>>() {}.getType();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(USERS_URL + "/" + userId + "/todos" ))
                .header("Content-Type", "application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            List<Todo> todos = gson.fromJson(response.body(), type);
            return todos.stream().filter(todo -> !todo.isCompleted()).collect(Collectors.toList());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        UserService us = new UserService();
        System.out.println("us.getALlUsers() = " + us.getALlUsers());
        User newUser = new User(
                "Ievgen Bardadym",
                "ib",
                "ievgen.bardadym@gmail.com",
                new Address(
                        "gen. Abrahama",
                        "Apt. 1*",
                        "Warsaw",
                        "03-98*",
                        new Geo("*.227741", "*.086019")
                ),
                "+487911716**",
                "https://github.com/ib-ua",
                new Company(
                        "TS Imagine",
                        "Changing markets and shifting regulatory landscapes",
                        "real-time risk management"
                )
        );
        System.out.println("saveUser\n" + us.saveUser(newUser));
        User userToUpdate = us.getUsers().get(2);
        userToUpdate.setUsername("bubble_gum");
        User updatedUser = us.updateUser(userToUpdate);
        System.out.println("updatedUser = " + updatedUser.getUsername());
        us.deleteUser(us.getUsers().get(5));
        System.out.println("us.getUserByUsername\n" + us.getUsersByUsername("Karianne"));
        System.out.println("us.getUserById(9)\n" + us.toJSON(us.getUserById(9)));
        us.saveToFileAllCommentsForLatestPostByUserId(1);
        System.out.println(us.getUncompletedTodosByUserId(1).stream()
                .map(todo -> String.format("%d %s %b", todo.getId(), todo.getTitle(), todo.isCompleted()))
                .collect(Collectors.joining("\n")));
    }
}
