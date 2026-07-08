package com.example.model

data class Slide(
    val title: String,
    val text: String,
    val emoji: String,
    val illustrationType: String
)

data class Question(
    val id: Int,
    val text: String,
    val options: List<String>,
    val correctAnswerIndex: Int,
    val explanation: String
)

data class Badge(
    val id: String,
    val name: String,
    val description: String,
    val iconEmoji: String,
    val colorHex: String
)

data class Level(
    val id: Int,
    val title: String,
    val subtitle: String,
    val category: String,
    val description: String,
    val colorHex: String,
    val shadowColorHex: String,
    val slides: List<Slide>,
    val questions: List<Question>,
    val badge: Badge,
    val unitId: Int
)

data class UnitExam(
    val unitId: Int,
    val title: String,
    val questions: List<Question>
)

data class LiteratureUnit(
    val id: Int,
    val title: String,
    val description: String,
    val colorHex: String,
    val shadowColorHex: String,
    val levels: List<Level>,
    val exam: UnitExam
)

object LiteratureDatabase {
    // 28 levels
    val levels = listOf(
        // UNIT 1 (1 to 5)
        Level(
            id = 1,
            title = "Tema 1: El origen del cosmos",
            subtitle = "El origen del cosmos",
            category = "Mitología Clásica",
            description = "Descubre cómo creían los griegos que nació el universo.",
            colorHex = "#1CB0F6",
            shadowColorHex = "#1899D6",
            unitId = 1,
            badge = Badge("badge_t1", "Cosmos", "Por descubrir el origen del universo griego", "🌌", "#1CB0F6"),
            slides = listOf(
                Slide("El Caos vacío", "Antes de que existieran la Tierra, el Sol o las flores, solo había Caos: un inmenso vacío oscuro.", "🌌", "universe"),
                Slide("Gea y Urano", "Del Caos nació Gea (la tierna Madre Tierra) y luego Urano (el cielo lleno de estrellas brillantes).", "🌍", "earth")
            ),
            questions = listOf(
                Question(1, "¿Qué existía al principio de todo?", listOf("Un pastel de fresa", "El Caos, un gran vacío oscuro", "Un robot parlante"), 1, "¡Exacto! El Caos era el vacío inicial."),
                Question(2, "¿Quién representaba a la Madre Tierra?", listOf("Gea", "Atenea", "Medusa"), 0, "¡Sí! Gea era la Madre Tierra."),
                Question(3, "¿Quién era Urano?", listOf("El dios del mar", "El cielo estrellado", "Un Titán de fuego"), 1, "¡Muy bien! Urano era el cielo."),
                Question(4, "¿Cómo eran los Cíclopes?", listOf("Gigantes con un solo ojo", "Duendes con alas", "Peces de colores"), 0, "¡Increíble! Tenían un solo ojo redondo."),
                Question(5, "¿Quiénes eran los Titanes?", listOf("Hijos fuertes de Gea y Urano", "Pequeños magos", "Astronautas griegos"), 0, "¡Eso es! Eran gigantes poderosos.")
            )
        ),
        Level(
            id = 2,
            title = "Tema 2: Dioses olímpicos I",
            subtitle = "Dioses olímpicos I",
            category = "Mitología Clásica",
            description = "Aprende sobre Zeus, Hera y Poseidón.",
            colorHex = "#1CB0F6",
            shadowColorHex = "#1899D6",
            unitId = 1,
            badge = Badge("badge_t2", "Olimpo I", "Por conocer a los primeros dioses olímpicos", "⚡", "#1CB0F6"),
            slides = listOf(
                Slide("El Monte Olimpo", "Los dioses más poderosos vivían en la cima del Monte Olimpo, ocultos por hermosas nubes.", "🏛️", "olympus"),
                Slide("Zeus y Poseidón", "Zeus gobernaba el cielo lanzando rayos, y su hermano Poseidón controlaba los océanos con su tridente.", "🔱", "poseidon")
            ),
            questions = listOf(
                Question(1, "¿Dónde vivían los dioses principales?", listOf("En el Monte Olimpo", "En una cueva de hielo", "En la luna"), 0, "¡Correcto! Vivían en el Olimpo."),
                Question(2, "¿Quién era el rey de los dioses?", listOf("Ares", "Zeus", "Hermes"), 1, "¡Exacto! Zeus gobernaba a todos."),
                Question(3, "¿Qué arma mágica usaba Poseidón?", listOf("Un tridente", "Un arco de oro", "Un martillo de piedra"), 0, "¡Excelente! Su tridente creaba olas gigantes."),
                Question(4, "¿Quién era Hera?", listOf("La diosa de las lechuzas", "La reina de los dioses y esposa de Zeus", "Una sirena traviesa"), 1, "¡Sí! Hera era la reina del Olimpo."),
                Question(5, "¿Dónde gobernaba el dios Hades?", listOf("En el cielo estrellado", "En el inframundo", "En el fondo de una piscina"), 1, "¡Correcto! Hades gobernaba el inframundo.")
            )
        ),
        Level(
            id = 3,
            title = "Tema 3: Dioses olímpicos II",
            subtitle = "Dioses olímpicos II",
            category = "Mitología Clásica",
            description = "Conoce a Atenea, Apolo, Ares y Hermes.",
            colorHex = "#1CB0F6",
            shadowColorHex = "#1899D6",
            unitId = 1,
            badge = Badge("badge_t3", "Olimpo II", "Por dominar la familia de dioses olímpicos", "🦉", "#1CB0F6"),
            slides = listOf(
                Slide("La diosa de la sabiduría", "Atenea nació directamente de la cabeza de Zeus. ¡Es la diosa de la inteligencia y ama a las lechuzas!", "🦉", "athena"),
                Slide("Hermes, el mensajero", "Hermes era el dios más rápido del Olimpo gracias a sus sandalias mágicas con alitas.", "🪽", "hermes")
            ),
            questions = listOf(
                Question(1, "¿Quién es la diosa de la sabiduría?", listOf("Afrodita", "Atenea", "Medusa"), 1, "¡Muy bien! Atenea representa la sabiduría."),
                Question(2, "¿Quién es el dios de la música y el sol?", listOf("Apolo", "Ares", "Hades"), 0, "¡Así es! Apolo tocaba una hermosa lira."),
                Question(3, "¿Qué usaba Hermes para volar?", listOf("Un monopatín eléctrico", "Sandalias con alas mágicas", "Un paracaídas de hojas"), 1, "¡Genial! Sus sandalias voladoras."),
                Question(4, "¿Quién es el impulsivo dios de la guerra?", listOf("Zeus", "Ares", "Poseidón"), 1, "¡Perfecto! Ares representaba la fuerza de la batalla."),
                Question(5, "¿De dónde surgió la diosa Afrodita?", listOf("De la cabeza de Zeus", "De la espuma del mar", "De una manzana de oro"), 1, "¡Correcto! Nació de la espuma marina.")
            )
        ),
        Level(
            id = 4,
            title = "Tema 4: Semidioses - Perseo",
            subtitle = "Perseo",
            category = "Mitología Clásica",
            description = "Descubre la hazaña de Perseo contra Medusa.",
            colorHex = "#1CB0F6",
            shadowColorHex = "#1899D6",
            unitId = 1,
            badge = Badge("badge_t4", "Semidioses", "Por aprender el mito del valiente Perseo", "🛡️", "#1CB0F6"),
            slides = listOf(
                Slide("El héroe de bronce", "Perseo era un valiente semidiós hijo de Zeus. Su misión más famosa fue vencer a la temible Medusa.", "🛡️", "heroes"),
                Slide("El reflejo salvador", "Como mirar a Medusa te convertía en piedra, Perseo usó su escudo brillante como un espejo para guiar su espada.", "🐍", "medusa")
            ),
            questions = listOf(
                Question(1, "¿De quién era hijo el semidiós Perseo?", listOf("De Zeus y una mortal", "De Poseidón", "De un campesino"), 0, "¡Exacto! Era hijo del rey del Olimpo."),
                Question(2, "¿Qué tenía Medusa en la cabeza?", listOf("Serpientes vivas", "Flores de colores", "Un sombrero mágico"), 0, "¡Excelente! Tenía serpientes vivas en vez de cabello."),
                Question(3, "¿Qué le pasaba a quien miraba a Medusa?", listOf("Le daba un ataque de risa", "Se convertía en piedra", "Se volvía invisible"), 1, "¡Así es! Su mirada petrificaba."),
                Question(4, "¿Qué usó Perseo para no verla directamente?", listOf("Un escudo brillante como espejo", "Unas gafas oscuras", "Un pañuelo en los ojos"), 0, "¡Estupendo! El escudo reflejó su silueta."),
                Question(5, "¿Qué ser alado nació de la sangre de Medusa?", listOf("Un dragón rojo", "Pegaso, el caballo con alas", "Una paloma gigante"), 1, "¡Maravilloso! El caballo Pegaso.")
            )
        ),
        Level(
            id = 5,
            title = "Tema 5: Los trabajos de Hércules",
            subtitle = "Los trabajos de Hércules",
            category = "Mitología Clásica",
            description = "Conoce las doce hazañas del héroe más fuerte.",
            colorHex = "#1CB0F6",
            shadowColorHex = "#1899D6",
            unitId = 1,
            badge = Badge("badge_t5", "Hércules", "Por dominar los 12 trabajos de Hércules", "💪", "#1CB0F6"),
            slides = listOf(
                Slide("El gran campeón", "Hércules poseía una fuerza increíble. Para probar su gran valentía, debió cumplir doce trabajos imposibles.", "💪", "hercules"),
                Slide("La Hidra peligrosa", "En su segundo trabajo, Hércules derrotó a la Hidra, un monstruo marino que regeneraba dos cabezas cada vez que perdía una.", "🐉", "hydra")
            ),
            questions = listOf(
                Question(1, "¿Qué cualidad hacía famoso a Hércules?", listOf("Su increíble fuerza física", "Su capacidad de volar", "Su habilidad con el violín"), 0, "¡Correcto! Su fuerza era legendaria."),
                Question(2, "¿Cuántos trabajos imposibles completó?", listOf("3 trabajos", "12 trabajos", "24 trabajos"), 1, "¡Exacto! Doce hazañas legendarias."),
                Question(3, "¿A qué gran león derrotó con sus manos?", listOf("Al León de Nemea", "Al León del Atlas", "Al León de oro"), 0, "¡Muy bien! Su piel era invulnerable."),
                Question(4, "¿Qué hacía especial a la Hidra de Lerna?", listOf("Escupía helado de fresa", "Le crecían dos cabezas por cada una que perdía", "Tenía alas de murciélago"), 1, "¡Genial! Era un monstruo regenerativo."),
                Question(5, "¿Quién le ordenaba los trabajos a Hércules?", listOf("El malvado rey Euristeo", "Su tía Hera", "Un duendecillo del bosque"), 0, "¡Correcto! Euristeo le dio las misiones.")
            )
        ),

        // UNIT 2 (6 to 9)
        Level(
            id = 6,
            title = "Tema 6: La Ilíada",
            subtitle = "La Ilíada",
            category = "Clasicismo",
            description = "Explora la guerra de Troya y la ira de Aquiles.",
            colorHex = "#58CC02",
            shadowColorHex = "#46A302",
            unitId = 2,
            badge = Badge("badge_t6", "La Ilíada", "Por entender los cantos épicos de Homero", "🐴", "#58CC02"),
            slides = listOf(
                Slide("La guerra de diez años", "La Ilíada cuenta los últimos meses de la guerra de Troya. ¡Un gran conflicto entre griegos y troyanos!", "⚔️", "trojan_war"),
                Slide("El gran Aquiles", "Aquiles era el guerrero griego más veloz y poderoso, pero se enojó mucho porque el rey le quitó su recompensa.", "🛡️", "achilles")
            ),
            questions = listOf(
                Question(1, "¿Quién escribió el poema de La Ilíada?", listOf("Homero", "Cervantes", "Shakespeare"), 0, "¡Exacto! El poeta griego Homero."),
                Question(2, "¿De qué trata principalmente La Ilíada?", listOf("Un viaje al espacio", "La guerra de Troya y el enojo de Aquiles", "Una carrera de caballos"), 1, "¡Muy bien! Se enfoca en Troya y Aquiles."),
                Question(3, "¿Cuál era el punto débil de Aquiles?", listOf("Su hombro izquierdo", "Su talón", "Su oreja derecha"), 1, "¡Correcto! Su talón era su único punto débil."),
                Question(4, "¿Quién era el gran amigo de Aquiles cuya muerte lo hizo volver a pelear?", listOf("Patroclo", "Héctor", "Ulises"), 0, "¡Exacto! Patroclo fue defendido por Aquiles."),
                Question(5, "¿Quién era el príncipe troyano que desafió a Aquiles?", listOf("Héctor", "Agamenón", "Perseo"), 0, "¡Sí! Héctor, el héroe de Troya.")
            )
        ),
        Level(
            id = 7,
            title = "Tema 7: Géneros literarios",
            subtitle = "Géneros literarios",
            category = "Clasicismo",
            description = "Distingue entre narrativo, lírico y dramático.",
            colorHex = "#58CC02",
            shadowColorHex = "#46A302",
            unitId = 2,
            badge = Badge("badge_t7", "Géneros", "Por dominar la clasificación de las obras", "📚", "#58CC02"),
            slides = listOf(
                Slide("Los tres caminos", "Las historias se agrupan en géneros: Lírico (poesía y sentimientos), Narrativo (novelas y cuentos) y Dramático (teatro).", "📚", "genres"),
                Slide("El poder del verso", "El género Lírico usa poemas, rimas y canciones, mientras que el Dramático cobra vida en un escenario.", "🎭", "lyrics")
            ),
            questions = listOf(
                Question(1, "¿Qué género narra historias con personajes?", listOf("Lírico", "Narrativo", "Dramático"), 1, "¡Correcto! El Narrativo cuenta relatos."),
                Question(2, "¿Qué género usa poemas para expresar emociones?", listOf("Lírico", "Narrativo", "Científico"), 0, "¡Perfecto! El Lírico expresa el alma."),
                Question(3, "¿Para qué se escribe una obra Dramática?", listOf("Para cantar bajo la ducha", "Para ser representada por actores en un teatro", "Para guardar en una caja fuerte"), 1, "¡Muy bien! El drama vive en las tablas."),
                Question(4, "¿A qué género pertenece una hermosa leyenda de duendes?", listOf("Género Lírico", "Género Narrativo", "Género Dramático"), 1, "¡Excelente! Al ser un relato, es narrativo."),
                Question(5, "¿Cómo se llaman las frases dialogadas en el teatro?", listOf("Notas de prensa", "Guiones o diálogos", "Fórmulas matemáticas"), 1, "¡Eso es! Diálogos de personajes.")
            )
        ),
        Level(
            id = 8,
            title = "Tema 8: Prometeo encadenado",
            subtitle = "Prometeo encadenado",
            category = "Clasicismo",
            description = "Descubre el castigo del titán que robó el fuego.",
            colorHex = "#58CC02",
            shadowColorHex = "#46A302",
            unitId = 2,
            badge = Badge("badge_t8", "Fuego", "Por aprender la tragedia de Prometeo", "🔥", "#58CC02"),
            slides = listOf(
                Slide("El regalo a los hombres", "Prometeo era un titán que amaba a los humanos. Para ayudarlos, robó el fuego sagrado de los dioses y se lo obsequió.", "🔥", "fire_titan"),
                Slide("El terrible castigo", "Zeus se enfureció tanto que encadenó a Prometeo en una montaña fría por siempre.", "⛓️", "chains")
            ),
            questions = listOf(
                Question(1, "¿Qué robó el titán Prometeo de los dioses?", listOf("Una bolsa de monedas", "El fuego sagrado", "Un pastel de manzana"), 1, "¡Exacto! El fuego sagrado."),
                Question(2, "¿A quiénes les regaló el fuego robado?", listOf("A los humanos", "A los duendes del lago", "A los marcianos"), 0, "¡Sí! El fuego ayudó a progresar al hombre."),
                Question(3, "¿Qué dios ordenó castigar a Prometeo?", listOf("Poseidón", "Zeus", "Hermes"), 1, "¡Correcto! Zeus lo castigó severamente."),
                Question(4, "¿Dónde encadenaron a Prometeo?", listOf("En el fondo del océano", "En una fría montaña del Cáucaso", "En una pastelería"), 1, "¡Excelente! Lo ataron a una roca inhóspita."),
                Question(5, "¿Qué animal visitaba a Prometeo en su castigo?", listOf("Un oso pardo", "Un águila gigante", "Un gatito blanco"), 1, "¡Muy bien! Un águila por órdenes de Zeus.")
            )
        ),
        Level(
            id = 9,
            title = "Tema 9: La Eneida",
            subtitle = "La Eneida",
            category = "Clasicismo",
            description = "Acompaña a Eneas a fundar la gran Roma.",
            colorHex = "#58CC02",
            shadowColorHex = "#46A302",
            unitId = 2,
            badge = Badge("badge_t9", "La Eneida", "Por revivir el origen épico de Roma", "🏛️", "#58CC02"),
            slides = listOf(
                Slide("El escape de Troya", "Eneas fue un héroe troyano que logró huir de la destrucción de su ciudad llevando a su familia.", "⛵", "eneas_escape"),
                Slide("La gran profecía", "Los dioses le encomendaron un destino de oro: viajar por el mar para fundar la gran civilización de Roma.", "🏛️", "roma")
            ),
            questions = listOf(
                Question(1, "¿Quién escribió La Eneida?", listOf("Virgilio", "Homero", "Shakespeare"), 0, "¡Exacto! El gran poeta romano Virgilio."),
                Question(2, "¿De qué ciudad escapó el héroe Eneas?", listOf("De Roma", "De Troya", "De Atenas"), 1, "¡Muy bien! Logró huir de Troya ardiente."),
                Question(3, "¿Qué gran imperio fundarían los descendientes de Eneas?", listOf("El Imperio Egipcio", "El Imperio de Roma", "El Imperio del Chocolate"), 1, "¡Perfecto! Eneas es el padre de Roma."),
                Question(4, "¿Quién era la mamá del héroe Eneas?", listOf("La diosa Venus (Afrodita)", "La reina de Troya", "Una ninfa del bosque"), 0, "¡Estupendo! Venus lo protegía del peligro."),
                Question(5, "¿Qué reina africana se enamoró de Eneas en Cartago?", listOf("Cleopatra", "Dido", "Atenea"), 1, "¡Sí! La reina Dido se enamoró perdidamente.")
            )
        ),

        // UNIT 3 (10 to 14)
        Level(
            id = 10,
            title = "Tema 10: Cantar de Mio Cid",
            subtitle = "Cantar de Mio Cid",
            category = "Medioevo y Humanismo",
            description = "La lealtad y honor de Rodrigo Díaz de Vivar.",
            colorHex = "#FF9600",
            shadowColorHex = "#E07B00",
            unitId = 3,
            badge = Badge("badge_t10", "Mio Cid", "Por conocer el cantar de gesta español", "⚔️", "#FF9600"),
            slides = listOf(
                Slide("Un caballero de honor", "Rodrigo Díaz de Vivar, el Cid Campeador, fue desterrado por el rey. ¡Pero nunca perdió su lealtad!", "⚔️", "knight"),
                Slide("Tizona y Colada", "Con sus famosas espadas, el Cid batalló con gran valentía para recuperar la confianza de su monarca.", "🛡️", "cid_swords")
            ),
            questions = listOf(
                Question(1, "¿Cómo llamaban al héroe Rodrigo Díaz de Vivar?", listOf("El Cid Campeador", "El rey de España", "El caballero de la luna"), 0, "¡Exacto! El Cid Campeador."),
                Question(2, "¿Qué castigo le dio el rey al Cid al inicio?", listOf("Le quitó su caballo", "Lo desterró de su tierra", "Lo obligó a cantar"), 1, "¡Muy bien! Lo desterró injustamente."),
                Question(3, "¿Cómo se llamaba una de sus espadas famosas?", listOf("Tizona", "Excalibur", "La espada de madera"), 0, "¡Correcto! Su espada se llamaba Tizona."),
                Question(4, "¿Por qué el Cid enviaba regalos al rey que lo desterró?", listOf("Para burlarse", "Para demostrarle su lealtad constante", "Porque le sobraban riquezas"), 1, "¡Excelente! El Cid demostró nobleza."),
                Question(5, "¿A qué famosa ciudad española logró conquistar el Cid?", listOf("Barcelona", "Valencia", "Madrid"), 1, "¡Sí! Logró tomar la rica ciudad de Valencia.")
            )
        ),
        Level(
            id = 11,
            title = "Tema 11: Cantar de Roldán",
            subtitle = "Cantar de Roldán",
            category = "Medioevo y Humanismo",
            description = "La valiente defensa del caballero francés Roldán.",
            colorHex = "#FF9600",
            shadowColorHex = "#E07B00",
            unitId = 3,
            badge = Badge("badge_t11", "Roldán", "Por aprender el heroísmo carolingio", "🎺", "#FF9600"),
            slides = listOf(
                Slide("El orgullo del rey", "Roldán era el caballero más valiente del emperador Carlomagno. Defendió el paso de montaña de Roncesvalles.", "⚔️", "roldan"),
                Slide("El cuerno Olifante", "Roldán tenía un cuerno de marfil llamado Olifante que soplaba tan fuerte que se oía a kilómetros.", "🎺", "olifant")
            ),
            questions = listOf(
                Question(1, "¿De qué país era el caballero Roldán?", listOf("Francia", "España", "Italia"), 0, "¡Exacto! Un héroe francés de Carlomagno."),
                Question(2, "¿Cómo se llamaba el gran emperador tío de Roldán?", listOf("Carlomagno", "Julio César", "Don Quijote"), 0, "¡Sí! El gran emperador Carlomagno."),
                Question(3, "¿Qué instrumento soplaba Roldán para pedir ayuda?", listOf("Una flauta dulce", "Su cuerno de marfil Olifante", "Un saxofón plateado"), 1, "¡Correcto! El Olifante sonaba con fuerza."),
                Question(4, "¿Cómo se llamaba la espada mágica de Roldán?", listOf("Durandarte", "Tizona", "Excalibur"), 0, "¡Perfecto! Durandarte tenía reliquias divinas."),
                Question(5, "¿Dónde ocurrió la épica batalla de Roldán?", listOf("En el paso de Roncesvalles", "En las playas de Normandía", "En un castillo inflable"), 0, "¡Excelente! En los Pirineos, Roncesvalles.")
            )
        ),
        Level(
            id = 12,
            title = "Tema 12: La Divina Comedia",
            subtitle = "La Divina Comedia",
            category = "Medioevo y Humanismo",
            description = "El fantástico viaje de Dante por tres mundos.",
            colorHex = "#FF9600",
            shadowColorHex = "#E07B00",
            unitId = 3,
            badge = Badge("badge_t12", "Divina Comedia", "Por completar el viaje literario de Dante", "🪵", "#FF9600"),
            slides = listOf(
                Slide("El bosque oscuro", "Dante se perdió en un bosque oscuro y temeroso, pero el poeta Virgilio apareció para guiarlo.", "🌲", "dark_forest"),
                Slide("Hacia la luz", "Dante viajó por el Infierno y el Purgatorio hasta llegar al hermoso Paraíso guiado por su amada Beatriz.", "✨", "paradise")
            ),
            questions = listOf(
                Question(1, "¿Quién escribió La Divina Comedia?", listOf("Dante Alighieri", "Garcilaso", "Cervantes"), 0, "¡Exacto! El poeta italiano Dante."),
                Question(2, "¿Quién guía a Dante al inicio de su viaje?", listOf("Un oso polar", "El sabio poeta Virgilio", "Un hada del bosque"), 1, "¡Muy bien! Virgilio representa la razón."),
                Question(3, "¿Cuáles son los tres reinos que visita Dante?", listOf("Infierno, Purgatorio y Paraíso", "Narnia, el Olimpo y la Atlántida", "La Tierra, Marte y Júpiter"), 0, "¡Perfecto! Los tres reinos de ultratumba."),
                Question(4, "¿Quién lo espera al final en el luminoso Paraíso?", listOf("Su amada Beatriz", "El Cíclope", "Un dragón dorado"), 0, "¡Excelente! Beatriz representa el amor divino."),
                Question(5, "¿En qué idioma original se escribió esta gran obra?", listOf("Inglés moderno", "Italiano (toscano)", "Latín antiguo"), 1, "¡Así es! Dante popularizó la lengua toscana.")
            )
        ),
        Level(
            id = 13,
            title = "Tema 13: El mercader de Venecia",
            subtitle = "El mercader de Venecia",
            category = "Medioevo y Humanismo",
            description = "Shakespeare, los cofres y el valor de la piedad.",
            colorHex = "#FF9600",
            shadowColorHex = "#E07B00",
            unitId = 3,
            badge = Badge("badge_t13", "Mercader", "Por aprender los misterios de los tres cofres", "⚖️", "#FF9600"),
            slides = listOf(
                Slide("El pagaré peligroso", "Para ayudar a su amigo, Antonio firma un contrato con Shylock arriesgando una libra de su propia carne.", "📜", "contract"),
                Slide("La astuta Porcia", "La joven Porcia se disfraza de abogada y salva a Antonio demostrando que la justicia debe tener piedad.", "⚖️", "courtroom")
            ),
            questions = listOf(
                Question(1, "¿Quién escribió El mercader de Venecia?", listOf("William Shakespeare", "Homero", "Molière"), 0, "¡Exacto! El bardo inglés Shakespeare."),
                Question(2, "¿En qué famosa ciudad de canales ocurre la historia?", listOf("Londres", "Venecia", "Roma"), 1, "¡Muy bien! En la histórica Venecia."),
                Question(3, "¿De qué metales eran los cofres del juego de Porcia?", listOf("Oro, plata y plomo", "Hierro, bronce y cobre", "Diamante, rubí y esmeralda"), 0, "¡Correcto! Oro, plata y el humilde plomo."),
                Question(4, "¿Qué pedía Shylock en el contrato si no le pagaban?", listOf("Un saco de perlas", "Una libra de carne de Antonio", "Un barco pirata"), 1, "¡Perfecto! Una libra de su propia carne."),
                Question(5, "¿Cómo se disfraza Porcia para defender a Antonio?", listOf("De bufón del palacio", "De un sabio doctor en leyes o abogado", "De marino mercante"), 1, "¡Excelente! Se vistió de astuto abogado.")
            )
        ),
        Level(
            id = 14,
            title = "Tema 14: Macbeth",
            subtitle = "Macbeth",
            category = "Medioevo y Humanismo",
            description = "La ambición desmedida y las tres brujas.",
            colorHex = "#FF9600",
            shadowColorHex = "#E07B00",
            unitId = 3,
            badge = Badge("badge_t14", "Macbeth", "Por descifrar la profecía de las brujas", "👑", "#FF9600"),
            slides = listOf(
                Slide("La profecía de las brujas", "Tres brujas misteriosas predicen que el valiente guerrero escocés Macbeth se convertirá en rey.", "🧙‍♀️", "witches"),
                Slide("El bosque caminante", "Consumido por la culpa, Macbeth ve cumplirse el último vaticinio cuando el bosque de Birnam avanza hacia su castillo.", "🌲", "birnam_wood")
            ),
            questions = listOf(
                Question(1, "¿En qué país transcurre la obra Macbeth?", listOf("Inglaterra", "Escocia", "Francia"), 1, "¡Exacto! Ocurre en los páramos de Escocia."),
                Question(2, "¿Quiénes le dicen a Macbeth que será rey?", listOf("Tres brujas misteriosas", "El rey actual", "Su caballo parlante"), 0, "¡Muy bien! Las brujas y sus profecías."),
                Question(3, "¿Quién convence a Macbeth de hacer cosas malas?", listOf("Su esposa, Lady Macbeth", "Un duende enano", "Su amigo Banquo"), 0, "¡Correcto! Lady Macbeth lo incitó con ambición."),
                Question(4, "¿Qué bosque parecía caminar hacia el castillo de Macbeth?", listOf("El bosque de Birnam", "El bosque prohibido", "El bosque de los Elfos"), 0, "¡Excelente! Los soldados se camuflaron con ramas."),
                Question(5, "¿Qué tema principal critica esta tragedia?", listOf("La pereza", "La ambición desmedida", "La falta de comida"), 1, "¡Perfecto! Critica la ambición desenfrenada.")
            )
        ),

        // UNIT 4 (15 to 17)
        Level(
            id = 15,
            title = "Tema 15: Miguel de Cervantes",
            subtitle = "Miguel de Cervantes",
            category = "Siglo de Oro Español",
            description = "La vida del escritor de Don Quijote de la Mancha.",
            colorHex = "#A55EEA",
            shadowColorHex = "#883FC7",
            unitId = 4,
            badge = Badge("badge_t15", "Cervantes", "Por conocer al genial 'Manco de Lepanto'", "✍️", "#A55EEA"),
            slides = listOf(
                Slide("El creador del Quijote", "Miguel de Cervantes Saavedra fue un brillante escritor español. ¡Escribió la novela más famosa del mundo!", "✍️", "cervantes"),
                Slide("El Manco de Lepanto", "Cervantes luchó en una gran batalla naval llamada Lepanto, donde hirieron su mano izquierda, ganándose un apodo legendario.", "⚓", "lepanto")
            ),
            questions = listOf(
                Question(1, "¿Qué obra inmortal escribió Cervantes?", listOf("La Ilíada", "Don Quijote de la Mancha", "La Divina Comedia"), 1, "¡Exacto! El Quijote y Sancho Panza."),
                Question(2, "¿Qué apodo recibió Cervantes tras la batalla de Lepanto?", listOf("El Manco de Lepanto", "El caballero de la triste figura", "El rey de la pluma"), 0, "¡Sí! Perdió el uso de su mano izquierda."),
                Question(3, "¿En qué época dorada de España vivió?", listOf("La Edad de Piedra", "El Siglo de Oro Español", "La Revolución Francesa"), 1, "¡Correcto! El Siglo de Oro de las letras."),
                Question(4, "¿Cómo se llamaba el fiel escudero de Don Quijote?", listOf("Sancho Panza", "Rocinante", "Dulcineo"), 0, "¡Excelente! El fiel y regordete Sancho Panza."),
                Question(5, "¿Qué confundía Don Quijote con gigantes malos?", listOf("Molinos de viento", "Ovejas blancas", "Árboles de manzanas"), 0, "¡Perfecto! Atacaba molinos creyendo que eran gigantes.")
            )
        ),
        Level(
            id = 16,
            title = "Tema 16: El licenciado vidriera",
            subtitle = "El licenciado vidriera",
            category = "Siglo de Oro Español",
            description = "El asombroso caso del hombre que creía ser de vidrio.",
            colorHex = "#A55EEA",
            shadowColorHex = "#883FC7",
            unitId = 4,
            badge = Badge("badge_t16", "Vidriera", "Por entender la cordura del hombre de vidrio", "💎", "#A55EEA"),
            slides = listOf(
                Slide("La pócima del amor", "Tomás Rodaja toma una pócima de amor embrujada por accidente. En lugar de enamorarse, cae enfermo y delirante.", "🧪", "potion"),
                Slide("¡Cuidado, me rompo!", "Tomás despierta creyendo que todo su cuerpo es de vidrio delicado. ¡Pedía que no se le acercaran para no quebrarse!", "💎", "glass_man")
            ),
            questions = listOf(
                Question(1, "¿Quién es el autor de El licenciado vidriera?", listOf("Miguel de Cervantes", "Lope de Vega", "William Shakespeare"), 0, "¡Exacto! Es una de sus Novelas Ejemplares."),
                Question(2, "¿Por qué se enferma el protagonista Tomás?", listOf("Por comer chocolate vencido", "Por tomar una pócima embrujada", "Por caer de su caballo"), 1, "¡Muy bien! Un hechizo que salió mal."),
                Question(3, "¿De qué material creía Tomás que era su cuerpo?", listOf("De chocolate crujiente", "De vidrio delicado", "De esponja suave"), 1, "¡Correcto! Pensaba que se rompería en pedazos."),
                Question(4, "¿Cómo vestía Tomás para evitar romperse?", listOf("Con una armadura de hierro", "Con ropas muy anchas y acolchadas", "No usaba ropa"), 1, "¡Excelente! Usaba ropa holgada para amortiguar golpes."),
                Question(5, "¿Qué demostró tener Tomás a pesar de su locura?", listOf("Un gran apetito por los dulces", "Una inteligencia increíble y sabias respuestas", "Miedo a la luz del sol"), 1, "¡Perfecto! Decía verdades ingeniosas que todos admiraban.")
            )
        ),
        Level(
            id = 17,
            title = "Tema 17: «La gitanilla»",
            subtitle = "«La gitanilla»",
            category = "Siglo de Oro Español",
            description = "La bella Preciosa y el poder de la poesía.",
            colorHex = "#A55EEA",
            shadowColorHex = "#883FC7",
            unitId = 4,
            badge = Badge("badge_t17", "Gitanilla", "Por descubrir el romance de Preciosa", "💃", "#A55EEA"),
            slides = listOf(
                Slide("Preciosa la talentosa", "Preciosa es una joven gitana asombrosamente bella, educada y virtuosa que canta coplas hermosas.", "💃", "dancer"),
                Slide("El amor a prueba", "Un joven noble se enamora de ella y acepta vivir dos años como gitano para demostrar que su amor es sincero.", "⚔️", "nobleman")
            ),
            questions = listOf(
                Question(1, "¿De qué libro de Cervantes forma parte esta historia?", listOf("Don Quijote", "Novelas ejemplares", "La Galatea"), 1, "¡Exacto! Es la novela que abre este libro."),
                Question(2, "¿Cómo se llama la hermosa y astuta protagonista?", listOf("Preciosa", "Dulcinea", "Beatriz"), 0, "¡Sí! Se llamaba Preciosa por su gracia."),
                Question(3, "¿Qué gran talento artístico tenía Preciosa?", listOf("Pintaba lienzos gigantes", "Cantaba y recitaba versos divinamente", "Hacía malabares con fuego"), 1, "¡Correcto! Encantaba a todos con su voz."),
                Question(4, "¿Qué condición le puso Preciosa al noble Juan de Cárcamo?", listOf("Comprarle un palacio de mármol", "Vivir dos años con los gitanos bajo el nombre de Andrés", "Ganar una batalla en el mar"), 1, "¡Excelente! Quería probar la verdad de su amor."),
                Question(5, "¿Qué secreto familiar se revela al final?", listOf("Que Preciosa era hija de un noble secuestrada de bebé", "Que todo era un sueño", "Que eran magos"), 0, "¡Perfecto! Se descubre su noble origen y se casan felices.")
            )
        ),

        // UNIT 5 (18 to 21)
        Level(
            id = 18,
            title = "Tema 18: El avaro",
            subtitle = "El avaro",
            category = "Neoclasicismo",
            description = "Harpagón, la arqueta de oro y la codicia cómica.",
            colorHex = "#FF4B4B",
            shadowColorHex = "#EA2B2B",
            unitId = 5,
            badge = Badge("badge_t18", "El avaro", "Por reír y aprender con la comedia de Molière", "💰", "#FF4B4B"),
            slides = listOf(
                Slide("Harpagón y su tesoro", "Harpagón ama a su arqueta llena de monedas de oro más que a sus propios hijos. ¡Es súper avaro!", "💰", "miser"),
                Slide("El robo fingido", "Para darle una lección, le esconden su tesoro, desatando un divertido caos en toda la mansión.", "📦", "hidden_box")
            ),
            questions = listOf(
                Question(1, "¿Quién escribió la obra El avaro?", listOf("Molière", "Shakespeare", "Homero"), 0, "¡Exacto! El gran comediógrafo francés Molière."),
                Question(2, "¿Cómo se llamaba el tacaño protagonista?", listOf("Harpagón", "Tomás", "Antonio"), 0, "¡Sí! Harpagón es el símbolo de la avaricia."),
                Question(3, "¿Qué guardaba Harpagón con tanto recelo?", listOf("Un saco de dulces", "Una arqueta con diez mil escudos de oro", "Un mapa del tesoro"), 1, "¡Correcto! Su querida caja de monedas."),
                Question(4, "¿A quién prefería Harpagón por encima de su familia?", listOf("A su dinero", "A sus caballos", "A su vecino"), 0, "¡Excelente! Valoraba el oro sobre todo."),
                Question(5, "¿Qué tono usa esta magnífica obra teatral?", listOf("Tragedia triste y sangrienta", "Comedia satírica para reír y pensar", "Poesía romántica"), 1, "¡Perfecto! Es una divertida comedia moralizante.")
            )
        ),
        Level(
            id = 19,
            title = "Tema 19: El médico a palos",
            subtitle = "El médico a palos",
            category = "Neoclasicismo",
            description = "Sganarelle y las divertidas mentiras médicas.",
            colorHex = "#FF4B4B",
            shadowColorHex = "#EA2B2B",
            unitId = 5,
            badge = Badge("badge_t19", "Médico", "Por reír con las ocurrencias de Sganarelle", "🩺", "#FF4B4B"),
            slides = listOf(
                Slide("La broma de Martina", "Martina, cansada de su perezoso esposo Sganarelle, les dice a unos criados que él es un médico sabio.", "🪵", "woodcutter"),
                Slide("Receta con humor", "Para obligarlo a curar a una joven que finge estar muda, Sganarelle es golpeado hasta que acepta ser doctor.", "🩺", "doctor")
            ),
            questions = listOf(
                Question(1, "¿Quién es el autor de El médico a palos?", listOf("Molière", "Shakespeare", "Virgilio"), 0, "¡Exacto! Otra divertidísima obra de Molière."),
                Question(2, "¿Cuál era el verdadero oficio de Sganarelle?", listOf("Abogado prestigioso", "Leñador modesto", "Pintor real"), 1, "¡Muy bien! Sganarelle cortaba leña para vivir."),
                Question(3, "¿Por qué Sganarelle acepta curar a la paciente?", listOf("Porque le pagaban con oro", "Porque le daban de palazos si decía que no", "Porque era muy bondadoso"), 1, "¡Correcto! Los palos lo hacían confesar."),
                Question(4, "¿Qué le pasaba a la paciente Lucinda?", listOf("Fingía estar muda para no casarse con un viejo", "Tenía fiebre alta", "Le dolía un diente"), 0, "¡Excelente! Fingía mudez por amor al joven Leandro."),
                Question(5, "¿Cómo termina esta comedia de enredos?", listOf("Todos terminan en prisión", "Se descubren las mentiras y triunfa el amor verdadero", "Huyen a la luna"), 1, "¡Perfecto! Es un final feliz lleno de risas.")
            )
        ),
        Level(
            id = 20,
            title = "Tema 20: El Cid (Pierre Corneille)",
            subtitle = "El Cid",
            category = "Neoclasicismo",
            description = "El dilema del honor y el amor en el teatro francés.",
            colorHex = "#FF4B4B",
            shadowColorHex = "#EA2B2B",
            unitId = 5,
            badge = Badge("badge_t20", "El Cid Drama", "Por descifrar la tragedia neoclásica", "🎭", "#FF4B4B"),
            slides = listOf(
                Slide("El amor herido", "Rodrigo y Jimena se aman profundamente, pero un terrible insulto familiar los obliga a elegir entre honor y amor.", "💔", "tragedy"),
                Slide("El héroe de la patria", "Rodrigo vence a los invasores, ganándose el perdón del rey y el apodo de 'El Cid'.", "⚔️", "hero_rodrigo")
            ),
            questions = listOf(
                Question(1, "¿Quién escribió esta obra dramática de El Cid?", listOf("Pierre Corneille", "Molière", "Cervantes"), 0, "¡Exacto! El dramaturgo francés Corneille."),
                Question(2, "¿Qué gran conflicto sufren los novios Rodrigo y Jimena?", listOf("No tienen dinero", "El honor familiar contra su inmenso amor", "Un naufragio marino"), 1, "¡Muy bien! Deben defender el honor de sus padres."),
                Question(3, "¿Cómo llama el rey a Rodrigo tras su victoria?", listOf("El Cid, que significa 'El Señor'", "El duque del sol", "El rey de las nubes"), 0, "¡Correcto! Lo aclama como el Cid Salvador."),
                Question(4, "¿Quiénes eran los enemigos que invadieron el reino?", listOf("Los marcianos", "Los moros", "Los vikingos"), 1, "¡Excelente! Los moros desembarcaron de noche."),
                Question(5, "¿Cómo se resuelve finalmente la obra?", listOf("Con una triste separación", "El rey otorga tiempo para curar heridas y celebrar la boda", "Rodrigo se vuelve leñador"), 1, "¡Perfecto! El amor triunfa bajo la justicia real.")
            )
        ),
        Level(
            id = 21,
            title = "Tema 21: El sí de las niñas",
            subtitle = "El sí de las niñas",
            category = "Neoclasicismo",
            description = "Moratín y la crítica a los matrimonios arreglados.",
            colorHex = "#FF4B4B",
            shadowColorHex = "#EA2B2B",
            unitId = 5,
            badge = Badge("badge_t21", "El sí", "Por comprender los valores del teatro de Moratín", "🤫", "#FF4B4B"),
            slides = listOf(
                Slide("La joven Francisca", "Doña Irene quiere casar a su joven hija Francisca con Don Diego, un hombre rico pero mucho mayor que ella.", "🤫", "forced_marriage"),
                Slide("La lección de Don Diego", "Al descubrir que Francisca ama al joven Carlos, Don Diego actúa con gran nobleza y cancela el casamiento.", "💡", "noble_diego")
            ),
            questions = listOf(
                Question(1, "¿Quién escribió El sí de las niñas?", listOf("Leandro Fernández de Moratín", "Miguel de Cervantes", "Pierre Corneille"), 0, "¡Exacto! Moratín, gran autor neoclásico español."),
                Question(2, "¿Qué costumbre de su época critica esta obra?", listOf("El desprecio por las flores", "Los matrimonios arreglados por conveniencia de los padres", "Las carreras de carruajes"), 1, "¡Sí! Criticaba casar a jóvenes sin su consentimiento."),
                Question(3, "¿Con quién querían casar a la joven Francisca (Paquita)?", listOf("Con un duendecillo", "Con el anciano adinerado Don Diego", "Con un marinero"), 1, "¡Correcto! Con Don Diego, mucho mayor que ella."),
                Question(4, "¿A quién amaba verdaderamente Paquita?", listOf("Al joven oficial Don Carlos (sobrino de Don Diego)", "A nadie, quería ser monja", "Al leñador del pueblo"), 0, "¡Excelente! Su amor era mutuo y secreto."),
                Question(5, "¿Qué hace Don Diego al conocer la verdad?", listOf("Se enoja y los encierra", "Bendice su amor y propicia el casamiento de los jóvenes", "Huye con el oro"), 1, "¡Perfecto! Demuestra sabiduría y generosidad.")
            )
        ),

        // UNIT 6 (22 to 25)
        Level(
            id = 22,
            title = "Tema 22: Los crímenes de la calle Morgue",
            subtitle = "Los crímenes de la calle Morgue",
            category = "Romanticismo y Realismo",
            description = "Dupin y el nacimiento de la novela de detectives.",
            colorHex = "#00D2D3",
            shadowColorHex = "#00A8A9",
            unitId = 6,
            badge = Badge("badge_t22", "Morgue", "Por resolver el misterio de la calle Morgue", "🔍", "#00D2D3"),
            slides = listOf(
                Slide("Un misterio imposible", "Ocurre un terrible crimen en un cuarto cerrado por dentro. ¡Nadie se explica cómo pudo escapar el culpable!", "🏠", "locked_room"),
                Slide("El genio Dupin", "Auguste Dupin usa la pura lógica para deducir pistas increíbles y dar con el sorprendente sospechoso.", "🔍", "detective")
            ),
            questions = listOf(
                Question(1, "¿Quién escribió este asombroso cuento?", listOf("Edgar Allan Poe", "Tolstoi", "Benito Pérez Galdós"), 0, "¡Exacto! El maestro del misterio Edgar Allan Poe."),
                Question(2, "¿Quién es el brillante detective que resuelve el caso?", listOf("Auguste Dupin", "Sherlock Holmes", "Sancho Panza"), 0, "¡Muy bien! Dupin, el primer gran detective literario."),
                Question(3, "¿Qué hacía misterioso al crimen de la calle Morgue?", listOf("Sucedió en una nave espacial", "Ocurrió en una habitación cerrada herméticamente por dentro", "La policía no quería investigar"), 1, "¡Correcto! Una aparente imposibilidad física."),
                Question(4, "¿Quién resultó ser el sorprendente culpable del crimen?", listOf("Un astuto pirata francés", "Un orangután de Sumatra que escapó de un marinero", "Un fantasma invisible"), 1, "¡Excelente! Dupin dedujo que no era un ser humano."),
                Question(5, "¿Qué género literario fundó este famoso relato?", listOf("La poesía romántica", "El género policial y de detectives", "La ciencia ficción espacial"), 1, "¡Perfecto! Es el origen de la narrativa de detectives.")
            )
        ),
        Level(
            id = 23,
            title = "Tema 23: La caída de la casa Usher",
            subtitle = "La caída de la casa Usher",
            category = "Romanticismo y Realismo",
            description = "La tétrica y melancólica mansión de los Usher.",
            colorHex = "#00D2D3",
            shadowColorHex = "#00A8A9",
            unitId = 6,
            badge = Badge("badge_t23", "Casa Usher", "Por sobrevivir a la mansión Usher", "🏚️", "#00D2D3"),
            slides = listOf(
                Slide("La mansión tétrica", "Un visitante llega a la antigua y sombría casa de su amigo Roderick Usher, rodeada de una atmósfera misteriosa.", "🏚️", "scary_house"),
                Slide("El triste final", "La culpa y la locura consumen a los hermanos Usher, derrumbando físicamente la vieja mansión bajo las aguas oscuras.", "🌩️", "storm")
            ),
            questions = listOf(
                Question(1, "¿Quién es el autor de La caída de la casa Usher?", listOf("Edgar Allan Poe", "Virgilio", "Miguel de Cervantes"), 0, "¡Exacto! Poe, genio del terror psicológico."),
                Question(2, "¿Cómo se describe la atmósfera de la casa?", listOf("Alegre, colorida y llena de sol", "Tétrica, sombría y melancólica", "Moderna y con luces de neón"), 1, "¡Muy bien! Un ambiente de gran pesadumbre."),
                Question(3, "¿Cómo se llamaba el último varón de la familia?", listOf("Roderick Usher", "Tomás", "Eneas"), 0, "¡Correcto! Roderick, afligido de los nervios."),
                Question(4, "¿Qué le sucede a la hermana Madeline Usher?", listOf("Se va de viaje a París", "Sufre catalepsia y es enterrada viva por error", "Se vuelve reina de Escocia"), 1, "¡Excelente! Un espeluznante regreso de la tumba."),
                Question(5, "¿Qué pasa con la mansión al final del cuento?", listOf("La pintan de color rosa", "Se agrieta y se hunde por completo en el lago oscuro", "La convierten en hotel"), 1, "¡Perfecto! La casa colapsa ante la tormenta.")
            )
        ),
        Level(
            id = 24,
            title = "Tema 24: Benito Pérez Galdós",
            subtitle = "Benito Pérez Galdós",
            category = "Romanticismo y Realismo",
            description = "Explora la sociedad española real en el siglo XIX.",
            colorHex = "#00D2D3",
            shadowColorHex = "#00A8A9",
            unitId = 6,
            badge = Badge("badge_t24", "Galdós", "Por comprender la novela realista española", "📰", "#00D2D3"),
            slides = listOf(
                Slide("Retrato de la realidad", "Galdós prefirió escribir sobre la vida real de la gente en las calles de Madrid, dejando atrás la fantasía.", "🏙️", "city_life"),
                Slide("Episodios Nacionales", "Escribió un gran conjunto de relatos históricos para contar las emocionantes batallas de su amada patria.", "📚", "history_books")
            ),
            questions = listOf(
                Question(1, "¿De qué país era el gran escritor Benito Pérez Galdós?", listOf("España", "Rusia", "Francia"), 0, "¡Exacto! El gran novelista canario de España."),
                Question(2, "¿Qué corriente literaria representaba Galdós?", listOf("Mitología Clásica", "Realismo Social e Histórico", "Vanguardismo Futurista"), 1, "¡Muy bien! Mostraba la realidad tal como era."),
                Question(3, "¿En qué ciudad ambientaba la mayoría de sus novelas?", listOf("Londres", "Madrid", "París"), 1, "¡Correcto! Las calles madrileñas cobraban vida."),
                Question(4, "¿Cómo se titulan sus famosas novelas de la historia de España?", listOf("Los Episodios Nacionales", "Los Cuentos de Hadas", "Las Tragedias del Olimpo"), 0, "¡Excelente! 46 novelas históricas memorables."),
                Question(5, "¿Qué valoraba el Realismo sobre la fantasía?", listOf("Los monstruos y magos", "La observación minuciosa y fiel de la realidad", "El uso excesivo de metáforas"), 1, "¡Perfecto! Retrataban la sociedad con precisión.")
            )
        ),
        Level(
            id = 25,
            title = "Tema 25: El realismo ruso: Tolstoi",
            subtitle = "León Tolstoi",
            category = "Romanticismo y Realismo",
            description = "Guerra y paz y las grandes almas rusas.",
            colorHex = "#00D2D3",
            shadowColorHex = "#00A8A9",
            unitId = 6,
            badge = Badge("badge_t25", "Tolstoi", "Por dominar la gran narrativa rusa", "❄️", "#00D2D3"),
            slides = listOf(
                Slide("El gigante de las letras", "León Tolstoi escribió inmensas novelas que retrataban la vida, el invierno ruso y el alma humana.", "❄️", "russia"),
                Slide("Guerra y paz", "Su obra maestra sigue la vida de varias familias rusas durante las batallas contra el ejército de Napoleón.", "⚔️", "war_and_peace")
            ),
            questions = listOf(
                Question(1, "¿De qué origen era el novelista León Tolstoi?", listOf("Ruso", "Francés", "Inglés"), 0, "¡Exacto! El maestro del realismo ruso."),
                Question(2, "¿Cuál es una de sus obras maestras más famosas?", listOf("Guerra y paz", "Macbeth", "La Ilíada"), 0, "¡Sí! Guerra y paz es monumental."),
                Question(3, "¿Qué emperador invadió Rusia en su novela Guerra y paz?", listOf("Julio César", "Napoleón Bonaparte", "Carlomagno"), 1, "¡Correcto! El ejército francés de Napoleón."),
                Question(4, "¿Qué otra célebre novela trágica escribió Tolstoi?", listOf("Anna Karénina", "La Eneida", "El licenciado vidriera"), 0, "¡Excelente! Anna Karénina es un clásico mundial."),
                Question(5, "¿Cómo vivía Tolstoi en sus últimos años de vida?", listOf("En un palacio rodeado de lujos", "De forma muy humilde y austera ayudando a los campesinos", "Viajando por el espacio"), 1, "¡Perfecto! Renunció a sus riquezas por nobleza espiritual.")
            )
        ),

        // UNIT 7 (26 to 28)
        Level(
            id = 26,
            title = "Tema 26: Vanguardismo: Futurismo",
            subtitle = "Futurismo",
            category = "Vanguardismo",
            description = "La adoración de la velocidad, las máquinas y el futuro.",
            colorHex = "#FFC107",
            shadowColorHex = "#D39E00",
            unitId = 7,
            badge = Badge("badge_t26", "Futurismo", "Por comprender la velocidad futurista", "🚀", "#FFC107"),
            slides = listOf(
                Slide("¡Rápido, rápido!", "El Futurismo rompió con el pasado. ¡Les encantaban los trenes, los autos veloces y la tecnología moderna!", "🚀", "speed"),
                Slide("Palabras en libertad", "Eliminaron las reglas antiguas de la poesía para llenarla de sonidos de motores y dinamismo.", "⚡", "energy")
            ),
            questions = listOf(
                Question(1, "¿Qué corriente artística es el Futurismo?", listOf("Un movimiento de Vanguardia", "Un mito de la antigua Grecia", "Una obra del Siglo de Oro"), 0, "¡Exacto! Una vanguardia de principios del siglo XX."),
                Question(2, "¿Qué elementos adoraban los poetas futuristas?", listOf("Las ruinas romanas y las hadas", "La velocidad, las máquinas, aviones y fábricas", "Los bosques oscuros medievales"), 1, "¡Muy bien! Les fascinaba el progreso moderno."),
                Question(3, "¿Quién fundó el Futurismo con un manifiesto?", listOf("Filippo Tommaso Marinetti", "Edgar Allan Poe", "Homero"), 0, "¡Correcto! Marinetti en 1909."),
                Question(4, "¿Qué decía Marinetti sobre un automóvil rugiente?", listOf("Que era feo y ruidoso", "Que era más hermoso que la célebre estatua de la Victoria de Samotracia", "Que pertenecía a los griegos"), 1, "¡Excelente! Valoraban la velocidad moderna sobre el arte antiguo."),
                Question(5, "¿Cómo querían las palabras en la poesía?", listOf("Atadas con reglas estrictas", "Libres de puntuación y con dinamismo", "Escritas solo en latín"), 1, "¡Perfecto! 'Palabras en libertad' era su lema.")
            )
        ),
        Level(
            id = 27,
            title = "Tema 27: El caligrama",
            subtitle = "El caligrama",
            category = "Vanguardismo",
            description = "Poemas visuales donde las palabras dibujan.",
            colorHex = "#FFC107",
            shadowColorHex = "#D39E00",
            unitId = 7,
            badge = Badge("badge_t27", "Caligrama", "Por crear arte con palabras escritas", "🎨", "#FFC107"),
            slides = listOf(
                Slide("Pintar con letras", "Un caligrama es un poema visual. En lugar de escribir líneas rectas, las palabras forman un dibujo hermoso.", "🎨", "poetry_draw"),
                Slide("El gran Apollinaire", "El poeta francés Guillaume Apollinaire hizo famosos estos poemas dibujando sombreros, lluvias y palomas.", "🕊️", "apollinaire")
            ),
            questions = listOf(
                Question(1, "¿Qué es un caligrama?", listOf("Un poema cuyas letras forman un dibujo alusivo al tema", "Una carta secreta escrita en código", "Una estatua de mármol"), 0, "¡Exacto! Las palabras dibujan el tema."),
                Question(2, "¿Qué poeta vanguardista los hizo sumamente famosos?", listOf("Guillaume Apollinaire", "Virgilio", "Dante"), 0, "¡Muy bien! Apollinaire creó maravillosos caligramas."),
                Question(3, "Si el poema habla de un perrito, ¿qué forma tendrá el caligrama?", listOf("La forma de un barco", "La silueta de un lindo perrito", "Un círculo perfecto"), 1, "¡Correcto! El dibujo imita el objeto del poema."),
                Question(4, "¿A qué gran familia artística pertenecen los caligramas?", listOf("Al Vanguardismo del siglo XX", "A la Épica Clásica", "Al Neoclasicismo"), 0, "¡Excelente! Es una audaz innovación visual vanguardista."),
                Question(5, "¿Qué sentidos se combinan al leer un caligrama?", listOf("La vista y la lectura poética", "El oído y el olfato", "El gusto y el tacto"), 0, "¡Perfecto! Es poesía visual y textual al mismo tiempo.")
            )
        ),
        Level(
            id = 28,
            title = "Tema 28: Madre Coraje y sus hijos",
            subtitle = "Madre Coraje",
            category = "Vanguardismo",
            description = "Bertolt Brecht, la carreta y la lección sobre la guerra.",
            colorHex = "#FFC107",
            shadowColorHex = "#D39E00",
            unitId = 7,
            badge = Badge("badge_t28", "Madre Coraje", "Por graduarte con honores del teatro de Brecht", "🛒", "#FFC107"),
            slides = listOf(
                Slide("La carreta de la guerra", "Anna Fierling, apodada Madre Coraje, recorre los campos de batalla vendiendo comida y provisiones en su carreta.", "🛒", "cart"),
                Slide("El teatro de la reflexión", "Bertolt Brecht escribió esta obra para hacernos pensar de forma crítica y no solo llorar por los personajes.", "💡", "critical_thinking")
            ),
            questions = listOf(
                Question(1, "¿Quién escribió Madre Coraje y sus hijos?", listOf("Bertolt Brecht", "William Shakespeare", "Molière"), 0, "¡Exacto! El gran dramaturgo alemán Bertolt Brecht."),
                Question(2, "¿Qué tira de forma constante Madre Coraje a lo largo de la obra?", listOf("Su pesada carreta de mercancías", "Un trineo en la nieve", "Un velero de madera"), 0, "¡Sí! Su carreta representa el comercio de la guerra."),
                Question(3, "¿Cómo obtiene sus ganancias Anna Fierling?", listOf("De la agricultura pacífica", "Vendiendo víveres a los soldados en plena guerra", "Buscando oro enterrado"), 1, "¡Correcto! Saca provecho del conflicto armado."),
                Question(4, "¿Qué técnica teatral usaba Brecht para hacernos reflexionar?", listOf("El efecto de distanciamiento crítico", "La magia con espejos reales", "Escribir en versos misteriosos"), 0, "¡Excelente! Quería que el espectador analice racionalmente."),
                Question(5, "¿Qué trágico precio paga Madre Coraje por comerciar con la guerra?", listOf("Pierde todas sus monedas de oro", "La pérdida de sus tres queridos hijos", "Se le rompe la rueda de la carreta"), 1, "¡Perfecto! Pierde trágicamente a su descendencia.")
            )
        )
    )

