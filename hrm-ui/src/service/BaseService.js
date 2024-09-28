import axios from "axios";
import { getAccessToken, logout, refreshApiCall, storeToken } from "./auth/AuthService";

const api = axios.create({
    baseURL: "http://143.110.248.76:8081" //
});
// what the actual fuck.

api.interceptors.request.use(function (config) {
    config.headers['Authorization'] = 'Bearer ' + getAccessToken();
    return config;
}, function (error) {
    return Promise.reject(error);
});

api.interceptors.response.use(
    response => response,
    async error => {
        const originalRequest = error.config;
        console.log(originalRequest);
        
        if (error.response.status === 401 && !originalRequest._retry) {
            originalRequest._retry = true;
            try {
                const { data } = await refreshApiCall();
                // Store the new tokens
                storeToken(data.accessToken, data.refreshToken);

                // Update the authorization header with the new access token
                api.defaults.headers['Authorization'] = `Bearer ${data.accessToken}`;

                // Retry the original request with the new access token
                originalRequest.headers['Authorization'] = `Bearer ${data.accessToken}`;
                return api(originalRequest);
            } catch (refreshError) {
                console.error('Refresh token is invalid or expired:', refreshError);
                logout();
                return Promise.reject(refreshError);
            }
        }
        return Promise.reject(error);
    }
);

export default api;