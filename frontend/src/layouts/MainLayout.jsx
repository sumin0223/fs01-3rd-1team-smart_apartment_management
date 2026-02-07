import Header from "../components/Header";
import Navigation from "../components/Navigation";
import Dashboard from "../pages/Dashboard";

export default function MainLayout() {
  return (
    <div className="min-h-screen bg-gray-50">
      <Header />
      <Navigation />

      <main className="pt-28 p-6">
        <Dashboard />
      </main>
    </div>
  );
}
