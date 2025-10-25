import { useState } from "react";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import AdminPage from "./pages/AdminPage";

function App() {
    const [user, setUser] = useState(() => {
        const token = localStorage.getItem("token");
        if (token) {
            try {
                const payload = JSON.parse(atob(token.split(".")[1]));
                return { correo: payload.sub, nombre: payload.nombre, role: payload.role };
            } catch (e) {
                console.error("Token inv�lido", e);
            }
        }
        return null;
    });

    return (
        <BrowserRouter>
            <Routes>
                {/* P�gina de login */}
                <Route
                    path="/"
                    element={
                        user ? <Navigate to="/admin" /> : <LoginPage setUser={setUser} />
                    }
                />

                {/* P�gina de administrador */}
                <Route
                    path="/admin"
                    element={
                        user && user.role === "ADMIN" ? (
                            <AdminPage user={user} setUser={setUser} />
                        ) : (
                            <Navigate to="/" />
                        )
                    }
                />
            </Routes>
        </BrowserRouter>
    );
}

export default App;