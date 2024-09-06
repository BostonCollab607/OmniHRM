import api from "./BaseService";

const baseURL = "/api/goals";

export const addPerformanceGoal = (goal) => {
    api.post(baseURL, goal);
}