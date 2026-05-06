const express = require("express");
const cors = require("cors");

const app = express();
app.use(cors());
app.use(express.json());

let vehicles = [
  {
    id: 1,
    name: "BMW 3 Series",
    brand: "BMW",
    price: 689999,
    status: "Available",
    mileage: 25000,
    branch: "Durban Central",
    imageUrl: "https://images.unsplash.com/photo-1555215695-3004980ad54e"
  },
  {
    id: 2,
    name: "Mercedes-Benz C-Class",
    brand: "Mercedes-Benz",
    price: 749999,
    status: "Reserved",
    mileage: 18000,
    branch: "Umhlanga",
    imageUrl: "https://images.unsplash.com/photo-1618843479313-40f8afb4b4d8"
  },
  {
    id: 3,
    name: "Audi A4",
    brand: "Audi",
    price: 619999,
    status: "Available",
    mileage: 31000,
    branch: "Phoenix",
    imageUrl: "https://images.unsplash.com/photo-1606664515524-ed2f786a0bd6"
  },
  {
    id: 4,
    name: "Toyota Hilux",
    brand: "Toyota",
    price: 589999,
    status: "Sold",
    mileage: 42000,
    branch: "Pinetown",
    imageUrl: "https://images.unsplash.com/photo-1605893477799-b99e3b8b93fe"
  }
];

app.get("/vehicles", (req, res) => {
  res.json(vehicles);
});

app.get("/vehicles/:id", (req, res) => {
  const vehicle = vehicles.find(v => v.id === Number(req.params.id));

  if (!vehicle) {
    return res.status(404).json({ message: "Vehicle not found" });
  }

  res.json(vehicle);
});

app.post("/vehicles", (req, res) => {
  const newVehicle = {
    id: vehicles.length + 1,
    ...req.body
  };

  vehicles.push(newVehicle);
  res.status(201).json(newVehicle);
});

app.put("/vehicles/:id", (req, res) => {
  const vehicleId = Number(req.params.id);
  const index = vehicles.findIndex(v => v.id === vehicleId);

  if (index === -1) {
    return res.status(404).json({ message: "Vehicle not found" });
  }

  vehicles[index] = {
    id: vehicleId,
    ...req.body
  };

  res.json(vehicles[index]);
});

app.delete("/vehicles/:id", (req, res) => {
  const vehicleId = Number(req.params.id);
  vehicles = vehicles.filter(v => v.id !== vehicleId);

  res.json({ message: "Vehicle deleted successfully" });
});

const bookings = [
  {
    id: 1,
    customerName: "Yash Govender",
    vehicleName: "BMW 3 Series",
    date: "2026-05-10",
    time: "10:30",
    consultant: "A. Naidoo",
    status: "Confirmed"
  }
];

const claims = [
  {
    id: 1,
    title: "Vehicle deposit refund",
    customerName: "R. Pillay",
    amount: 2500,
    status: "Pending",
    date: "2026-05-05"
  }
];

app.post("/login", (req, res) => {
  const { email, password } = req.body;

  if (email && password) {
    return res.json({
      success: true,
      userId: 1,
      name: "Admin User",
      role: "Administrator",
      token: "mock-token-elite-fleet"
    });
  }

  res.status(400).json({
    success: false,
    message: "Email and password required"
  });
});



app.get("/bookings", (req, res) => {
  res.json(bookings);
});

app.get("/claims", (req, res) => {
  res.json(claims);
});

app.listen(3000, () => {
  console.log("Elite Fleet API running on http://localhost:3000");
});