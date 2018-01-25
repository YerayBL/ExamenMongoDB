package servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.bson.Document;

/**
 *
 * @author yeray
 */
public class getExamenServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://MongoUser:mongomongo@cluster0-vgqbk.mongodb.net/test");
        MongoClient mongoClient = new MongoClient(uri);
        
        MongoDatabase database = mongoClient.getDatabase("Examen");

        
        List<Document> examenes = database.getCollection("Examenes").find().into(new ArrayList<>());
        List<Document> examenD = new ArrayList<>();
        for (Document examen : examenes) {
            if (examen.getString("Nombre").equals(request.getParameter("tipoExamen"))) {
                examenD.add(examen);
            }

        }

        mongoClient.close();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(examenD);
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            out.println(json);
        }

    }

}
