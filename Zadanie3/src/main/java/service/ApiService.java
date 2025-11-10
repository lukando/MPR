package service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import exception.ApiException;
import model.Employee;
import model.Position;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


public class ApiService {

    private final HttpClient httpClient;
    private final Gson gson;

    private static final String API_URL = "https://jsonplaceholder.typicode.com/users";

    public ApiService() {
        this.httpClient = HttpClient.newHttpClient();
        this.gson = new Gson();
    }

    public List<Employee> fetchEmployeesFromApi() throws ApiException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .GET()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new ApiException("Błąd odpowiedzi HTTP: " + response.statusCode());
            }

            String jsonBody = response.body();
            JsonArray usersArray = gson.fromJson(jsonBody, JsonArray.class);

            return parseJsonToEmployeeList(usersArray);

        } catch (Exception e) {
            throw new ApiException("Nie udało się pobrać danych z API: " + e.getMessage(), e);
        }
    }

    private List<Employee> parseJsonToEmployeeList(JsonArray usersArray) {
        List<Employee> apiEmployees = new ArrayList<>();

        double programmerSalary = Position.PROGRAMISTA.getBazowaPensja();

        for (JsonElement userElement : usersArray) {
            JsonObject userObject = userElement.getAsJsonObject();

            String email = userObject.get("email").getAsString();
            String fullName = userObject.get("name").getAsString();

            String companyName = userObject.get("company")
                    .getAsJsonObject()
                    .get("name")
                    .getAsString();

            String firstName;
            String lastName;
            String[] nameParts = fullName.split(" ", 2);
            firstName = nameParts[0];
            if (nameParts.length > 1) {
                lastName = nameParts[1];
            } else {
                lastName = "BrakNazwiska";
            }

            Employee employee = new Employee(
                    firstName,
                    lastName,
                    email,
                    companyName,
                    Position.PROGRAMISTA,
                    programmerSalary
            );

            apiEmployees.add(employee);
        }

        return apiEmployees;
    }
}