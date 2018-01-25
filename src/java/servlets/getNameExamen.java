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
public class getNameExamen extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://MongoUser:mongomongo@cluster0-vgqbk.mongodb.net/test");
        MongoClient mongoClient = new MongoClient(uri);
        

        MongoDatabase database = mongoClient.getDatabase("Examen");

        
        MongoCollection<Document> ex = database.getCollection("Examenes");

        List<Document> examenes = database.getCollection("Examenes").find().into(new ArrayList<>());
        List<String> namesExamenes = new ArrayList<>();
        for (Document examen : examenes) {
            namesExamenes.add(examen.getString("Nombre"));
        }
        mongoClient.close();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(namesExamenes);
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            out.println(json);
        }

    }
    @Override
    public void init() throws ServletException {
        try {
            
            MongoClientURI uri = new MongoClientURI(
                    "mongodb+srv://MongoUser:mongomongo@cluster0-vgqbk.mongodb.net/test");
            MongoClient mongoClient = new MongoClient(uri);
            
            MongoDatabase database = mongoClient.getDatabase("Examen");

            
            MongoCollection<Document> collEx = database.getCollection("Examenes");
            collEx.drop();
            collEx = database.getCollection("Examenes");

            collEx.insertOne(getExamenA());
            collEx.insertOne(getExamenB());
            collEx.insertOne(getExamenC());
            mongoClient.close();

        } catch (Exception e) {

        }

    }

    private Document getExamenA() {
        Document examenA = new Document("Nombre", "Exámen A");

        examenA.put("0", pregunta1ExA());
        examenA.put("1", pregunta2ExA());
        examenA.put("2", pregunta3ExA());
        examenA.put("3", pregunta4ExA());
        examenA.put("4", pregunta5ExA());
        examenA.put("5", pregunta6ExA());
        examenA.put("6", pregunta7ExA());
        examenA.put("7", pregunta8ExA());
        examenA.put("8", pregunta9ExA());
        examenA.put("9", pregunta10ExA());
        return examenA;
    }

    private Document getExamenB() {
        Document examenB = new Document("Nombre", "Exámen B");

        examenB.put("0", pregunta1ExB());
        examenB.put("1", pregunta2ExB());
        examenB.put("2", pregunta3ExB());
        examenB.put("3", pregunta4ExB());
        examenB.put("4", pregunta5ExB());
        examenB.put("5", pregunta6ExB());
        examenB.put("6", pregunta7ExB());
        examenB.put("7", pregunta8ExB());
        examenB.put("8", pregunta9ExB());
        examenB.put("9", pregunta10ExB());

        return examenB;
    }
    
        private Document getExamenC() {
        Document examenC = new Document("Nombre", "Exámen C");

        examenC.put("0", pregunta1ExC());
        examenC.put("1", pregunta2ExC());
        examenC.put("2", pregunta3ExC());
        examenC.put("3", pregunta4ExC());
        examenC.put("4", pregunta5ExC());
        examenC.put("5", pregunta6ExC());
        examenC.put("6", pregunta7ExC());
        examenC.put("7", pregunta8ExC());
        examenC.put("8", pregunta9ExC());
        examenC.put("9", pregunta10ExC());

        return examenC;
    }

    private Document pregunta1ExA() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "radio")
                .append("titulo", "¿A que empresa pertenece Super Mario?");
        pregunta.put("correcta", 2);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "Microsoft")
                .append("1", "Sony")
                .append("2", "Nintendo")
                .append("3", "Hacendado");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

    private Document pregunta1ExB() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "radio")
                .append("titulo", "¿Por qué se suicidó el libro de matemáticas?");
        pregunta.put("correcta", 3);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "Porque se tropezó.")
                .append("1", "Porque sus acciones bajaron mucho en bolsa.")
                .append("2", "Por el calentamiento global.")
                .append("3", "Porque tenía muchos problemas.");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

    private Document pregunta1ExC() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "radio")
                .append("titulo", "¿En qué país nació el manga?");
        pregunta.put("correcta", 3);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "Rusia")
                .append("1", "España")
                .append("2", "Mongolia")
                .append("3", "Japón");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

    private Document pregunta2ExA() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "radio")
                .append("titulo", "¿Cual de estos juegos es real?");
        pregunta.put("correcta", 1);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "Residen Ivol 7")
                .append("1", "Super acrobatic rocket-powered battle cars")
                .append("2", "Pokemon Arcoiris")
                .append("3", "Sanic");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

    private Document pregunta2ExB() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "radio")
                .append("titulo", "¿Qué es un moño?");
        pregunta.put("correcta", 2);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "El perro Mistetas.")
                .append("1", "Ño sé.")
                .append("2", "Un añimal que come bañañas.");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

    private Document pregunta2ExC() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "radio")
                .append("titulo", "¿Que serie de televisión es famosa por pupularizar el anime fuera de Japón?");
        pregunta.put("correcta", 2);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "Los simpsons.")
                .append("1", "Boku no piko.")
                .append("2", "Dragon Ball.")
                .append("3", "South Park.");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

    private Document pregunta3ExA() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "text")
                .append("titulo", "¿A qué videojuego se le conoce por ser el primero de la historia?");

        pregunta.put("correcta", "pong");

        return pregunta;
    }

    private Document pregunta3ExB() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "text")
                .append("titulo", "Oro parece, plata no és. ¿Qué es?");

        pregunta.put("correcta", "platano");

        return pregunta;
    }

    private Document pregunta3ExC() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "text")
                .append("titulo", "¿Quien es el dueño de microsoft?");

        pregunta.put("correcta", "Bill Gates.");

        return pregunta;
    }

    private Document pregunta4ExA() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "text")
                .append("titulo", "¿Qué hace crecer a Mario?");

        pregunta.put("correcta", "champiñones");

        return pregunta;
    }

    private Document pregunta4ExB() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "text")
                .append("titulo", "Una cajita chiquita, blanca como la cal. Todos la saben abrir, nadie la sabe cerrar.");

        pregunta.put("correcta", "huevo");

        return pregunta;
    }

    private Document pregunta4ExC() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "text")
                .append("titulo", "En en anime naruto ¿Que son todos los protagonistas?");

        pregunta.put("correcta", "ninjas");

        return pregunta;
    }

    private Document pregunta5ExA() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "checkbox")
                .append("titulo", "¿Quienes son heroe y villano en la saga Super Mario?");

        //Add repuestas correctas.
        Document correcta = new Document("tam", 2)
                .append("0", 0)
                .append("1", 3);
        pregunta.put("correcta", correcta);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "Mario")
                .append("1", "Samus")
                .append("2", "Cloud")
                .append("3", "Bowser");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

    private Document pregunta5ExB() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "checkbox")
                .append("titulo", "Negro por fuera amarillo por dentro, ¿Qué es?");

        //Add repuestas correctas.
        Document correcta = new Document("tam", 5)
                .append("0", 1)
                .append("1", 2)
                .append("2", 3)
                .append("3", 4)
                .append("4", 5);
        pregunta.put("correcta", correcta);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "La verdad.")
                .append("1", "Un pollo ninja")
                .append("2", "Un platano que parecía pocho.")
                .append("3", "Un coche muy feo.")
                .append("4", "Una caja negra que guarda una pelota amarilla.")
                .append("5", "Lo que tu imagines.")
                .append("6", "El cielo.");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

    private Document pregunta5ExC() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "checkbox")
                .append("titulo", "¿Qué animes de la lista existen?");

        //Add repuestas correctas.
        Document correcta = new Document("tam", 3)
                .append("0", 0)
                .append("1", 1)
                .append("2", 3);
        pregunta.put("correcta", correcta);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "Dragon ball")
                .append("1", "Yu yu hakusho")
                .append("2", "Remicade")
                .append("3", "Bleach");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

    private Document pregunta6ExA() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "checkbox")
                .append("titulo", "¿Cuales son Fantasmas de Pacman?");

        //Add repuestas correctas.
        Document correcta = new Document("tam", 2)
                .append("0", 0)
                .append("1", 2);
        pregunta.put("correcta", correcta);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "Clyde")
                .append("1", "Lala")
                .append("2", "Inky");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

    private Document pregunta6ExB() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "checkbox")
                .append("titulo", "Si estás participando en una carrera y adelantas al segundo. ¿En qué posición terminarias la carrera?");

        //Add repuestas correctas.
        Document correcta = new Document("tam", 2)
                .append("0", 0)
                .append("1", 1);
        pregunta.put("correcta", correcta);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "No lo puedes saber, aún no has terminado.")
                .append("1", "Segundo.")
                .append("2", "Ganas, eres el mejor.");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

    private Document pregunta6ExC() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "checkbox")
                .append("titulo", "Mazinger Z, ¿Qué era?");

        //Add repuestas correctas.
        Document correcta = new Document("tam", 2)
                .append("0", 0)
                .append("1", 2);
        pregunta.put("correcta", correcta);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "Un robot.")
                .append("1", "La asistenta.")
                .append("2", "Un mecha.")
                .append("3", "Un animal prehistórico.");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

    private Document pregunta7ExA() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "selectS")
                .append("titulo", "¿Cuantas medallas tienes que ganar en Pokemon?");
        pregunta.put("correcta", 2);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "3")
                .append("1", "9456")
                .append("2", "8")
                .append("3", "3,14");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

    private Document pregunta7ExB() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "selectS")
                .append("titulo", "¿Cuando puedes salir de clase?");
        pregunta.put("correcta", 2);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "Cuando llueva.")
                .append("1", "Nunca, MUAHAHAHAHAHA.")
                .append("2", "Después de entrar.");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

    private Document pregunta7ExC() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "selectS")
                .append("titulo", "¿Donde quería jugar futbol Olvier Atom?");
        pregunta.put("correcta", 2);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "En el patio.")
                .append("1", "En Mestalla.")
                .append("2", "En Brasil.");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

    private Document pregunta8ExA() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "selectS")
                .append("titulo", "¿En qué categoria se suelen clasificar los juegos de coches?");
        pregunta.put("correcta", 1);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "Rpg.")
                .append("1", "Simulador.")
                .append("2", "Bullet hell.")
                .append("3", "Roguelike.");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

    private Document pregunta8ExB() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "selectS")
                .append("titulo", "¿Por qué los tontos salen de la cocina?");
        pregunta.put("correcta", 1);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "Porque Gordon Ramsay los saca a patadas.")
                .append("1", "Porque ven un tarro que pone sal.")
                .append("2", "Porque estan llenos.");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

    private Document pregunta8ExC() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "selectS")
                .append("titulo", "¿Qué anime trata sobre piratas?");
        pregunta.put("correcta", 0);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "One piece.")
                .append("1", "Boku no hero.")
                .append("2", "Mazinger Z.")
                .append("3", "Bleach.");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

    private Document pregunta9ExA() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "multiple")
                .append("titulo", "¿Qué Pokemon pertenecen a la misma linea evolutiva?");

        //Add repuestas correctas.
        Document correcta = new Document("tam", 2)
                .append("0", 1)
                .append("1", 2);
        pregunta.put("correcta", correcta);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "Ramón.")
                .append("1", "Pikachu.")
                .append("2", "Raichu.")
                .append("3", "Ratatta.");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

    private Document pregunta9ExB() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "multiple")
                .append("titulo", "Marca la respuestas correctas.");

        //Add repuestas correctas.
        Document correcta = new Document("tam", 3)
                .append("0", 0)
                .append("1", 1)
                .append("2", 3);
        pregunta.put("correcta", correcta);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "Soy una respuesta correcta.")
                .append("1", "Márcame.")
                .append("2", "A mi no me elijas.")
                .append("3", "Ahora haces un exámen.");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

    private Document pregunta9ExC() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "multiple")
                .append("titulo", "¿Que animes son mundialmente famosos?");

        //Add repuestas correctas.
        Document correcta = new Document("tam", 2)
                .append("0", 0)
                .append("1", 1);
        pregunta.put("correcta", correcta);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "Naruto.")
                .append("1", "Dragon ball.")
                .append("2", "Breaking Bad.")
                .append("3", "The Big Bang Theory.");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

    private Document pregunta10ExA() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "multiple")
                .append("titulo", "¿En qué juegos aparece Mario?");

        //Add repuestas correctas.
        Document correcta = new Document("tam", 3)
                .append("0", 0)
                .append("1", 1)
                .append("2", 3);
        pregunta.put("correcta", correcta);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "Super Smash Bros.")
                .append("1", "Mario Kart.")
                .append("2", "The legend of Zelda.")
                .append("3", "Super Mario Bros.");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

    private Document pregunta10ExB() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "multiple")
                .append("titulo", "Un pato y un niño nacen el mismo día. Al cabo de un año ¿Cuál es mayor de los dos?");

        //Add repuestas correctas.
        Document correcta = new Document("tam", 2)
                .append("0", 0)
                .append("1", 3);
        pregunta.put("correcta", correcta);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "Ninguno.")
                .append("1", "El día.")
                .append("2", "El perro Mistetas.")
                .append("3", "El pato, porque tendrá un año y pico.")
                .append("4", "El niño.");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

    private Document pregunta10ExC() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "multiple")
                .append("titulo", "¿Quienes son protagonistas de Dragon Ball?");

        //Add repuestas correctas.
        Document correcta = new Document("tam", 3)
                .append("0", 0)
                .append("1", 2)
                .append("2", 3);
        pregunta.put("correcta", correcta);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "Cell.")
                .append("1", "Mark Lenders.")
                .append("2", "Son Goku.")
                .append("3", "Vegeta.");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

}

