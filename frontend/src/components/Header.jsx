// src/components/Header.jsx
import { Building2, Bell, Moon, Sun } from "lucide-react";

export default function Header() {
  return (
    <header className="bg-white dark:bg-gray-800 border-b fixed top-0 left-0 right-0 z-50">
      <div className="flex items-center justify-between px-6 py-4">
        <div className="flex items-center gap-3">
          <div className="bg-blue-600 p-2 rounded-lg">
            <Building2 className="size-6 text-white" />
          </div>
          <div>
            <h1 className="text-xl">행복 아파트</h1>
            <p className="text-sm text-gray-500">관리자 시스템</p>
          </div>
        </div>

        <div className="flex items-center gap-4">
          <Moon className="cursor-pointer" />
          <Bell className="cursor-pointer" />
          <span className="text-sm">로그아웃</span>
        </div>
      </div>
    </header>
  );
}
