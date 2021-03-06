package servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.bson.Document;

/**
 *
 * @author yeray
 */
public class notasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            MongoClientURI uri = new MongoClientURI(
                    "mongodb+srv://MongoUser:mongomongo@cluster0-vgqbk.mongodb.net/test");
            MongoClient mongoClient = new MongoClient(uri);
            
            MongoDatabase database = mongoClient.getDatabase("Examen");

            
            MongoCollection<Document> collectionNotas = database.getCollection("Notas");
      
            
            List<Document> notas = (List<Document>) collectionNotas.find().into(
                    new ArrayList<Document>());
            mongoClient.close();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(notas);
            response.setContentType("application/json");
            try (PrintWriter out = response.getWriter()) {
                out.println(json);
            }

        } catch (Exception e) {
            Map<String, String> emess = new HashMap<>();
            emess.put("error", e.toString());

            Gson gson = new GsonBuilder().create();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            PrintWriter pw = response.getWriter();
            pw.println(gson.toJson(emess));

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nota = request.getParameter("nota");
        String DNI = request.getParameter("DNI");
        String tipoExamen = request.getParameter("tipoExamen");

        try {
            
            MongoClientURI uri = new MongoClientURI(
                    "mongodb+srv://MongoUser:mongomongo@cluster0-vgqbk.mongodb.net/test");
            MongoClient mongoClient = new MongoClient(uri);
            
            MongoDatabase database = mongoClient.getDatabase("Examen");

            
            MongoCollection<Document> collectionNotas = database.getCollection("Notas");

            
            List<Document> resultados = database.getCollection("Notas").find().into(new ArrayList<>());
            for (Document r : resultados) {
                if (r.getString("DNI").equals(DNI) && r.getString("tipoExamen").equals(tipoExamen)) {
                    collectionNotas.deleteOne(r);
                }
            }

            
            Document notaDoc = new Document()
                    .append("DNI", DNI)
                    .append("tipoExamen", tipoExamen)
                    .append("nota", nota);

            collectionNotas.insertOne(notaDoc);
            mongoClient.close();

        } catch (Exception e) {
            Map<String, String> emess = new HashMap<>();
            emess.put("error", e.toString());

            Gson gson = new GsonBuilder().create();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            PrintWriter pw = response.getWriter();
            pw.println(gson.toJson(emess));

        }

    }

}
