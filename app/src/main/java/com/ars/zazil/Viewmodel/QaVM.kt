package com.ars.zazil.Viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class Qa(val pregunta: String, val respuesta: String)

class PreguntasViewModel : ViewModel() {
    private val _preguntas = MutableStateFlow<List<Qa>>(emptyList())
    val preguntas: StateFlow<List<Qa>> = _preguntas

    init {
        // Cargar preguntas pregeneradas
        _preguntas.value = listOf(
            Qa(pregunta = "¿Qué es menstruación?", respuesta = "La menstruación es un fenómeno que se produce de forma cíclica, cada vez que una mujer ovula, pero no se queda embarazada."),
            Qa(pregunta = "¿Qué es la ovulación?", respuesta = "Es el nombre del proceso en el que los cambios hormonales hacen que un ovario libere un óvulo y se produce normalmente una vez en cada ciclo menstrual. Solo puede quedarse embarazada si un espermatozoide fecunda un óvulo. La ovulación se produce, generalmente, de 12 a 16 días antes del inicio del siguiente periodo."),
            Qa(pregunta = "¿Cuándo es más fértil una mujer?", respuesta = "Los días de mayor fertilidad de cada ciclo y, por tanto, en los que tiene mayor probabilidad de quedarse embarazada tras tener relaciones sexuales sin protección, son el día de la ovulación y el día previo."),
            Qa(pregunta = "¿Cuántos días tiene un ciclo menstrual normal?", respuesta = "En la mayoría de los casos, el ciclo menstrual tiene entre 28 y 35 días. Sin embargo, los ciclos menstruales que tienen entre 24 y 38 días de diferencia todavía se consideran «normales»."),
            Qa(pregunta = "¿Cuántos días debe durar la menstruación?", respuesta = "En la mayoría de los casos, el período menstrual dura de 4 a 6 días. Sin embargo, hasta 8 días de pérdidas menstruales se consideran normales."),
            Qa(pregunta = "¿Cómo se llama la primera menstruación?", respuesta = "El primer periodo menstrual se llama menarca."),
            Qa(pregunta = "¿Cuáles son las principales causas de la menstruación retrasada?", respuesta = "La principal causa de retraso menstrual es el embarazo. Sin embargo, infecciones, uso de medicamentos, estrés, cambios significativos de peso corporal, cambios hormonales, también pueden ser causa de un retraso."),
            Qa(pregunta = "¿Cuál es la edad normal para el primer período?", respuesta = "Se considera normal cuando la menarquia se presenta entre los 9 y los 15 años."),
            Qa(pregunta = "¿Cuál es la edad normal para la menopausia?", respuesta = "La mayoría de las mujeres presenta la menopausia entre los 45 y 55 años de edad, con un promedio de 51 años. Cuando la menopausia llega antes de 40 años, ella se llama menopausia temprana, porque los ovarios han fallado antes de lo habitual."),
            Qa(pregunta = "¿Cuál es el color normal de la menstruación?", respuesta = "Puede comenzar con un color más oscuro y poco flujo. A medida que aumenta el flujo menstrual, se volverá más rojizo y también puede ser rosado o vino tinto. Al final, con la reducción del flujo, la menstruación vuelve a ponerse un poco marrón, ya que la sangre que tarda en ser expulsada se oscurece con el paso de los días."),
            Qa(pregunta = "¿Es posible quedarse embarazada mientras se está menstruando?", respuesta = "Sí, pero es inusual. Los espermatozoides de buena calidad pueden permanecer viables en el tracto ginecológico de la mujer por hasta 7 días. Si la mujer tiene un corto ciclo y ha tenido relaciones sexuales al final del período, ella puede ovular dentro de estos 7 días y así correr el riesgo de quedarse embarazada."),
            Qa(pregunta = "¿Cuál es la cantidad de sangre que se pierde normalmente en la menstruación?", respuesta = "En promedio, las mujeres pierden aproximadamente de 30 a 50 ml de sangre en cada ciclo menstrual. El límite que se considera normal es de aproximadamente 80 ml por ciclo."),
            Qa(pregunta = "¿Cuáles son los factores a considerar para decir que un sangrado menstrual está siendo anormal?", respuesta = "1. Duración mayor de 8 días.\n" +
                    "\n" +
                    "2. Necesidad de cambiar el absorbente más de 6 veces al día.\n" +
                    "\n" +
                    "3. Ciclos menstruales que se producen con intervalos de menos de 24 días.\n" +
                    "\n" +
                    "4. Impresión de que el flujo menstrual es mucho más grande de lo habitual, inclusive si no cabe en las 3 condiciones anteriores."),
            Qa(pregunta = "¿Qué días somos más fértiles dentro de nuestro ciclo menstrual? ", respuesta = "Los proveedores de atención médica recomiendan que las parejas que estén tratando de tener un bebé tengan relaciones sexuales entre los días 7 y 20 del ciclo menstrual de la mujer. El día 1 es el primer día de sangrado."),
            Qa(pregunta = "¿Cuántas toallas desechables ocupa una mujer en su ciclo menstrual?", respuesta = "Tomando en cuenta la recomendación de usar una toalla cada cuatro horas durante el periodo, por 5 días regulares, tenemos que, al mes, utilizará 30 toallas. El cálculo para un año es de 360. Si esta mujer llega a la menopausia a los 50 años y comenzó su ciclo a los 13, significa que utilizará 13 mil 320 toallas femeninas durante su vida."),
            Qa(pregunta = "¿Cuánto tarda en degradarse una toalla femenina desechable?", respuesta = "Tardan entre 500 y 800 años en degradarse y que, una vez que han cumplido su labor, terminan en vertederos, cursos de agua o sencillamente en nuestros océanos."),
            Qa(pregunta = "¿Cuánto tiempo dura la menstruación en la vida de una mujer?", respuesta = "Para una mujer, la regla será una compañera de vida, mes tras mes, durante unos 40 años."),
            Qa(pregunta = "¿Qué daños causa usar toallas femeninas desechables?", respuesta = "Las investigaciones han vinculado esta práctica con un incremento del riesgo de infecciones bacterianas y por levaduras, enfermedad inflamatoria de la pelvis, cáncer cervical, incremento de la transmisión de ETS y otras consecuencias adversas para la salud."),
            Qa(pregunta = "Toxinas encontradas en tampones y toallas femeninas desechables:", respuesta = "•\tAluminio\n" +
                    "•\tAlcohol\n" +
                    "•\tFragancias\n" +
                    "•\tHidrocarbonos\n" +
                    "•\tDioxina (componente químico relacionado al proceso de blanqueamiento)\n" +
                    "•\tPlástico\n" +
                    "•\tCloruro de metileno\n" +
                    "•\tXileno\n" +
                    "•\tFtalatos\n" +
                    "•\tTolueno"),
            Qa(pregunta = "¿Cuánto dinero gasta una mujer en toallas femeninas desechables?", respuesta = "\$26,000 pesos sería el gasto promedio en toallas sanitarias a lo largo de la vida si se considera que el precio es de $2 a $3 por cada una; o $30,000 en tampones si se considera que el precio promedio es de $4 por cada uno.\n"),
        )
    }
}