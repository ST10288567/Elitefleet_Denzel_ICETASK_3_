const express = require("express");
const cors = require("cors");

const app = express();
app.use(cors());
app.use(express.json());

const vehicles = [
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
    brand: "Mercedes",
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
  }
];

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

app.get("/vehicles", (req, res) => {
  res.json(vehicles);
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