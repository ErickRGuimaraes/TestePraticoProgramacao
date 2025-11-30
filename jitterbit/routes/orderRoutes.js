const express = require("express");
const router = express.Router();
const Order = require("../models/order");

router.post("/order", async (req, res) => {
    try {
        const data = req.body;

        const mapped = {
            orderId: data.numeroPedido.replace("-01", ""),
            value: data.valorTotal,
            creationDate: new Date(data.dataCriacao),
            items: data.items.map(i => ({
                productId: Number(i.idItem),
                quantity: i.quantidadeItem,
                price: i.valorItem
            }))
        };

        const order = Order.create(mapped);
        res.status(201).json(order);
    } catch (err) {
        res.status(500).json({
            error: "Erro ao criar o pedido",
            details: err.message
        });
    }
});

router.get("/order", (req, res) => {
    const orders = Order.findAll();
    res.json(orders);
});

router.get("/order/:id", (req, res) => {
    const order = Order.findById(Number(req.params.id));

    if (!order) {
        return res.status(404).json({ error: "Pedido não encontrado" });
    }

    res.json(order);
});

router.put("/order/:id", (req, res) => {
    const updated = Order.update(Number(req.params.id), req.body);

    if (!updated) {
        return res.status(404).json({ error: "Pedido não encontrado" });
    }

    res.json(updated);
});

router.delete("/order/:id", (req, res) => {
    const success = Order.delete(Number(req.params.id));

    if (!success) {
        return res.status(404).json({ error: "Pedido não encontrado" });
    }

    res.json({ message: "Pedido removido com sucesso" });
});

module.exports = router;
