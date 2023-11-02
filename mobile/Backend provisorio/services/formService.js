
const formRepository = require("../data/formData");

const individualFormRepository = new formRepository.IndividualFormRepository();
const enterpriseFormRepository = new formRepository.EnterpriseFormRepository();

async function getFormTypes() {
  try {
    const formTypes = [
      {
        id: individualFormRepository.formId,
        label: individualFormRepository.label,
        type: individualFormRepository.formPersonType,
      },
      {
        id: enterpriseFormRepository.formId,
        label: enterpriseFormRepository.label,
        type: enterpriseFormRepository.formPersonType,
      },
    ]
    return {
      response: "success",
      formTypes: formTypes,
    };
  } catch (error) {
    console.log("Error al obtener la primera pregunta.", error);
    throw error;
  }
}

async function getFirstIndividualQuestion() {
  try {
    const firstQuestion = await individualFormRepository.screens[0];
    return {
      response: "success",
      question: firstQuestion
    };
  } catch (error) {
    console.log("Error al obtener la primera pregunta.", error);
    throw error;
  }
}

async function getFirstEnterpriseQuestion() {
  try {
    const firstQuestion = await enterpriseFormRepository.screens[0];
    return {
      response: "success",
      question: firstQuestion
    };
  } catch (error) {
    console.log("Error al obtener la primera pregunta.", error);
    throw error;
  }
}

async function getIndividualQuestionById(id) {
  try {
    const nextQuestion = await individualFormRepository.screens[id - 1];
    return {
      response: "success",
      question: nextQuestion
    };
  } catch (error) {
    console.log("Error al obtener la siguiente pregunta.", error);
    throw error;
  }
}

async function getEnterpriseQuestionById(id) {
  try {
    const nextQuestion = await enterpriseFormRepository.screens[id - 7];
    return {
      response: "success",
      question: nextQuestion
    };
  } catch (error) {
    console.log("Error al obtener la siguiente pregunta.", error);
    throw error;
  }
}

async function getResults(userAnswers) {
  try {
    // Simulated calculation
    var myTonsCO2 = userAnswers.length + (userAnswers.length / 10)
    var myCarobPerYearEquivalence = userAnswers.length + 1
    var myKmByCarEquivalence = userAnswers.length * 100 ** 2 - (userAnswers.length * 10) + (userAnswers.length + 2) ** 2
    var myFlightsEquivalence = userAnswers.length + 3
    //

    const results = {
      tonsCO2: myTonsCO2,
      equivalences: {
        carobPerYearEquivalence: `${myCarobPerYearEquivalence} algarrobos cortados por año`,
        kmByCarEquivalence: `${myKmByCarEquivalence} km recorridos en auto mediano por año`,
        flightsEquivalence: `${myFlightsEquivalence} vuelos ida y vuelta Buenos Aires-San Pablo`,
      }
    };
    return {
      response: "success",
      results: results,
    };
  } catch (error) {
    console.log("Error al obtener los resultados.", error);
    throw error;
  }
};

module.exports = {
  getFormTypes,
  getFirstIndividualQuestion,
  getIndividualQuestionById,
  getFirstEnterpriseQuestion,
  getEnterpriseQuestionById,
  getResults,
};
