const express = require("express");
const app = express();

app.use(express.json());

const orderRoutes = require("./routes/orderRoutes");

app.use("/api", orderRoutes);

app.listen(3000, () => {
    console.log("API rodando na porta 3000");
});
