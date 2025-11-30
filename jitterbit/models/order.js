let orders = [];
let nextId = 1;

class Order {
    static create(data) {
        const order = { id: nextId++, ...data };
        orders.push(order);
        return order;
    }

    static findAll() {
        return orders;
    }

    static findById(id) {
        return orders.find(o => o.id === id);
    }

    static update(id, newData) {
        const index = orders.findIndex(o => o.id === id);
        if (index === -1) return null;

        orders[index] = { ...orders[index], ...newData };
        return orders[index];
    }

    static delete(id) {
        const index = orders.findIndex(o => o.id === id);
        if (index === -1) return false;

        orders.splice(index, 1);
        return true;
    }
}

module.exports = Order;
