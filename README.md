# 🌾 FarmLink – Bridging Farmers and Buyers

**FarmLink** is a modern Android application built using **Jetpack Compose** and **Firebase**, aimed at streamlining agricultural trade by directly connecting farmers with buyers. The app ensures transparent pricing, effortless product listing, and a seamless user experience tailored for both farmers and buyers.

---

## 📱 App Preview

[![Watch the Demo](https://github.com/user-attachments/assets/53f807fc-ddc8-4a12-a572-044bf8fbe6db)](https://youtube.com/shorts/hBcEQ83oxf8?feature=share)

> 🌾 `FarmLink` 

---

## 🚀 Features

### 🔐 Authentication
- **Login / Signup** using phone number or email.
- Secure session management using Firebase Auth.

### 🧑‍🌾 Role Selection
- Choose your role as a **Farmer** or **Buyer** after authentication.
- Tailored UI and functionality for each role.

### 🌱 Farmer Dashboard
- **Home**: View all your listed produce (image, name, price, description).
- **Add Produce**: Upload produce image and enter details like crop name, price, description.
- **Orders** *(Coming Soon)*: Track and manage incoming orders from buyers.
- **Profile**: View and manage your personal information with logout functionality.

### 🛒 Buyer Dashboard
- **Home**: Browse all available produce listed by farmers.
  - Each item displays image, name, price, description.
  - Options to:
    - **Buy** (via Razorpay - test mode).
    - **Chat** with farmer *(Coming Soon)*.
- **Cart** *(Coming Soon)*: Manage added produce.
- **Track Orders** *(Coming Soon)*: Track real-time order status.
- **Profile**: Similar to farmer profile, includes logout.

---

## 🛠️ Tech Stack

| Category        | Tech Used                                 |
|----------------|--------------------------------------------|
| Language        | Kotlin                                     |
| UI Framework    | Jetpack Compose                            |
| Backend         | Firebase (Auth, Firestore, Storage)        |
| Image Upload    | Firebase Storage                           |
| Payment Gateway | Razorpay (Test Mode)                       |
| Architecture    | MVVM + Clean Architecture                 |
| Version Control | Git, GitHub                                |

---

## 📂 Project Structure (Simplified)

```
/farmlink-app
├── screens/
│   ├── Farmer/
│   ├── Buyer/
│   └── Auth/
├── viewmodels/
├── data/
│   ├── models/
│   └── firebase/
├── components/
└── utils/
```

---

## 🔒 Razorpay Integration
- Configured in **Test Mode**.
- On successful payment (mock), UI navigates to appropriate screen.
- Real-time transaction and cart features are **under development**.

---

## 💬 Chat Feature (Coming Soon)
- One-to-one chat between farmer and buyer using Firebase Realtime Database or Firestore.

---

## 🧪 Features in Progress
- 🔁 Order Tracking
- 🛒 Add to Cart & Cart Management
- 💬 Chat System
- 📦 Order Management for Farmers
- 🧾 Real Razorpay Production Integration

---

## 📦 How to Run

1. Clone this repo:
   ```bash
   git clone https://github.com/Gopal0Gupta/FarmLink.git
   ```
2. Open in Android Studio.
3. Connect Firebase project and update `google-services.json`.
4. Add Razorpay API key in `strings.xml`.
5. Run on emulator or real device (API 26+ recommended).

---

## 🛡 Badges

![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-2024-brightgreen)
![Firebase Auth](https://img.shields.io/badge/Firebase-Auth-yellow)
![MVVM](https://img.shields.io/badge/Architecture-MVVM-blue)
![Razorpay](https://img.shields.io/badge/Payment-Razorpay-lightgrey)
![Kotlin](https://img.shields.io/badge/Language-Kotlin-orange)

---

## 🤝 Contributing
Pull requests and suggestions are welcome! For major changes, please open an issue first to discuss what you would like to change.

---

## 👨‍💻 Developer

- **Gopal Gupta**  
  📧 gopalgupta072004@gmail.com  
  🔗 [LinkedIn](https://www.linkedin.com/in/gopal-gupta-056585253?utm_source=share&utm_campaign=share_via&utm_content=profile&utm_medium=android_app) | [GitHub](https://github.com/Gopal0Gupta)

---
