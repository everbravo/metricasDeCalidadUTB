import React, { useCallback, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import AuthService from '../services/auth.service.ts';

const ProfilePage = ({ currentUser, onLogout }) => {
    const navigate = useNavigate();

    useEffect(() => {
        if (!currentUser) {
            navigate('/login');
        }
    }, [currentUser, navigate]);

    const handleDeleteAccount = useCallback(async () => {
        try {
            const response = await AuthService.deleteUser(currentUser.username);
            if (response.status === 200) {
                onLogout();
                navigate('/login');
            }
        } catch (error) {
            console.error('Error deleting account:', error);
        }
    }, [currentUser, navigate, onLogout]);
    console.log("currentUser en ProfilePage:", currentUser);

    return (
        <div className="container mt-5">
            {currentUser ? (
                <div className="row justify-content-center">
                    <div className="col-md-8">
                        <div className="card">
                            <div className="card-header bg-primary text-white">
                                <h4>Profile</h4>
                            </div>
                            <div className="card-body">
                                <h5 className="card-title">Welcome, {currentUser.username}</h5>
                                <p className="card-text"><strong>Email:</strong> {currentUser.email}</p>
                                <p className="card-text"><strong>Phone:</strong> {currentUser.phone}</p>
                                <div className="d-flex justify-content-between">
                                    <button className="btn btn-danger" onClick={handleDeleteAccount}>Delete My Account</button>
                                    <button className="btn btn-secondary" onClick={onLogout}>Logout</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            ) : (
                <div className="text-center">
                    <p>You need to be logged in to view your profile.</p>
                    <button className="btn btn-primary" onClick={() => navigate('/login')}>Go to Login</button>
                </div>
            )}
        </div>
    );
};

export default ProfilePage;
