export default function LoginPage({ onLogin }) {
  return (
    <div className="min-h-screen flex items-center justify-center bg-blue-50">
      <div className="w-[380px] bg-white rounded-xl shadow p-8">
        <div className="flex flex-col items-center mb-6">
          <div className="w-12 h-12 bg-blue-600 rounded-full mb-3" />
          <h1 className="text-xl font-semibold">아파트 관리 시스템</h1>
          <p className="text-sm text-gray-500">단지 관리자 로그인</p>
        </div>

        <div className="space-y-4">
          <input placeholder="아이디" className="w-full border rounded px-3 py-2" />
          <input
            placeholder="비밀번호"
            type="password"
            className="w-full border rounded px-3 py-2"
          />
          <button onClick={onLogin} className="w-full bg-blue-600 text-white rounded py-2">
            로그인
          </button>
        </div>
      </div>
    </div>
  );
}
