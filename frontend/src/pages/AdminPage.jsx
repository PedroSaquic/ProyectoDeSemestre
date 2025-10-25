import { useNavigate } from "react-router-dom";

export const PrivateRoute = ({ user, children, role }) => {
    if (!user) return <Navigate to="/" />;
    if (role && user.role !== role) return <Navigate to="/" />;
    return children;
};

const AdminPage = ({ user, setUser }) => {
    const navigate = useNavigate();

    const handleLogout = () => {
        localStorage.removeItem("token");
        setUser(null); 
        navigate("/", {replace:true}); 
    };

    return (
        <div className="admin-container">
            <h1>Admin Dashboard</h1>
            <p>Bienvenido, {user?.nombre || user?.correo}</p>
            <button onClick={handleLogout}>Cerrar sesión</button>
        </div>
    );
};

export default AdminPage;