import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../services/api"; // asegúrate de que este archivo exista

export default function LoginPage({ setUser }) {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!email || !password) {
            setError("Por favor complete todos los campos");
            return;
        }

        try {
            const res = await api.post("/auth/login", {
                correo: email,
                contrasena: password,
            });

            const token = res.data.token;

            // Guardar token en localStorage
            localStorage.setItem("token", token);

            // Decodificar payload
            const payload = JSON.parse(atob(token.split(".")[1]));

            // Actualizar estado global de user
            setUser({ correo: payload.sub, nombre: payload.nombre, role: payload.role });

            // Redirigir al admin
            navigate("/admin", { replace: true });
        } catch (err) {
            console.error(err);
            setError("Credenciales incorrectas o cuenta inactiva");
        }
    };

    return (
        <div className="login-container">
            <h2>Inicio de Sesión</h2>
            <form onSubmit={handleSubmit}>
                <input
                    type="email"
                    placeholder="Correo"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                />
                <input
                    type="password"
                    placeholder="Contraseña"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
                <button type="submit">Ingresar</button>
            </form>
            {error && <p style={{ color: "red" }}>{error}</p>}
        </div>
    );
}