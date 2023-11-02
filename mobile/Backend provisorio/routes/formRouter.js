
const express = require("express");
const router = express.Router();
const formService = require("../services/formService.js");

router.get("/", async function (req, res, next) {
  try {
    const formTypes = await formService.getFormTypes();
    res.json(formTypes);
  } catch (error) {
    res
      .status(500)
      .json({ mensaje: "Ocurrió un error al obtener la primera pregunta.", error: error.message });
  }
});

router.get("/individual", async function (req, res, next) {
  try {
    const firstQuestion = await formService.getFirstIndividualQuestion();
    res.json(firstQuestion);
  } catch (error) {
    res
      .status(400)
      .json({ mensaje: "Ocurrió un error al obtener la siguiente pregunta.", error: error.message });
  }
});

router.get("/enterprise", async function (req, res, next) {
  try {
    const firstQuestion = await formService.getFirstEnterpriseQuestion();
    res.json(firstQuestion);
  } catch (error) {
    res
      .status(400)
      .json({ mensaje: "Ocurrió un error al obtener la siguiente pregunta.", error: error.message });
  }
});

router.post("/individual/:id", async function (req, res, next) {
  try {
    const nextQuestion = await formService.getIndividualQuestionById(req.body[0].nextDefaultScreenNavigationId);
    res.json(nextQuestion);
  } catch (error) {
    res
      .status(400)
      .json({ mensaje: "Ocurrió un error al obtener la siguiente pregunta.", error: error.message });
  }
});

router.post("/enterprise/:id", async function (req, res, next) {
  try {
    const nextQuestion = await formService.getEnterpriseQuestionById(req.body[0].nextDefaultScreenNavigationId);
    res.json(nextQuestion);
  } catch (error) {
    res
      .status(400)
      .json({ mensaje: "Ocurrió un error al obtener la siguiente pregunta.", error: error.message });
  }
});

router.post("/individual", async function (req, res, next) {
  try {
    const results = await formService.getResults(req.body);
    res.status(201).json(results);
  } catch (error) {
    res
      .status(400)
      .json({ mensaje: "Ocurrió un error al obtener los resultados.", error: error.message });
  }
});

router.post("/enterprise", async function (req, res, next) {
  try {
    const results = await formService.getResults(req.body);
    res.status(201).json(results);
  } catch (error) {
    res
      .status(400)
      .json({ mensaje: "Ocurrió un error al obtener los resultados.", error: error.message });
  }
});

module.exports = router;
