import { useEffect, useState } from "react";
import api from "../services/api";

export default function ClientesPage() {
    const [clientes, setClientes] = useState([]);
    const [nuevoCliente, setNuevoCliente] = useState({ nombre: "", correo: "" });

    const cargarClientes = async () => {
        const res = await api.get("/clientes");
        setClientes(res.data);
    };

    const crearCliente = async (e) => {
        e.preventDefault();
        if (!nuevoCliente.nombre || !nuevoCliente.correo) return alert("Campos requeridos");
        await api.post("/clientes", nuevoCliente);
        setNuevoCliente({ nombre: "", correo: "" });
        cargarClientes();
    };

    useEffect(() => {
        cargarClientes();
    }, []);

    return (
        <div>
            <h2>Gestión de Clientes</h2>
            <form onSubmit={crearCliente}>
                <input
                    type="text"
                    placeholder="Nombre"
                    value={nuevoCliente.nombre}
                    onChange={(e) => setNuevoCliente({ ...nuevoCliente, nombre: e.target.value })}
                />
                <input
                    type="email"
                    placeholder="Correo"
                    value={nuevoCliente.correo}
                    onChange={(e) => setNuevoCliente({ ...nuevoCliente, correo: e.target.value })}
                />
                <button type="submit">Agregar</button>
            </form>

            <ul>
                {clientes.map((c) => (
                    <li key={c.id}>{c.nombre} - {c.correo}</li>
                ))}
            </ul>
        </div>
    );
}