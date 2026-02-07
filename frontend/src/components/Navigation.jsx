// src/components/Navigation.jsx
import { LayoutDashboard, FileText } from "lucide-react";

export default function Navigation({ currentPage, onNavigate }) {
  return (
    <nav className="border-t bg-white fixed top-20 left-0 right-0 z-40">
      <div className="flex px-6">
        <button
          onClick={() => onNavigate("dashboard")}
          className={`px-4 py-3 flex items-center gap-2 ${
            currentPage === "dashboard" ? "text-blue-600" : "text-gray-500"
          }`}
        >
          <LayoutDashboard size={16} /> 대시보드
        </button>

        <button
          onClick={() => onNavigate("notices")}
          className={`px-4 py-3 flex items-center gap-2 ${
            currentPage === "notices" ? "text-blue-600" : "text-gray-500"
          }`}
        >
          <FileText size={16} /> 마이페이지
        </button>
      </div>
    </nav>
  );
}
