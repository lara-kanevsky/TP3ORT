
const { Screen } = require("../models/screen");
const { Option } = require("../models/option");
const { EditText } = require("../models/editText");
const { Category } = require("../models/category");

class IndividualFormRepository {
    constructor() {
        this.formId = 1;
        this.formPersonType = "individual";
        this.label = "Individuo";
        this.formSharingId = "1";
        this.adminCreatorId = "1";
        this.isActive = true;
        this.screenCount = 6;
        this.screens = [];

        this.screens.push(
            new Screen(
                "RadioButton", 1, "1", 1, 10, false, Category.TRANSPORT, "En el último año, ¿cuántos días a la semana viajaste a tu lugar de trabajo?",
                [
                    new Option("RadioButton", 1, "Ninguno", 1.0, "6", "6", 1, "N/A"),
                    new Option("RadioButton", 2, "1", 1.0, "2", "2", 1, "N/A"),
                    new Option("RadioButton", 3, "2", 1.0, "2", "2", 1, "N/A"),
                    new Option("RadioButton", 4, "3", 1.0, "2", "2", 1, "N/A"),
                    new Option("RadioButton", 5, "4", 1.0, "2", "2", 1, "N/A"),
                    new Option("RadioButton", 6, "5", 1.0, "2", "2", 1, "N/A"),
                    new Option("RadioButton", 7, "6", 1.0, "2", "2", 1, "N/A"),
                    new Option("RadioButton", 8, "7", 1.0, "2", "2", 1, "N/A"),
                ],
            )
        );

        this.screens.push(
            new Screen(
                "EditText", 2, "2", 1, 25, false, Category.TRANSPORT, "¿Cuál es la distancia estimada en km entre tu casa y el lugar de trabajo?",
                [
                    new EditText("EditText", 9, "Ingresá los KM", 1.0, "3", "3", 2, "KM"),
                ],
            )
        );

        this.screens.push(
            new Screen(
                "RadioButton", 3, "3", 1, 40, false, Category.TRANSPORT, "¿Cuál fue el medio de transporte que más usaste en el último año?",
                [
                    new Option("RadioButton", 10, "Auto", 1.0, "4", "4", 3, "N/A"),
                    new Option("RadioButton", 11, "Moto", 1.0, "4", "4", 3, "N/A"),
                    new Option("RadioButton", 12, "Autobús", 1.0, "5", "5", 3, "N/A"),
                    new Option("RadioButton", 13, "Tren", 1.0, "5", "5", 3, "N/A"),
                    new Option("RadioButton", 14, "Metro", 1.0, "5", "5", 3, "N/A"),
                    new Option("RadioButton", 15, "Bicicleta", 1.0, "5", "5", 3, "N/A"),
                    new Option("RadioButton", 16, "Caminando", 1.0, "5", "5", 3, "N/A"),
                ],
            )
        );

        this.screens.push(
            new Screen(
                "RadioButton", 4, "4", 1, 55, false, Category.TRANSPORT, "¿Qué tipo de combustible usa el vehículo?",
                [
                    new Option("RadioButton", 17, "Nafta", 1.0, "5", "5", 4, "N/A"),
                    new Option("RadioButton", 18, "Diesel", 1.0, "5", "5", 4, "N/A"),
                    new Option("RadioButton", 19, "Gas natural", 1.0, "5", "5", 4, "N/A"),
                    new Option("RadioButton", 20, "Eléctrico", 1.0, "5", "5", 4, "N/A"),
                ],
            )
        );

        this.screens.push(
            new Screen(
                "EditText", 5, "5", 1, 70, false, Category.TRANSPORT, "¿Cuántos vuelos realizaste en los últimos 12 meses?",
                [
                    new EditText("EditText", 21, "Menores a 2.5 hs", 1.0, "6", "6", 5, "hs"),
                    new EditText("EditText", 22, "Entre 2.5 y 10 hs", 1.0, "6", "6", 5, "hs"),
                    new EditText("EditText", 23, "Mayores a 10 hs", 1.0, "6", "6", 5, "hs"),
                ],
            )
        );

        this.screens.push(
            new Screen(
                "RadioButton", 6, "6", 1, 85, true, Category.HABITS, "¿Separaste los residuos reciclables?",
                [
                    new Option("RadioButton", 24, "Sí", 1.0, "-1", "-1", 6, "N/A"),
                    new Option("RadioButton", 25, "No", 1.0, "-1", "-1", 6, "N/A"),
                ],
            )
        );
    }
}

class EnterpriseFormRepository {
    constructor() {
        this.formId = 2;
        this.formPersonType = "enterprise";
        this.label = "Empresa";
        this.formSharingId = "2";
        this.adminCreatorId = "2";
        this.isActive = true;
        this.screenCount = 5;
        this.screens = [];

        this.screens.push(
            new Screen(
                "EditText", 1, "1", 2, 1, false, Category.CONSUMPTION, "Detalle las cantidades consumidas para cada tipo de combustible",
                [
                    new EditText("EditText", 26, "Gas natural", 1.0, "2", "2", 1, "m3"),
                    new EditText("EditText", 27, "Diesel/gasoil", 1.0, "2", "2", 1, "lts"),
                    new EditText("EditText", 28, "GLP/gas envasado", 1.0, "2", "2", 1, "kg"),
                    new EditText("EditText", 29, "Nafta", 1.0, "2", "2", 1, "lts"),
                ],
            )
        );

        this.screens.push(
            new Screen(
                "EditText", 2, "2", 2, 20, false, Category.CONSUMPTION, "Detalle la cantidad de electricidad consumida en todas las instalaciones",
                [
                    new EditText("EditText", 30, "Electricidad", 1.0, "3", "3", 2, "kWs"),
                ],
            )
        );

        this.screens.push(
            new Screen(
                "EditText", 3, "3", 2, 40, false, Category.TRANSPORT, "Estime la sumatoria de km recorridos por sus empleados en cada medio de transporte",
                [
                    new EditText("EditText", 31, "Autobus", 1.0, "4", "4", 3, "km"),
                    new EditText("EditText", 32, "Tren", 1.0, "4", "4", 3, "km"),
                    new EditText("EditText", 33, "Metro", 1.0, "4", "4", 3, "km"),
                    new EditText("EditText", 34, "Auto", 1.0, "4", "4", 3, "km"),
                    new EditText("EditText", 35, "Moto", 1.0, "4", "4", 3, "km"),
                    new EditText("EditText", 36, "Bicicleta", 1.0, "4", "4", 3, "km"),
                    new EditText("EditText", 37, "Caminando", 1.0, "4", "4", 3, "km"),
                ],
            )
        );

        this.screens.push(
            new Screen(
                "EditText", 4, "4", 2, 60, false, Category.CONSUMPTION, "¿Cuál fue la cantidad de kg de papel consumida por la empresa?",
                [
                    new EditText("EditText", 38, "Papel", 1.0, "5", "5", 4, "kg"),
                ],
            )
        );

        this.screens.push(
            new Screen(
                "EditText", 5, "5", 2, 80, true, Category.HABITS, "¿Cuál es la cantidad de días a la semana con modalidad home office?",
                [
                    new EditText("EditText", 39, "Días", 1.0, "-1", "-1", 5, "días"),
                ],
            )
        );
    }
}

module.exports = {
    IndividualFormRepository,
    EnterpriseFormRepository,
};
