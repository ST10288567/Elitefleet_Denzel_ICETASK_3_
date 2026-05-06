const express = require("express");
const cors = require("cors");

const app = express();

app.use(cors());
app.use(express.json());

/* =========================
   VEHICLES DATA
========================= */

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

/* =========================
   BOOKINGS DATA
========================= */

let bookings = [
  {
    id: 1,
    customerName: "Yash Govender",
    customerEmail: "yash@email.com",
    vehicleName: "BMW 3 Series",
    date: "2026-05-10",
    time: "10:30",
    consultant: "A. Naidoo",
    status: "Confirmed"
  },
  {
    id: 2,
    customerName: "Riya Pillay",
    customerEmail: "riya@email.com",
    vehicleName: "Audi A4",
    date: "2026-05-12",
    time: "14:00",
    consultant: "K. Moodley",
    status: "Pending"
  }
];

/* =========================
   CLAIMS DATA
========================= */

let claims = [
  {
    id: 1,
    title: "Vehicle deposit refund",
    customerName: "R. Pillay",
    amount: 2500,
    status: "Pending",
    date: "2026-05-05"
  },
  {
    id: 2,
    title: "Warranty claim",
    customerName: "K. Naidoo",
    amount: 8500,
    status: "Approved",
    date: "2026-05-06"
  }
];

/* =========================
   LOGIN API
========================= */

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

/* =========================
   VEHICLES CRUD
========================= */

app.get("/vehicles", (req, res) => {
  res.json(vehicles);
});

app.get("/vehicles/:id", (req, res) => {
  const vehicle = vehicles.find(
    v => v.id === Number(req.params.id)
  );

  if (!vehicle) {
    return res.status(404).json({
      message: "Vehicle not found"
    });
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

  const index = vehicles.findIndex(
    v => v.id === vehicleId
  );

  if (index === -1) {
    return res.status(404).json({
      message: "Vehicle not found"
    });
  }

  vehicles[index] = {
    id: vehicleId,
    ...req.body
  };

  res.json(vehicles[index]);
});

app.delete("/vehicles/:id", (req, res) => {
  const vehicleId = Number(req.params.id);

  vehicles = vehicles.filter(
    v => v.id !== vehicleId
  );

  res.json({
    message: "Vehicle deleted successfully"
  });
});

/* =========================
   BOOKINGS CRUD
========================= */

app.get("/bookings", (req, res) => {
  res.json(bookings);
});

app.get("/bookings/:id", (req, res) => {
  const booking = bookings.find(
    b => b.id === Number(req.params.id)
  );

  if (!booking) {
    return res.status(404).json({
      message: "Booking not found"
    });
  }

  res.json(booking);
});

app.post("/bookings", (req, res) => {
  const newBooking = {
    id: bookings.length + 1,
    ...req.body
  };

  bookings.push(newBooking);

  res.status(201).json(newBooking);
});

app.put("/bookings/:id", (req, res) => {
  const bookingId = Number(req.params.id);

  const index = bookings.findIndex(
    b => b.id === bookingId
  );

  if (index === -1) {
    return res.status(404).json({
      message: "Booking not found"
    });
  }

  bookings[index] = {
    id: bookingId,
    ...req.body
  };

  res.json(bookings[index]);
});

app.delete("/bookings/:id", (req, res) => {
  const bookingId = Number(req.params.id);

  bookings = bookings.filter(
    b => b.id !== bookingId
  );

  res.json({
    message: "Booking deleted successfully"
  });
});

/* =========================
   CLAIMS CRUD
========================= */

app.get("/claims", (req, res) => {
  res.json(claims);
});

app.put("/claims/:id", (req, res) => {
  const claimId = Number(req.params.id);

  const index = claims.findIndex(
    c => c.id === claimId
  );

  if (index === -1) {
    return res.status(404).json({
      message: "Claim not found"
    });
  }

  claims[index] = {
    id: claimId,
    ...req.body
  };

  res.json(claims[index]);
});

/* =========================
   START SERVER
========================= */

app.listen(3000, () => {
  console.log(
    "Elite Fleet API running on http://localhost:3000"
  );
});