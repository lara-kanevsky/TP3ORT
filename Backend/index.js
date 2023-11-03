
const express = require("express");
const cors = require("cors");
const bodyParser = require("body-parser");
const app = express();
const port = 3000;
const formRouter = require("./routes/formRouter.js");
const path = require("path");

app.set("views", path.join(__dirname, "views"));
app.set("view engine", "ejs");

app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());
app.use(cors());
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(express.static(path.join(__dirname, "public")));

app.use("/forms", formRouter);

app.get("/", (req, res) => {
  res.send("Backend provisorio para testeo inicial de la APP mobile.");
});

app.listen(port, () => {
  console.log(`Servidor escuchando en el puerto ${port}
  http://localhost:` + port + `/`);
});

module.exports = app;
