import { useState } from "react";
import LoginPage from "./pages/LoginPage";
import MainLayout from "./layouts/MainLayout";

export default function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  if (!isLoggedIn) {
    return <LoginPage onLogin={() => setIsLoggedIn(true)} />;
  }

  return <MainLayout />;
}