    // 7 units
    val units = listOf(
        LiteratureUnit(
            id = 1,
            title = "Unidad 1: Mitología Clásica y Orígenes del Cosmos",
            description = "Aprende sobre el Caos, el Olimpo, Perseo y las hazañas de Hércules.",
            colorHex = "#1CB0F6",
            shadowColorHex = "#1899D6",
            levels = levels.filter { it.unitId == 1 },
            exam = UnitExam(
                unitId = 1,
                title = "Examen de la Unidad 1",
                questions = listOf(
                    Question(1, "¿Quién personificaba a la Madre Tierra en la mitología griega?", listOf("Hera", "Gea", "Atenea"), 1, "¡Correcto! Gea representaba a la Tierra."),
                    Question(2, "¿Cuál era el palacio de los dioses en las nubes?", listOf("El Monte Olimpo", "La Casa Usher", "Ítaca"), 0, "¡Excelente! Vivían en el Monte Olimpo."),
                    Question(3, "¿Quién era el veloz mensajero de sandalias aladas?", listOf("Apolo", "Ares", "Hermes"), 2, "¡Perfecto! Hermes llevaba los mensajes."),
                    Question(4, "¿Qué héroe venció a Medusa usando su escudo brillante?", listOf("Hércules", "Perseo", "Eneas"), 1, "¡Estupendo! Perseo la venció con el reflejo."),
                    Question(5, "¿Cuántos trabajos imposibles cumplió Hércules?", listOf("5 trabajos", "12 trabajos", "7 trabajos"), 1, "¡Sí! Cumplió 12 famosas misiones."),
                    Question(6, "¿Qué monstruo de muchas cabezas regenerativas derrotó Hércules?", listOf("El Minotauro", "La Hidra de Lerna", "La Medusa"), 1, "¡Genial! Venció a la Hidra quemando sus cuellos."),
                    Question(7, "¿Quién representaba al cielo estrellado surgido de Gea?", listOf("Urano", "Zeus", "Poseidón"), 0, "¡Espectacular! Urano era el firmamento.")
                )
            )
        ),
        LiteratureUnit(
            id = 2,
            title = "Unidad 2: Fundamentos y Epopeyas del Clasicismo",
            description = "La Ilíada de Homero, los géneros poéticos, Prometeo y Eneas.",
            colorHex = "#58CC02",
            shadowColorHex = "#46A302",
            levels = levels.filter { it.unitId == 2 },
            exam = UnitExam(
                unitId = 2,
                title = "Examen de la Unidad 2",
                questions = listOf(
                    Question(1, "¿Quién escribió el célebre poema de La Ilíada?", listOf("Virgilio", "Homero", "Corneille"), 1, "¡Exacto! El poeta ciego Homero."),
                    Question(2, "¿Cuál era el único punto vulnerable del gran guerrero Aquiles?", listOf("Su codo derecho", "Su talón", "Su nariz"), 1, "¡Excelente! Su talón no fue bañado en agua sagrada."),
                    Question(3, "¿Qué género literario se escribe expresamente para ser actuado por actores?", listOf("Género Lírico", "Género Dramático (Teatro)", "Género Narrativo"), 1, "¡Muy bien! El drama se representa en el escenario."),
                    Question(4, "¿Qué valioso elemento robó Prometeo para regalarlo a los hombres?", listOf("El fuego sagrado", "Una espada de oro", "La manzana de la discordia"), 0, "¡Perfecto! Robó el fuego para iluminar al hombre."),
                    Question(5, "¿Qué emperador y patria fundarían los hijos de Eneas?", listOf("Grecia clásica", "La gran Roma", "El imperio de Cartago"), 1, "¡Genial! Eneas es el ancestro de Roma."),
                    Question(6, "¿Quién escribió la troyana y romana obra de La Eneida?", listOf("Virgilio", "Homero", "Molière"), 0, "¡Estupendo! Virgilio escribió esta epopeya latina."),
                    Question(7, "¿Qué animal visitaba al encadenado Prometeo por orden de Zeus?", listOf("Un león salvaje", "Un águila gigante", "Una serpiente"), 1, "¡Así es! Un águila que lo atormentaba.")
                )
            )
        ),
        LiteratureUnit(
            id = 3,
            title = "Unidad 3: Épica, Narrativa Medieval y Humanismo",
            description = "El Cid, Roldán, la Divina Comedia y el teatro de Shakespeare.",
            colorHex = "#FF9600",
            shadowColorHex = "#E07B00",
            levels = levels.filter { it.unitId == 3 },
            exam = UnitExam(
                unitId = 3,
                title = "Examen de la Unidad 3",
                questions = listOf(
                    Question(1, "¿Cómo se llamaba la espada brillante del Cid Campeador?", listOf("Excalibur", "Tizona", "Durandarte"), 1, "¡Exacto! La espada Tizona."),
                    Question(2, "¿Qué instrumento soplaba Roldán en Roncesvalles?", listOf("Un violín de madera", "Su cuerno de marfil Olifante", "Un silbato"), 1, "¡Muy bien! El cuerno Olifante."),
                    Question(3, "¿Quién guía a Dante por el Infierno y el Purgatorio?", listOf("El sabio poeta Virgilio", "Su amada Beatriz", "El Cid"), 0, "¡Perfecto! Virgilio representa la razón humana."),
                    Question(4, "¿En qué ciudad italiana transcurre el drama de Porcia y Antonio?", listOf("Florencia", "Venecia", "Roma"), 1, "¡Excelente! Transcurre en Venecia."),
                    Question(5, "¿Qué ambición ciega al guerrero escocés Macbeth?", listOf("El deseo de viajar al espacio", "La profecía que le anunciaba que sería rey", "El amor por las flores"), 1, "¡Genial! La profecía de las brujas lo tentó."),
                    Question(6, "¿Quién escribió la tétrica tragedia de Macbeth?", listOf("William Shakespeare", "Dante Alighieri", "Molière"), 0, "¡Sí! El gran William Shakespeare."),
                    Question(7, "¿Quién representa el amor puro que guía a Dante al Paraíso?", listOf("Beatriz", "Lady Macbeth", "Jimena"), 0, "¡Estupendo! Su amada Beatriz.")
                )
            )
        ),
        LiteratureUnit(
            id = 4,
            title = "Unidad 4: El Siglo de Oro Español y la Narrativa Novelesca",
            description = "La pluma de Cervantes, Tomás Vidriera y la bella Preciosa.",
            colorHex = "#A55EEA",
            shadowColorHex = "#883FC7",
            levels = levels.filter { it.unitId == 4 },
            exam = UnitExam(
                unitId = 4,
                title = "Examen de la Unidad 4",
                questions = listOf(
                    Question(1, "¿Qué famoso apodo recibió Cervantes por su herida militar?", listOf("El caballero de la triste figura", "El Manco de Lepanto", "El Cid de las letras"), 1, "¡Exacto! Manco de Lepanto por la batalla naval."),
                    Question(2, "¿Qué confunde Don Quijote con gigantes malvados?", listOf("Castillos medievales", "Molinos de viento", "Ovejas blancas"), 1, "¡Muy bien! Atacaba los molinos de viento."),
                    Question(3, "¿De qué material creía Tomás Rodaja que era su cuerpo?", listOf("De fino cristal o vidrio", "De chocolate dulce", "De esponja"), 0, "¡Perfecto! Creía ser de vidrio delicado."),
                    Question(4, "¿Cómo se llamaba la hermosa y talentosa gitana de Cervantes?", listOf("Dulcinea", "Preciosa", "Francisca"), 1, "¡Excelente! Se llamaba Preciosa."),
                    Question(5, "¿A qué género pertenece El licenciado vidriera?", listOf("Una novela ejemplar de Cervantes", "Un poema de amor lírico", "Una tragedia teatral"), 0, "¡Genial! Es una de las Novelas Ejemplares."),
                    Question(6, "¿Qué noble se disfraza de gitano para amar a Preciosa?", listOf("Rodrigo", "Juan de Cárcamo (Andrés)", "Tomás"), 1, "¡Así es! Cambió su nombre por Andrés."),
                    Question(7, "¿Cuál era el nombre del fiel caballo flaco de Don Quijote?", listOf("Rocinante", "Sancho", "Baciega"), 0, "¡Perfecto! Rocinante era su corcel.")
                )
            )
        ),
        LiteratureUnit(
            id = 5,
            title = "Unidad 5: El Siglo de las Luces: Neoclasicismo Francés e Hispano",
            description = "Las sátiras de Molière, el drama de Corneille y el sí de Moratín.",
            colorHex = "#FF4B4B",
            shadowColorHex = "#EA2B2B",
            levels = levels.filter { it.unitId == 5 },
            exam = UnitExam(
                unitId = 5,
                title = "Examen de la Unidad 5",
                questions = listOf(
                    Question(1, "¿Qué tacaño protagonista adora su arqueta de monedas de oro?", listOf("Harpagón", "Sganarelle", "Don Diego"), 0, "¡Exacto! Harpagón, el gran avaro."),
                    Question(2, "¿A qué se dedicaba Sganarelle antes de ser obligado a actuar como médico?", listOf("Era abogado real", "Era un humilde leñador", "Era panadero"), 1, "¡Muy bien! Sganarelle cortaba leña."),
                    Question(3, "¿Quién escribió las geniales comedias El avaro y El médico a palos?", listOf("Pierre Corneille", "Molière", "Moratín"), 1, "¡Perfecto! El maestro francés Molière."),
                    Question(4, "¿Qué dilema moral sufren Rodrigo y Jimena en el drama de Corneille?", listOf("El honor de sus familias contra su profundo amor", "No saber a dónde viajar", "Perder su fortuna de oro"), 0, "¡Excelente! El honor contra el amor."),
                    Question(5, "¿Qué costumbre social critica Moratín en El sí de las niñas?", listOf("Comer postres caros", "Los matrimonios forzados arreglados por padres", "La mala educación vial"), 1, "¡Genial! Abogaba por la libertad de elegir pareja."),
                    Question(6, "¿Quién actúa con nobleza al final de El sí de las niñas cancelando el arreglo?", listOf("La madre Irene", "El anciano Don Diego", "El leñador Sganarelle"), 1, "¡Así es! Don Diego facilitó la felicidad de los jóvenes."),
                    Question(7, "¿De qué país era el dramaturgo Leandro Fernández de Moratín?", listOf("Francia", "España", "Rusia"), 1, "¡Maravilloso! Un ilustrado español.")
                )
            )
        ),
        LiteratureUnit(
            id = 6,
            title = "Unidad 6: Del Romanticismo Oscuro al Realismo Social",
            description = "El terror de Poe, el realismo de Galdós y el invierno de Tolstoi.",
            colorHex = "#00D2D3",
            shadowColorHex = "#00A8A9",
            levels = levels.filter { it.unitId == 6 },
            exam = UnitExam(
                unitId = 6,
                title = "Examen de la Unidad 6",
                questions = listOf(
                    Question(1, "¿Quién resolvió el misterio de la habitación cerrada de la calle Morgue?", listOf("Sherlock Holmes", "Auguste Dupin", "Harpagón"), 1, "¡Exacto! El detective Auguste Dupin."),
                    Question(2, "¿Qué animal resultó ser el responsable de los crímenes de la calle Morgue?", listOf("Un gorila gigante", "Un orangután de Sumatra", "Un gato negro"), 1, "¡Excelente! Un animal que imitaba a su dueño."),
                    Question(3, "¿Qué sucede finalmente con la mansión de los hermanos Usher?", listOf("La remodelan", "Se agrieta y se hunde en el lago oscuro", "Es devorada por el fuego"), 1, "¡Perfecto! Se derrumbó tétrica de melancolía."),
                    Question(4, "¿Quién escribió La caída de la casa Usher?", listOf("Edgar Allan Poe", "León Tolstoi", "Benito Pérez Galdós"), 0, "¡Estupendo! El genio del suspenso Edgar Allan Poe."),
                    Question(5, "¿Qué retrataba minuciosamente Benito Pérez Galdós en sus novelas?", listOf("Mundos mágicos con hadas", "La vida real de la gente en las calles de Madrid", "Viajes al espacio sideral"), 1, "¡Genial! Mostraba el realismo social de su época."),
                    Question(6, "¿Qué célebre obra histórica relata la defensa nacional española según Galdós?", listOf("Los Episodios Nacionales", "La Ilíada", "Novelas Ejemplares"), 0, "¡Así es! Una vasta epopeya histórica en novelas."),
                    Question(7, "¿De quién es la monumental novela realista Guerra y paz?", listOf("Fiodor Dostoievski", "León Tolstoi", "Edgar Allan Poe"), 1, "¡Maravilloso! León Tolstoi, el titán ruso.")
                )
            )
        ),
        LiteratureUnit(
            id = 7,
            title = "Unidad 7: Rupturas Vanguardistas y Teatro del Siglo XX",
            description = "La adoración tecnológica del Futurismo, los caligramas y Madre Coraje.",
            colorHex = "#FFC107",
            shadowColorHex = "#D39E00",
            levels = levels.filter { it.unitId == 7 },
            exam = UnitExam(
                unitId = 7,
                title = "Examen de la Unidad 7",
                questions = listOf(
                    Question(1, "¿Qué corriente amaba la tecnología, los motores y los trenes veloces?", listOf("El Neoclasicismo", "El Futurismo vanguardista", "El Romanticismo oscuro"), 1, "¡Exacto! Les fascinaba el mañana y las máquinas."),
                    Question(2, "¿Quién fundó el Futurismo publicando su manifiesto en 1909?", listOf("Filippo Tommaso Marinetti", "Guillaume Apollinaire", "Bertolt Brecht"), 0, "¡Muy bien! Filippo Marinetti."),
                    Question(3, "¿Qué es un caligrama?", listOf("Un poema cuyas letras dibujan el objeto del que habla", "Una obra de teatro muda", "Un tipo de pintura al óleo"), 0, "¡Perfecto! Poesía visual donde el texto dibuja."),
                    Question(4, "¿Qué poeta vanguardista hizo famosos los caligramas literarios?", listOf("Guillaume Apollinaire", "Homero", "Molière"), 0, "¡Excelente! Apollinaire innovó con estas formas visuales."),
                    Question(5, "¿De qué forma constante tira Anna Fierling en la obra de Brecht?", listOf("De una barca de madera", "De su pesada carreta de provisiones", "De un carruaje real"), 1, "¡Genial! Su carreta representa el negocio de la guerra."),
                    Question(6, "¿Quién escribió el drama reflexivo Madre Coraje y sus hijos?", listOf("Bertolt Brecht", "William Shakespeare", "Leandro de Moratín"), 0, "¡Así es! El gran dramaturgo alemán Bertolt Brecht."),
                    Question(7, "¿Qué trágico precio pagó Madre Coraje por beneficiarse del conflicto bélico?", listOf("Perdió toda su salud", "La pérdida lamentable de sus tres queridos hijos", "Tener que vender su carreta"), 1, "¡Increíble! Perdió a su familia por lucrar con la guerra.")
                )
            )
        )
    )
}
