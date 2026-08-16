# CarMarket

An Android-based vehicle marketplace application that allows users to browse vehicle information, search vehicle listings, manage accounts, and receive real-time notifications.

Developed using Java and Android Studio as part of a Mobile Application Development project.

---

## Features

### User Authentication
- User Registration
- User Login
- Forgot Password
- Google Sign-In (Credential Manager)
- User Logout

### Vehicle Marketplace
- Vehicle GridView Display
- Vehicle Details Page
- Vehicle Search
- Category Filtering
- Vehicle Information Browsing

### Notifications
- Firebase Cloud Messaging (FCM)
- Local Notifications
- Unified Notification Interface

### Data Persistence
- Room Database
- User Information Storage
- Repository Pattern Implementation

### User Interface
- Navigation Drawer
- View Binding
- Activity Transition Animation
- Responsive Layout Design

---

## Technology Stack

| Component | Technology |
|------------|------------|
| Language | Java |
| IDE | Android Studio |
| JDK | 17.0.19 |
| Android SDK | API 36 |
| Database | Room |
| Authentication | Credential Manager |
| Notifications | Firebase Cloud Messaging (FCM) |
| UI Binding | View Binding |
| Build System | Gradle |
| Architecture | Repository Pattern |

---

## Project Structure

```text
app/
└── src/main/
    ├── java/com.example.carmarket/
    │
    ├── common/
    │   └── Result
    │
    ├── database/
    │   ├── User
    │   ├── UserDao
    │   └── UserDatabase
    │
    ├── repository/
    │   ├── CarRepository
    │   └── UserRepository
    │
    ├── util/
    │
    ├── CarItem
    ├── GridAdapter
    ├── HomeActivity
    ├── DetailsActivity
    ├── LoginActivity
    ├── RegisterActivity
    ├── ForgotActivity
    ├── GoogleAuthManager
    ├── NotificationHelper
    ├── MyFirebaseMessagingService
    └── MyApplication
    │
    └── res/
        ├── anim/
        ├── drawable/
        ├── drawable-xxxhdpi/
        ├── layout/
        ├── mipmap/
        └── values/
```

---

## Architecture

```text
UI Layer
(Activity + View Binding)
        │
        ▼
Repository Layer
(UserRepository / CarRepository)
        │
        ▼
Data Layer
(Room Database)
```

### UI Layer
Responsible for:
- User interactions
- Navigation
- Form validation
- Data presentation

### Repository Layer
Responsible for:
- Data abstraction
- Business logic
- Database communication

### Data Layer
Responsible for:
- Local data persistence
- User account storage
- CRUD operations

---

## Authentication

### Local Authentication
Users can:
- Register a new account
- Login using username and password
- Reset forgotten passwords

User information is stored locally using Room Database.

### Google Authentication
Google Sign-In is implemented using the modern Credential Manager API.

Advantages:
- Secure authentication flow
- Simplified login experience
- Recommended by Google
- Future-proof implementation

---

## Notifications

CarMarket supports both local and remote notifications.

### Firebase Cloud Messaging (FCM)
Used for:
- Promotional messages
- Application updates
- Future notification expansion

### Local Notifications
Used for:
- User reminders
- In-app notification simulation

Both notification types are managed through a unified NotificationHelper component.

---

## Database

Room Database is used for local persistence.

### User Entity
Stores:
- Username
- Password
- Email

Benefits of Room:
- Type-safe SQL operations
- Compile-time query validation
- Improved maintainability
- Simplified database management

---

## Screenshots

### Login Screen
<img width="270" height="540" alt="Login_Page" src="https://github.com/user-attachments/assets/33e838c8-0147-46c1-b579-4dbe5f362ed2" />


### Register Screen
<img width="270" height="540" alt="Register_Page" src="https://github.com/user-attachments/assets/50a428d7-0264-4505-b9b5-1440c136624d" />

### Forgot Screen
<img width="270" height="540" alt="Forgot_Page" src="https://github.com/user-attachments/assets/3fe66ec8-9fb8-43a0-9266-42c896139dad" />

### Home Screen
<img width="270" height="540" alt="Home_Page" src="https://github.com/user-attachments/assets/d9c18673-ca19-4119-bd2e-913496e1fa18" />

### Vehicle Details Screen
<img width="270" height="540" alt="Detail_Page" src="https://github.com/user-attachments/assets/757e2896-f749-4d33-9609-abd8c4898f84" />


### Navigation Drawer
<img width="270" height="540" alt="Profile_Drawer_Page" src="https://github.com/user-attachments/assets/4ddbbcd0-6da7-4b3a-a6f3-00d867f38de7" />

---

## Requirements

### Development Environment
- Android Studio
- JDK 17.0.19
- Android SDK 36

### Dependencies
- Credential Manager
- Room Database
- Firebase Cloud Messaging
- View Binding
- AndroidX Libraries

Gradle will automatically download all required dependencies when the project is synced.

---

## Setup Instructions

### 1. Clone Repository

```bash
git clone <repository-url>
```

### 2. Open Project

Open the project using Android Studio.

### 3. Configure Firebase

Add:

```text
google-services.json
```

to:

```text
app/
```

### 4. Sync Gradle

Allow Android Studio to download required dependencies.

### 5. Run Application

Launch an emulator using AVD Manager or connect a physical Android device.

---

## Future Improvements

Potential future enhancements include:

- Cloud Database Integration
- Vehicle Posting Functionality
- Real-Time Vehicle Inventory Updates
- User Wishlist System
- Vehicle Booking Functionality
- Dark Mode Support
- Multi-Language Support
- MVVM Architecture Migration

---

## Author

CarMarket was developed as part of a Mobile Application Development project using Android Studio, Java, Room Database, Firebase Cloud Messaging, Credential Manager, and View Binding.
