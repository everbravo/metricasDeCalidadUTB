import React from 'react';
import { Link } from 'react-router-dom';  // Para navegar a otras páginas

const HomePage = () => {
    return (
        <div>
            {/* Navbar */}
            
            {/* Hero Section */}
            <div className="jumbotron jumbotron-fluid bg-secondary text-white text-center py-5">
                <div className="container">
                    <h1 className="display-4">Mesfix</h1>
                    <p className="lead">Ahora el paso más fácil es invertir</p>
                    <hr className="my-4" />
                    <p>¡Hay que darlo!</p>
                    <Link className="btn btn-light btn-lg" to="/login" role="button">Login</Link>
                    <Link className="btn btn-light btn-lg ms-3" to="/register" role="button">Register</Link>
                </div>
            </div>

            {/* Footer */}
            <footer className="bg-dark text-white text-center py-3">
                <p>&copy; 2025 My App | All Rights Reserved</p>
            </footer>
        </div>
    );
};

export default HomePage;
